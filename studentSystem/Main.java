package studentSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Admin.LoginAdmin;
import Director.Logindirector;
import faculty.LoginFaculty;

public class Main extends JFrame  {
	
	static Main f;
	
	public static void main(String[] args) {
		f= new Main();
		f.setVisible(true);
		
	}
	
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 500, 400);
		JPanel Panel = new JPanel();
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		
		JLabel lbl1 = new JLabel("Student Information System");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl1.setForeground(Color.GRAY);
		
		JButton btnStudent = new JButton("Student Login");
		btnStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			LoginStudent s= new LoginStudent();
			s.LoginStudents();
			s.setVisible(true);
			
			}
		});
		
		JButton btnAdmin = new JButton("Admin Login");
	    btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			LoginAdmin a= new LoginAdmin();
			a.setVisible(true);
			
			
			
			}
		});
	    
	    JButton btnFaculty= new JButton("Faculty Login");
	    btnFaculty.setFont(new Font("Tahoma", Font.PLAIN,15));
	    btnFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    dispose();	
			LoginFaculty a= new LoginFaculty();
			a.setVisible(true);
			
			}
		});
	    
	    JButton btnDirector=new JButton("Director Login");
	    btnDirector.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    btnDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			dispose();	
			Logindirector a= new Logindirector();
			a.setVisible(true);
			
			}
		});
	    
		GroupLayout groupLayout = new GroupLayout(Panel);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(lbl1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(140)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnStudent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,135, Short.MAX_VALUE)
								.addComponent(btnAdmin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					            .addComponent(btnFaculty, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(btnDirector, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))))
                                .addContainerGap(95, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl1)
					.addGap(32)
					.addComponent(btnStudent, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAdmin, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFaculty, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDirector, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		Panel.setLayout(groupLayout);
	}
	
}
