package poiexceldriven;
import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	static XSSFSheet sheet;
	
	public ArrayList<String> getData(String sheetName, String testCase) throws IOException {
		// fileinputstream 
				ArrayList<String> arrayList=new ArrayList<String>();
				DataFormatter formatter = new DataFormatter();
				
				FileInputStream dataFile=new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\ExcelDrivenPOI\\src\\test\\resources\\testdata.xlsx");
				
				XSSFWorkbook workBook=new XSSFWorkbook(dataFile);
				int noOfSheets=workBook.getNumberOfSheets();
				
				for (int i = 0; i <noOfSheets; i++) {
					if(workBook.getSheetName(i).equalsIgnoreCase(sheetName)) {
						sheet=workBook.getSheetAt(i);	
						
						Iterator<Row> rowIterator=sheet.iterator();
						Row firstRow=rowIterator.next();
						
						Iterator<Cell> cellIterator=firstRow.cellIterator();
					
						int k=0;
						int col=0;
						while (cellIterator.hasNext()) {
							Cell cellValue=cellIterator.next();
							if(cellValue.getStringCellValue().equalsIgnoreCase("Testcases")) {
								col=k;
								
							}
							k++;
						}
						
						while(rowIterator.hasNext()) {
							Row tableRow=rowIterator.next();
							String val = formatter.formatCellValue(tableRow.getCell(col));
							if(val.equalsIgnoreCase(testCase)) {
								
								Iterator<Cell> celIterator=tableRow.cellIterator();
								while (celIterator.hasNext()) {
								String val1=formatter.formatCellValue(celIterator.next());	
								arrayList.add(val1);
									
								}
							}
							
						}
						
					}
						
			}
				return arrayList;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

			}
}
