package Admin;

import studentSystem.DB;
import studentSystem.Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class courseData extends JFrame implements ActionListener{
	static courseData s;
	
	JLabel lb, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
    JTextField tfCapacity;
    JButton btn1, btn2;
    boolean status;
    String s1;
    public courseData(){
    	
        btn2 = new JButton("Clear");
        btn2.setBounds(160,360, 100, 20);
        btn2.addActionListener(this);
 
        lb = new JLabel("Entering Course Information into Database");
        lb.setBounds(30, 80, 450, 30);
        
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
        
        JLabel lblCapacity = new JLabel("Capacity:");
        lblCapacity.setBounds(20, 330, 100, 20);
        tfCapacity = new JTextField(50);
        tfCapacity.setBounds(130, 330, 200, 20);
 
        btn1 = new JButton("Submit");
        btn1.setBounds(60,360, 100, 20);
        btn1.addActionListener(this);
       
        
        JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,400, 100, 20);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new adminHome().setVisible(true);
    				
    		}
    	});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBounds(170,400, 100, 20);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    				
    		}
    	});
    	
        add(btnBack);
        add(btnLogout);
        add(lblCapacity);
        add(tfCapacity);
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
	            s1 = tf1.getText();
	            String s2 = tf2.getText(); 
	            String s3 = tf3.getText();
	            String s4 = tf4.getText();
	            String s6 = tf6.getText();
	            String s7 = tf7.getText(); 
	            String s8 = tf8.getText();
	            String capacity=tfCapacity.getText();
	            checkCourseId();
    			if(status==true){
    				JOptionPane.showMessageDialog(courseData.this,"This course already exist");
    			}else{
    				
	            try
	            {
	            	
	            	 Class.forName("oracle.jdbc.driver.OracleDriver");
	            	 Connection con =DB.getConnection();
	            	 
	            	 PreparedStatement ps = con.prepareStatement("insert into courses values (?,?,?,?,?,TO_DATE(?,'yyyy/mm/dd'),?)");
	            	    ps.setString(1, s1);
	                    ps.setString(2, s2);
	                    ps.setString(3, s3);
	                    ps.setString(4, s4);
	                    ps.setString(5, s6);
	                    ps.setString(6, s7);
	                    ps.setString(7, s8);
	                    ResultSet rs = ps.executeQuery(); 
	                    
	                    ps= con.prepareStatement("insert into capacity values(?,?)");
	                    ps.setString(1, s1);
	                    ps.setString(2, capacity);
	                    
	                    rs = ps.executeQuery(); 
	            	x++;
	            if(x>0){ JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");}	
	            	            	
	            }catch(Exception ex)
	            {System.out.println(ex);}
			 
		 }
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
          tfCapacity.setText("");
      }
		 
}
	public void checkCourseId(){
		try{
    		List<String> courseIds=new ArrayList<String>();
    		PreparedStatement statement = null;
    		Connection con=DB.getConnection();
			String id = s1;
			System.out.println(id);
			String sql = "Select course_id from courses";
            
            statement = con.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
				 courseIds.add(rs.getString(1));
            }
            con.close();
            status=false;
            for(int i=0;i<courseIds.size();i++){
            	if(id.equals(courseIds.get(i))){
            		status=true;
            	}
            	
            }
            
    	}
		catch(SQLException ex) {
					System.out.println("error");
				}
    	
    	
    }
	 public static void main(String args[])
	   {
	        new courseData();
	    }
}
