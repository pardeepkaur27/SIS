package faculty;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import studentSystem.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FacultyHome extends JFrame{

     public FacultyHome() {
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(250, 250, 450, 350);
	
	JLabel lbl1 = new JLabel(Faculty.getName()+" Home");
	lbl1.setFont(new Font("Tahoma", Font.BOLD, 25));
	lbl1.setForeground(Color.GRAY);
	lbl1.setBounds(100, 40, 190, 30);
	
	JButton btnAddCourse = new JButton("Add grades");
	btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnAddCourse.setBounds(40, 120, 150, 30);
	btnAddCourse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		dispose();	
		GradesAdd obj= new GradesAdd();
		obj.setVisible(true);
				
		}
	});
	
	JButton btnUpdateGrades = new JButton(" Update Grades");
	btnUpdateGrades.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnUpdateGrades.setBounds(200, 120, 150, 30);
    btnUpdateGrades.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new ModifyGrades().setVisible(true);
		}
	});
	
	JButton btnViewCgpa = new JButton("View CGPA");
	btnViewCgpa.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewCgpa.setBounds(40, 170, 150, 30);
	btnViewCgpa.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new viewGpa().setVisible(true);
		}
	});
	
	JButton btnViewTranscript = new JButton("View transcript");
	btnViewTranscript.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnViewTranscript.setBounds(200, 170, 150, 30);
	btnViewTranscript.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			new ViewTranscript().setVisible(true);
		}
	});
	
	
	
	JButton btnBack = new JButton("Back");
	btnBack.setBounds(50,270, 100, 30);
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new LoginFaculty().setVisible(true);
		}
		});
	
	JButton btnLogout = new JButton("Logout");
	btnLogout.setBounds(170,270, 100, 30);
	btnLogout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Main().setVisible(true);
		}
		});
	
	setLayout(null);
	add(lbl1);
	add(btnAddCourse);
	add(btnViewCgpa);
	add(btnViewTranscript);
	add(btnBack);
	add(btnLogout);
	add(btnUpdateGrades);
	
     }
	
	}
