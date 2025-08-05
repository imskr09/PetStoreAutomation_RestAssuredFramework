package api.utlilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {

    private String filePath;
    private Workbook workbook;

    // Constructor - opens the Excel file
    public ExcelUtils(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    // Get number of rows in a sheet
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum() + 1;
    }

    // Get number of cells in a row
    public int getCellCount(String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        return row.getLastCellNum(); 
    }

    // Read cell data as String
    public String getCellData(String sheetName, int rowNum, int colNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";
        
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Write data to a cell
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null)
            row = sheet.createRow(rowNum);
        
        Cell cell = row.getCell(colNum);
        if (cell == null)
            cell = row.createCell(colNum);

        cell.setCellValue(data);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
    }

    // Save and close workbook
    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
