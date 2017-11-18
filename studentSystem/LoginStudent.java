package studentSystem;

import studentSystem.studentHome;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LoginStudent extends JFrame{
    LoginStudent s;
	Student student=new Student();
	public  String Uname;
	String Pword;
	/*public static void main(String[] args) {
		//Main frame=new Main();
		//frame.setVisible(true);
		s= new LoginStudent();
		s.setVisible(true);
		
	}*/
	
	
	public void LoginStudents(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	JPanel Panel = new JPanel();
	Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(Panel);
	
	JLabel lblStudentLogin = new JLabel("Student Login");
	lblStudentLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
	lblStudentLogin.setForeground(Color.GRAY);
	
	JLabel lblEnterName= new JLabel("Enter Username");
	lblEnterName.setFont(new Font("Tahoma", Font.PLAIN,15));
	lblEnterName.setForeground(Color.GRAY);
	
	JLabel lblPassword= new JLabel("Enter Password");
	lblPassword.setFont(new Font("Tahoma", Font.PLAIN,15));
	lblPassword.setForeground(Color.GRAY);
	
	final JTextField textField= new JTextField();
	
	final JPasswordField passwordField= new JPasswordField();
	
	JButton btnLogin= new JButton("Login");
	btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnLogin.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String name= textField.getText();
			String pssword= String.valueOf(passwordField.getPassword());
			List<String> Unames=new ArrayList<String>();
			List<String> Pwords=new ArrayList<String>();
			List<String> Names=new ArrayList<String>();
		try{
			Connection con=DB.getConnection();
			Statement statement = con.createStatement();
			ResultSet rset = statement.executeQuery("SELECT Sid, Password, Name from studentDetails");
			int i=0;
			while(rset.next()) {
				
			     Unames.add(rset.getString(1));
			     Pwords.add(rset.getString(2));
			     Names.add(rset.getString(3));
			     i++;
			     
			}
			con.close();
			boolean status=false;
			for(int j=0;j<Unames.size();j++){
			if (name.equals(Unames.get(j)) && pssword.equals(Pwords.get(j))){
				student.setUname(Unames.get(j));
				student.setName(Names.get(j));
				System.out.println(student.getUname());
				s=new LoginStudent();
				s.setVisible(false);
				dispose();
				studentHome h= new studentHome();
				h.setVisible(true);
				status=true;
				
			}
			    
			}
			if(status==false)
			{
				JOptionPane.showMessageDialog(LoginStudent.this,"Invalid login credentials");
				textField.setText("");
				passwordField.setText("");}
		}catch(SQLException ex) {
			System.out.println("error");
		}

		
			} 
		
		
	}
	);
	
	JButton btnBack= new JButton("Back");
	btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnBack.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			dispose();
			Main m=new Main();
			m.setVisible(true);
		}
		}
			);
			
		
	
	GroupLayout groupLayout= new GroupLayout(Panel);
	Panel.setLayout(groupLayout);
	
	groupLayout.setAutoCreateGaps(true);
	groupLayout.setAutoCreateContainerGaps(true);
	
	
	
	groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
			.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
							.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblStudentLogin))
							.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
											.addComponent(lblEnterName)
											.addComponent(lblPassword))
									.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
											.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
											.addComponent(textField,GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))))
									      //  .addContainerGap(65, Short.MAX_VALUE)
									       // addComponent(passwordField,GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))))
										    .addContainerGap(70, Short.MAX_VALUE))
			.addGroup(groupLayout.createSequentialGroup()
			.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
			.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
			.addGap(151))
					);
			
	
	groupLayout.setVerticalGroup(
			groupLayout.createSequentialGroup()
			.addComponent(lblStudentLogin)
			.addGap(40)
			.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
					.addComponent(lblEnterName)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(28)
			.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
					.addComponent(lblPassword)
				    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(28)
			 .addComponent(btnLogin)
			 .addComponent(btnBack)
					
			);
	
	
	
	}
	
	
}
