package studentSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	String selected, cardNo;
	boolean control;
	String selPayOption;
	boolean cardStatus;
	int pay,pay2;
	int amount;
	 String sem_nam;
	Student student = new Student();
	JTextField txtCardNo;
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
		
		String term[]={"Winter 2018","Summer 2018","Fall 2018","Winter 2019","Summer 2019","Fall 2019"};        
		final JComboBox cbTerm=new JComboBox(term);    
        cbTerm.setBounds(150, 90,100, 20); 
        
       // int index= cbTerm.getSelectedIndex();
       // setAmount(index);
        
        final JTextField txtTotal=new JTextField();
    	txtTotal.setBounds(150, 125, 100, 20);
        
        cbTerm.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e) {
     			int index= cbTerm.getSelectedIndex();
     	        setAmount(index);
     	        txtTotal.setText(String.valueOf(getAmount()));
     		}});
		
		JLabel lblTotalFee = new JLabel("Charges: ");
		lblTotalFee.setBounds(40, 120, 100, 30);
		
    	
    	System.out.println(String.valueOf(getAmount()));
    	
		
    	JLabel lblPayFee = new JLabel("Pay Now: ");
    	lblPayFee.setBounds(40, 150, 100, 30);
    	
    	final JTextField txtPayFee=new JTextField();
    	txtPayFee.setBounds(150, 155, 100, 20);
    	
    	JLabel lblSelect = new JLabel("Payment mode: ");
    	lblSelect.setBounds(40, 180, 150, 30);
    	setLayout(null);
    	
    	final JLabel lblCardNo = new JLabel("Enter Card no.: ");
   	    lblCardNo.setBounds(40, 220, 150, 30);
 	
 	    txtCardNo=new JTextField(16);
 	    txtCardNo.setBounds(150, 220, 150, 20);
 	  
 	
    	String methodPay[]={"Credit Card","International wire transfer"};        
   	    final JComboBox cbPay=new JComboBox(methodPay);    
   	    cbPay.setBounds(150, 185,150, 20); 
   	    
   	    
   	    
   	       JButton btnPay = new JButton("Pay");
    	btnPay.setBounds(70,260, 100, 30);
    	
    	JButton btnInquiry = new JButton("Account Inquiry");
    	btnInquiry.setBounds(190,260, 150, 30);
    	btnInquiry.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
   		     AccountInquiry Accountinquiry= new AccountInquiry();
   		       				
   		}
   	});
   	
    	
    	
    	
    	btnPay.addActionListener(new ActionListener() {
    		
     		public void actionPerformed(ActionEvent e) {
     			
     			pay=Integer.parseInt(txtPayFee.getText());
     			 pay2=getAmount()-pay;
     			 
     			 if(getAmount()==pay){
     				JOptionPane.showMessageDialog(FeePayment.this,"You have no future dues for this semester");
     				 control=true;
     				 }else if (getAmount()<pay){
     					JOptionPane.showMessageDialog(FeePayment.this," You paid more than the charges for this semester it will be reflected in the future due as negative.");
     					control=true;
     				 }else if(pay<=0){
     					JOptionPane.showMessageDialog(FeePayment.this,"Please enter valid amount");
     					control=false;
     					//System.exit(-1);
     				}else if(getAmount()>pay){
     					JOptionPane.showMessageDialog(FeePayment.this,"You have future dues for this semester");
        				 control=true;
     				}else if(pay!=(int)pay){
     					JOptionPane.showMessageDialog(FeePayment.this,"You have entered wrong input data");
       				 control=false;
     				} else {
     				 control=true;
     				 }  			 
     			selected=cbPay.getSelectedItem().toString();
     			System.out.println(selected);
     			if(cardStatus==false && control==true){
     				 try{
     					PreparedStatement statement = null;
     					Connection con=DB.getConnection();
     					 String id = student.getUname();
     					
     					String sql = "update fees set payments='"+ pay +"' ,due='" + pay2 + "' where Sid='" +id+"' and sem_name='" + sem_nam +"'"; 
     			         statement = con.prepareStatement(sql);
     			         statement.executeUpdate();
     			        
     			        JOptionPane.showMessageDialog(FeePayment.this," Payment received sucessfully");	
     				con.close();
     				}
     				catch(SQLException ex) {
     							System.out.println("Error"+ex);
     							//JOptionPane.showMessageDialog(FeePayment.this," Payment received sucessfully");	
     							
     						} 
     			}
     			else{
     			cardNo=txtCardNo.getText();
     			if (cardNo.length()==16 && control==true){
     				
     				
     				 try{
      					PreparedStatement statement = null;
      					Connection con=DB.getConnection();
      					String id = student.getUname();
      					
      					String sql = "update fees set payments='" +pay+ "' ,due='" + pay2 + "' where Sid='" + id +"' and sem_name='" + sem_nam +"'"; 
      			        statement = con.prepareStatement(sql);
      			        statement.executeUpdate();
      			        
      			        JOptionPane.showMessageDialog(FeePayment.this," Payment succesful");	
      				con.close();
      				}
      				catch(Exception ex) {
      							System.out.println("Ex" +ex);
      						} 
     				
     			}else{
     			
     				JOptionPane.showMessageDialog(FeePayment.this," Enter valid card no.");
     			}
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
    	 add(btnInquiry);
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
     		    	cardStatus=true;
      			 }
      		}	
      		});
    	
    	 
	}
	
	/* public void updateFee(){
		 try{
				PreparedStatement statement = null;
				Connection con=DB.getConnection();
				 String id = student.getUname();
				
				String sql = "update fees set payments='" +pay+ "' , due='"+pay2+"' where Sid='" + id +"' and sem_name='" +sem_nam+"'"; 
		         statement = con.prepareStatement(sql);
		         statement.executeUpdate();
		        
		        JOptionPane.showMessageDialog(FeePayment.this," Payment received sucessfully");	
			con.close();
			}
			catch(SQLException ex) {
						System.out.println("Sql error" +ex);
					} 
	 }
	*/
 public void setAmount(int index){
	 int amount = 100;
	
	 if (index==0){sem_nam="Winter 2018";}
	 else if (index==1){sem_nam="Summer 2018";}
	 else if (index==2){sem_nam="Fall 2018";}
	 else if (index==3){sem_nam="Winter 2019";}
	 else if (index==4){sem_nam="Summer 2019";}
	 else {sem_nam="Fall 2019";}
	 
	 try{
			Connection con=DB.getConnection();
			String id = student.getUname();
			String second;
		System.out.println(sem_nam+" "+id);
			String sql="Select due from fees where Sid='" + id + "' and sem_name='" + sem_nam + "' ";
			
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
   }}