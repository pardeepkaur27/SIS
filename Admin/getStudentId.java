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
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Student;

public class getStudentId extends JFrame{
	Student student = new Student();
	static String S1;
	boolean status;
	public getStudentId(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	
	JLabel lbl = new JLabel("Enroll student");
	lbl.setBounds(110, 80, 150, 20);
	lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
	
	JLabel lbl1 = new JLabel("Enter Student id: ");
	lbl1.setBounds(20, 120, 100, 20);
	
	final JTextField textField= new JTextField(10);
	textField.setBounds(130, 120, 200, 20);
	setLayout(null);
	
	textField.setEditable(true);
	
	
	JButton btnViewTranscript = new JButton("Continue");
	btnViewTranscript.setBounds(80,160, 100, 20);
	btnViewTranscript.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			S1=textField.getText();
			checkStudentId();
			if(status==true){
				System.out.println("student id"+S1);
				student.setUname(S1);
				EnrollStudent enroll=new EnrollStudent();
			}else{
				JOptionPane.showMessageDialog(getStudentId.this,"Invalid student id");
				textField.setText("");
			}
			
				
		}
	});
	
	JButton btnBack = new JButton("Back");
	btnBack.setBounds(50,200, 100, 20);
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new adminHome().setVisible(true);
				
		}
	});
	
	
	add(lbl);
	add(lbl1);
	add(textField);
	add(btnViewTranscript);
	add(btnBack);
	
	
	
	 setVisible(true);
}
	public void checkStudentId(){
    	try{
    		List<String> studentIds=new ArrayList<String>();
    		PreparedStatement statement = null;
    		Connection con=DB.getConnection();
			String id = S1;
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
            	if(S1.equals(studentIds.get(i))){
            		status=true;
            	}
            	
            }
            
    	}
		catch(SQLException ex) {
					System.out.println("error");
				}
    	
    	
    }
}
