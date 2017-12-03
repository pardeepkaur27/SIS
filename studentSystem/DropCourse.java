package studentSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DropCourse extends JFrame {
	 Student student = new Student();
	 getEnrollDetails enrollDetails=new getEnrollDetails();
	 List<String> courseId=new ArrayList<String>();
	 List<Integer> addIds=new ArrayList<Integer>();
	 List<String> StucourseId=new ArrayList<String>();
	 public DropCourse(){
// The Connection is obtained
	   try{
      Connection con=DB.getConnection();
      
      String id = student.getUname();

      String sql="Select A.course_id, C.Course_name, C.faculty, S.Sem_name, S.year, A.add_id from add_course A, courses C, semester S where Sid='" +id+ "' and A.course_id=C.course_id and A.Sem_id=S.sem_id";

      PreparedStatement statement = con.prepareStatement(sql);
     
      ResultSet rs = statement.executeQuery(sql);

      JTable table = new JTable(buildTableModel(rs));
        
     JOptionPane.showMessageDialog(null, new JScrollPane(table));
     int selected=table.getSelectedRow();
     System.out.println(selected+ " id:"+id);

     rs = statement.executeQuery(sql);
     while(rs.next()) {
     courseId.add(rs.getString(1));
     addIds.add(rs.getInt(6));
     }
     String course= courseId.get(selected);
     int addId= addIds.get(selected);
     boolean status=new checkDeadline().check(); 
   // Statement stmnt=con.createStatement();
    if(status==false){JOptionPane.showMessageDialog(DropCourse.this," Sorry, you cannot drop after the deadline");}
    if(status==true){
    	Statement stmnt=con.createStatement();
    	System.out.println("addid is"+addId);
    	 String sql2= "Delete from add_course where add_id='" + addId + "'";
         stmnt.execute(sql2);
      con.close();
      JOptionPane.showMessageDialog(DropCourse.this,"Course dropped"); 
	  }
     
     }catch(SQLException ex) {
    	 ex.printStackTrace();
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

