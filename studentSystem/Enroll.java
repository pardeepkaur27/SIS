package studentSystem;
import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Enroll extends JFrame {
	    
	public static void main(String[] args) throws Exception {
		Enroll enroll= new Enroll();
		
	}
	
	 public	Enroll() {
		     Student student = new Student();
			 getEnrollDetails enrollDetails=new getEnrollDetails();
			 List<String> courseId=new ArrayList<String>();
			 List<String> StucourseId=new ArrayList<String>();
	    // The Connection is obtained
			 try{
		Connection con=DB.getConnection();
		String dept=enrollDetails.getDept();
		String semId=enrollDetails.getSemId();
		String sql="select course_id, course_name, faculty, dated from courses where department='" + dept + "' and Sem_id='"+ semId+"'";
		
		PreparedStatement statement = con.prepareStatement(sql);
		System.out.println(enrollDetails.getDept());
		ResultSet rs = statement.executeQuery(sql);
	    
	    JTable table = new JTable(buildTableModel(rs));
                
	   JOptionPane.showMessageDialog(null, new JScrollPane(table));
	   int selected=table.getSelectedRow();
	   System.out.println(selected);
	   
	  rs = statement.executeQuery(sql);
	  while(rs.next()) {
		  courseId.add(rs.getString(1));
		 
	  }
	  String id = student.getUname();
	  
	  String sqlSel="Select add_id, course_id from add_course where Sid='" +id+ "' and Sem_id='" + semId+"'";
	  statement = con.prepareStatement(sqlSel);
	   rs = statement.executeQuery(sqlSel);
	   int addId=0, count=0;
	   while(rs.next()) {
		   addId= rs.getInt(1);
		   StucourseId.add(rs.getString(2));
		   count++;
	   }
	  int x=0;
	  sqlSel="Select add_id, course_id from add_course order by add_id";
	  statement = con.prepareStatement(sqlSel);
	   rs = statement.executeQuery(sqlSel);
	   int AddId=0;
	   while(rs.next()) {
		   AddId= rs.getInt(1);
		  
	   }
	 AddId++;
	 
	  System.out.println(addId+" "+count+" "+AddId);
	  System.out.println(courseId.get(selected)+" "+id+" "+enrollDetails.getSemId()+" "+enrollDetails.getDept());
	  
	  String sql3="Select D.Sid, D.department from studentDept D, studentDetails S where S.Sid = D.Sid and S.Sid='" + id+"'";
	  statement = con.prepareStatement(sql3);
	   rs = statement.executeQuery(sql3);
	   String stuDept = null;
	   while(rs.next()) {
		  stuDept= rs.getString(2);
		  
	   }
	   
	  boolean status=new checkDeadline().check(); 
	  System.out.println("Student dpt is "+stuDept+" "+enrollDetails.getDept());
	  if(count>3){  JOptionPane.showMessageDialog(Enroll.this,"Sorry, you cannot register for more than three courses");
	  }
	  else{
		  if(!stuDept.equals(enrollDetails.getDept())){
			  JOptionPane.showMessageDialog(Enroll.this," Sorry, you cannot enroll out of department course. Contact admin"); 
		  }
		  else{
			  if(status==false){JOptionPane.showMessageDialog(Enroll.this," Sorry, you cannot enroll after the deadline");}
			  else{
	  String sql2="insert into add_course (add_id, course_id, Sid, Sem_id, department) values (?,?,?,?,?)";
	  PreparedStatement statement2 = con.prepareStatement(sql2);
	  statement2.setInt(1, AddId);
	  statement2.setString(2,courseId.get(selected));
	  statement2.setString(3, id);
	  statement2.setString(4, enrollDetails.getSemId());
	  statement2.setString(5, enrollDetails.getDept());
	  
	  statement2.executeUpdate(); 
	  con.close();
	  x++;
			  }
	  }   
	 }
      if(x>0){ JOptionPane.showMessageDialog(Enroll.this,"Data Saved Successfully");}	
     
	  }catch(SQLException ex) {
				System.out.println("error");
	        }
		
		 }
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
