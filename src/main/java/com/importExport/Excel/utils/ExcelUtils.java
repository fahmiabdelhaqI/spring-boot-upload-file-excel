package com.importExport.Excel.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.importExport.Excel.model.Customer;

public class ExcelUtils {

	public static ByteArrayInputStream customerToExcel(List<Customer> customer) throws IOException {
		String[] COLUMNs = {"id", "nama", "alamat", "umur"};
		try (
			Workbook workbook = new XSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			CreationHelper createHelper = workbook.getCreationHelper();
			
			Sheet sheet = workbook.createSheet("Customer");
			//Row for Header
			Row headerRow = sheet.createRow(0);
			
			//Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
		        
			}
			
			int rowIdx = 1;
			
			for(Customer customer1 : customer) {
				Row row = sheet.createRow(rowIdx++);
				
				row.createCell(0).setCellValue(customer1.getId());
				row.createCell(0).setCellValue(customer1.getNama());
				row.createCell(0).setCellValue(customer1.getAlamat());
				row.createCell(0).setCellValue(customer1.getUmur());
			}
			
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
			
		}
	}
	 public static List<Customer> parseExcelFile(InputStream is) {
		    try {
		        Workbook workbook = new XSSFWorkbook(is);
		     
		        Sheet sheet = workbook.getSheet("Customers");
		        Iterator<Row> rows = sheet.iterator();
		        
		        List<Customer> lstCustomers = new ArrayList<Customer>();
		        
		        int rowNumber = 0;
		        while (rows.hasNext()) {
		          Row currentRow = rows.next();
		          
		          // skip header
		          if(rowNumber == 0) {
		            rowNumber++;
		            continue;
		          }
		          
		          Iterator<Cell> cellsInRow = currentRow.iterator();
		 
		          Customer cust = new Customer();
		          
		          int cellIndex = 0;
		          while (cellsInRow.hasNext()) {
		            Cell currentCell = cellsInRow.next();
		            
		            if(cellIndex==0) { // ID
		              cust.setId((long) currentCell.getNumericCellValue());
		            } else if(cellIndex==1) { // Name
		              cust.setNama(currentCell.getStringCellValue());
		            } else if(cellIndex==2) { // Address
		              cust.setAlamat(currentCell.getStringCellValue());
		            } else if(cellIndex==3) { // Age
		            	cust.setUmur(currentCell.getStringCellValue());
		            }
		            
		            cellIndex++;
		          }
		          
		          lstCustomers.add(cust);
		        }
		        
		        // Close WorkBook
		        workbook.close();
		        
		        return lstCustomers;
		        } catch (IOException e) {
		          throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		        }
		  }
}
