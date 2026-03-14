package utilities;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
public class ExcelReporter {
	public static void recordResult(String id, String desc, String exp, String act, String status) {
        String fileName = "C:\\Users\\sanja\\Downloads\\Untitled spreadsheet (7).xlsx";
        Workbook workbook;
        Sheet sheet;
        File file = new File(fileName);

        try {
            if (file.exists()) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Results");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("TC ID");
                header.createCell(1).setCellValue("Description");
                header.createCell(2).setCellValue("Expected");
                header.createCell(3).setCellValue("Actual");
                header.createCell(4).setCellValue("Status");
            }

            int lastRow = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRow + 1);
            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(desc);
            row.createCell(2).setCellValue(exp);
            row.createCell(3).setCellValue(act);
            row.createCell(4).setCellValue(status);

            FileOutputStream fos = new FileOutputStream(fileName);
            workbook.write(fos);
            workbook.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
