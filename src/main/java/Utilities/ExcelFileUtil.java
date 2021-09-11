package Utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelFileUtil
{
        Workbook wb;
        // to read excel path
    public ExcelFileUtil() throws Throwable
    {
        String path = System.getProperty("user.dir");
        FileInputStream fis=new FileInputStream(path+"\\InputData\\InputSheet.xlsx");
        wb= WorkbookFactory.create(fis);
    }
        //count no of rows from sheet
        public int  rowCount(String sheetname) {
        return wb.getSheet(sheetname).getLastRowNum();
    }
        //count no of coloumns in row
        public  int colCount(String sheetname){
        return wb.getSheet(sheetname).getRow(0).getLastCellNum();	}
        // get cell data from sheet
        public String getData(String sheetname,int row,int column){
        String data="";
        if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC){
            int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
            //convert celldata numeric to string
            data=String.valueOf(celldata);
        }
        else{
            data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
        }
        return data;
    }
        //to write into workbook
        public void setCellData(String sheetname,int row,int column,String status) throws Throwable{
        //get sheet from workbook
        Sheet ws=wb.getSheet(sheetname);
        //get row from sheet
        Row rownum=ws.getRow(row);
        //create cell into row
        Cell cell=rownum.createCell(column);
        //write status into cell
        cell.setCellValue(status);
        if(status.equalsIgnoreCase("Pass"))
        {
            //create cell style
            CellStyle style=wb.createCellStyle();
            //CREATE A FONT
            Font font=wb.createFont();
            //apply color to the text
            font.setColor(IndexedColors.GREEN.getIndex());
            //apply bold to the text
            font.setBold(true);
            //set font
            style.setFont(font);
            //set cell style
            rownum.getCell(column).setCellStyle(style);
        }
        else if (status.equalsIgnoreCase("Fail"))
        {
            //create cell style
            CellStyle style=wb.createCellStyle();
            //CREATE A FONT
            Font font=wb.createFont();
            //apply color to the text
            font.setColor(IndexedColors.RED.getIndex());
            //apply bold to the text
            font.setBold(true);
            //set font
            style.setFont(font);
            //set cell style
            rownum.getCell(column).setCellStyle(style);

        }
        else if (status.equalsIgnoreCase("Not Executed"))

        {
            //create cell style
            CellStyle style=wb.createCellStyle();
            //CREATE A FONT
            Font font=wb.createFont();
            //apply color to the text
            font.setColor(IndexedColors.BLUE.getIndex());
            //apply bold to the text
            font.setBold(true);
            //set font
            style.setFont(font);
            //set cell style
            rownum.getCell(column).setCellStyle(style);
        }
            String path = System.getProperty("user.dir");
        FileOutputStream fos=new FileOutputStream(path+"\\TestOutput\\Results.xlsx");
        wb.write(fos);
        fos.close();
        //wb.close();

    }
}
