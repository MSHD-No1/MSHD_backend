package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExcelBehavior implements FileBehavior {
    public static void main(String[] args) throws IOException {
        String filePath = "/home/ayamir/Documents/Template.xlsx";
        Workbook book = new XSSFWorkbook(filePath);
        Sheet sheet = book.getSheetAt(0);
        JSONArray data = read(sheet, book);
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            log.error(obj.toString());
        }
    }

    public static JSONArray read(Sheet sheet, Workbook book) throws IOException {
        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum();    // 尾行下标
        // 如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            book.close();
            return new JSONArray();
        }
        // 获取第一行JSON对象键
        Row firstRow = sheet.getRow(rowStart);
        int cellStart = firstRow.getFirstCellNum();
        int cellEnd = firstRow.getLastCellNum();
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        for (int j = cellStart; j < cellEnd; j++) {
            keyMap.put(j, getValue(firstRow.getCell(j), rowStart, j, book, true));
        }
        // 获取每行JSON对象的值
        JSONArray array = new JSONArray();
        for (int i = rowStart + 1; i <= rowEnd; i++) {
            Row eachRow = sheet.getRow(i);
            JSONObject obj = new JSONObject();
            StringBuilder sb = new StringBuilder();
            for (int k = cellStart; k < cellEnd; k++) {
                if (eachRow != null) {
                    String val = getValue(eachRow.getCell(k), i, k, book, false);
                    sb.append(val);        // 所有数据添加到里面，用于判断该行是否为空
                    obj.put(keyMap.get(k), val);
                }
            }
            if (sb.toString().length() > 0) {
                array.put(obj);
            }
        }
        book.close();
        return array;
    }

    public static String getValue(Cell cell, int rowNum, int index, Workbook book, boolean isKey) throws IOException {
        // 空白或空
        if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
            if (isKey) {
                book.close();
                throw new NullPointerException(String.format("the key on row %s index %s is null ", ++rowNum, ++index));
            } else {
                return "";
            }
        }

        // 0. 数字 类型
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return df.format(date);
            }
            String val = cell.getNumericCellValue() + "";
            val = val.toUpperCase();
            if (val.contains("E")) {
                val = val.split("E")[0].replace(".", "");
            }
            return val;
        }

        // 1. String类型
        if (cell.getCellTypeEnum() == CellType.STRING) {
            String val = cell.getStringCellValue();
            if (val == null || val.trim().length() == 0) {
                if (book != null) {
                    book.close();
                }
                return "";
            }
            return val.trim();
        }

        // 2. 公式 CELL_TYPE_FORMULA
        if (cell.getCellTypeEnum() == CellType.FORMULA) {
            return cell.getStringCellValue();
        }

        // 4. 布尔值 CELL_TYPE_BOOLEAN
        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        }

        // 5.	错误 CELL_TYPE_ERROR
        return "";
    }

    public JSONArray transferToJson(String filePath) throws IOException {
        Workbook book = new XSSFWorkbook(filePath);
        Sheet sheet = book.getSheetAt(0);
        JSONArray data = read(sheet, book);
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            log.error(obj.toString());
        }
        return data;
    }
}
