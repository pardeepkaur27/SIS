package faculty;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import studentSystem.DB;
import studentSystem.Main;

public class ModifyGrades extends JFrame {
	List<String> semester=new ArrayList<String>();
	List<String> semIds=new ArrayList<String>();
	List<String> courseIds=new ArrayList<String>();
	List<String> courseNames=new ArrayList<String>();
	String selectedTerm;
    String selectedYear, semId;
    static String selectedCourse;
	
	public ModifyGrades(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	
	JLabel lblEnroll = new JLabel("Add Grades");
	lblEnroll.setBounds(160, 60, 150, 30);
	lblEnroll.setFont(new Font("Tahoma", Font.BOLD, 15));
	
	JLabel lblTerm = new JLabel("Select Term: ");
	lblTerm.setBounds(40, 120, 100, 30);
	
	JLabel lblYear = new JLabel("Select Year: ");
	lblYear.setBounds(40, 150, 100, 30);
	
	JLabel lblDept = new JLabel("Select Course: ");
	lblDept.setBounds(40, 180, 150, 30);
	setLayout(null);
	 String years[]={"2017","2018"}; 
	getTerm();
	
	 String term[]=semester.toArray(new String[0]);        
	 JComboBox cbTerm=new JComboBox(term);    
	 cbTerm.setBounds(180, 120,100, 20); 
	 
	 JComboBox cbYear=new JComboBox(years);    
	 cbYear.setBounds(180, 150,100, 20); 
	 
	       
	 JComboBox cbCourse=new JComboBox();    
	 cbCourse.setBounds(180, 180,200, 20); 
	 
	 JButton btnOk = new JButton("Ok");
	 btnOk.setBounds(300,135, 100, 30);
	 btnOk.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e) {
 			selectedTerm = cbTerm.getSelectedItem().toString();
 			selectedYear = cbYear.getSelectedItem().toString();
 			getSemId();
 			getCourses();
 			cbCourse.setModel(new DefaultComboBoxModel(courseNames.toArray()));
 		 }
		});
	 
	
    
	
	JButton btnContinue = new JButton("Continue");
	btnContinue.setBounds(100,220, 100, 30);
	btnContinue.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int selectedCName=cbCourse.getSelectedIndex();
			selectedCourse=courseIds.get(selectedCName);
			System.out.println(selectedCourse);
			dispose();
			new Modify().setVisible(true);
		 }
	});
	JButton btnBack = new JButton("Back");
	btnBack.setBounds(50,270, 100, 30);
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new FacultyHome().setVisible(true);
		}
		});
	
	JButton btnLogout = new JButton("Logout");
	btnLogout.setBounds(170,270, 100, 30);
	btnLogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Main().setVisible(true);
		}
		});
	
	add(lblEnroll);
	add(lblTerm);
	add(lblYear);
	add(lblDept);
	add(cbTerm);
	add(cbYear);
	add(cbCourse);
	add(btnContinue);
	add(btnBack);
	add(btnOk);
	add(btnLogout);
	setVisible(true);
	
	}
		public void getTerm(){
			PreparedStatement statement = null;
	        
	    	try{
	    
			Connection con=DB.getConnection();
			String sql = "Select sem_id, sem_name, year from semester order by year"; 
	        
	        statement = con.prepareStatement(sql);
	      
	        ResultSet rs = statement.executeQuery();
	        while(rs.next()) {
	        	 semIds.add(rs.getString(1));
			     semester.add(rs.getString(2));
			       
	        }
	    	
		con.close();
		}
		catch(SQLException ex) {
					System.out.println("error");
				}
		}
		
		public void getCourses(){
        PreparedStatement statement = null;
	        
	    	try{
	        String prof=Faculty.getName();
	        System.out.println(prof+" "+semId);
			Connection con=DB.getConnection();
			String sql = "select course_id, course_name from courses where faculty='" + prof +"' and sem_id='"+ semId +"'"; 
	        
	        statement = con.prepareStatement(sql);
	      
	        ResultSet rs = statement.executeQuery();
	        while(rs.next()) {
	        	courseIds.add(rs.getString(1));
	        	courseNames.add(rs.getString(2));
	        	System.out.println(rs.getString(2));
	        }
		}catch(SQLException ex) {
			System.out.println("error");
		}
	}
		
		public void getSemId(){
          PreparedStatement statement = null;
	        
	    	try{
	    
			Connection con=DB.getConnection();
			String sql = "Select sem_id from semester where sem_name='" + selectedTerm +"' and year='"+ selectedYear +"'";; 
	        
	        statement = con.prepareStatement(sql);
	      
	        ResultSet rs = statement.executeQuery();
	        while(rs.next()) {
	        	 semId=rs.getString(1);
			        
	        }
	    	
		con.close();
		System.out.println(semId);
		}
		catch(SQLException ex) {
					System.out.println("error");
				}
			
		}
		
}

