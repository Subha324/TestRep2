import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class Utilities {
	// ################## Utility Functions ##############################
		public static String[][] readXL(String fPath, String fSheet) throws Exception {
			// Inputs : XL Path and XL Sheet name
			// Output :
			String[][] xData;
			int xRows, xCols;

			DataFormatter dataFormatter = new DataFormatter();
			String cellValue;

			// Get to the Worksheet
			File myxl = new File(fPath);
			FileInputStream myStream = new FileInputStream(myxl);
			HSSFWorkbook myWB = new HSSFWorkbook(myStream);
			HSSFSheet mySheet = myWB.getSheet(fSheet);

			// Count Rows and Columns. Create empty 2D array.
			xRows = mySheet.getLastRowNum() + 1;
			xCols = mySheet.getRow(0).getLastCellNum();
			xData = new String[xRows][xCols];
			System.out.println("Rows : " + xRows);
			System.out.println("Cols : " + xCols);

			System.out.println("~~~~~~~~~~~~ TEST DATA BELOW ~~~~~~~~~~~~");

			for (int i = 0; i < xRows; i++) {
				HSSFRow row = mySheet.getRow(i);
				for (int j = 0; j < xCols; j++) {
					cellValue = "-";
					cellValue = dataFormatter.formatCellValue(row.getCell(j));
					if (cellValue != null) {
						xData[i][j] = cellValue;
					}
					System.out.print(cellValue);
					System.out.print("||||");
				}
				System.out.println("");
			}

			myxl = null; // Memory gets released
			return xData;
		}

		// Method to write into an XL
		public static void writeXL(String fPath, String fSheet, String[][] xData) throws Exception {

			File outFile = new File(fPath);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet osheet = wb.createSheet(fSheet);
			int xR_TS = xData.length;
			int xC_TS = xData[0].length;
			for (int myrow = 0; myrow < xR_TS; myrow++) {
				HSSFRow row = osheet.createRow(myrow);
				for (int mycol = 0; mycol < xC_TS; mycol++) {
					HSSFCell cell = row.createCell(mycol);
					// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(xData[myrow][mycol]);
				}
				FileOutputStream fOut = new FileOutputStream(outFile);
				wb.write(fOut);
				fOut.flush();
				fOut.close();
			}
			wb = null;
			osheet = null;
		}}

		