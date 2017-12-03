package Admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import studentSystem.DB;
import studentSystem.Main;

public class Retrieve extends JFrame {
	boolean status = false;
	String pss1,pss2;
	String Sid=new PasswordRetrieve().studentId;
	@SuppressWarnings("deprecation")
	public Retrieve(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
		
		JLabel lbl = new JLabel("Change Password");
    	lbl.setBounds(40, 40, 300, 20);
    	lbl.setFont(new Font("Tahoma", Font.BOLD, 25));
    	
    	JLabel lblPssword = new JLabel("Enter Password: ");
    	lblPssword.setBounds(20, 130, 100, 20);
    	
    	JPasswordField pfPassword= new JPasswordField(10);
    	pfPassword.setBounds(130, 130, 200, 20);
    	
    	JLabel lblConfirmPssword = new JLabel("Confirm Password: ");
    	lblConfirmPssword.setBounds(20, 160, 100, 20);
    	
    	JPasswordField pfConfirm= new JPasswordField(10);
    	pfConfirm.setBounds(130, 160, 200, 20);
    	
    	JButton btnChange = new JButton("Change Password");
    	btnChange.setBounds(50,190, 180, 30);
    	btnChange.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			pss1=pfPassword.getText();
    	    	pss2=pfConfirm.getText();
    	    	if(pss1.equals(pss2)){
    	    		status=true;
    	    	}
    	    	else{
    	    		JOptionPane.showMessageDialog(Retrieve.this,"Please enter password again correctly");
    	    	}
    			System.out.println(status);
    			if(status==true){
    			retrievePass();
    			}
    				
    		}
    	});
    	
    	JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,230, 100, 30);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new PasswordRetrieve().setVisible(true);
    				
    		}
    	});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBounds(170,230, 100, 30);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    		}
    		});
    	 setLayout(null);
    	 add(lbl);
    	add(lblPssword);
    	add(pfPassword);
    	add(lblConfirmPssword);
    	add(pfConfirm);
    	add(btnChange);
    	add(btnBack);
    	add(btnLogout);
    	setVisible(true);
    	
    	
    	
    	
    	
    }
	public void retrievePass(){
    	try{
    		System.out.println("Student id: "+Sid+" "+pss1);
    		Connection con=DB.getConnection();
    		 String sql="update studentDetails set Password=? where Sid=?";
    		  PreparedStatement statement = con.prepareStatement(sql);
    		  statement.setString(1,pss1);
    		 statement.setString(2,Sid);
    		 statement.executeUpdate();
    		 
    		 JOptionPane.showMessageDialog(Retrieve.this,"Password changed successfully");
    		
    	}
    catch(SQLException ex) {
		  ex.printStackTrace();
				System.out.println("error");
	        }
		
		 
    }
   
}
