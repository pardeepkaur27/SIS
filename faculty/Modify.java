package faculty;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Main;

public class Modify extends JFrame {
	String StudentId, Grade, AddId;
	public Modify(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
    	
         
    	JLabel lblEnroll = new JLabel("Update Grades");
    	lblEnroll.setBounds(160, 60, 150, 30);
    	lblEnroll.setFont(new Font("Tahoma", Font.BOLD, 15));
    	
    	JLabel lblStuId = new JLabel("Enter Student ID: ");
    	lblStuId .setBounds(40, 120, 100, 30);
    	
    	JTextField textStuId= new JTextField();
    	textStuId.setBounds(160, 120, 100, 30);
    	
    	JLabel lblGrade = new JLabel("Enter Grade: ");
    	lblGrade.setBounds(40, 150, 100, 30);
    	
    	JTextField textGrade= new JTextField();
    	textGrade.setBounds(160, 150, 100, 30);

    	JButton btnAdd = new JButton("Add");
    	btnAdd.setBounds(100,220, 100, 30);
    	btnAdd.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			StudentId=textStuId.getText();
    			Grade=textGrade.getText();
    			getAddId();
    			UpdateGrade();
    		}
    	});
    	
    	JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,270, 100, 30);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new FacultyHome().setVisible(true);
    		}
    		});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBounds(170,270, 100, 30);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    		}
    		});
    	
    	setLayout(null);
    	add(lblEnroll);
    	add(lblGrade);
    	add(lblStuId);
    	add(textStuId);
    	add(textGrade);
    	add(btnBack);
    	add(btnLogout);
    	add(btnAdd);
    	setVisible(true);
	}
	
	public void getAddId(){
		PreparedStatement statement = null;
        
    	try{
    		
    	String courseId=ModifyGrades.selectedCourse;
    	System.out.println("Student id is"+" "+StudentId+" "+courseId);
		Connection con=DB.getConnection();
		String sql = "select A.add_id from add_course A, courses c, studentDetails S where C.course_id=A.course_id and S.Sid=A.Sid and A.Sid='"+StudentId+"' and A.course_id='"+courseId+"'"; 
        
        statement = con.prepareStatement(sql);
      
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
        	AddId=rs.getString(1);
		        
        }
    	System.out.println("add id is"+" "+AddId);
	con.close();
	}
	catch(SQLException ex) {
				System.out.println("error");
			}
	
	}

	public void UpdateGrade(){
     try{
   	 int x=0;
   	 Connection con =DB.getConnection();
   	 double numericGrade=getNumericGrade(Grade);
   	if (AddId!= null && !AddId.equals("")) {
   		
   	 PreparedStatement ps = con.prepareStatement("update grades set grade =?, numeric_grade=? where add_id= '" +AddId+ "'");
   	 ps.setString(1, Grade);
   	 ps.setDouble(2, numericGrade);
   	 System.out.println(getNumericGrade(Grade));
           
           ResultSet rs = ps.executeQuery(); 
   	x++;
   if(x>0){ JOptionPane.showMessageDialog(Modify.this, "Data updated Successfully");}
   
  	 }
  	 else{ JOptionPane.showMessageDialog(Modify.this, "Student is not enrolled for this course");
   	 }
   	            	
   }catch(Exception ex)
   {System.out.println(ex);}

}
	public double getNumericGrade(String grade){
		double numGrade=0;
		if(grade.equals("A+")){
			numGrade= 4.3;
        		
        	}else if(grade.equals("A")){
        		numGrade= 4.0;
        	}else if(grade.equals("A-")){
        		numGrade= 3.7;
        	}else if(grade.equals("B+")){
        		numGrade= 3.30;
        	}else if(grade.equals("B")){
        		numGrade= 3.00;
        	}else if(grade.equals("B-")){
        		numGrade= 2.70;
        	}else if(grade.equals("C+")){
        		numGrade= 2.30;
        	}else if(grade.equals("C")){
        		numGrade= 2.00;
        	}else if(grade.equals("C-")){
        		numGrade= 1.70;
        	}else if(grade.equals("D+")){
        		numGrade= 1.30;
        	}else if(grade.equals("D")){
        		numGrade= 1.00;
        	}else if(grade.equals("D-")){
        		numGrade= 0.70;
        	}
        	else{
        		numGrade=0.0;
        	}
		return numGrade;
	}
}

