package com.importExport.Excel.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.importExport.Excel.helper.ExcelHelper;
import com.importExport.Excel.model.Tutorial;
import com.importExport.Excel.repository.TutorialRepository;

@Service
public class ExcelService {
  @Autowired
  TutorialRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public List<Tutorial> getAllTutorials() {
    return repository.findAll();
  }
  
//  public List<Tutorial> ReadDataFromExcel(MultipartFile excelPath) throws EncryptedDocumentException,
//  InvalidFormatException, IOException
//  {
//	  XSSFWorkbook workbook = new XSSFWorkbook(excelPath.getInputStream() + "Sheets: ");
//	  
//	  System.out.println("WorkBook has" + workbook.getNumberOfSheets() + "Sheets :");
//	  System.out.println("Retrieving Sheets using for-each loop");
//	  
//	  for(Sheet sheet: workbook) {
//		  System.out.println("=> " + sheet.getSheetName());
//		  
//		  DataFormatter dataFormatter = new DataFormatter();
//		  for(Row row: sheet) {
//			  
//			  double id = row.getCell(0).getNumericCellValue();
//			  String title = row.getCell(1).getStringCellValue();
//			  String description = row.getCell(2).getStringCellValue();
//			  boolean published = row.getCell(3).getBooleanCellValue();
//			  
//			  Tutorial tutor = new Tutorial();
//			  tutor.setId((long)id);
//			  tutor.setTitle(title);
//			  tutor.setDescription(description);
//			  tutor.setPublished(published);
//			  
//			  repository.save(tutor);
//			  
//			  System.out.println(row.getCell(0).getNumericCellValue());
//			  System.out.println(row.getCell(1).getStringCellValue());
//			  System.out.println(row.getCell(2).getStringCellValue());
//			  System.out.println(row.getCell(3).getBooleanCellValue());
//		  }
//	  }
//	  return null;
//  }
}
