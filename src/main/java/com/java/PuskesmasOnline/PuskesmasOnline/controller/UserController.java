package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.java.PuskesmasOnline.PuskesmasOnline.dto.ForGotPass;
import com.java.PuskesmasOnline.PuskesmasOnline.exception.CommonResponse;
import com.java.PuskesmasOnline.PuskesmasOnline.exception.NotFoundException;
import com.java.PuskesmasOnline.PuskesmasOnline.exception.ResponseHelper;
import com.java.PuskesmasOnline.PuskesmasOnline.model.LoginRequest;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.service.UserImpl;
import com.java.PuskesmasOnline.PuskesmasOnline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/lowongan-a0c4a.appspot.com/o/%s?alt=media";

    @Autowired
    private UserService userService;

    @Autowired
    UserImpl userImpl;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public CommonResponse<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseHelper.ok( userService.login(loginRequest));
    }

    @PostMapping("/user/addUser")
    public CommonResponse<User> addUser(@RequestBody User user){

        return ResponseHelper.ok( userService.addUser(user));
    }

    @PostMapping("/user/checkEmail")
    public ResponseEntity<Boolean> checkEmailExists(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        boolean emailExists = userRepository.existsByEmail(email);

        return ResponseEntity.ok(emailExists);
    }
    @PostMapping("/user/addAdmin")
    public CommonResponse<User> addAdmin(@RequestBody User user){
        return ResponseHelper.ok( userService.addAdmin(user));
    }


    @PutMapping("/user/upload-image/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User tidak ditemukan"));

        // Upload file ke Firebase Storage dan dapatkan URL download
        String downloadURL = uploadFileToFirebaseStorage(image, image.getOriginalFilename());

        // Update informasi gambar user
        user.setImgUser(downloadURL);
        userRepository.save(user);

        return ResponseEntity.ok(downloadURL);
    }

    public String uploadFile(File fileId, String fileName) throws IOException {
        // Create a temporary file with the specified filename
        File file = File.createTempFile("user-image-", fileName);

        // Write the file contents to the temporary file
        // **Replace "your_file_contents" with the actual file contents**
        byte[] fileContents = "your_file_contents".getBytes();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileContents);
        }

        // Upload the temporary file to Firebase Storage
        String downloadURL = uploadFileToFirebaseStorage((MultipartFile) file, fileName);

        // Delete the temporary file
        file.delete();

        return downloadURL;
    }

    private String uploadFileToFirebaseStorage(MultipartFile multipartFile, String fileName) throws IOException {
        // Create a BlobId object with the image data and metadata
        BlobId blobId = BlobId.of("lowongan-a0c4a.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("media")
                .build();

        // Get the credentials for accessing Firebase Storage
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json"));

        // Get the Storage service
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        // Upload the image data directly from MultipartFile
        storage.create(blobInfo, multipartFile.getBytes());

        // Generate the download URL for the image
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
    public String getExtentions(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public String imageConverter(MultipartFile multipartFile) {
        try {
            String fileName = getExtentions(multipartFile.getOriginalFilename());
            File file = convertToFile(multipartFile, fileName);
            var RESPONSE_URL = uploadFile(file, fileName);
            file.delete();
            return RESPONSE_URL;
        } catch (Exception e) {
            e.getStackTrace();
            throw new RuntimeException("error  ");
        }
    }
    public String uploadImage(User user, @RequestParam("image") MultipartFile image) throws IOException {
        String fileName = getExtentions(image.getOriginalFilename());

        // Validasi format file
        if (!Arrays.asList("jpg", "jpeg", "png", "gif", "webp").contains(fileName)) {
            throw new RuntimeException("Format file gambar tidak didukung");
        }

        // Validasi ukuran file
        if (image.getSize() > 50_000_000) {
            throw new RuntimeException("Ukuran file gambar melebihi batas 50 MB");
        }

        // Upload file ke Firebase Storage dan dapatkan URL download
        String downloadURL = this.uploadFileToFirebaseStorage(image, fileName);

        // Update informasi gambar user
        user.setImgUser(downloadURL);
        userRepository.save(user);

        return downloadURL;
    }
    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return file;
    }


    @DeleteMapping("/user/delete-image/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User tidak ditemukan"));
            String imageUrl = user.getImgUser();

            if (imageUrl != null) {
                deleteFileFromFirebaseStorage(imageUrl);
            }

            user.setImgUser(null);
            userRepository.save(user);

            return ResponseEntity.ok("Foto berhasil dihapus");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gagal menghapus foto: " + e.getMessage());
        }
    }

    private void deleteFileFromFirebaseStorage(String imageUrl) {
        try {
             Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("./src/main/resources/ServiceAccount.json")))
                    .build()
                    .getService();

            BlobId blobId = BlobId.of("lowongan-a0c4a.appspot.com", extractFileNameFromUrl(imageUrl));
            boolean deleted = storage.delete(blobId);

            if (!deleted) {
                throw new IOException("Gagal menghapus file dari Firebase Storage");
            }
        } catch (IOException e) {
            throw new RuntimeException("Gagal menghapus file dari Firebase Storage: " + e.getMessage());
        }
    }

    private String extractFileNameFromUrl(String imageUrl) {
        return FilenameUtils.getName(imageUrl);
    }

    @PutMapping("/user/update-otp/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                           @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id : " + id));

        user.setNoTel(updatedUser.getNoTel());
        user.setCodeVer(updatedUser.getCodeVer());

        final User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/user/forgot_password")
    public CommonResponse<ForGotPass> sendEmail(@RequestBody ForGotPass forGotPass) throws MessagingException, jakarta.mail.MessagingException {
        return ResponseHelper.ok(userService.sendEmail(forGotPass));

    }
    @GetMapping("/user/{id}")
    public CommonResponse <User> get(@PathVariable("id") Long id){
        return ResponseHelper.ok( userService.get(id));
    }

    @GetMapping("/user/all")
    public CommonResponse<List<User>> getAll(){
        return ResponseHelper.ok( userService.getAll());
    }
    @PutMapping("/user/{id}")
    public CommonResponse<User> put(@PathVariable("id") Long id , @RequestBody User user){
        return ResponseHelper.ok( userService.edit(id, user));
    }
    @DeleteMapping("/user/{id}")
    public CommonResponse<?> delete(@PathVariable("id")  Long id) {
        return ResponseHelper.ok( userService.delete(id));
    }

}