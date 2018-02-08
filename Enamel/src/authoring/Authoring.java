package authoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Authoring {
	private static JFrame frame;
	private static JButton chooseScenario;
	private static JFileChooser file_chooser;
	private static JTextArea scenarioReader;
	
	private static void openFileChooser() {
		if(file_chooser.showOpenDialog(chooseScenario) == JFileChooser.APPROVE_OPTION) {
			
			StringBuffer buff = new StringBuffer();
			scenarioReader.setText("");
			int lineCounter = 0;
			try {
				Scanner scanner = new Scanner(file_chooser.getSelectedFile());
				while(scanner.hasNextLine()) {
					//Check if first two lines follow the specific plan.
					String line = scanner.nextLine();
					if(lineCounter == 0) {
						if(!line.startsWith("Cell")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							frame.setTitle("Authoring Application - Invalid Format");
							scanner.close();
							return;
						}
					}
					if(lineCounter == 1) {
						if(!line.startsWith("Button")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							frame.setTitle("Authoring Application - Invalid Format");
							scanner.close();
							return;
						}
					}
					lineCounter+=1;
					buff.append(line+"\n");
				}
				scanner.close();
				//System.out.println(buff.toString());
				scenarioReader.setText(buff.toString());
				System.out.println("File: " + file_chooser.getSelectedFile() + " has been imported");
				frame.setTitle("Authoring Application - " + file_chooser.getSelectedFile());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
		}else {
			System.out.println("Error Selecting File");
			frame.setTitle("Authoring Application - Error Selecting File");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setBounds(100,100,648,428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
		frame.setTitle("Authoring Application");

		file_chooser = new JFileChooser();
		file_chooser.setCurrentDirectory(new java.io.File("C:"));
		file_chooser.setDialogTitle("Open Scenario File");
		chooseScenario = new JButton("Choose Scenario");
		chooseScenario.setVisible(true);
		
		scenarioReader = new JTextArea();
		scenarioReader.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scenarioReader.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		scenarioReader.setWrapStyleWord(true);
		scenarioReader.setLineWrap(true);
		scenarioReader.setEditable(false);

		chooseScenario.getAccessibleContext().setAccessibleName("Choose a Scenario");
		chooseScenario.getAccessibleContext().setAccessibleDescription("Choose a damn Scenario.");
		
		JScrollPane scroll = new JScrollPane(scenarioReader);

		
		chooseScenario.addActionListener(new ActionListener() {

			
			
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				// TODO Auto-generated method stub
				openFileChooser();
			}
			
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(chooseScenario, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scenarioReader))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addComponent(scenarioReader, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(chooseScenario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(359, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		scenarioReader.setVisible(true);
		frame.getContentPane().add(scroll);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);
		
		mntmOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				openFileChooser();
			}
			
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);
		
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Save Pls");
				
			}
			});
		
		frame.setVisible(true);
		
		
	}
}
