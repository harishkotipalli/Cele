package com.hcl.pmoautomation.AddAction.controller;





import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;
import java.util.Date;

public class downloadxcel {

  private Connection connection = null;

  public downloadxcel()
  	throws ClassNotFoundException, SQLException {

		// Create MySQL database connection
    Class.forName("com.mysql.jdbc.Driver");

    String url = "jdbc:mysql://localhost:3306/mydb";
    connection = DriverManager.getConnection(url,"root","root");
    System.out.println("connected");
  }


public void generateXls()
  	throws SQLException, FileNotFoundException, IOException {

    // Create new Excel workbook and sheet
 /*   HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
    HSSFSheet xlsSheet = xlsWorkbook.createSheet();
    short rowIndex = 0;*/
	  XSSFWorkbook xlsxWorkbook = new XSSFWorkbook();
	  XSSFSheet xlsSheet = xlsxWorkbook.createSheet();
	  short rowIndex = 1;
    // Execute SQL query
    PreparedStatement stmt =
    connection.prepareStatement("select * from mydb.gat");
    ResultSet rs = stmt.executeQuery();
System.out.println(stmt);
System.out.println(rs);
    
    try {
    	 // Get the list of column names and store them as the first
    	 // row of the spreadsheet.
    	 ResultSetMetaData colInfo = rs.getMetaData();
    	 System.out.println(rs.getMetaData());
    	 ArrayList<String> colNames = new ArrayList<>();
    	 XSSFRow titleRow = xlsSheet.createRow(rowIndex++);
System.out.println("hiiiiiiiiiiiiiii67");
System.out.println( xlsSheet.createRow(rowIndex++));
    	for (int i = 1; i <= colInfo.getColumnCount(); i++) {
    		System.out.println("in for looop");
    	 colNames.add(colInfo.getColumnName(i));
    	 System.out.println( colNames.add(colInfo.getColumnName(i)));
    	 titleRow.createCell((short) (i-1)).setCellValue(
    	 new XSSFRichTextString(colInfo.getColumnName(i)));
    	 System.out.println("latest "+new XSSFRichTextString(colInfo.getColumnName(i)));
    	 xlsSheet.setColumnWidth((short) (i-1), (short) 4000);
    	 }

    	// Save all the data from the database table rows
    	 while (rs.next()) {
    	 XSSFRow dataRow = xlsSheet.createRow(rowIndex++);
    	 short colIndex = 0;
    	 for (String colName : colNames) {
    	 dataRow.createCell(colIndex++).setCellValue(
    	 new XSSFRichTextString(rs.getString(colName)));
    	// System.out.println("last " +new XSSFRichTextString(rs.getString(colName)));
    	 }
    	 }
    	 // Write to disk
    	 String yemi = "C:/"+new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss'.xls'").format(new Date());
    	
    	 xlsxWorkbook.write(new FileOutputStream(yemi));
    	//Message.informationMessage(this, "Data Exported Successfully");
    	 }
    	catch(SQLException | IOException e) {
    	 e.printStackTrace();
    	// System.exit(1);
    	 }

    
    
    
    // Get the list of column names and store them as the first
    // row of the spreadsheet.
   /* ResultSetMetaData colInfo = rs.getMetaData();
    ArrayList<String> colNames = new ArrayList<String>();
    HSSFRow titleRow = xlsSheet.createRow(rowIndex++);

    for (int i = 1; i <= colInfo.getColumnCount(); i++) {
      colNames.add(colInfo.getColumnName(i));
      titleRow.createCell((short) (i-1)).setCellValue(
        new HSSFRichTextString(colInfo.getColumnName(i)));
      xlsSheet.setColumnWidth((short) (i-1), (short) 4000);
    }*/

    // Save all the data from the database table rows
   /* while (rs.next()) {
      HSSFRow dataRow = xlsSheet.createRow(rowIndex++);
      short colIndex = 0;
      for (String colName : colNames) {
        dataRow.createCell(colIndex++).setCellValue(
          new HSSFRichTextString(rs.getString(colName)));
      }
    }*/
    
    

    

 /*   // Write to disk
    xlsWorkbook.write(new FileOutputStream(filename));*/
  }

  // Close database connection
/*  public void close() throws SQLException {
    connection.close();
  }
*/
  public static void main(String[] args) {
    try {
downloadxcel mysqlToXls = new downloadxcel();
System.out.println("working");
      mysqlToXls.generateXls();
    //  mysqlToXls.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
