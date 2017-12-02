package studentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class checkCapacity {
	int capacity=0;
	int count=0;
   getEnrollDetails enrollDetails=new  getEnrollDetails();
	public boolean capacityCheck(){
		Boolean status=false;
		try{
			
				PreparedStatement statement = null;
				Connection con=DB.getConnection();
				 
				String course_id=enrollDetails.getCourseId();
				//String course_id="SOEN6441";
				String sql = "select capacity from capacity where course_id='"+course_id+"'"; 
		         statement = con.prepareStatement(sql);
		         ResultSet rs= statement.executeQuery();
		      
		         while(rs.next()) {
		        capacity=rs.getInt(1);
		        enrollDetails.setCapacity(rs.getInt(1));
		         }
		        con.close();
		        
		        countStudents();
		        System.out.println(course_id+"capacity is:"+capacity+" "+count);
		        if(count<capacity){
		       status=true;}
			
			
			}
			catch(SQLException ex) {
				ex.printStackTrace();
						System.out.println("error1");
					}
		
		return status;
		
	}
	public void countStudents(){
		String Sid="40014728";
		try{
			
				PreparedStatement statement = null;
				Connection con=DB.getConnection();
				//String course_id="SOEN6441"; 
				String course_id=enrollDetails.getCourseId();
				String sql = "select Sid from add_course where course_id='" +course_id+"'"; 
		         statement = con.prepareStatement(sql);
		         ResultSet rs= statement.executeQuery();
		         
		         while(rs.next()) {
    				 Sid=rs.getString(1);
    				 count++;
    				 }
		         System.out.println(count);
		con.close();
		
	}
	catch(SQLException ex) {
				System.out.println("error2");
			}
	}
public static void main(String[] args){
	checkCapacity check=new checkCapacity();
	check.capacityCheck();
}
}
