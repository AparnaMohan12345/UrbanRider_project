package UtilityFiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class utility {
	static XSSFWorkbook workbook;
	public static String varyingMail() {
		Date date= new Date();
		String VeryingInvalidMail=date.toString().replace(" ","_").replace(":","_");
		return "test"+VeryingInvalidMail+"@gmail.com";
		}
	public static Object[][] excelRead(String sheetname) {
		File excelfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\UtilityFiles\\UrbanRiderExcel.xlsx");
	    try {
		FileInputStream read=new FileInputStream(excelfile);
		workbook=new XSSFWorkbook(read);
	    }catch(Throwable e) {
	    	e.printStackTrace();
	    }
	    XSSFSheet sheet=workbook.getSheet(sheetname);
	    int rows= sheet.getLastRowNum();
	    int col=sheet.getRow(0).getLastCellNum();
	    Object[][] data=new Object[rows][col];
	    for(int i=0;i<rows;i++) {
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<col;j++) {
				XSSFCell cell=row.getCell(j);
				CellType celltype=cell.getCellType();
				switch(celltype) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
						}
			}
		}
		return data;
	}
	}

