package Admin;

import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.sql.*; 

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import studentSystem.DB;
public class calander {  
	private static final String FILE_NAME = "itext.pdf";
        public static void main(String[] args) throws Exception{
        	 
                /* Create Connection objects */
        	   Connection con=DB.getConnection();
                Statement stmt = con.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("SELECT DAY,DATES,EVENT from calander");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(new File(FILE_NAME)));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(3);
                //create a cell object
                PdfPCell table_cell;
               
                while (query_set.next()) {                
                                String day = query_set.getString("DAY");
                                table_cell=new PdfPCell(new Phrase(day));
                                my_report_table.addCell(table_cell);
                                String dates = query_set.getString("DATES");
                                table_cell=new PdfPCell(new Phrase(dates));
                                my_report_table.addCell(table_cell);
                                String events=query_set.getString("EVENT");
                                table_cell=new PdfPCell(new Phrase(events));
                                my_report_table.addCell(table_cell);
                                
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                Desktop.getDesktop().open(new File(FILE_NAME));
                /* Close all DB related objects */
                query_set.close();
                stmt.close(); 
                con.close();               
                
        }
}