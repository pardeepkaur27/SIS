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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class adminHome extends JFrame{

     public adminHome() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	JPanel Panel = new JPanel();
	Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(Panel);
	
	JLabel lbl1 = new JLabel(" Admin Home");
	lbl1.setFont(new Font("Tahoma", Font.BOLD, 35));
	lbl1.setForeground(Color.GRAY);
	
	JButton btnAddCourse = new JButton("Add courses");
	btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnAddCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		courseData obj= new courseData();
		obj.setVisible(true);
				
		}
	});
	
	JButton btnDropCourse = new JButton("Update Calender");
	btnDropCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	JButton btnViewCgpa = new JButton("View CGPA");
	btnViewCgpa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	JButton btnViewTranscript = new JButton("View transcript");
	btnViewTranscript.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	JButton btnPayFees = new JButton("Enroll Student");
	btnPayFees.setFont(new Font("Tahoma", Font.PLAIN, 15));
	
	
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
					
			);
	
     }
	
	}
