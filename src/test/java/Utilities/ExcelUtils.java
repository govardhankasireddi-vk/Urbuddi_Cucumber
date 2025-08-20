package Utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {


    public static List<Map<String, String>> readAllRows(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> data = new HashMap<>();

            for (int j = 0; j < row.getLastCellNum(); j++) {
                String key = headerRow.getCell(j).getStringCellValue();
                Cell cell = row.getCell(j);
                DataFormatter formatter = new DataFormatter();
                String value = formatter.formatCellValue(cell);
                data.put(key, value);
            }
            dataList.add(data);
        }
        workbook.close();
        fis.close();
        return dataList;

    }
}


