//package com.java.PuskesmasOnline.PuskesmasOnline.excel_service;
//
//import com.java.PuskesmasOnline.PuskesmasOnline.helper.ExcelHelper;
//import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
//import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
//import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
//import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
//import com.java.PuskesmasOnline.PuskesmasOnline.service.KlinikPagiService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.persistence.EntityNotFoundException;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class ExcelHistoryAdminService {
//    @Autowired
//    KlinikPagi klinikPagi ;
//
//    @Autowired
//    KlinikPagiRepository klinikPagiRepository;
//    @Autowired
//    UserRepository userRepository;
//    private User user;
//
//
//    public void savee(MultipartFile file, long id) {
//        try {
//            User sekolah = userRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
//            KlinikPagi klinikPagi = klinikPagiRepository.findById(id).orElse(null);
//            List<KlinikPagi> history = ExcelHelper.excelToGurus(file.getInputStream(), sekolah);
//            klinikPagiRepository.saveAll(history);
//        } catch (IOException e) {
//            throw new RuntimeException("fail to store excel data: " + e.getMessage());
//        }
//    }
//
//    public ByteArrayInputStream load(Long id) {
//        User userr = userRepository.findById(id).orElse(null);
//        List<KlinikPagi> klinikhistory = repository.fin();
//        ByteArrayInputStream in = ExcelHelper.gurusToExcel(klinikhistory);
//        return in;
//    }
//}
