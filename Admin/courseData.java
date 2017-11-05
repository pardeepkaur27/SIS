package Admin;

import studentSystem.DB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class courseData extends JFrame implements ActionListener{
	static courseData s;
	
	JLabel lb, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
    JButton btn1, btn2;
    
    public courseData(){
    	//provide title
    //super("Fetching scourse details");
    lb5 = new JLabel("Enter course id:");
        lb5.setBounds(20, 20, 100, 20);
        tf5 = new JTextField(20);
        tf5.setBounds(130, 20, 200, 20);
 
       
        
        btn2 = new JButton("Clear");
        btn2.setBounds(160,360, 100, 20);
        btn2.addActionListener(this);
 
        lb = new JLabel("Entering Course Information into Database");
        lb.setBounds(30, 80, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
 
        lb1 = new JLabel("CourseId:");
        lb1.setBounds(20, 120, 100, 20);
        tf1 = new JTextField(50);
        tf1.setBounds(130, 120, 200, 20);
        lb2 = new JLabel("Course Name:");
        lb2.setBounds(20, 150, 100, 20);
        tf2 = new JTextField(100);
        tf2.setBounds(130, 150, 200, 20);
        lb3 = new JLabel("Semester:");
        lb3.setBounds(20, 180, 100, 20);
        tf3 = new JTextField(50);
        tf3.setBounds(130, 180, 200, 20);
        lb4 = new JLabel("Department");
        lb4.setBounds(20, 210, 100, 20);
        tf4 = new JTextField(50);
        tf4.setBounds(130, 210, 200, 20);
        lb6 = new JLabel("Faculty Name");
        lb6.setBounds(20, 240, 100, 20);
        tf6 = new JTextField(50);
        tf6.setBounds(130, 240, 200, 20);
        lb7 = new JLabel("Date");
        lb7.setBounds(20, 270, 100, 20);
        tf7 = new JTextField(50);
        tf7.setBounds(130, 270, 200, 20);
        lb8 = new JLabel("Credits");
        lb8.setBounds(20, 300, 100, 20);
        tf8 = new JTextField(50);
        tf8.setBounds(130, 300, 200, 20);
        setLayout(null);
 
        btn1 = new JButton("Submit");
        btn1.setBounds(160,330, 100, 20);
        btn1.addActionListener(this);
        //Add components to the JFrame
        //add(lb5);
        //add(tf5);
        
        add(lb);
        add(lb1);
        add(tf1);
        add(lb2);
        add(tf2);
        add(lb3);
        add(tf3);
        add(lb4);
        add(tf4);
        add(lb6);
        add(tf6);
        add(lb7);
        add(tf7);
        add(lb8);
        add(tf8);        
        add(btn1);
        add(btn2);
 
        //Set TextField Editable False
        tf1.setEditable(true);
        tf2.setEditable(true);
        tf3.setEditable(true);
        tf4.setEditable(true);
        tf6.setEditable(true);
        tf7.setEditable(true);
        tf8.setEditable(true);
    }
	public void actionPerformed(ActionEvent e)
	{
		
		 if (e.getSource() == btn1){
			 int x = 0;
	            String s1 = tf1.getText();
	            String s2 = tf2.getText(); 
	            String s3 = tf3.getText();
	            String s4 = tf4.getText();
	            String s6 = tf6.getText();
	            String s7 = tf7.getText(); 
	            String s8 = tf8.getText();
	            
	       
	            
	            try
	            {
	            	
	            	 Class.forName("oracle.jdbc.driver.OracleDriver");
	            	 Connection con =DB.getConnection();
	            	 
	            	 PreparedStatement ps = con.prepareStatement("insert into Courses values (?,?,?,?,?,TO_DATE(?,'yyyy/mm/dd'),?)");
	            	    ps.setString(1, s1);
	                    ps.setString(2, s2);
	                    ps.setString(3, s3);
	                    ps.setString(4, s4);
	                    ps.setString(5, s6);
	                    ps.setString(6, s7);
	                    ps.setString(7, s8);
	                    ResultSet rs = ps.executeQuery(); 
	            	x++;
	            if(x>0){ JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");}	
	            	            	
	            }catch(Exception ex)
	            {System.out.println(ex);}
			 
		 }
	
	
	 else
     {
          tf1.setText("");
          tf2.setText("");
          tf3.setText("");
          tf4.setText("");
          tf6.setText("");
          tf7.setText("");
          tf8.setText("");
          
      }
		 
}
	 public static void main(String args[])
	   {
	        new courseData();
	    }
}
