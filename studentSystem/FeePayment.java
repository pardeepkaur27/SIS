package studentSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FeePayment  extends JFrame {
	String selected;
	String selPayOption;
	int pay;
	int amount;
	 String semId;
	Student student = new Student();
	 getEnrollDetails enrollDetails=new getEnrollDetails();
	public FeePayment(){
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 400);
    	
    	JLabel lbl1 = new JLabel(" Fee payment");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl1.setForeground(Color.GRAY);
		lbl1.setBounds(80, 40, 200, 30);
		
		JLabel lblTerm=new JLabel("Term:");
		lblTerm.setBounds(40, 90, 100, 30);
		
		String term[]={"Fall","Winter","Summer1","Summer2"};        
		JComboBox cbTerm=new JComboBox(term);    
        cbTerm.setBounds(150, 90,100, 20); 
        
       // int index= cbTerm.getSelectedIndex();
       // setAmount(index);
        
        JTextField txtTotal=new JTextField();
    	txtTotal.setBounds(150, 125, 100, 20);
        
        cbTerm.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			int index= cbTerm.getSelectedIndex();
     	        setAmount(index);
     	        txtTotal.setText(String.valueOf(getAmount()));
     		}});
		
		JLabel lblTotalFee = new JLabel("Total fees to pay: ");
		lblTotalFee.setBounds(40, 120, 100, 30);
		
    	
    	System.out.println(String.valueOf(getAmount()));
    	
		
    	JLabel lblPayFee = new JLabel("Pay Now: ");
    	lblPayFee.setBounds(40, 150, 100, 30);
    	
    	JTextField txtPayFee=new JTextField();
    	txtPayFee.setBounds(150, 155, 100, 20);
    	
    	JLabel lblSelect = new JLabel("Payment mode: ");
    	lblSelect.setBounds(40, 180, 150, 30);
    	setLayout(null);
    	
    	JLabel lblCardNo = new JLabel("Enter Card no.: ");
   	    lblCardNo.setBounds(40, 220, 150, 30);
 	
 	    JTextField txtCardNo=new JTextField();
 	    txtCardNo.setBounds(150, 220, 100, 20);
    	
    	String methodPay[]={"VISA","Mastercard","International wire transfer"};        
   	    JComboBox cbPay=new JComboBox(methodPay);    
   	    cbPay.setBounds(150, 185,150, 20); 
   	    
   	    
   	    
   	    
   	    JButton btnPay = new JButton("Pay");
    	btnPay.setBounds(100,260, 100, 30);
    	btnPay.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			pay=Integer.parseInt(txtPayFee.getText());
     			pay=getAmount()-pay;
     			selected=cbPay.getSelectedItem().toString();
     			System.out.println(selected);
     			try{
     				PreparedStatement statement = null;
     				Connection con=DB.getConnection();
     				 String id = student.getUname();
     				
     				String sql = "update fee set amount='" +pay+ "' where Sid='" + id +"' and sem_id='" +semId+"'"; 
     		         statement = con.prepareStatement(sql);
     		         statement.executeUpdate();
     		        
     		        JOptionPane.showMessageDialog(null," Payment received sucessfully");	
     			con.close();
     			}
     			catch(SQLException ex) {
     						System.out.println("error");
     					}
     		}
     	});
    	
    	JButton btnBack = new JButton("Back");
    	btnBack.setBounds(70,300, 100, 30);
    	btnBack.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new studentHome().setVisible(true);
    	}
	});
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBounds(190,300, 100, 30);
    	btnLogout.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			new Main().setVisible(true);
    		
    	}
	});
    	
    	 add(lblCardNo);
         add(txtCardNo);	
         add(lbl1);
         add(lblTerm);
         add(cbTerm);
         add(lblTotalFee);
     	 add(txtTotal);
    	 add(lblPayFee);
    	 add(txtPayFee);
    	 add(lblSelect);
    	 add(cbPay);
    	 add(btnPay);
    	 add(btnBack);
    	 add(btnLogout);
    	 setVisible(true);
    	 
    	 cbPay.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			selected=cbPay.getSelectedItem().toString();
      			 if (selected.equals("International wire transfer")){
      		    	 lblCardNo.setVisible(false);
      		    	 txtCardNo.setVisible(false);
      		    	 }
      			 else{
      				lblCardNo.setVisible(true);
     		    	 txtCardNo.setVisible(true);
      			 }
      		}	
      		});
    	
    	 
	}
	
 public void setAmount(int index){
	 int amount = 0;
	
	 if (index==0){semId="1";}
	 else if (index==1){semId="2";}
	 else if (index==2){semId="3";}
	 else  {semId="4";}
	 try{
			Connection con=DB.getConnection();
			String id = student.getUname();
			System.out.println(semId+" "+id);
			String sql="Select amount from fee where Sid='" + id + "' and Sem_id='"+ semId+"'";
			
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
}
