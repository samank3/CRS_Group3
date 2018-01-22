package enamel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Authoring {
	//Branch Upper
	private static JFrame frame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setVisible(true);
		frame.setTitle("Authoring Application");

		JFileChooser file_chooser = new JFileChooser();
		file_chooser.setCurrentDirectory(new java.io.File("%HOMEPATH%"));
		file_chooser.setDialogTitle("Open Scenario File");
		JButton chooseScenario = new JButton("Choose Scenario");
		chooseScenario.setVisible(true);
		chooseScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(file_chooser.showOpenDialog(chooseScenario) == JFileChooser.APPROVE_OPTION) {
					System.out.println("File: " + file_chooser.getSelectedFile() + " has been imported");
					frame.setTitle("Authoring Application - " + file_chooser.getSelectedFile());
				}else {
					System.out.println("Error Selecting File");
					frame.setTitle("Authoring Application - Error Selecting File");
				}
			}
			
		});
		
		chooseScenario.setBounds(125,100,200,43);
		frame.getContentPane().add(chooseScenario);
		frame.setVisible(true);
		
		
	}

}
