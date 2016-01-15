package com.humanize.server.service;

import java.util.List;

import com.humanize.server.data.Content;
import com.humanize.server.exception.ExcelToJsonConversionException;

public interface ExcelToJsonService {

	List<Content> toJson(String excelFile) throws ExcelToJsonConversionException;
}
