package Admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import studentSystem.DB;
import studentSystem.Main;
import studentSystem.studentHome;

public class FeeDetails extends JFrame{
	String semId, studentId;
	int amount;
	boolean status;
	public FeeDetails(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
    	
    	JLabel lbl = new JLabel("View Payment status");
    	lbl.setBounds(110, 40, 200, 20);
    	lbl.setFont(new Font("Tahoma", Font.BOLD, 20));
    	
    	JLabel lbl1 = new JLabel("Enter Student id: ");
    	lbl1.setBounds(20, 80, 100, 20);
    	
    	final JTextField textField= new JTextField(10);
    	textField.setBounds(130, 80, 200, 20);
    	setLayout(null);
    	
    	JLabel lblTerm=new JLabel("Term:");
		lblTerm.setBounds(40, 110, 100, 30);
		
		String term[]={"Fall","Winter","Summer1","Summer2"};        
		JComboBox cbTerm=new JComboBox(term);    
        cbTerm.setBounds(150, 110,100, 20); 
        
       
        JLabel lblTotalFee = new JLabel("Fee overdue: ");
		lblTotalFee.setBounds(40, 140, 100, 30);
        
        JTextField txtTotal=new JTextField();
    	txtTotal.setBounds(150, 140, 100, 20);
        
        cbTerm.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			studentId=textField.getText();
     			int index= cbTerm.getSelectedIndex();
     			checkStudentId();
    			if(status==true){
    				setAmount(index);
         	        txtTotal.setText(String.valueOf(getAmount()));
    			}else{
    				JOptionPane.showMessageDialog(FeeDetails.this,"Invalid student id");
    				textField.setText("");
    			}
     	        
     		}});
        
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
    	
    	add(lblTotalFee);
    	add(lbl);
    	add(lbl1);
    	add(textField);
    	add(lblTerm);
    	add(cbTerm);
    	add(txtTotal);
    	add(btnBack);
    	add(btnLogout);
    	
	}
	public void setAmount(int index){
		 int amount = 0;
		
		 if (index==0){semId="1";}
		 else if (index==1){semId="2";}
		 else if (index==2){semId="3";}
		 else  {semId="4";}
		 try{
				Connection con=DB.getConnection();
				
				System.out.println(semId+" "+studentId);
				String sql="Select amount from fee where Sid='" + studentId + "' and Sem_id='"+ semId+"'";
				
				PreparedStatement statement = con.prepareStatement(sql);
				ResultSet rs = statement.executeQuery(sql);
				 while(rs.next()) {
					  amount=rs.getInt(1);
					 
				  }
		 }catch(SQLException ex) {
			System.out.println("error");
	 }
		 System.out.println(amount);
		 this.amount=amount;
	 }
	   public int getAmount(){
		 return amount;
	   }
	   
	   public void checkStudentId(){
	    	try{
	    		List<String> studentIds=new ArrayList<String>();
	    		PreparedStatement statement = null;
	    		Connection con=DB.getConnection();
				String id = studentId;
				System.out.println(id);
				String sql = "Select Sid from studentDetails";
	            
	            statement = con.prepareStatement(sql);
	            
	            ResultSet rs = statement.executeQuery();
	            while(rs.next()) {
					 studentIds.add(rs.getString(1));
	            }
	            con.close();
	            status=false;
	            for(int i=0;i<studentIds.size();i++){
	            	if(studentId.equals(studentIds.get(i))){
	            		status=true;
	            	}
	            	
	            }
	            
	    	}
			catch(SQLException ex) {
						System.out.println("error");
					}
	    	
	    	
	    }
	}
