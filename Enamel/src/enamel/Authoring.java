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
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Authoring {
	private static JFrame frame;
	private static JButton btnChooseScenario;
	private static JFileChooser file_chooser;
	private static JTextArea scenarioReader;
	private static JButton btnCreateScenario;
	private static JButton btnCreateAudioFiles;
	private static JButton btnTestScenario;
	private static JPanel pnlCreateScenarios;
	private static int buttonNum;
	private static int cellNum;
	
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
				btnTestScenario.setEnabled(true);
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
		frame.setBounds(100,100,661,696);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
		frame.setTitle("Authoring Application");

		file_chooser = new JFileChooser();
		file_chooser.setCurrentDirectory(new java.io.File("C:"));
		file_chooser.setDialogTitle("Open Scenario File");
		btnChooseScenario = new JButton("Import Scenario");
		btnChooseScenario.setLocation(new Point(100, 100));
		btnChooseScenario.setVisible(true);
		
		scenarioReader = new JTextArea();
		scenarioReader.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scenarioReader.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		scenarioReader.setWrapStyleWord(true);
		scenarioReader.setLineWrap(true);
		scenarioReader.setEditable(false);

		
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
				
				String gottenCells = JOptionPane.showInputDialog(null,"Enter Number of Braille Cells to Use","Enter Number of Braille Cells to Use",JOptionPane.QUESTION_MESSAGE);
				String gottenButtons = JOptionPane.showInputDialog(null,"Enter Number of Buttons to Use","Enter Number of Buttons to Use", JOptionPane.QUESTION_MESSAGE);
				
				int cells = Integer.parseInt(gottenCells);
				int buttons = Integer.parseInt(gottenButtons);
				
				scenarioReader.setText("");
				scenarioReader.append("Cell " + cells + "\n");
				scenarioReader.append("Button " + buttons + "\n");
				buttonNum = buttons;
				cellNum = cells;
				pnlCreateScenarios.setVisible(true);
				
			}
		});
		btnCreateScenario.setPreferredSize(new Dimension(95, 23));
		btnCreateScenario.setMinimumSize(new Dimension(95, 23));
		btnCreateScenario.setMaximumSize(new Dimension(95, 23));
		
		btnTestScenario = new JButton("Test Scenario");
		btnTestScenario.setEnabled(false);
		btnTestScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fileToCheck;
				
				if(!file_chooser.getSelectedFile().toString().isEmpty()) {
					fileToCheck = file_chooser.getSelectedFile().toString();
				}else {
					File currentFile = new File("FactoryScenarios/Scenario_temp.txt");
					try {
						if(!currentFile.exists()) {
							currentFile.createNewFile();
						}
						
						FileWriter fw = new FileWriter(currentFile);
						fw.write(scenarioReader.getText());
						fw.close();
						
					}catch(IOException e1) {
						e1.printStackTrace();
					}
					
					fileToCheck = "FactoryScenarios/Scenario_temp.txt";
				}
				
				Thread starterCodeThread = new Thread("Starter Code Thread") {
				    public void run(){    
				        ScenarioParser s = new ScenarioParser(true);
				        s.setScenarioFile(fileToCheck);
				    }
				};
				starterCodeThread.start();
			}
		});
		
		btnCreateAudioFiles = new JButton("Create Audio File");
		btnCreateAudioFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gottenFileName = JOptionPane.showInputDialog(null,"Enter Name of Audio File to Create","Enter Name of Audio File to Create",JOptionPane.QUESTION_MESSAGE);
				String gottenDuration = JOptionPane.showInputDialog(null,"Enter The Duration of the Audio File in Seconds","Enter The Duration of the Audio File in Seconds",JOptionPane.QUESTION_MESSAGE);
				
				
				
				
				
				if(gottenFileName == null || gottenDuration == null) {
					// Do Nothing
				}else {
					long duration = Long.parseLong(gottenDuration) * 1000;
					
					AudioRecorder recorder = new AudioRecorder(gottenFileName,duration);
				int recordConfirm = JOptionPane.showConfirmDialog(null, "Recording Will Start When You Press Yes. If No Is Pressed Recording Will Cancel","Recording Will Start When You Press Yes. If No Is Pressed Recording Will Cancel",JOptionPane.YES_NO_OPTION);
				
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
		
		pnlCreateScenarios = new JPanel();
		pnlCreateScenarios.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnTestScenario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnChooseScenario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnCreateScenario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
								.addComponent(btnCreateAudioFiles, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlCreateScenarios, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scenarioReader))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scenarioReader, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCreateScenario, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnChooseScenario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(btnTestScenario, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCreateAudioFiles, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(pnlCreateScenarios, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)))
					.addGap(0))
		);
		pnlCreateScenarios.setLayout(null);
		
		JLabel lblScenarioFeatures = new JLabel("Scenario Features");
		lblScenarioFeatures.setHorizontalAlignment(SwingConstants.CENTER);
		lblScenarioFeatures.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblScenarioFeatures.setBounds(0, 0, 197, 29);
		pnlCreateScenarios.add(lblScenarioFeatures);
		
		JButton btnAddTexttospeech = new JButton("Add Text-to-Speech");
		btnAddTexttospeech.setBounds(0, 25, 184, 23);
		pnlCreateScenarios.add(btnAddTexttospeech);
		
		JButton btnAddSoundFile = new JButton("Add Sound File");
		btnAddSoundFile.setBounds(0, 51, 184, 23);
		pnlCreateScenarios.add(btnAddSoundFile);
		
		JButton btnAddPause = new JButton("Add Pause");
		btnAddPause.setBounds(0, 77, 184, 23);
		pnlCreateScenarios.add(btnAddPause);
		
		JButton btnDisplayBrailleString = new JButton("Display Braille String");
		btnDisplayBrailleString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String brailleString = JOptionPane.showInputDialog(null,"Enter the String to be Displayed In Braille.","Enter the String to be Displayed in Braille.",JOptionPane.QUESTION_MESSAGE);
				
				if(brailleString != null) {
				scenarioReader.append("/~disp-string:" + brailleString + "\n");
				}
			}
		});
		btnDisplayBrailleString.setBounds(0, 102, 184, 23);
		pnlCreateScenarios.add(btnDisplayBrailleString);
		
		JButton btnAddRepeatString = new JButton("Add Repeat String");
		btnAddRepeatString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textToRepeat = JOptionPane.showInputDialog(null,"Enter the Text to Repeat.","Enter the Text to Repeat.",JOptionPane.QUESTION_MESSAGE);
				
				if(textToRepeat != null) {
				scenarioReader.append("/~repeat" + "\n");
				scenarioReader.append(textToRepeat  + "\n");
				scenarioReader.append("/~endrepeat" + "\n");
				}
			}
		});
		btnAddRepeatString.setBounds(0, 127, 184, 23);
		pnlCreateScenarios.add(btnAddRepeatString);
		
		JButton btnAddRepeatButton = new JButton("Add Repeat Button");
		btnAddRepeatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonIndex = JOptionPane.showInputDialog(null,"Enter the Button Number You Want Repeated.","Enter the Button Number You Want Repeated.",JOptionPane.QUESTION_MESSAGE);
				
				if(buttonIndex != null && Integer.parseInt(buttonIndex) > 0 && Integer.parseInt(buttonIndex) <= buttonNum) {
				scenarioReader.append("/~repeat-button:" + (Integer.parseInt(buttonIndex) -1) + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
				}
			}
		});
		btnAddRepeatButton.setBounds(0, 152, 184, 23);
		pnlCreateScenarios.add(btnAddRepeatButton);
		
		JButton btnAddSkipButton = new JButton("Skip Button");
		btnAddSkipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonIndex = JOptionPane.showInputDialog(null,"Enter the Button Number You Want Skipped.","Enter the Button Number You Want Skipped.",JOptionPane.QUESTION_MESSAGE);
				String identifier = JOptionPane.showInputDialog(null,"Where Do You Want to Skip to? Enter an Identifier.", "Where Do You Want to Skip to? Enter an Indentifier.",JOptionPane.QUESTION_MESSAGE);
				
				
				if(buttonIndex != null && Integer.parseInt(buttonIndex) > 0 && Integer.parseInt(buttonIndex) <= buttonNum) {
				scenarioReader.append("/~skip-button:" + (Integer.parseInt(buttonIndex) -1) + " " + identifier + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
				}
			}
		});
		btnAddSkipButton.setBounds(0, 177, 100, 23);
		pnlCreateScenarios.add(btnAddSkipButton);
		
		JButton btnAddUserInput = new JButton("Add User Input");
		btnAddUserInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scenarioReader.append("/~user-input" + "\n");
				
			}
		});
		btnAddUserInput.setBounds(0, 203, 184, 23);
		pnlCreateScenarios.add(btnAddUserInput);
		
		JButton btnAddResetButtons = new JButton("Reset Buttons");
		btnAddResetButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scenarioReader.append("/~reset-buttons" + "\n");
			}
		});
		btnAddResetButtons.setBounds(0, 228, 184, 23);
		pnlCreateScenarios.add(btnAddResetButtons);
		
		JButton btnSkipToClause = new JButton("Add Skip");
		btnSkipToClause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifier = JOptionPane.showInputDialog(null,"Where Do You Want to Skip to? Enter an Identifier.","Where Do You Want to Skip to? Enter an Identifier.",JOptionPane.QUESTION_MESSAGE);
				
				if(identifier != null) {
				scenarioReader.append("/~skip:" + identifier + "\n");
				}
			}
		});
		btnSkipToClause.setBounds(0, 253, 100, 23);
		pnlCreateScenarios.add(btnSkipToClause);
		
		JButton btnClearAllCells = new JButton("Clear All Cells");
		btnClearAllCells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scenarioReader.append("/~disp-clearAll" + "\n");
			}
		});
		btnClearAllCells.setBounds(0, 279, 184, 23);
		pnlCreateScenarios.add(btnClearAllCells);
		
		JButton btnClearSpecificCell = new JButton("Clear Specific Cell");
		btnClearSpecificCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null,"Enter the Cell Number You Want Cleared.","Enter the Cell Number You Want Cleared.",JOptionPane.QUESTION_MESSAGE);
				
				if(cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum) {
				scenarioReader.append("/~disp-clear-cell:" + (Integer.parseInt(cellIndex) -1) + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
				}
			}
		});
		btnClearSpecificCell.setBounds(0, 305, 184, 23);
		pnlCreateScenarios.add(btnClearSpecificCell);
		
		JButton btnSetSpecificPin = new JButton("Set Specific Pin");
		btnSetSpecificPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null,"Enter the Cell Number You Want Displayed.","Enter the Cell Number You Want Displayed.",JOptionPane.QUESTION_MESSAGE);
				
				String sequence = JOptionPane.showInputDialog(null,"Enter the 8-Character Sequence Consisting of 0's and 1's.","Enter the  8-Character Sequence Consisting of 0's and 1's.",JOptionPane.QUESTION_MESSAGE);
				
				
				
				if(cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum && sequence.length() == 8 && sequence.matches("[01]+")) {
				scenarioReader.append("/~disp-cell-pins:" + (Integer.parseInt(cellIndex) -1) + " " + sequence + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists and the 8-Character Sequence is Valid.");
				}
			}
		});
		btnSetSpecificPin.setBounds(0, 330, 184, 23);
		pnlCreateScenarios.add(btnSetSpecificPin);
		
		JButton btnDisplayCellCharacter = new JButton("Display Cell Character");
		btnDisplayCellCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null,"Enter the Cell Number You Want Displayed.","Enter the Cell Number You Want Displayed.",JOptionPane.QUESTION_MESSAGE);
				
				String alphabet = JOptionPane.showInputDialog(null,"Enter a Valid English Alphabet.","Enter a Valid English Alphabet.",JOptionPane.QUESTION_MESSAGE);
				char c = alphabet.charAt(0);
				
				if(cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum && alphabet.length() == 1 && Character.isLetter(c)) {
				scenarioReader.append("/~disp-cell-char:" + (Integer.parseInt(cellIndex) -1) + " " + alphabet + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists and a Valid English Alphabet is Selected.");
				}
			}
		});
		btnDisplayCellCharacter.setBounds(0, 355, 184, 23);
		pnlCreateScenarios.add(btnDisplayCellCharacter);
		
		JButton btnRaiseOnePin = new JButton("Raise One Pin");
		btnRaiseOnePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null,"Enter the Cell Number You Want Raised.","Enter the Cell Number You Want Raised.",JOptionPane.QUESTION_MESSAGE);
				
				String pinIndex = JOptionPane.showInputDialog(null,"Enter the Pin Number to Raise.","Enter the  Pin Number to Raise.",JOptionPane.QUESTION_MESSAGE);
				
				
				
				if(cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum && Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
				scenarioReader.append("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) -1) + " " + (Integer.parseInt(pinIndex) -1) + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists and the Pin Number is between 1 and 8.");
				}
			}
		});
		btnRaiseOnePin.setBounds(0, 380, 184, 23);
		pnlCreateScenarios.add(btnRaiseOnePin);
		
		JButton btnLowerOnePin = new JButton("Lower One Pin");
		btnLowerOnePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null,"Enter the Cell Number You Want Lowered.","Enter the Cell Number You Want Lowered.",JOptionPane.QUESTION_MESSAGE);
				
				String pinIndex = JOptionPane.showInputDialog(null,"Enter the Pin Number to Lower.","Enter the  Pin Number to Lower.",JOptionPane.QUESTION_MESSAGE);
				
				
				
				if(cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum && Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
				scenarioReader.append("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) -1) + " " + (Integer.parseInt(pinIndex) -1) + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists and the Pin Number is between 1 and 8.");
				}
			}
		});
		btnLowerOnePin.setBounds(0, 404, 184, 23);
		pnlCreateScenarios.add(btnLowerOnePin);
		
		JButton btnSkipTo = new JButton("Skip To");
		btnSkipTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifier = JOptionPane.showInputDialog(null,"Enter the Identifier You Previously Used.","Enter the Identifier You Previously Used.",JOptionPane.QUESTION_MESSAGE);
				
				if(identifier != null) {
				scenarioReader.append("/~" + identifier + "\n");
				}
			}
		});
		btnSkipTo.setBounds(109, 177, 75, 23);
		pnlCreateScenarios.add(btnSkipTo);
		
		JButton btnSkipTo_1 = new JButton("Skip To");
		btnSkipTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifier = JOptionPane.showInputDialog(null,"Enter Identifier Where to Skip to.","Enter Identifier Where to Skip to.",JOptionPane.QUESTION_MESSAGE);
				
				if(identifier != null) {
				scenarioReader.append("/~" + identifier + "\n");
				}
			}
		});
		btnSkipTo_1.setBounds(109, 253, 75, 23);
		pnlCreateScenarios.add(btnSkipTo_1);
		btnAddPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pause = JOptionPane.showInputDialog(null,"Enter the Number of Seconds to Pause the Scenario for.","Enter the Number of Seconds to Pause the Scenario for.",JOptionPane.QUESTION_MESSAGE);
				
				if(pause != null && Integer.parseInt(pause) >= 0) {
				scenarioReader.append("/~pause:" + pause + "\n");
				}else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pause Time Is Greater Than 0 Seconds.");
				}
			}
		});
		btnAddSoundFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = "";// = JOptionPane.showInputDialog(null,"Enter the Text You Want Said Via Text-To-Speech.","Enter the Text You Want Said Via Text-To-Speech",JOptionPane.QUESTION_MESSAGE);
				file_chooser.setCurrentDirectory(new File("FactoryScenarios/AudioFiles/"));
				if(file_chooser.showOpenDialog(btnAddSoundFile) == JFileChooser.APPROVE_OPTION) {
					fileName = file_chooser.getSelectedFile().getName();
					scenarioReader.append("/~sound:FactoryScenarios/AudioFiles/" + fileName + "\n");
				}
				
				
			}
		});
		btnAddTexttospeech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textToSpeech = JOptionPane.showInputDialog(null,"Enter the Text You Want Said Via Text-To-Speech.","Enter the Text You Want Said Via Text-To-Speech",JOptionPane.QUESTION_MESSAGE);
				
				if(textToSpeech != null) {
				scenarioReader.append(textToSpeech + "\n");
				}
			}
		});
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
		
		
		btnChooseScenario.getAccessibleContext().setAccessibleName("Choose a Scenario");
		btnChooseScenario.getAccessibleContext().setAccessibleDescription("Choose an existing scenario to edit.");
		
		btnCreateScenario.getAccessibleContext().setAccessibleName("Create a New Scenario");
		btnCreateScenario.getAccessibleContext().setAccessibleDescription("Create a totally new scenario with new features.");
		
		btnCreateAudioFiles.getAccessibleContext().setAccessibleName("Create an Audio File");
		btnCreateAudioFiles.getAccessibleContext().setAccessibleDescription("Create an audio file to use with your script.");
		
		btnTestScenario.getAccessibleContext().setAccessibleName("Test the Scenario Open");
		btnTestScenario.getAccessibleContext().setAccessibleDescription("Test the current open scenario file with the visual player");
		
		frame.setVisible(true);
		
		
	}
}
