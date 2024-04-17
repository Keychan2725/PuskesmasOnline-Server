package com.java.PuskesmasOnline.PuskesmasOnline.helper;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.java.PuskesmasOnline.PuskesmasOnline.model.KlinikPagi;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.Document;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id", "namaKlinik",  "alamat", "noAntrian", "count", "statusKlinik",   "tanggalWaktu"};
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static ByteArrayInputStream klinikPagiToExcel(List<KlinikPagi> klinikPagiList) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (KlinikPagi klinikPagi : klinikPagiList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(klinikPagi.getId());
                row.createCell(1).setCellValue(klinikPagi.getNamaKlinik());
                row.createCell(2).setCellValue(klinikPagi.getAlamat());
                row.createCell(3).setCellValue(klinikPagi.getNoAntrian());
                row.createCell(4).setCellValue(klinikPagi.getCount());
                row.createCell(5).setCellValue(klinikPagi.getStatusKlinik());
                row.createCell(6).setCellValue(klinikPagi.getTanggalWaktu().toString());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to import data to Excel file: " + e.getMessage());
        }
    }

//    public static ByteArrayInputStream generatePDF(List<KlinikPagi> klinikPagiList) {
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            PdfWriter.getInstance(document, out);
//            document.open();
//
//            for (KlinikPagi klinikPagi : klinikPagiList) {
//                document.add(new Paragraph()
//                        .add("ID: " + klinikPagi.getId())
//                        .add("\nNama Klinik: " + klinikPagi.getNamaKlinik())
//                        .add("\nAlamat: " + klinikPagi.getAlamat())
//                        .add("\nNo Antrian: " + klinikPagi.getNoAntrian())
//                        .add("\nCount: " + klinikPagi.getCount())
//                        .add("\nStatus Klinik: " + klinikPagi.getStatusKlinik())
//                        .add("\nTanggal Waktu: " + klinikPagi.getTanggalWaktu().toString())
//                );
//            }
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } finally {
//            document.close();
//        }
//
//        return new ByteArrayInputStream(out.toByteArray());
//    }

    public static List<KlinikPagi> excelToKlinikPagi(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<KlinikPagi> klinikPagiList = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                KlinikPagi klinikPagi = new KlinikPagi();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            klinikPagi.setNamaKlinik(currentCell.getStringCellValue());
                            break;
                        case 1:
                            klinikPagi.setAlamat(currentCell.getStringCellValue());
                            break;
                        case 2:
                            klinikPagi.setNoAntrian(currentCell.getStringCellValue());
                            break;
                        case 3:
                            klinikPagi.setCount(currentCell.getStringCellValue());
                            break;
                        case 4:
                            klinikPagi.setStatusKlinik( currentCell.getStringCellValue());
                            break;
                        case 5:
                            klinikPagi.setTanggalWaktu(currentCell.getDateCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                klinikPagiList.add(klinikPagi);
            }
            workbook.close();
            return klinikPagiList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

}