package com.humanize.server.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.exception.ExcelToJsonConversionException;

public class ExcelToJson {
	
	private String filename;
	
	private static Logger logger = Logger.getLogger(ExcelToJson.class);

	public ExcelToJson(String filename) {
		this.filename = filename;
	}

	public List<Content> toJson() throws Exception {
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(filename);

			Workbook workbook = WorkbookFactory.create(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet != null) {
				int rowCount = sheet.getLastRowNum();
				
				return toJson(sheet, rowCount);
			} 
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			throw new ExcelToJsonConversionException(ExceptionConfig.EXCEL_FILE_NOT_FOUND_ERROR_CODE, ExceptionConfig.EXCEL_FILE_NOT_FOUND_EXCEPTION);
		} catch (InvalidFormatException exception) {
			exception.printStackTrace();
			throw new ExcelToJsonConversionException(ExceptionConfig.EXCEL_TO_JSON_CONVERSION_ERROR_CODE, ExceptionConfig.EXCEL_TO_JSON_CONVERSION_EXCEPTION);
		} catch (IOException exception) {
			exception.printStackTrace();
			throw new ExcelToJsonConversionException(ExceptionConfig.FILE_READING_ERROR_CODE, ExceptionConfig.FILE_READING_EXCEPTION);
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException exception) {

			} 
		}
		
		throw new ExcelToJsonConversionException(ExceptionConfig.EXCEL_TO_JSON_CONVERSION_ERROR_CODE, ExceptionConfig.EXCEL_TO_JSON_CONVERSION_EXCEPTION);
	}
	
	private List<Content> toJson(Sheet sheet, int rowCount) throws Exception {
		List<Content> contents = new ArrayList<Content>();
		
		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			
			if (row != null) {
				int columnCount = row.getLastCellNum();
				
				if (columnCount >= 1) {
					Content content = new Content();
					content.setUrl(row.getCell(0).getStringCellValue());
					content.setCategory(row.getCell(1).getStringCellValue());
					contents.add(content);
				} else {
					System.out.println("column count less than 2");
					throw new ExcelToJsonConversionException(ExceptionConfig.EXCEL_TO_JSON_CONVERSION_ERROR_CODE, ExceptionConfig.EXCEL_TO_JSON_CONVERSION_EXCEPTION);
				}
			} else {
				System.out.println("null row");
				throw new ExcelToJsonConversionException(ExceptionConfig.EXCEL_TO_JSON_CONVERSION_ERROR_CODE, ExceptionConfig.EXCEL_TO_JSON_CONVERSION_EXCEPTION);
			}
		}
		
		return contents;
	}
}
