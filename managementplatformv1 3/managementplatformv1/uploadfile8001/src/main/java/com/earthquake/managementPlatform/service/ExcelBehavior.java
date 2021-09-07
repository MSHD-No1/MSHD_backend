package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelBehavior implements FileBehavior {
    /* Return sheet data in a two-dimensional list.
     * Each element in the outer list represent a row,
     * Each element in the inner list represent a column.
     * The first row is the column name row.*/
    private static List<List<String>> getSheetDataList(Sheet sheet) {
        List<List<String>> ret = new ArrayList<List<String>>();
        // Get the first and last sheet row number.
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        if (lastRowNum > 0) {
            // Loop in sheet rows.
            for (int i = firstRowNum; i < lastRowNum + 1; i++) {
                // Get current row object.
                Row row = sheet.getRow(i);
                // Get first and last cell number.
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                // Create a String list to save column data in a row.
                List<String> rowDataList = new ArrayList<String>();
                // Loop in the row cells.
                for (int j = firstCellNum; j < lastCellNum; j++) {
                    // Get current cell.
                    Cell cell = row.getCell(j);
                    // Get cell type.
                    int cellType = cell.getCellType();
                    if (cellType == CellType.NUMERIC.getCode()) {
                        double numberValue = cell.getNumericCellValue();
                        // BigDecimal is used to avoid double value is counted use Scientific counting method.
                        // For example the original double variable value is 12345678, but jdk translated the value to 1.2345678E7.
                        String stringCellValue = BigDecimal.valueOf(numberValue).toPlainString();
                        rowDataList.add(stringCellValue);
                    } else if (cellType == CellType.STRING.getCode()) {
                        String cellValue = cell.getStringCellValue();
                        rowDataList.add(cellValue);
                    } else if (cellType == CellType.BOOLEAN.getCode()) {
                        boolean numberValue = cell.getBooleanCellValue();
                        String stringCellValue = String.valueOf(numberValue);
                        rowDataList.add(stringCellValue);
                    } else if (cellType == CellType.BLANK.getCode()) {
                        rowDataList.add("");
                    }
                }
                // Add current row data list in the return list.
                ret.add(rowDataList);
            }
        }
        return ret;
    }

    /* Return a JSON string from the string list. */
    private static String getJSONStringFromList(List<List<String>> dataTable) {
        String ret = "";
        if (dataTable != null) {
            int rowCount = dataTable.size();
            if (rowCount > 1) {
                // Create a JSONObject to store table data.
                JSONObject tableJsonObject = new JSONObject();
                // The first row is the header row, store each column name.
                List<String> headerRow = dataTable.get(0);
                int columnCount = headerRow.size();
                // Loop in the row data list.
                for (int i = 1; i < rowCount; i++) {
                    // Get current row data.
                    List<String> dataRow = dataTable.get(i);
                    // Create a JSONObject object to store row data.
                    JSONObject rowJsonObject = new JSONObject();
                    for (int j = 0; j < columnCount; j++) {
                        String columnName = headerRow.get(j);
                        String columnValue = dataRow.get(j);
                        rowJsonObject.put(columnName, columnValue);
                    }
                    tableJsonObject.put("Row " + i, rowJsonObject);
                }
                // Return string format data of JSONObject object.
                ret = tableJsonObject.toString();
            }
        }
        return ret;
    }

    public JSONArray transferToJson(String filePath) throws IOException {
        JSONArray data = new JSONArray();
        File excelFile = new File(filePath.trim());
        InputStream in = new FileInputStream(excelFile);
        Workbook excelWorkBook = new HSSFWorkbook(in);
        int totalSheetNumber = excelWorkBook.getNumberOfSheets();
        for (int i = 0; i < totalSheetNumber; i++) {
            Sheet sheet = excelWorkBook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            if (sheetName != null && sheetName.length() > 0) {
                List<List<String>> sheetDataTable = getSheetDataList(sheet);
                String jsonString = getJSONStringFromList(sheetDataTable);
                data = new JSONArray(jsonString);
            }
        }
        excelWorkBook.close();
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            log.error(obj.toString());
        }
        return data;
    }
}
