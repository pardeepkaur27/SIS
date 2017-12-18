package studentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AccountInquiry {
	public AccountInquiry(){
		Student student = new Student();
		 getEnrollDetails enrollDetails=new getEnrollDetails();
		 //String Un = "40014804";
		List<String> courseId=new ArrayList<String>();
		 List<String> StucourseId=new ArrayList<String>();
		 
   // The Connection is obtained
		 try{
	Connection con=DB.getConnection();
	//student.setUname(Un);
	String id=student.getUname();
	
	//String semId=enrollDetails.getSemId();
	String sql="select sem_name as Term, charges,payments, due as Future_Due from fees where Sid= '" + id + "' ";
	//student.setUname(dep);
	//String d=student.getUname();
	PreparedStatement statement = con.prepareStatement(sql);
	System.out.println(student.getUname()+ id);
	ResultSet rs = statement.executeQuery(sql);
	
   JTable table = new JTable(buildTableModel(rs));
  // enrollDetails.setCourseId(rs.getString(1));        
  JOptionPane.showMessageDialog(null, new JScrollPane(table));
  int selected=table.getSelectedRow();
  System.out.println(selected);
  
 rs = statement.executeQuery(sql);
 while(rs.next()) {
	  courseId.add(rs.getString(1));
	 
 }}catch(SQLException ex) {
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