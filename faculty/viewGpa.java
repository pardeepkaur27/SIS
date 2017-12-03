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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Main;
import studentSystem.Student;
import studentSystem.ViewCgpa;
import studentSystem.studentHome;

public class viewGpa extends JFrame{
	String selectedTerm, selectedYear, semId, studentId;
	List<String> addIds=new ArrayList<String>();
	 List<Integer> numericGrades=new ArrayList<Integer>();
	Student student = new Student();
	int Gpa, Cgpa;
	JTextField tfStuId;
	public viewGpa(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 450, 350);
		
		JLabel lblEnroll = new JLabel("View GPA");
		lblEnroll.setBounds(160, 50, 150, 30);
		lblEnroll.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblStuId=new JLabel("Enter Student id: ");
		lblStuId.setBounds(40, 90, 100, 30);
		
		tfStuId=new JTextField();
		tfStuId.setBounds(180, 90, 80,20);
		
		JLabel lblTerm = new JLabel("Select Term: ");
		lblTerm.setBounds(40, 120, 100, 30);
		
		JLabel lblYear = new JLabel("Select Year: ");
		lblYear.setBounds(40, 150, 100, 30);
		
		JLabel lblGpa = new JLabel("Term GPA: ");
		lblGpa.setBounds(40, 180, 150, 30);
		
		JTextField textGpa=new JTextField();
		textGpa.setBounds(180, 180, 80, 20); 
		
		JLabel lblCGpa = new JLabel("CGPA: ");
		lblCGpa.setBounds(40, 230, 80, 30);
		
		JTextField textCGpa=new JTextField();
		textCGpa.setBounds(180, 230,80, 20); 
		
		 String term[]= {"Fall","Winter","Summer1","Summer2"};        
		 JComboBox cbTerm=new JComboBox(term);    
		 cbTerm.setBounds(180, 120,100, 20); 
		 
		 setLayout(null);
		 String years[]={"2017","2018"}; 
		 JComboBox cbYear=new JComboBox(years);    
		 cbYear.setBounds(180, 150,100, 20); 
		 
		 JButton ok=new JButton("OK");
			ok.setBounds(300, 140, 80, 30);
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					studentId=tfStuId.getText();
					selectedTerm=cbTerm.getSelectedItem().toString();
					selectedYear=cbYear.getSelectedItem().toString();
					SemId();
					calculateGpa();
					calculateCGpa();
					textGpa.setText(String.valueOf((Gpa)));
					textCGpa.setText(String.valueOf((Cgpa)));
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
			
			add(lblStuId);
			add(tfStuId);
			add(lblEnroll);
			add(lblTerm);
			add(lblYear);
			add(lblGpa);
			add(ok);
			add(textGpa);
			add(lblCGpa);
			add(textCGpa);
			add(cbTerm);
			add(cbYear);
			add(btnBack);
			add(btnLogout);
			setVisible(true);
		 
	}
	public void SemId(){
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
        	  
          }else{
        	  JOptionPane.showMessageDialog(viewGpa.this,"No data found");
          }
        	  
            
			}catch(SQLException ex) {
				ex.printStackTrace();
				System.out.println("error");
	        }
			
	 }
	
	public void calculateGpa(){
		try{
			
			Connection con=DB.getConnection();
			String sql = "select G.add_id, G.numeric_grade from grades G, add_course A where A.add_id=G.add_id and A.Sid='" + studentId + "' and A.Sem_id='" + semId + "'";

			PreparedStatement statement = con.prepareStatement(sql);
         
            ResultSet rs = statement.executeQuery();
            
            semId=null;
            while(rs.next()) {
				 addIds.add(rs.getString(1));
				 numericGrades.add(rs.getInt(2));
				 }
            int sumGrades=0;
            for(int i=0;i<numericGrades.size();i++){
            	sumGrades=sumGrades+numericGrades.get(i);
            }
            Gpa=sumGrades/numericGrades.size();
            System.out.println("Gpa is: " + Gpa);
            
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("error");
        }
		
	}
	
	public void calculateCGpa(){
		try{
			
			Connection con=DB.getConnection();
			String sql = "select G.add_id, G.numeric_grade from grades G, add_course A where A.add_id=G.add_id and A.Sid='" + studentId + "'";

			PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            semId=null;
            while(rs.next()) {
				 addIds.add(rs.getString(1));
				 numericGrades.add(rs.getInt(2));
				 }
            int sumGrades=0;
            for(int i=0;i<numericGrades.size();i++){
            	sumGrades=sumGrades+numericGrades.get(i);
            }
            Cgpa=sumGrades/numericGrades.size();
            System.out.println("CGpa is: " + Gpa);
            
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("error");
        }
	}
	}
	
