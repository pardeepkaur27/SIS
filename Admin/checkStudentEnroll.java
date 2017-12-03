package Admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import studentSystem.DB;
import studentSystem.Main;
import studentSystem.Student;
import studentSystem.getEnrollDetails;

public class checkStudentEnroll extends JFrame{
	String studentId;
	public checkStudentEnroll(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
    	
    	JLabel lbl = new JLabel("View student Enrollment");
    	lbl.setBounds(40, 40, 300, 30);
    	lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
    	
    	JLabel lbl1 = new JLabel("Enter Student id: ");
    	lbl1.setBounds(20, 80, 100, 20);
    	
    	final JTextField textField= new JTextField(10);
    	textField.setBounds(130, 80, 200, 20);
    	
    	
    	JButton btnOk = new JButton("Ok");
     	btnOk.setBounds(70,110, 100, 30);
     	btnOk.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			studentId=textField.getText();
     			checkEnroll();
     			
     	}
 	});
    	
    	JButton btnBack = new JButton("Back");
     	btnBack.setBounds(70,170, 100, 30);
     	btnBack.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			dispose();
     			new adminHome().setVisible(true);
     	}
 	});
     	
     	JButton btnLogout = new JButton("Logout");
     	btnLogout.setBounds(190,170, 100, 30);
     	btnLogout.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			dispose();
     			new Main().setVisible(true);
     		
     	}
 	});
     	
     	setLayout(null);
     	add(lbl);
    	add(lbl1);
    	add(textField);
    	add(btnOk);
    	add(btnBack);
    	add(btnLogout);
    	setVisible(true);
	}
	
	public void checkEnroll(){
		 
		 
	// The Connection is obtained
		   try{
	      Connection con=DB.getConnection();
	      
	      

	      String sql="Select A.course_id, C.Course_name, C.faculty, S.Sem_name, S.year from add_course A, courses C, semester S where Sid='" +studentId+ "' and A.course_id=C.course_id and A.Sem_id=S.sem_id";

	      PreparedStatement statement = con.prepareStatement(sql);
	     
	      ResultSet rs = statement.executeQuery(sql);

	      JTable table = new JTable(buildTableModel(rs));
	        
	     JOptionPane.showMessageDialog(checkStudentEnroll.this, new JScrollPane(table));
	     
	     rs = statement.executeQuery(sql);
	    /* while(rs.next()) {
	     courseId.add(rs.getString(1));
	     addIds.add(rs.getInt(6));
	     }*/
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


	