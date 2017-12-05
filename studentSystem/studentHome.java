package studentSystem;

import Admin.ViewTranscript;
import Admin.calander;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import Admin.ViewTranscript;
import Admin.courseData;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class studentHome extends JFrame{
	StudentTranscript view;
	FeePayment fee;
	Student student = new Student();
	
    public studentHome() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 400);
	
	
	String name=student.getName();
	JLabel lbl1 = new JLabel(name+ " Home");
	lbl1.setFont(new Font("Tahoma", Font.BOLD, 25));
	lbl1.setBounds(80, 40, 200, 30);
	
	JButton btnAddCourse = new JButton("Add course");
	btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnAddCourse.setBounds(40, 90, 150, 30);
	btnAddCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();	
		EnrollCourse obj= new EnrollCourse();
		obj.setVisible(true);
				
		}
	});
	
	JButton btnSearchCourse = new JButton("Search course");
	btnSearchCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnSearchCourse.setBounds(200, 90,150, 30); 
	btnSearchCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();	
		courseSearch search=new courseSearch();
		search.setVisible(true);
				
		}
	});
	
	JButton btnDropCourse = new JButton("Drop course");
	btnDropCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnDropCourse.setBounds(40, 130, 150, 30);
	btnDropCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//dispose();	
		DropCourse obj= new DropCourse();
		
				
		}
	});
	
	JButton btnViewCgpa = new JButton("View CGPA");
	btnViewCgpa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewCgpa.setBounds(200, 130, 150, 30);
	btnViewCgpa.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		 dispose();	
		 ViewCgpa cgpa= new ViewCgpa();
		cgpa.setVisible(true);
				
		}
	});
	
	JButton btnViewTranscript = new JButton("View transcript");
	btnViewTranscript.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewTranscript.setBounds(40, 170, 150, 30);
	btnViewTranscript.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		     view= new StudentTranscript();
		     view.writeUsingIText();
				
		}
	});
	
	JButton btnPayFees = new JButton("Make payment");
	btnPayFees.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnPayFees.setBounds(200,170,150,30);
	btnPayFees.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		     fee= new FeePayment();
		     fee.setVisible(true);
		     }
	});
	
	JButton btnShowCalendar = new JButton("Show Calendar");
	btnShowCalendar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnShowCalendar.setBounds(90,210, 150, 30);
	btnShowCalendar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new calander();
		    
		 }
	});
	
	JButton btnLogout = new JButton("Logout");
	btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnLogout.setBounds(90,250, 150, 30);
	btnLogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Main().setVisible(true);
		    
			
		 }
	});
	
	setLayout(null);
	add(lbl1);
	add(btnShowCalendar);
	add(btnSearchCourse);
	add(btnAddCourse);
	add(btnDropCourse);
	add(btnViewCgpa);
	add(btnViewTranscript);
	add(btnPayFees);
	add(btnLogout);
    }
	
    }