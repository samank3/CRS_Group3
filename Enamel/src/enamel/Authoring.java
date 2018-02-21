package enamel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Point;
import java.awt.Dimension;

public class Authoring {
	private static JFrame frame;
	private static JButton btnChooseScenario;
	private static JFileChooser file_chooser;
	private static JTextArea scenarioReader;
	private static JButton btnCreateScenario;
	private static JButton btnCreateAudioFiles;
	private static JButton btnTestScenario;
	
	private static void openFileDialog() {
		if(file_chooser.showOpenDialog(btnChooseScenario) == JFileChooser.APPROVE_OPTION) {
			
			StringBuffer buff = new StringBuffer();
			scenarioReader.setText("");
			int lineCounter = 0;
			try {
				Scanner scanner = new Scanner(file_chooser.getSelectedFile());
				//ScenarioParser s = new ScenarioParser(true);
				//s.setScenarioFile(file_chooser.getSelectedFile().toString());
				
				while(scanner.hasNextLine()) {
					//Check if first two lines follow the specific plan.
					String line = scanner.nextLine();
					if(lineCounter == 0) {
						if(!line.startsWith("Cell")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							//frame.setTitle("Authoring Application - Invalid Format");
							JOptionPane.showMessageDialog(null, "Please select a valid scenario file.");
							scanner.close();
							return;
						}
					}
					if(lineCounter == 1) {
						if(!line.startsWith("Button")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							//frame.setTitle("Authoring Application - Invalid Format");
							JOptionPane.showMessageDialog(null, "Please select a valid scenario file.");
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
				//frame.setTitle("Authoring Application - " + file_chooser.getSelectedFile());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
				
			
		}else {
			System.out.println("Error Opening File");
			//frame.setTitle("Authoring Application - Error Selecting File");
			JOptionPane.showMessageDialog(null, "Error Opening File");
		}
	}
	
	private static void saveFileDialog() {
		if(file_chooser.showSaveDialog(btnChooseScenario) == JFileChooser.APPROVE_OPTION) {
			File currentFile = file_chooser.getSelectedFile();
			
			try {
				if(!currentFile.exists()) {
					currentFile.createNewFile();
				}
				
				FileWriter fw = new FileWriter(currentFile);
				fw.write(scenarioReader.getText());
				fw.close();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("Error Saving File");
			//frame.setTitle("Authoring Application - Error Selecting File");
			JOptionPane.showMessageDialog(null, "Error Saving File");
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
		btnChooseScenario = new JButton("Edit Scenario");
		btnChooseScenario.setLocation(new Point(100, 100));
		btnChooseScenario.setVisible(true);
		
		scenarioReader = new JTextArea();
		scenarioReader.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scenarioReader.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		scenarioReader.setWrapStyleWord(true);
		scenarioReader.setLineWrap(true);
		scenarioReader.setEditable(false);

		btnChooseScenario.getAccessibleContext().setAccessibleName("Choose a Scenario");
		btnChooseScenario.getAccessibleContext().setAccessibleDescription("Choose an existing scenario to edit.");
		
		btnCreateScenario.getAccessibleContext().setAccessibleName("Create a New Scenario");
		btnCreateScenario.getAccessibleContext().setAccessibleDescription("Create a totally new scenario with new features.");
		
		btnCreateAudioFiles.getAccessibleContext().setAccessibleName("Create an Audio File");
		btnCreateAudioFiles.getAccessibleContext().setAccessibleDescription("Create an audio file to use with your script.");
		
		btnChooseScenario.getAccessibleContext().setAccessibleName("Test the Scenario Open");
		btnChooseScenario.getAccessibleContext().setAccessibleDescription("Test the current open scenario file with the visual player");
		
		//JScrollPane scroll = new JScrollPane(scenarioReader);

		
		
		btnChooseScenario.addActionListener(new ActionListener() {

			
			
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				openFileDialog();
			}
			
		});
		
		btnCreateScenario = new JButton("Create Scenario");
		btnCreateScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreateScenario.setPreferredSize(new Dimension(95, 23));
		btnCreateScenario.setMinimumSize(new Dimension(95, 23));
		btnCreateScenario.setMaximumSize(new Dimension(95, 23));
		
		btnTestScenario = new JButton("Test Scenario");
		btnTestScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCreateAudioFiles = new JButton("Create Audio File");
		btnCreateAudioFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gottenFileName = JOptionPane.showInputDialog(null,"Enter Name of Audio File to Create","Create Audio File",JOptionPane.QUESTION_MESSAGE);
				String gottenDuration = JOptionPane.showInputDialog(null,"Enter The Duration of the Audio File in Seconds","Create Audio File",JOptionPane.QUESTION_MESSAGE);
				
				
				
				
				
				if(gottenFileName == null || gottenDuration == null) {
					// Do Nothing
				}else {
					long duration = Long.parseLong(gottenDuration) * 1000;
					
					AudioRecorder recorder = new AudioRecorder(gottenFileName,duration);
				int recordConfirm = JOptionPane.showConfirmDialog(null, "Recording Will Start When You Press Yes. If No Is Pressed Recording Will Cancel","Confirm Recording",JOptionPane.YES_NO_OPTION);
				
				if(recordConfirm == 0) {
					//YES
					try {
						recorder.start();
					} catch (LineUnavailableException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Recording Cancelled");
				}
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreateAudioFiles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnTestScenario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCreateScenario, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
							.addComponent(btnChooseScenario, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
					.addGap(18)
					.addComponent(scenarioReader, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scenarioReader, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCreateScenario, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnChooseScenario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(btnTestScenario, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreateAudioFiles, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		frame.getContentPane().setLayout(groupLayout);
		scenarioReader.setVisible(true);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);
		
		mntmOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFileDialog();
			}
			
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);
		
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFileDialog();
				
			}
			});
		
		frame.setVisible(true);
		
		
	}
}
