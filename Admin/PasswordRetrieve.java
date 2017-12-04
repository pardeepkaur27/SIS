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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Main;
import studentSystem.Student;

public class PasswordRetrieve extends JFrame {
		
		static String studentId;
		boolean status;
	    
	    
	    public void passwordRetrieve(){
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	setBounds(250, 250, 450, 350);
	    	
	    	JLabel lbl = new JLabel("Password Retrieval");
	    	lbl.setBounds(110, 40, 150, 20);
	    	lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	
	    	JLabel lbl1 = new JLabel("Enter Student id: ");
	    	lbl1.setBounds(20, 70, 100, 20);
	    	
	    	final JTextField tfStuId= new JTextField(10);
	    	tfStuId.setBounds(130, 70, 200, 20);
	    	setLayout(null);
	    	
	    	tfStuId.setEditable(true);
	    	
	    	System.out.println(studentId); 
	    	JButton btnViewTranscript = new JButton("Continue");
	    	btnViewTranscript.setBounds(80,100, 100, 30);
	    	btnViewTranscript.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			studentId=tfStuId.getText();
	    			System.out.println(studentId);
	    			checkStudentId();
	    			if(status==true){
	    				dispose();
		    			new Retrieve();
	    			}else{
	    				JOptionPane.showMessageDialog(PasswordRetrieve.this,"Invalid student id");
	    				
	    			}
	    			
	    				
	    		}
	    	});
	    	
	    	JButton btnBack = new JButton("Back");
	    	btnBack.setBounds(50,140, 100, 30);
	    	btnBack.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			dispose();
	    			new adminHome().setVisible(true);
	    				
	    		}
	    	});
	    	
	    	JButton btnLogout = new JButton("Logout");
	    	btnLogout.setBounds(170,140, 100, 30);
	    	btnLogout.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			dispose();
	    			new Main().setVisible(true);
	    		}
	    		});
	        setLayout(null);
	    	
	    	add(lbl);
	    	add(lbl1);
	    	add(tfStuId);
	    	add(btnViewTranscript);
	    	add(btnBack);
	    	add(btnLogout);
	    	
	    	
	    	 setVisible(true);
	    }
	    
	    public void checkStudentId(){
	    	try{
	    		List<String> studentIds=new ArrayList<String>();
	    		PreparedStatement statement = null;
	    		Connection con=DB.getConnection();
				String id = studentId;
				System.out.println(id);
				String sql = "Select Sid from studentDetails";
	            
	            statement = con.prepareStatement(sql);
	            
	            ResultSet rs = statement.executeQuery();
	            while(rs.next()) {
					 studentIds.add(rs.getString(1));
	            }
	            con.close();
	            status=false;
	            for(int i=0;i<studentIds.size();i++){
	            	if(studentId.equals(studentIds.get(i))){
	            		status=true;
	            	}
	            	
	            }
	            
	    	}
			catch(SQLException ex) {
						System.out.println("error");
					}
	    	
	    	
	    }    
}
