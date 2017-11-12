package studentSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollCourse extends JFrame {
	List<String> semester=new ArrayList<String>();
	List<String> department=new ArrayList<String>();
	String selectedTerm;
    String selectedDept;
	String selectedYear;
	getEnrollDetails enrollDetails=new getEnrollDetails();
	public static void main(String[] args){
		EnrollCourse enroll=new EnrollCourse();
		enroll.setVisible(true);
		
	}
	
	
	
	public EnrollCourse(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
    	
         
    	JLabel lblEnroll = new JLabel("Enroll Course");
    	lblEnroll.setBounds(160, 60, 150, 30);
    	lblEnroll.setFont(new Font("Tahoma", Font.BOLD, 15));
    	
    	JLabel lblTerm = new JLabel("Select Term: ");
    	lblTerm.setBounds(40, 120, 100, 30);
    	
    	JLabel lblYear = new JLabel("Select Year: ");
    	lblYear.setBounds(40, 150, 100, 30);
    	
    	JLabel lblDept = new JLabel("Select Department: ");
    	lblDept.setBounds(40, 180, 150, 30);
    	setLayout(null);
    	 String years[]={"2017","2018"}; 
    	getTerm();
    	 String term[]=semester.toArray(new String[0]);        
    	 JComboBox cbTerm=new JComboBox(term);    
    	 cbTerm.setBounds(180, 120,100, 20); 
    	 
    	 JComboBox cbYear=new JComboBox(years);    
    	 cbYear.setBounds(180, 150,100, 20); 
    	 
    	 
    	 String dept[]={"INSE","SOEN"};        
    	 JComboBox cbDept=new JComboBox(dept);    
    	 cbDept.setBounds(180, 180,100, 20); 
        
    	
    	JButton btnContinue = new JButton("Continue");
    	btnContinue.setBounds(100,220, 100, 30);
    	btnContinue.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			selectedTerm = cbTerm.getSelectedItem().toString();
    			enrollDetails.setTerm(selectedTerm);
    			selectedYear = cbYear.getSelectedItem().toString();
    			enrollDetails.setYear(selectedYear);
    			selectedDept = cbDept.getSelectedItem().toString();
    			enrollDetails.setDept(selectedDept);
    			System.out.println(selectedTerm+" " +selectedDept+" "+selectedYear);
    			try{
    			Connection con=DB.getConnection();
    			String sql = "select sem_id from semester where sem_name=? and year=?";

    			PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, selectedTerm);
                statement.setString(2, selectedYear);
                ResultSet rs = statement.executeQuery();
                
                while(rs.next()) {
   				 getEnrollDetails.setSemId(rs.getString(1));
   				 }
                System.out.println(getEnrollDetails.getSemId());
                Enroll enroll= new Enroll();
                
    			}catch(SQLException ex) {
    				System.out.println("error");
    	        }
    			
    			
    	 }
    	});
    	add(lblEnroll);
    	add(lblTerm);
    	add(lblYear);
    	add(lblDept);
    	add(cbTerm);
    	add(cbYear);
    	add(cbDept);
    	add(btnContinue);
    	setVisible(true);
    	
	}
	public void getTerm(){
		
    	
    	
    	PreparedStatement statement = null;
        
    	try{
    
		Connection con=DB.getConnection();
		String sql = "Select sem_name, year from semester order by year"; 
        
        statement = con.prepareStatement(sql);
        //statement.setString(1, id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
        	
		     semester.add(rs.getString(1));
		     
		     //department.add(rs.getString(5));
        }
    	
	con.close();
	}
	catch(SQLException ex) {
				System.out.println("error");
			}
	}
	
	
	
}
	
	
	