package Director;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Admin.CourseSearch;
import Admin.adminHome;
import studentSystem.DB;
import studentSystem.Main;

public class ViewGrades extends JFrame {
	List<String> semester=new ArrayList<String>();
    List<String> department=new ArrayList<String>();
	String selectedTerm;
    //String selectedDept;
	String selectedYear;
	String semId, courseId;
	public ViewGrades(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 400);
    	
    	JLabel lbl = new JLabel("View Grades");
    	lbl.setBounds(110, 40, 150, 20);
    	lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
    	
    	JLabel lbl1 = new JLabel("Enter Course id: ");
    	lbl1.setBounds(20, 70, 100, 20);
    	
    	final JTextField tfCourseId= new JTextField(10);
    	tfCourseId.setBounds(130, 70, 200, 20);
    	setLayout(null);
    	
    	JLabel lblTerm = new JLabel("Select Term: ");
    	lblTerm.setBounds(40, 100, 100, 30);
    	
    	JLabel lblYear = new JLabel("Select Year: ");
    	lblYear.setBounds(40, 140, 100, 30);
    	
    	/*JLabel lblDept = new JLabel("Select Department: ");
    	lblDept.setBounds(40, 180, 150, 30);*/
    	setLayout(null);
    	 String years[]={"2017","2018"}; 
    	getTerm();
    	 String term[]=semester.toArray(new String[0]);        
    	 JComboBox cbTerm=new JComboBox(term);    
    	 cbTerm.setBounds(180,100,100, 20); 
    	 
    	 JComboBox cbYear=new JComboBox(years);    
    	 cbYear.setBounds(180, 140,100, 20); 
    	 
    	 
    	/* String dept[]={"INSE","SOEN"};        
    	 JComboBox cbDept=new JComboBox(dept);    
    	 cbDept.setBounds(180, 180,100, 20); */
        
    	
 
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(50, 220, 100, 20);
        btnSubmit.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			courseId=tfCourseId.getText();
        selectedTerm = cbTerm.getSelectedItem().toString();
		
		selectedYear = cbYear.getSelectedItem().toString();
		
		//selectedDept = cbDept.getSelectedItem().toString();
		
		System.out.println(selectedTerm+" " +" "+selectedYear);
		try{
		Connection con=DB.getConnection();
		String sql = "select sem_id from semester where sem_name=? and year=?";

		PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, selectedTerm);
        statement.setString(2, selectedYear);
        ResultSet rs = statement.executeQuery();
        
        semId=null;
        while(rs.next()) {
			 semId=rs.getString(1);
			 }
        System.out.println(semId);
        if(semId!=null){
        	Grades();
        }else{
      	  JOptionPane.showMessageDialog(ViewGrades.this,"No data found");
        }
    	      
    	       
    	        
    			}catch(SQLException ex) {
    				System.out.println("error");
    	        }
    			
    		}
    	});
        JButton btnBack = new JButton("Back");
    	btnBack.setBounds(50,300, 100, 30);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new adminHome().setVisible(true);
    		}
    		});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBounds(170,300, 100, 30);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    		}
    		});
        
        
        
        add(lbl);
        add(lbl1);
        add(tfCourseId);
        add(btnSubmit);
        add(btnBack);
        add(btnLogout);
        add(lblTerm);
        add(lblYear);
       // add(lblDept);
        add(cbTerm);
        add(cbYear);
        //add(cbDept);
        setVisible(true);

	}
	public void getTerm(){
		
    	PreparedStatement statement = null;
        
    	try{
    
		Connection con=DB.getConnection();
		String sql = "Select sem_name, year from semester order by year"; 
        
        statement = con.prepareStatement(sql);
      
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
        	
		     semester.add(rs.getString(1));
		      
        }
    	
	con.close();
	}
	catch(SQLException ex) {
				System.out.println("error");
			}
	}
	
	public void Grades(){
		try{
		Connection con=DB.getConnection();
		
		String sql="select A.Sid, G.grade from add_course A, grades G where A.add_id=G.add_id and course_id='" + courseId + "' and Sem_id='"+ semId+"'";
		
		PreparedStatement statement = con.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery(sql);
		
	    JTable table = new JTable(buildTableModel(rs));
	        
	   JOptionPane.showMessageDialog(null, new JScrollPane(table));
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
