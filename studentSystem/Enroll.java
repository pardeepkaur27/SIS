package studentSystem;
import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	  boolean status=true;
	  count++;
	  System.out.println(addId+" "+count);
	  System.out.println(courseId.get(selected)+" "+id+" "+enrollDetails.getSemId()+" "+enrollDetails.getDept());
	  if(count<3){
		  for(int i=0; i<count;i++){
			  if(StucourseId.get(i)==courseId.get(selected)){
				  status=false;
			  }
				  
		  }  
		  }
		  if(status==true){
	  String sql2="insert into add_course (add_id, course_id, Sid, Sem_id,department) values (?,?,?,?,?)";
	  PreparedStatement statement2 = con.prepareStatement(sql2);
	  statement2.setInt(1, count);
	  statement2.setString(2,courseId.get(selected));
	  statement2.setString(3, id);
	  statement2.setString(4, enrollDetails.getSemId());
	  statement2.setString(5, enrollDetails.getDept());
	  
	  statement2.executeUpdate(); 
	  con.close();
	  x++;
		    
      if(x>0){ JOptionPane.showMessageDialog(Enroll.this,"Data Saved Successfully");}	
      else{
    	  JOptionPane.showMessageDialog(Enroll.this,"Not saved");
      
      }
	  }else{
		  JOptionPane.showMessageDialog(Enroll.this,"Sorry, you cannot register for more than three courses");
	  }
	  
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
