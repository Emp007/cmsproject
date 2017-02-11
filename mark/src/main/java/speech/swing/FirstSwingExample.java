package speech.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import speech.mark.Speeker;

public class FirstSwingExample extends JFrame {

	public static final String FILE_PATH = "/home/orange/Documents/AIJAVA/kernal/";
	
	FirstSwingExample() {
		
		String name=JOptionPane.showInputDialog("Enter Name");
		Speeker.getSpeek("You have entered "+name);
		final JTextField tf = new JTextField(name);
		tf.setBounds(50, 50, 150, 20);
		JButton b = new JButton(new ImageIcon("/home/orange/Pictures/smartcrackers.png"));
		b.setBounds(50, 100, 200, 100);
		b.addActionListener(new ActionListener() {
			
		public void actionPerformed(ActionEvent e) {
			
			}
		});
			add(b);
			add(tf);
			setSize(400, 400);
			setLayout(null);
			setVisible(true);
	}

	public static void main(String[] args) {

		FirstSwingExample firstSwingExample = new FirstSwingExample();
	}
}
