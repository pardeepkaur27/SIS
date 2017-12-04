package Admin;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

//import Admin.addCourse;
import Admin.courseData;
import studentSystem.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class adminHome extends JFrame{
	ViewTranscript view;
     public adminHome() {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	setBounds(250, 250, 450, 400);
 	
	
	JLabel lbl1 = new JLabel("Admin Home");
	lbl1.setFont(new Font("Tahoma", Font.BOLD, 25));
	lbl1.setBounds(80, 40, 200, 30);
	
	JButton btnAddCourse = new JButton("Add courses");
	btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnAddCourse.setBounds(40, 90, 150, 30);
	btnAddCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();	
		courseData obj= new courseData();
		obj.setVisible(true);
				
		}
	});
	
	JButton btnUpdateCalendar = new JButton("Update Calender");
	btnUpdateCalendar.setBounds(200, 90,150, 30); 
	
	JButton btnViewCgpa = new JButton("View CGPA");
	btnViewCgpa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewCgpa.setBounds(40, 130, 150, 30);
	btnViewCgpa.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		      new ViewCgpa().setVisible(true);
		     
		     
				
		}
	});
	
	JButton btnViewTranscript = new JButton("View transcript");
	btnViewTranscript.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewTranscript.setBounds(200, 130, 150, 30);
	btnViewTranscript.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		     view= new ViewTranscript();
		     view.setVisible(true);
		     
				
		}
	});
	
	JButton btnEnroll = new JButton("Enroll Student");
	btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnEnroll.setBounds(40, 170, 150, 30);
	btnEnroll.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			getStudentId getStu=new getStudentId();
			getStu.setVisible(true);
		     
				
		}
	});
	
	JButton btnFeeDetails = new JButton("View fee payment");
	btnFeeDetails.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnFeeDetails.setBounds(200,170,150,30);
	btnFeeDetails.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			FeeDetails getFee=new FeeDetails();
			getFee.setVisible(true);
		     
				
		}
	});
	
	JButton btnUpdateCapacity = new JButton("Update capacity");
	btnUpdateCapacity.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnUpdateCapacity.setBounds(40,210,150,30);
	btnUpdateCapacity.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			UpdateCapacity update= new UpdateCapacity();
			update.setVisible(true);
		     
				
		}
	});
	
	JButton btnViewStatus = new JButton("View student enrollment");
	btnViewStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
	btnViewStatus.setBounds(200,210,150,30);
	btnViewStatus.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			checkStudentEnroll update= new checkStudentEnroll();
			update.setVisible(true);
		     
				
		}
	});
	
	JButton btnPassRetrieve = new JButton("Password retrieval");
	btnPassRetrieve.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnPassRetrieve.setBounds(40,250,150,30);
	btnPassRetrieve.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			PasswordRetrieve pss= new PasswordRetrieve();
			pss.passwordRetrieve();
			pss.setVisible(true);
		     
				
		}
	});
	
	JButton btnSearchCourse = new JButton("Search Course");
	btnSearchCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnSearchCourse.setBounds(200,250, 150, 30);
	btnSearchCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new CourseSearch().setVisible(true);
		    
			
		 }
	});
	
	JButton btnLogout = new JButton("Logout");
	btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnLogout.setBounds(80,290, 150, 30);
	btnLogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Main().setVisible(true);
		    
			
		 }
	});
	setLayout(null);
	
	add(btnPassRetrieve);
	add(lbl1);
	add(btnAddCourse);
	add(btnUpdateCalendar);
	add(btnViewStatus);
	add(btnUpdateCapacity);
	add(btnViewTranscript);
	add(btnEnroll);
	add(btnFeeDetails);
	add(btnViewCgpa);
	add(btnSearchCourse);
	add(btnLogout);
     }
	
	}
