package Admin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import studentSystem.DB;

import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ViewTranscript {

    private static final String FILE_NAME = "itext.pdf";
    
    public static void main(String[] args) {
        writeUsingIText();
    }

    private static void writeUsingIText() {
    	List<String> courseId=new ArrayList<String>();
    	List<String> courseName=new ArrayList<String>();
    	List<String> grades=new ArrayList<String>();
    	List<String> semester=new ArrayList<String>();
    	List<String> year=new ArrayList<String>();
    	String studentId = null,name = null,Type = null, course_id = null, sem_id = null;
        Document document = new Document();

        
        	try{
        
			Connection con=DB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery("Select S.Sid, S.Name, S.Type, A.course_id, C.course_name, N.sem_name , N.year, G.grade from studentDetails S, add_course A, grades G, courses C, semester N where S.Sid=A.Sid and A.add_id=G.add_id and N.Sem_id=A.Sem_id and A.course_id=C.course_id");
			
	            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
			     
	            document.open();

	            Paragraph p = new Paragraph();
	            p.add("Student Transcript");
	            p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);
	           
	           Paragraph p2 = new Paragraph();
	           Paragraph p3 = new Paragraph();
	           Paragraph p4 = new Paragraph();
	            
	            
	            
	           int count=0; 
			while(rset.next()) {
				 studentId=rset.getString(1);
				 name=rset.getString(2);
			     courseId.add(rset.getString(4));
			     courseName.add(rset.getString(5));
			     semester.add(rset.getString(6));
			     year.add(rset.getString(7));
			     grades.add(rset.getString(8));
			    
			      count++;
		           
		            
			      }
			p2.add(name);
			p2.setAlignment(Element.ALIGN_LEFT);
			
			p3.add(studentId);
			p3.setAlignment(Element.ALIGN_RIGHT);
			
			p3.add("\n");
            p3.add("\n");
            
            for(int i=0;i<count;i++){
            	p4.add(semester.get(i));
            	p4.add("	      ");
            	p4.add(year.get(i));
            	p4.add("	      ");
            	p4.add(courseId.get(i));
            	p4.add("	      ");
            	p4.add(courseName.get(i));
            	p4.add("	      ");
            	
            	p4.add(grades.get(i));
            	
            	p4.add("\n");
            	
            }
           
            document.add(p2);
            document.add(p3);
            document.add(p4);
           
			document.close();
			System.out.println("Done");
			
            Desktop.getDesktop().open(new File(FILE_NAME));
            
			con.close();
        	}
			catch(SQLException ex) {
						System.out.println("error");
					}
        	
            

         catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
