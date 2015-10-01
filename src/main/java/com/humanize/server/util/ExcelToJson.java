package com.humanize.server.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.humanize.server.controller.ContentController;
import com.humanize.server.data.Content;

public class ExcelToJson {
	
	static Logger logger = Logger.getLogger(ExcelToJson.class);

	String filename;

	public ExcelToJson(String filename) {
		this.filename = filename;
	}

	public ArrayList<Content> toJson() {
		FileInputStream fileInputStream = null;
		ArrayList<Content> contents = new ArrayList<Content>();

		try {
			fileInputStream = new FileInputStream(filename);

			Workbook workbook = WorkbookFactory.create(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);

			int rowsCount = sheet.getLastRowNum();
			System.out.println(rowsCount);

			for (int i = 0; i <= rowsCount; i++) {
				Row row = sheet.getRow(i);
				int colCounts = row.getLastCellNum();
				Content content = new Content();

				content.setContentURL(row.getCell(0).getStringCellValue());
				content.setCategory(row.getCell(1).getStringCellValue());
				contents.add(content);
			}
		} catch (Exception ex) {
			logger.error("ExcelToJSON", ex);
			ex.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				// return new JSONObject();
			} catch (IOException ex) {
				logger.error("ExcelTOJSON", ex);
				ex.printStackTrace();
			} finally {
				return contents;
			}
		}
	}
}
