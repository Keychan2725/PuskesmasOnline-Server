package com.java.PuskesmasOnline.PuskesmasOnline.controller;

import com.java.PuskesmasOnline.PuskesmasOnline.excel_service.ExcelHistoryAdminService;
import com.java.PuskesmasOnline.PuskesmasOnline.helper.ExcelHelper;
import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ExcelHistoryAdminController {
    @Autowired
    ExcelHistoryAdminService excelService;

    @Autowired
    private  ExcelHelper excelHelper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveExcelToDatabase(file);
                return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to upload file: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file!");
        }
    }

    @GetMapping("/download/excel")
    public ResponseEntity<ByteArrayInputStream> downloadExcelFile() {
        try {
            ByteArrayInputStream in = excelService.getExcel();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=klinik_pagi.xlsx")
                    .body(in);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

//    @GetMapping("/download/pdf")
//    public ResponseEntity<ByteArrayInputStream> downloadPDFFile(List<KlinikPagi> klinikPagiList) {
//        try {
//            ByteArrayInputStream in = excelHelper.generatePDF(klinikPagiList);
//            return ResponseEntity.ok()
//                    .header("Content-Disposition", "attachment; filename=klinik_pagi.pdf")
//                    .body(in);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
//        }
//    }
}
