package com.java.PuskesmasOnline.PuskesmasOnline.excel_service;

import com.java.PuskesmasOnline.PuskesmasOnline.helper.ExcelHelper;
import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import com.java.PuskesmasOnline.PuskesmasOnline.model.User;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.KlinikPagiRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.repository.UserRepository;
import com.java.PuskesmasOnline.PuskesmasOnline.service.KlinikPagiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class ExcelHistoryAdminService {
    @Autowired
    ExcelHelper excelHelper;

    @Autowired
    KlinikPagiRepository klinikPagiRepository;

    public void saveExcelToDatabase(MultipartFile file) {
        List<KlinikPagi> klinikPagiList = excelHelper.excelToKlinikPagi((InputStream) file);
        klinikPagiRepository.saveAll(klinikPagiList);
    }

    public ByteArrayInputStream getExcel() {
        List<KlinikPagi> klinikPagiList = klinikPagiRepository.findAll();
        return excelHelper.klinikPagiToExcel(klinikPagiList);
    }
}
