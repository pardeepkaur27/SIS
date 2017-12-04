package Director;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import Admin.ViewTranscript;
import studentSystem.Main;

public class DirectorHome extends JFrame {
	viewTrans view;
	public DirectorHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 250, 450, 350);
		
		JLabel lbl1 = new JLabel(" Director Home");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl1.setForeground(Color.GRAY);
		lbl1.setBounds(80, 60, 250, 30);
		
		JButton btnViewTranscript = new JButton("View transcript");
		btnViewTranscript.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewTranscript.setBounds(100, 100, 150, 30);
		btnViewTranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			     view= new viewTrans();
			     view.setVisible(true);
			  		
			}
		});
		
		JButton btnViewGrades = new JButton("View Grades");
		btnViewGrades.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewGrades.setBounds(100, 140, 150, 30);
		btnViewGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    new ViewGrades().setVisible(true);
			     
			  		
			}
		});
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.setBounds(100,200, 150, 30);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Main().setVisible(true);
			    
				
			 }
		});
		
		setLayout(null);
		add(lbl1);
		add(btnViewTranscript);
		add(btnViewGrades);
		add(btnLogout);
		setVisible(true);
		
	}

}
