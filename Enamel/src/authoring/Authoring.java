package authoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

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
		file_chooser.setCurrentDirectory(new java.io.File("C:"));
		file_chooser.setDialogTitle("Open Scenario File");
		JButton chooseScenario = new JButton("Choose Scenario");
		chooseScenario.setVisible(true);
		
		JTextArea Tarea = new JTextArea();

		chooseScenario.getAccessibleContext().setAccessibleName("Choose a Scenario");
		chooseScenario.getAccessibleContext().setAccessibleDescription("Choose a damn Scenario.");
		
		chooseScenario.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent arg0){
				// TODO Auto-generated method stub
				if(file_chooser.showOpenDialog(chooseScenario) == JFileChooser.APPROVE_OPTION) {
					System.out.println("File: " + file_chooser.getSelectedFile() + " has been imported");
					frame.setTitle("Authoring Application - " + file_chooser.getSelectedFile());
					StringBuffer buff = new StringBuffer();
					Tarea.setText("");
					try {
						Scanner scanner = new Scanner(file_chooser.getSelectedFile());
						while(scanner.hasNext()) {
							buff.append(scanner.nextLine()+"\n");
						}
						scanner.close();
						//System.out.println(buff.toString());
						Tarea.setText(buff.toString());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						
					
				}else {
					System.out.println("Error Selecting File");
					frame.setTitle("Authoring Application - Error Selecting File");
				}
			}
			
		});
		
		chooseScenario.setBounds(450,300,200,43);
		Tarea.setBounds(0,0,450,300);
		frame.getContentPane().add(chooseScenario);
		frame.getContentPane().add(Tarea);
		Tarea.setVisible(true);
		frame.setVisible(true);
		
		
	}

}
