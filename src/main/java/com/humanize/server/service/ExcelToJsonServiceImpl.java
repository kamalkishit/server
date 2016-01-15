package com.humanize.server.service;

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
import org.springframework.stereotype.Service;

import com.humanize.server.data.Content;
import com.humanize.server.exception.ExcelToJsonConversionException;

@Service
public class ExcelToJsonServiceImpl implements ExcelToJsonService {

	private static Logger logger = Logger.getLogger(ExcelToJsonService.class);

	public List<Content> toJson(String excelFile) throws ExcelToJsonConversionException {
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(excelFile);

			Workbook workbook = WorkbookFactory.create(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet != null) {
				int rowCount = sheet.getLastRowNum();
				
				return toJson(sheet, rowCount);
			} 
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			throw new ExcelToJsonConversionException(0, null);
		} catch (InvalidFormatException exception) {
			exception.printStackTrace();
			throw new ExcelToJsonConversionException(0, null);
		} catch (IOException exception) {
			exception.printStackTrace();
			throw new ExcelToJsonConversionException(0, null);
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException exception) {

			} 
		}
		
		throw new ExcelToJsonConversionException(0, null);
	}
	
	private List<Content> toJson(Sheet sheet, int rowCount) throws ExcelToJsonConversionException {
		List<Content> contents = new ArrayList<Content>();
		
		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			
			if (row != null) {
				int columnCount = row.getLastCellNum();
				
				if (columnCount >= 1) {
					Content content = new Content();
					content.setOriginalUrl(row.getCell(0).getStringCellValue());
					content.setCategory(row.getCell(1).getStringCellValue());
					contents.add(content);
				} else {
					System.out.println("column count less than 2");
					throw new ExcelToJsonConversionException(0, null);
				}
			} else {
				System.out.println("null row");
				throw new ExcelToJsonConversionException(0, null);
			}
		}
		
		return contents;
	}
}