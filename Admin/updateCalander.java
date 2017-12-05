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
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Main;

public class updateCalander extends JFrame{
String DAYDATE1,DAYDATE2;
	public updateCalander(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
    	
    	JLabel lbl = new JLabel("Update Calendar");
    	lbl.setBounds(110, 80, 150, 20);
    	lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
    	
    	JLabel lbl1 = new JLabel("Old Value: ");
    	lbl1.setBounds(20, 120, 100, 20);
    	
    	final JTextField textField= new JTextField(10);
    	textField.setBounds(130, 120, 200, 20);
    	setLayout(null);
    	
    	JLabel lblEnterCid = new JLabel("Enter new: ");
    	lblEnterCid.setBounds(20, 150, 100, 20);
    	
    	final JTextField textCid= new JTextField(10);
    	textCid.setBounds(130, 150, 200, 20);
    	
    	textField.setEditable(true);
    	 
    	JButton btnUpdate = new JButton("Update");
    	btnUpdate.setBounds(80,200, 100, 30);
    	btnUpdate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			DAYDATE1=textField.getText();
    			DAYDATE2=textCid.getText();
    			textField.setText("");
    			textCid.setText("");
    	    	System.out.println(DAYDATE1+" "+DAYDATE2);
    			Update();
    				
    		}
    	});
    	
    	JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,240, 100, 30);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new adminHome().setVisible(true);
    				
    		}
    	});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
    	btnLogout.setBounds(170,240, 100, 30);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    		    
    			
    		 }
    	});
    	add(textCid);
    	add(lblEnterCid);
    	add(lbl);
    	add(lbl1);
    	add(textField);
    	add(btnUpdate);
    	add(btnBack);
    	add(btnLogout);
    	
    	
    	
    	 setVisible(true);
    }
	
	public void Update(){
		try{
			PreparedStatement statement = null;
				Connection con=DB.getConnection();
				 
				
				String sql = "update calander set DAY='" +DAYDATE2+ "' where DAY ='" + DAYDATE1 +"'"; 
		         statement = con.prepareStatement(sql);
		         statement.executeUpdate();
		        
		        JOptionPane.showMessageDialog(null," Calander updated sucessfully");	
			con.close();
			}
			catch(SQLException ex) {
						System.out.println("error");
					}
		}
		
	}
