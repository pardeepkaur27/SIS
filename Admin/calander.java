package Admin;

import java.awt.Desktop;
import java.io.*;
import java.util.*;
import java.sql.*; 

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class calander {  
	private static final String FILE_NAME = "itext.pdf";
        public static void main(String[] args) throws Exception{
        	 
                /* Create Connection objects */
                Class.forName ("oracle.jdbc.OracleDriver"); 
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "deep2", "system");
                Statement stmt = conn.createStatement();
                /* Define the SQL query */
                ResultSet query_set = stmt.executeQuery("SELECT DAYDATE,EVENTS from calander");
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(new File(FILE_NAME)));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(2);
                //create a cell object
                PdfPCell table_cell;
               
                while (query_set.next()) {                
                                String dept_id = query_set.getString("DAYDATE");
                                table_cell=new PdfPCell(new Phrase(dept_id));
                                my_report_table.addCell(table_cell);
                                String dept_name=query_set.getString("EVENTS");
                                table_cell=new PdfPCell(new Phrase(dept_name));
                                my_report_table.addCell(table_cell);
                                //String manager_id=query_set.getString("MANAGER_ID");
                                //table_cell=new PdfPCell(new Phrase(manager_id));
                                //my_report_table.addCell(table_cell);
                                //String location_id=query_set.getString("LOCATION_ID");
                                //table_cell=new PdfPCell(new Phrase(location_id));
                                //my_report_table.addCell(table_cell);
                                }
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                Desktop.getDesktop().open(new File(FILE_NAME));
                /* Close all DB related objects */
                query_set.close();
                stmt.close(); 
                conn.close();               
                
        }
}