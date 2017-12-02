package studentSystem;

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

public class courseSearch extends JFrame{
	List<String> semester=new ArrayList<String>();
	List<String> department=new ArrayList<String>();
	String selectedTerm;
    String selectedDept;
	String selectedYear;
	String semId;
	getEnrollDetails enrollDetails=new getEnrollDetails();
	public courseSearch(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	
     
	JLabel lblEnroll = new JLabel("Search Course");
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
            
            semId=null;
            while(rs.next()) {
				 semId=rs.getString(1);
				 }
            System.out.println(semId);
            enrollDetails.setSemId(semId);
          if(semId!=null){
        	  
          }else{
        	  JOptionPane.showMessageDialog(null,"No data found");
          }
            Search search= new Search();
            
			}catch(SQLException ex) {
				System.out.println("error");
	        }
			
	 }
	});
	
	JButton btnBack = new JButton("Back");
	btnBack.setBounds(50,270, 100, 30);
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new studentHome().setVisible(true);
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
	add(cbDept);
	add(btnContinue);
	add(btnBack);
	add(btnLogout);
	setVisible(true);
	
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



