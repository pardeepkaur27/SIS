package studentSystem;

import Admin.ViewTranscript;
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
	setBounds(250, 250, 450, 350);
	JPanel Panel = new JPanel();
	Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(Panel);
	
	String name=student.getName();
	JLabel lbl1 = new JLabel(name+ " Home");
	lbl1.setFont(new Font("Tahoma", Font.BOLD, 35));
	lbl1.setForeground(Color.GRAY);
	
	JButton btnAddCourse = new JButton("Add course");
	btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnAddCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();	
		EnrollCourse obj= new EnrollCourse();
		obj.setVisible(true);
				
		}
	});
	
	JButton btnDropCourse = new JButton("Drop course");
	btnDropCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnDropCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//dispose();	
		DropCourse obj= new DropCourse();
		obj.setVisible(true);
				
		}
	});
	
	JButton btnViewCgpa = new JButton("View CGPA");
	btnViewCgpa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	JButton btnViewTranscript = new JButton("View transcript");
	btnViewTranscript.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewTranscript.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		     view= new StudentTranscript();
		     view.writeUsingIText();
				
		}
	});
	
	JButton btnPayFees = new JButton("Make payment");
	btnPayFees.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnPayFees.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		     fee= new FeePayment();
		     fee.setVisible(true);
		     }
	});
	
	JButton btnLogout = new JButton("Logout");
	btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnLogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Main().setVisible(true);
		    
			
		 }
	});
	
	GroupLayout groupLayout= new GroupLayout(Panel);
	Panel.setLayout(groupLayout);
	
	groupLayout.setAutoCreateGaps(true);
	groupLayout.setAutoCreateContainerGaps(true);
	
	
	
	groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
			.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
							.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lbl1))
							.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
											.addComponent(btnAddCourse,GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
											.addComponent(btnViewCgpa, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
									.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
											.addComponent(btnViewTranscript, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
											.addComponent(btnDropCourse,GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))))
									      //  .addContainerGap(65, Short.MAX_VALUE)
									       // addComponent(passwordField,GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))))
										    .addContainerGap(70, Short.MAX_VALUE))
			.addGroup(groupLayout.createSequentialGroup()
			.addComponent(btnPayFees, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
			.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
			.addGap(151))
					);
			
	
	groupLayout.setVerticalGroup(
			groupLayout.createSequentialGroup()
			.addComponent(lbl1)
			.addGap(40)
			.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
					.addComponent(btnAddCourse)
					.addGap(40)
					.addComponent(btnDropCourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(28)
			.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
					.addComponent(btnViewCgpa)
				    .addComponent(btnViewTranscript, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(28)
			 .addComponent(btnPayFees, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			 .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)		
			);
	
     }
	
	}
