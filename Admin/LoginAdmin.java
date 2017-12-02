package Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;

import studentSystem.DB;
import studentSystem.LoginStudent;
import studentSystem.Main;
import studentSystem.Student;
import studentSystem.studentHome;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginAdmin extends JFrame{
	
	adminHome s;
		Student student=new Student();
	
	public LoginAdmin(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	JPanel Panel = new JPanel();
	Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(Panel);
	
	JLabel lblAdminLogin = new JLabel("Admin Login");
	lblAdminLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
	lblAdminLogin.setForeground(Color.GRAY);
	
	JLabel lblEnterName= new JLabel("Enter Username");
	lblEnterName.setFont(new Font("Tahoma", Font.PLAIN,15));
	lblEnterName.setForeground(Color.GRAY);
	
	JLabel lblPassword= new JLabel("Enter Password");
	lblPassword.setFont(new Font("Tahoma", Font.PLAIN,15));
	lblPassword.setForeground(Color.GRAY);
	
	final JTextField textField= new JTextField();
	
	final JPasswordField passwordField= new JPasswordField();
	
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
			ResultSet rset = statement.executeQuery("SELECT Adminid, password from AdminDetails");
			int i=0;
			while(rset.next()) {
				
			     Unames.add(rset.getString(1));
			     Pwords.add(rset.getString(2));
			     i++;
			     
			}
			boolean status=false;
			for(int j=0;j<Unames.size();j++){
			if (name.equals(Unames.get(j)) && pssword.equals(Pwords.get(j))){
				dispose();
				s=new adminHome();
				s.setVisible(true);
				status=true;
				
				
			}
			    
			}
			if(status==false)
			{
				JOptionPane.showMessageDialog(LoginAdmin.this,"Invalid login credentials");
				textField.setText("");
				passwordField.setText("");}
		}catch(SQLException ex) {
			System.out.println("error");
		}

		
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
									.addComponent(lblAdminLogin))
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
			.addComponent(lblAdminLogin)
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