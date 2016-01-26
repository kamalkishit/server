package com.humanize.server.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.data.Content;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.ExcelToJsonConversionException;

@Service
public class ExcelToJsonServiceImpl implements ExcelToJsonService {

	private static final Logger logger = LoggerFactory.getLogger(ExcelToJsonServiceImpl.class);
	private static final String TAG = ExcelToJsonServiceImpl.class.getSimpleName();

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
			logger.error(TAG, exception);
			throw new ExcelToJsonConversionException(ErrorCodes.EXCEL_TO_JSON_CONVERSION_ERROR);
		} catch (InvalidFormatException exception) {
			logger.error(TAG, exception);
			throw new ExcelToJsonConversionException(ErrorCodes.EXCEL_TO_JSON_CONVERSION_ERROR);
		} catch (IOException exception) {
			logger.error(TAG, exception);
			throw new ExcelToJsonConversionException(ErrorCodes.EXCEL_TO_JSON_CONVERSION_ERROR);
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException exception) {
				logger.error(TAG, exception);
			} 
		}
		
		throw new ExcelToJsonConversionException(ErrorCodes.EXCEL_TO_JSON_CONVERSION_ERROR);
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
					logger.error("column count less than 2");
					throw new ExcelToJsonConversionException(ErrorCodes.EXCEL_TO_JSON_CONVERSION_ERROR);
				}
			} else {
				logger.error("null row");
				throw new ExcelToJsonConversionException(ErrorCodes.EXCEL_TO_JSON_CONVERSION_ERROR);
			}
		}
		
		return contents;
	}
}