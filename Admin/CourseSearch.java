package Admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Main;
import studentSystem.studentHome;

public class CourseSearch extends JFrame {
	List<String> semester=new ArrayList<String>();
    List<String> department=new ArrayList<String>();
	String selectedTerm;
    String selectedDept;
	String selectedYear;
	String semId;
	JLabel lblCapacity,lb1CourseId, lblCourseName,lblFaculty;
	JTextField tfCId,tfCourseName,tfFaculty, tfCapacity, tfCourseId;
	public CourseSearch(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 400);
    	JLabel lblSearchCourse=new JLabel("Search Course");
    	lblSearchCourse.setFont(new Font("Tahoma", Font.BOLD, 13));
    	lblSearchCourse.setBounds(110, 10, 150, 10);
    	
		JLabel lbCourseId = new JLabel("Enter course id:");
    	lbCourseId.setBounds(20, 25, 100, 20);
    	tfCourseId = new JTextField(20);
    	tfCourseId.setBounds(130, 25, 200, 20);
        
        JLabel lblTerm = new JLabel("Select Term: ");
    	lblTerm.setBounds(40, 50, 100, 30);
    	
    	JLabel lblYear = new JLabel("Select Year: ");
    	lblYear.setBounds(40, 80, 100, 30);
    	
    	JLabel lblDept = new JLabel("Select Department: ");
    	lblDept.setBounds(40, 110, 150, 30);
    	setLayout(null);
    	 String years[]={"2017","2018"}; 
    	getTerm();
    	 String term[]=semester.toArray(new String[0]);        
    	 JComboBox cbTerm=new JComboBox(term);    
    	 cbTerm.setBounds(180, 50,100, 20); 
    	 
    	 JComboBox cbYear=new JComboBox(years);    
    	 cbYear.setBounds(180, 80,100, 20); 
    	 
    	 
    	 String dept[]={"INSE","SOEN"};        
    	 JComboBox cbDept=new JComboBox(dept);    
    	 cbDept.setBounds(180, 110,100, 20); 
        
    	
 
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(50, 140, 100, 20);
        btnSubmit.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
        selectedTerm = cbTerm.getSelectedItem().toString();
		
		selectedYear = cbYear.getSelectedItem().toString();
		//enrollDetails.setYear(selectedYear);
		selectedDept = cbDept.getSelectedItem().toString();
		//enrollDetails.setDept(selectedDept);
		System.out.println(selectedTerm+" " +selectedDept+" "+selectedYear);
		try{
		Connection con=DB.getConnection();
		String sql = "select sem_id from semester where sem_name=? and year=?";

		PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, selectedTerm);
        statement.setString(2, selectedYear);
        ResultSet rs = statement.executeQuery();
        
        semId=null;
        while(rs.next()) {
			 semId=rs.getString(1);
			 }
        System.out.println(semId);
        if(semId!=null){
      	  Search();
        }else{
      	  JOptionPane.showMessageDialog(CourseSearch.this,"No data found");
        }
    	      
    	       // Enroll enroll= new Enroll();
    	        
    			}catch(SQLException ex) {
    				System.out.println("error");
    	        }
    			
    		}
    	});
        
        lb1CourseId = new JLabel("Course Id:");
        lb1CourseId.setBounds(20, 170, 100, 20);
        
        tfCId = new JTextField(50);
        tfCId.setBounds(130, 170, 200, 20);
        
        lblCourseName = new JLabel("Name:");
        lblCourseName.setBounds(20, 200, 100, 20);
       
        tfCourseName = new JTextField(100);
        tfCourseName.setBounds(130, 200, 200, 20);
        
        lblFaculty = new JLabel("Faculty:");
        lblFaculty.setBounds(20, 240, 100, 20);
        
        tfFaculty = new JTextField(50);
        tfFaculty.setBounds(130, 240, 200, 20);
        
        lblCapacity = new JLabel("Capacity:");
        lblCapacity.setBounds(20, 270, 100, 20);
        
        tfCapacity = new JTextField(50);
        tfCapacity.setBounds(130, 270, 100, 20);
        
        JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,300, 100, 30);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new adminHome().setVisible(true);
    		}
    		});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBounds(170,300, 100, 30);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    		}
    		});
        setLayout(null);
        
        
        add(lblSearchCourse);
        add(lbCourseId);
        add(tfCourseId);
        add(lblTerm);
        add(lblYear);
        add(lblDept);
        add(cbTerm);
        add(cbYear);
        add(cbDept);
        add(btnSubmit);
        
        add(lb1CourseId);
        add(tfCId);
        add(lblCourseName);
        add(tfCourseName);
        add(lblFaculty);
        add(tfFaculty);
        add(lblCapacity);
        add(tfCapacity);
        add(btnBack);
        add(btnLogout);
        setVisible(true);
	}
        
	
	public void Search(){
		try{
			  String str = tfCourseId.getText();
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con =DB.getConnection();
			 
			 PreparedStatement st = con.prepareStatement("SELECT C.course_id, C.course_name, C.faculty, D.capacity from Courses C, capacity D where C.course_id = D.course_id and C.course_id =? and C.sem_id =?");
		     st.setString(1, str);
		     st.setString(2, semId);
			 
			 ResultSet rset= st.executeQuery();
		     if(rset.next()){
			   String s1= rset.getString(1);
			   String s2 = rset.getString(2);
		       String s3 = rset.getString(3);
		       String s4 = rset.getString(4);
		             
		       tfCId.setText(s1);
		       tfCourseName.setText(s2);
		       tfFaculty.setText(s3);
		       tfCapacity.setText(s4);
				}else{
					  JOptionPane.showMessageDialog(null, "Course not Found");
				}
				
				//Create Exception Handler
		 } catch (Exception ex) {
			 
			 System.out.println(ex);
		 }
		    
		    
	}
	
        public void getTerm(){
    		
        	PreparedStatement statement = null;
            
        	try{
        
    		Connection con=DB.getConnection();
    		String sql = "Select sem_name, year from semester order by year"; 
            
            statement = con.prepareStatement(sql);
          
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
            	
    		     semester.add(rs.getString(1));
    		      
            }
        	
    	con.close();
    	}
    	catch(SQLException ex) {
    				System.out.println("error");
    			}
    	}
        
        
	}
