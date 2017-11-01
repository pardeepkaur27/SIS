package faculty;
import javax.swing.*;

import studentSystem.DB;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

import studentSystem.DB;
public class addGrades extends JFrame implements ActionListener{
	JLabel lb, lb1, lb2, lb3, lb4, lb5;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton btn;
    
    addGrades(){
    	//provide title
    	super("Adding Student Grades");
    //	lb5 = new JLabel("Enter course id:");
      //  lb5.setBounds(20, 20, 100, 20);
        //tf5 = new JTextField(20);
        //tf5.setBounds(130, 20, 200, 20);
 
 
       lb = new JLabel("Enter Student Grades");
        lb.setBounds(30, 80, 450, 30);
        lb.setForeground(Color.red);
        lb.setFont(new Font("Serif", Font.BOLD, 20));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
 
        lb1 = new JLabel("Semester");
        lb1.setBounds(20, 120, 100, 20);
        tf1 = new JTextField(50);
        tf1.setBounds(130, 120, 200, 20);
        lb2 = new JLabel("Course Id");
        lb2.setBounds(20, 150, 100, 20);
        tf2 = new JTextField(100);
        tf2.setBounds(130, 150, 200, 20);
        lb3 = new JLabel("Student Id");
        lb3.setBounds(20, 180, 100, 20);
        tf3 = new JTextField(50);
        tf3.setBounds(130, 180, 200, 20);
        lb4 = new JLabel("Grade");
        lb4.setBounds(20, 210, 100, 20);
        tf4 = new JTextField(50);
        tf4.setBounds(130, 210, 100, 20);
        

        btn = new JButton("Submit");
        btn.setBounds(80, 250, 100, 20);
        btn.addActionListener(this);
        setLayout(null);
 
        //Add components to the JFrame
        //add(lb5);
       // add(tf5);
        
 
        add(lb);
        add(lb1);
        add(tf1);
        add(lb2);
        add(tf2);
        add(lb3);
        add(tf3);
        add(lb4);
        add(tf4);
        add(btn);
 
        //Set TextField Editable False
        tf1.setEditable(true);
        tf2.setEditable(true);
        tf3.setEditable(true);
        tf4.setEditable(true);
      
        
    }
    	
    public void actionPerformed(ActionEvent e) {
        //Create DataBase Conection and Fetching Records
    	 if (e.getSource() == btn){
			 int x = 0;
	            String s1 = tf1.getText();
	            String s2 = tf2.getText(); 
	            String s5 = tf3.getText();
	            String s6 = tf4.getText();
	            double num=0.0;
	            
	            
	            if(s6.equals("A+")){
	            	num= 4.3;
	            		
	            	}else if(s6.equals("A")){
	            		num= 4.0;
	            	}else if(s6.equals("A-")){
	            		num= 3.7;
	            	}else if(s6.equals("B+")){
	            		num= 3.30;
	            	}else if(s6.equals("B")){
	            		num= 3.00;
	            	}else if(s6.equals("B-")){
	            		num= 2.70;
	            	}else if(s6.equals("C+")){
	            		num= 2.30;
	            	}else if(s6.equals("C")){
	            		num= 2.00;
	            	}else if(s6.equals("C-")){
	            		num= 1.70;
	            	}else if(s6.equals("D+")){
	            		num= 1.30;
	            	}else if(s6.equals("D")){
	            		num= 1.00;
	            	}else if(s6.equals("D-")){
	            		num= 0.70;
	            	}
	            	else{
	            		num=0.0;
	            	}
	            		
	            	
	         	     
	            
	            String s7 = Double.toString(num);
	           //int s6 = Integer.parseInt(tf4.getText());
	         // Integer tf4 = Integer.getInteger(tf4.getText(), null);
	            
	            try
	            {
	            	
	            	 Class.forName("oracle.jdbc.driver.OracleDriver");
	            	 Connection con =DB.getConnection();
	            	 
	            	 PreparedStatement ps = con.prepareStatement("insert into grade values (?,?,?,?,?)");
	            	    ps.setString(1, s1);
	                    ps.setString(2, s2);
	                    ps.setString(3, s5);
	                    ps.setString(4,s6);
	                    ps.setString(5, s7);
	                    
	                    ResultSet rs = ps.executeQuery(); 
	                   
	            	x++;
	            if(x>0){ JOptionPane.showMessageDialog(btn, "Data Saved Successfully");}
	           // else if(x==1){JOptionPane.showMessageDialog(btn,"Invalid data"); }
	            
	            	            	
	           }catch(Exception ex)
	            {
	           JOptionPane.showMessageDialog(btn, "Data already exists", "Data already saved", x);}
			 
		 
    	 }else
	     {
			
			tf1.setText("");
	          tf2.setText("");
	          tf3.setText("");
	          tf4.setText("");
	          
	      }
		
		//Create Exception Handler
 }
    
  //Running Constructor
    
    public static void main(String args[]) {
        new addGrades();
    }
    
} 


