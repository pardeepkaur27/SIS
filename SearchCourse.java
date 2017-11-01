package Admin;
import javax.swing.*;

import studentSystem.DB;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import studentSystem.DB;
public class SearchCourse extends JFrame implements ActionListener{
	JLabel lb, lb1, lb2, lb3, lb4, lb5;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton btn;
    
    SearchCourse(){
    	//provide title
    	super("Fetching scourse details");
    	lb5 = new JLabel("Enter course id:");
        lb5.setBounds(20, 20, 100, 20);
        tf5 = new JTextField(20);
        tf5.setBounds(130, 20, 200, 20);
 
        btn = new JButton("Submit");
        btn.setBounds(50, 50, 100, 20);
        btn.addActionListener(this);
 
        lb = new JLabel("Fetching Course Information From Database");
        lb.setBounds(30, 80, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
 
        lb1 = new JLabel("Cid:");
        lb1.setBounds(20, 120, 100, 20);
        tf1 = new JTextField(50);
        tf1.setBounds(130, 120, 200, 20);
        lb2 = new JLabel("Code:");
        lb2.setBounds(20, 150, 100, 20);
        tf2 = new JTextField(100);
        tf2.setBounds(130, 150, 200, 20);
        lb3 = new JLabel("Title:");
        lb3.setBounds(20, 180, 100, 20);
        tf3 = new JTextField(50);
        tf3.setBounds(130, 180, 200, 20);
        lb4 = new JLabel("Credit:");
        lb4.setBounds(20, 210, 100, 20);
        tf4 = new JTextField(50);
        tf4.setBounds(130, 210, 100, 20);
        setLayout(null);
 
        //Add components to the JFrame
        add(lb5);
        add(tf5);
        add(btn);
 
        add(lb);
        add(lb1);
        add(tf1);
        add(lb2);
        add(tf2);
        add(lb3);
        add(tf3);
        add(lb4);
        add(tf4);
 
        //Set TextField Editable False
        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
    }
    	
    public void actionPerformed(ActionEvent e) {
        //Create DataBase Conection and Fetching Records
 try{
	  String str = tf5.getText();
	 Class.forName("oracle.jdbc.driver.OracleDriver");
	 Connection con =DB.getConnection();
	 
	 PreparedStatement st = con.prepareStatement("SELECT * from Course where Cid=?");
     st.setString(1, str);
	 
	 
	// Statement statement = con.createStatement();
		ResultSet rset= st.executeQuery();
		if(rset.next()){
			String s= rset.getString(1);
			 String s1 = rset.getString(2);
             String s2 = rset.getString(3);
             String s3 = rset.getString(4);
             
             
           //Sets Records in TextFields.
             tf1.setText(s);
             tf2.setText(s1);
             tf3.setText(s2);
             tf4.setText(s3);
		}else{
			  JOptionPane.showMessageDialog(null, "Course not Found");
		}
		
		//Create Exception Handler
 } catch (Exception ex) {
	 
	 System.out.println(ex);
 }
    
    }
  //Running Constructor
    
    public static void main(String args[]) {
        new SearchCourse();
    }
    
} 


