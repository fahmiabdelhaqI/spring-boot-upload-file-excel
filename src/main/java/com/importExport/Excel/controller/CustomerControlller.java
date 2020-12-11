package com.importExport.Excel.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.importExport.Excel.helper.ExcelHelper;
import com.importExport.Excel.message.ResponseMessage;
import com.importExport.Excel.service.FileServices;

@Controller
@RequestMapping("/api/excel")
public class CustomerControlller {

	@Autowired
	FileServices fileService;
	
	@PostMapping("/uploadfile")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("File") MultipartFile File) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(File)) {
	      try {
	        fileService.store(File);

	        message = "Uploaded the file successfully: " + File.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	    	  StringWriter sw = new StringWriter();
	    	  PrintWriter pw = new PrintWriter(sw);
	    	  e.printStackTrace(pw);
	    	  String sStackTrace = sw.toString();
	        message = "Could not upload the file: " + File.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }

	    message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }
}
