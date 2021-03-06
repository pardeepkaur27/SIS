package Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import studentSystem.DB;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LoginAdmin extends JFrame {
	static LoginAdmin s;

	public static void main(String[] args) {
		// Main frame=new Main();
		// frame.setVisible(true);
		s = new LoginAdmin();
		s.setVisible(true);

	}

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
	
	JButton btnLogin= new JButton("Login");
	btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	
	
	
	
	btnLogin.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			String name= textField.getText();
			String pssword= String.valueOf(passwordField.getPassword());
			String Uname, Pword;
		
			if (Authenticate(name, pssword)){
				adminHome h= new adminHome();
				h.setVisible(true);
			    s.dispose();
				//JOptionPane.showMessageDialog(LoginAdmin.this,"Login successful");
			}
			else
			{
				JOptionPane.showMessageDialog(LoginAdmin.this,"Invalid login credentials");
				textField.setText("");
				passwordField.setText("");}}
		
		
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
					
			);
	
	
	
	}
	public boolean Authenticate(String uname, String Password){
		//String name= textField.getText();
		//String pssword= String.valueOf(passwordField.getPassword());
		//String Uname, Pword;
		boolean passwordStatus=false;
		String validPassword="";
	try{
		Connection con=DB.getConnection();
		Statement statement = con.createStatement();
		ResultSet rset = statement.executeQuery("SELECT toolkit.decrypt(Password) AS password FROM AdminAccess where Username='"+uname+"' ");
		while(rset.next()) {
			validPassword= rset.getString("password");
		     //Pword=rset.getString(2);
		
			}
	
			}catch(SQLException ex) {}
	if(validPassword.equals(Password)){passwordStatus=true;}
	else{passwordStatus=false;}

		return passwordStatus;

	}
}
