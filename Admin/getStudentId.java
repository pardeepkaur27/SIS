package Admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import studentSystem.Student;

public class getStudentId extends JFrame{
	Student student = new Student();
	static String S1;
	public getStudentId(){
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(250, 250, 450, 350);
	
	JLabel lbl = new JLabel("Enroll student");
	lbl.setBounds(110, 80, 150, 20);
	lbl.setFont(new Font("Tahoma", Font.BOLD, 15));
	
	JLabel lbl1 = new JLabel("Enter Student id: ");
	lbl1.setBounds(20, 120, 100, 20);
	
	final JTextField textField= new JTextField(10);
	textField.setBounds(130, 120, 200, 20);
	setLayout(null);
	
	textField.setEditable(true);
	
	
	JButton btnViewTranscript = new JButton("Continue");
	btnViewTranscript.setBounds(80,160, 100, 20);
	btnViewTranscript.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			S1=textField.getText();
			System.out.println("student id"+S1);
		student.setUname(S1);
		EnrollStudent enroll=new EnrollStudent();
				
		}
	});
	
	JButton btnBack = new JButton("Back");
	btnBack.setBounds(50,200, 100, 20);
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new adminHome().setVisible(true);
				
		}
	});
	
	
	add(lbl);
	add(lbl1);
	add(textField);
	add(btnViewTranscript);
	add(btnBack);
	
	
	
	 setVisible(true);
}
}
