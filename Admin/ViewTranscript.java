package Admin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import studentSystem.DB;
import studentSystem.Main;
import studentSystem.Student;
import studentSystem.StudentTranscript;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;


public class ViewTranscript  extends JFrame {
	Student student = new Student();
	static String S1;
    private static final String FILE_NAME = "itext.pdf";
    
    public ViewTranscript(){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
    	
    	JLabel lbl = new JLabel("View Transcript");
    	lbl.setBounds(110, 80, 150, 20);
    	lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
    	
    	JLabel lbl1 = new JLabel("Enter Student id: ");
    	lbl1.setBounds(20, 120, 100, 20);
    	
    	final JTextField textField= new JTextField(10);
    	textField.setBounds(130, 120, 200, 20);
    	setLayout(null);
    	
    	textField.setEditable(true);
    	S1=textField.getText();
    	System.out.println(S1); 
    	JButton btnViewTranscript = new JButton("Continue");
    	btnViewTranscript.setBounds(80,160, 100, 20);
    	btnViewTranscript.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			S1=textField.getText();
    		    writeUsingIText();
    				
    		}
    	});
    	
    	JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,200, 100, 20);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new adminHome().setVisible(true);
    				
    		}
    	});
    	
    	
    	add(lbl);
    	add(lbl1);
    	add(textField);
    	add(btnViewTranscript);
    	add(btnBack);
    	
    	
    	
    	 setVisible(true);
    }
    

    public void writeUsingIText() {
    	List<String> courseId=new ArrayList<String>();
    	List<String> courseName=new ArrayList<String>();
    	List<String> grades=new ArrayList<String>();
    	List<String> semester=new ArrayList<String>();
    	List<String> year=new ArrayList<String>();
    	String studentId = null,name = null,Type = null, course_id = null, sem_id = null;
        Document document = new Document();
        PreparedStatement statement = null;
        
        	try{
        
			Connection con=DB.getConnection();
			String id = S1;
			System.out.println(id);
			String sql = "Select S.Sid, S.Name, S.Type, A.course_id, C.course_name, N.sem_name , N.year, G.grade from studentDetails S, add_course A, grades G, courses C, semester N where S.Sid = ? and S.Sid=A.Sid and A.add_id=G.add_id and N.Sem_id=A.Sem_id and A.course_id=C.course_id";
            
            statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
			
			//ResultSet rset = statement.executeQuery("Select S.Sid, S.Name, S.Type, A.course_id, C.course_name, N.sem_name , N.year, G.grade from studentDetails S, add_course A, grades G, courses C, semester N where S.Sid = ? and S.Sid=A.Sid and A.add_id=G.add_id and N.Sem_id=A.Sem_id and A.course_id=C.course_id");
			
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
			while(rs.next()) {
				 studentId=rs.getString(1);
				 name=rs.getString(2);
			     courseId.add(rs.getString(4));
			     courseName.add(rs.getString(5));
			     semester.add(rs.getString(6));
			     year.add(rs.getString(7));
			     grades.add(rs.getString(8));
			    
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
    public static void main(String[] args) {
    	ViewTranscript view= new ViewTranscript();
    	view.setVisible(true);
        //view.writeUsingIText();
    }
}
