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
	public static JButton btnChooseScenario;
	public static  JFileChooser file_chooser;
	public static JFileChooser sound_chooser;
	public static JTextArea scenarioReader;
	public static JButton btnCreateScenario;
	public static JButton btnCreateAudioFiles;
	public static JButton btnTestScenario;
	public static JButton btnAddTexttospeech;
	public static JButton btnAddSoundFile;
	public static JButton btnAddPause;
	public static JButton btnDisplayBrailleString;
	public static JButton btnAddRepeatString;
	public static JButton btnAddRepeatButton;
	public static JButton btnAddSkipButton;
	public static JButton btnAddUserInput;
	public static JButton btnAddResetButtons;
	public static JButton btnSkipToClause;
	public static JButton btnClearAllCells;
	public static JButton btnClearSpecificCell;
	public static JButton btnSetSpecificPin;
	public static JButton btnDisplayCellCharacter;
	public static JButton btnRaiseOnePin;
	public static JButton btnLowerOnePin;
	public static JButton btnSkipTo;
	public static JButton btnSkipTo_1;
	private static JPanel pnlCreateScenarios;
	private static int buttonNum;
	private static int cellNum;
	public static boolean testResult;

	
	public static boolean openFileDialog() {
		file_chooser.setDialogTitle("Open Scenario File");
		if (file_chooser.showOpenDialog(btnChooseScenario) == JFileChooser.APPROVE_OPTION) {

			StringBuffer buff = new StringBuffer();
			scenarioReader.setText("");
			int lineCounter = 0;
			try {
				Scanner scanner = new Scanner(file_chooser.getSelectedFile());
				// ScenarioParser s = new ScenarioParser(true);
				// s.setScenarioFile(file_chooser.getSelectedFile().toString());

				while (scanner.hasNextLine()) {
					// Check if first two lines follow the specific plan.
					String line = scanner.nextLine();
					if (lineCounter == 0) {
						if (!line.startsWith("Cell")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							// frame.setTitle("Authoring Application - Invalid Format");
							JOptionPane.showMessageDialog(null, "Please select a valid scenario file.");
							scanner.close();
							return false;
						}
					}
					if (lineCounter == 1) {
						if (!line.startsWith("Button")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							// frame.setTitle("Authoring Application - Invalid Format");
							JOptionPane.showMessageDialog(null, "Please select a valid scenario file.");
							scanner.close();
							return false;
						}
					}
					lineCounter += 1;
					buff.append(line + "\n");
				}
				scanner.close();
				// System.out.println(buff.toString());
				scenarioReader.setText(buff.toString());
				btnTestScenario.setEnabled(true);
				System.out.println("File: " + file_chooser.getSelectedFile() + " has been imported");
				// frame.setTitle("Authoring Application - " + file_chooser.getSelectedFile());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Error Opening File");
			// frame.setTitle("Authoring Application - Error Selecting File");
			JOptionPane.showMessageDialog(null, "Error Opening File");
		}

		return true;
	}

	public static void testCreateScenarios() {
		scenarioReader.setText("");
		scenarioReader.append("Cell " + 1 + "\n");
		scenarioReader.append("Button " + 4 + "\n");
	}
	
	public static void addUserInputString() {
		scenarioReader.append("/~user-input" + "\n");
	}
	
	public static boolean saveFileDialog() {
		file_chooser.setDialogTitle("Save Scenario File");
		if (file_chooser.showSaveDialog(btnChooseScenario) == JFileChooser.APPROVE_OPTION) {
			File currentFile = file_chooser.getSelectedFile();

			try {
				if (!currentFile.exists()) {
					currentFile.createNewFile();
				}

				FileWriter fw = new FileWriter(currentFile);
				fw.write(scenarioReader.getText());
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Error Saving File");
			// frame.setTitle("Authoring Application - Error Selecting File");
			JOptionPane.showMessageDialog(null, "Error Saving File");
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setBounds(100, 100, 661, 696);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
		frame.setTitle("Authoring Application");

		file_chooser = new JFileChooser();
		sound_chooser = new JFileChooser();
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

		// JScrollPane scroll = new JScrollPane(scenarioReader);

		btnChooseScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFileDialog();
			}

		});

		btnCreateScenario = new JButton("Create Scenario");
		btnCreateScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				file_chooser.setSelectedFile(null);
				String gottenCells = JOptionPane.showInputDialog(null, "Enter Number of Braille Cells to Use",
						"Enter Number of Braille Cells to Use", JOptionPane.QUESTION_MESSAGE);
				String gottenButtons = JOptionPane.showInputDialog(null, "Enter Number of Buttons to Use",
						"Enter Number of Buttons to Use", JOptionPane.QUESTION_MESSAGE);

				int cells = Integer.parseInt(gottenCells);
				int buttons = Integer.parseInt(gottenButtons);

				scenarioReader.setText("");
				scenarioReader.append("Cell " + cells + "\n");
				scenarioReader.append("Button " + buttons + "\n");
				buttonNum = buttons;
				cellNum = cells;
				pnlCreateScenarios.setVisible(true);
				btnTestScenario.setEnabled(true);
				testResult = true;

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
				// System.out.println(file_chooser.getSelectedFile().toString());
				if (file_chooser.getSelectedFile() != null && !file_chooser.getSelectedFile().toString().isEmpty()) {
					fileToCheck = file_chooser.getSelectedFile().toString();
				} else {
					File currentFile = new File("FactoryScenarios/Scenario_temp.txt");
					try {
						if (!currentFile.exists()) {
							currentFile.createNewFile();
						}

						FileWriter fw = new FileWriter(currentFile);
						fw.write(scenarioReader.getText());
						fw.close();

					} catch (IOException e1) {
						e1.printStackTrace();
					}

					fileToCheck = "FactoryScenarios/Scenario_temp.txt";
				}

				Thread starterCodeThread = new Thread("Starter Code Thread") {
					public void run() {
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
				String gottenFileName = JOptionPane.showInputDialog(null, "Enter Name of Audio File to Create",
						"Enter Name of Audio File to Create", JOptionPane.QUESTION_MESSAGE);
				String gottenDuration = JOptionPane.showInputDialog(null,
						"Enter The Duration of the Audio File in Seconds",
						"Enter The Duration of the Audio File in Seconds", JOptionPane.QUESTION_MESSAGE);

				if (gottenFileName == null || gottenDuration == null) {
					// Do Nothing
				} else {
					long duration = Long.parseLong(gottenDuration) * 1000;

					AudioRecorder recorder = new AudioRecorder(gottenFileName, duration);
					int recordConfirm = JOptionPane.showConfirmDialog(null,
							"Recording Will Start When You Press Yes. If No Is Pressed Recording Will Cancel",
							"Recording Will Start When You Press Yes. If No Is Pressed Recording Will Cancel",
							JOptionPane.YES_NO_OPTION);

					if (recordConfirm == 0) {
						// YES
						try {
							recorder.start();
						} catch (LineUnavailableException | InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Recording Cancelled");
					}
				}
			}
		});

		pnlCreateScenarios = new JPanel();
		pnlCreateScenarios.setVisible(false);

		JButton btnExportScenario = new JButton("Export Scenario");
		btnExportScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileDialog();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlCreateScenarios, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 197,
										Short.MAX_VALUE)
								.addComponent(btnCreateScenario, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 197,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCreateAudioFiles, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(btnTestScenario, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(btnExportScenario, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(btnChooseScenario, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scenarioReader, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(3)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(scenarioReader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 630,
												Short.MAX_VALUE)
										.addGroup(Alignment.LEADING,
												groupLayout.createSequentialGroup()
														.addComponent(btnCreateScenario, GroupLayout.PREFERRED_SIZE, 31,
																GroupLayout.PREFERRED_SIZE)
														.addGap(9)
														.addComponent(btnChooseScenario, GroupLayout.PREFERRED_SIZE, 35,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnExportScenario, GroupLayout.PREFERRED_SIZE, 32,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnTestScenario, GroupLayout.PREFERRED_SIZE, 33,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnCreateAudioFiles, GroupLayout.PREFERRED_SIZE,
																33, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(pnlCreateScenarios, GroupLayout.PREFERRED_SIZE,
																428, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		pnlCreateScenarios.setLayout(null);

		JLabel lblScenarioFeatures = new JLabel("Scenario Features");
		lblScenarioFeatures.setHorizontalAlignment(SwingConstants.CENTER);
		lblScenarioFeatures.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblScenarioFeatures.setBounds(0, 0, 197, 29);
		pnlCreateScenarios.add(lblScenarioFeatures);

		btnAddTexttospeech = new JButton("Add Text-to-Speech");
		btnAddTexttospeech.setBounds(0, 25, 197, 23);
		pnlCreateScenarios.add(btnAddTexttospeech);

		btnAddSoundFile = new JButton("Add Sound File");
		btnAddSoundFile.setBounds(0, 51, 197, 23);
		pnlCreateScenarios.add(btnAddSoundFile);

		btnAddPause = new JButton("Add Pause");
		btnAddPause.setBounds(0, 77, 197, 23);
		pnlCreateScenarios.add(btnAddPause);

		btnDisplayBrailleString = new JButton("Display Braille String");
		btnDisplayBrailleString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String brailleString = JOptionPane.showInputDialog(null, "Enter the String to be Displayed In Braille",
						"Enter the String to be Displayed in Braille", JOptionPane.QUESTION_MESSAGE);

				if (brailleString != null) {
					scenarioReader.append("/~disp-string:" + brailleString + "\n");
				}
			}
		});
		btnDisplayBrailleString.setBounds(0, 102, 197, 23);
		pnlCreateScenarios.add(btnDisplayBrailleString);

		btnAddRepeatString = new JButton("Add Repeat String");
		btnAddRepeatString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textToRepeat = JOptionPane.showInputDialog(null, "Enter the Text to Repeat",
						"Enter the Text to Repeat.", JOptionPane.QUESTION_MESSAGE);

				if (textToRepeat != null) {
					scenarioReader.append("/~repeat" + "\n");
					scenarioReader.append(textToRepeat + "\n");
					scenarioReader.append("/~endrepeat" + "\n");
				}
			}
		});
		btnAddRepeatString.setBounds(0, 127, 197, 23);
		pnlCreateScenarios.add(btnAddRepeatString);

		btnAddRepeatButton = new JButton("Add Repeat Button");
		btnAddRepeatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Repeated",
						"Enter the Button Number You Want Repeated.", JOptionPane.QUESTION_MESSAGE);

				if (buttonIndex != null && Integer.parseInt(buttonIndex) > 0
						&& Integer.parseInt(buttonIndex) <= buttonNum) {
					scenarioReader.append("/~repeat-button:" + (Integer.parseInt(buttonIndex) - 1) + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Button Number Is Valid And Exists");
				}
			}
		});
		btnAddRepeatButton.setBounds(0, 152, 197, 23);
		pnlCreateScenarios.add(btnAddRepeatButton);

		btnAddSkipButton = new JButton("Skip Button");
		btnAddSkipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Skipped.",
						"Enter the Button Number You Want Skipped.", JOptionPane.QUESTION_MESSAGE);
				String identifier = JOptionPane.showInputDialog(null,
						"Where Do You Want to Skip to? Enter an Identifier.",
						"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE);

				if (buttonIndex != null && Integer.parseInt(buttonIndex) > 0
						&& Integer.parseInt(buttonIndex) <= buttonNum) {
					scenarioReader
							.append("/~skip-button:" + (Integer.parseInt(buttonIndex) - 1) + " " + identifier + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Button Number Is Valid And Exists");
				}
			}
		});
		btnAddSkipButton.setBounds(0, 177, 100, 23);
		pnlCreateScenarios.add(btnAddSkipButton);

		btnAddUserInput = new JButton("Add User Input");
		btnAddUserInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUserInputString();
				//scenarioReader.append("/~user-input" + "\n");

			}
		});
		btnAddUserInput.setBounds(0, 203, 197, 23);
		pnlCreateScenarios.add(btnAddUserInput);

		btnAddResetButtons = new JButton("Reset Buttons");
		btnAddResetButtons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scenarioReader.append("/~reset-buttons" + "\n");
			}
		});
		btnAddResetButtons.setBounds(0, 228, 197, 23);
		pnlCreateScenarios.add(btnAddResetButtons);

		btnSkipToClause = new JButton("Add Skip");
		btnSkipToClause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifier = JOptionPane.showInputDialog(null,
						"Where Do You Want to Skip to? Enter an Identifier.",
						"Where Do You Want to Skip to? Enter an Identifier.", JOptionPane.QUESTION_MESSAGE);

				if (identifier != null) {
					scenarioReader.append("/~skip:" + identifier + "\n");
				}
			}
		});
		btnSkipToClause.setBounds(0, 253, 100, 23);
		pnlCreateScenarios.add(btnSkipToClause);

		btnClearAllCells = new JButton("Clear All Cells");
		btnClearAllCells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scenarioReader.append("/~disp-clearAll" + "\n");
			}
		});
		btnClearAllCells.setBounds(0, 279, 197, 23);
		pnlCreateScenarios.add(btnClearAllCells);

		btnClearSpecificCell = new JButton("Clear Specific Cell");
		btnClearSpecificCell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Cleared.",
						"Enter the Cell Number You Want Cleared.", JOptionPane.QUESTION_MESSAGE);

				if (cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum) {
					scenarioReader.append("/~disp-clear-cell:" + (Integer.parseInt(cellIndex) - 1) + "\n");
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
				}
			}
		});
		btnClearSpecificCell.setBounds(0, 305, 197, 23);
		pnlCreateScenarios.add(btnClearSpecificCell);

		btnSetSpecificPin = new JButton("Set Specific Pin");
		btnSetSpecificPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
						"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE);

				String sequence = JOptionPane.showInputDialog(null,
						"Enter the 8-Character Sequence Consisting of 0's and 1's.",
						"Enter the  8-Character Sequence Consisting of 0's and 1's.", JOptionPane.QUESTION_MESSAGE);

				if (cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum
						&& sequence.length() == 8 && sequence.matches("[01]+")) {
					scenarioReader
							.append("/~disp-cell-pins:" + (Integer.parseInt(cellIndex) - 1) + " " + sequence + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Cell Number Is Valid And Exists and the 8-Character Sequence is Valid.");
				}
			}
		});
		btnSetSpecificPin.setBounds(0, 330, 197, 23);
		pnlCreateScenarios.add(btnSetSpecificPin);

		btnDisplayCellCharacter = new JButton("Display Cell Character");
		btnDisplayCellCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
						"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE);

				String alphabet = JOptionPane.showInputDialog(null, "Enter a Valid English Alphabet.",
						"Enter a Valid English Alphabet.", JOptionPane.QUESTION_MESSAGE);
				char c = alphabet.charAt(0);

				if (cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum
						&& alphabet.length() == 1 && Character.isLetter(c)) {
					scenarioReader
							.append("/~disp-cell-char:" + (Integer.parseInt(cellIndex) - 1) + " " + alphabet + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Cell Number Is Valid And Exists and a Valid English Alphabet is Selected.");
				}
			}
		});
		btnDisplayCellCharacter.setBounds(0, 355, 197, 23);
		pnlCreateScenarios.add(btnDisplayCellCharacter);

		btnRaiseOnePin = new JButton("Raise One Pin");
		btnRaiseOnePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Raised.",
						"Enter the Cell Number You Want Raised.", JOptionPane.QUESTION_MESSAGE);

				String pinIndex = JOptionPane.showInputDialog(null, "Enter the Pin Number to Raise.",
						"Enter the  Pin Number to Raise.", JOptionPane.QUESTION_MESSAGE);

				if (cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum
						&& Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
					scenarioReader.append("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) - 1) + " "
							+ (Integer.parseInt(pinIndex) - 1) + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Cell Number Is Valid And Exists and the Pin Number is between 1 and 8.");
				}
			}
		});
		btnRaiseOnePin.setBounds(0, 380, 197, 23);
		pnlCreateScenarios.add(btnRaiseOnePin);

		btnLowerOnePin = new JButton("Lower One Pin");
		btnLowerOnePin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Lowered.",
						"Enter the Cell Number You Want Lowered.", JOptionPane.QUESTION_MESSAGE);

				String pinIndex = JOptionPane.showInputDialog(null, "Enter the Pin Number to Lower.",
						"Enter the  Pin Number to Lower.", JOptionPane.QUESTION_MESSAGE);

				if (cellIndex != null && Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= cellNum
						&& Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
					scenarioReader.append("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) - 1) + " "
							+ (Integer.parseInt(pinIndex) - 1) + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Cell Number Is Valid And Exists and the Pin Number is between 1 and 8.");
				}
			}
		});
		btnLowerOnePin.setBounds(0, 404, 197, 23);
		pnlCreateScenarios.add(btnLowerOnePin);

		btnSkipTo = new JButton("Skip To");
		btnSkipTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifier = JOptionPane.showInputDialog(null, "Enter the Identifier You Previously Used.",
						"Enter the Identifier You Previously Used.", JOptionPane.QUESTION_MESSAGE);

				if (identifier != null) {
					scenarioReader.append("/~" + identifier + "\n");
				}
			}
		});
		btnSkipTo.setBounds(101, 177, 96, 23);
		pnlCreateScenarios.add(btnSkipTo);

		btnSkipTo_1 = new JButton("Skip To");
		btnSkipTo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identifier = JOptionPane.showInputDialog(null, "Enter Identifier Where to Skip to.",
						"Enter Identifier Where to Skip to.", JOptionPane.QUESTION_MESSAGE);

				if (identifier != null) {
					scenarioReader.append("/~" + identifier + "\n");
				}
			}
		});
		btnSkipTo_1.setBounds(101, 253, 96, 23);
		pnlCreateScenarios.add(btnSkipTo_1);
		btnAddPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pause = JOptionPane.showInputDialog(null,
						"Enter the Number of Seconds to Pause the Scenario for.",
						"Enter the Number of Seconds to Pause the Scenario for.", JOptionPane.QUESTION_MESSAGE);

				if (pause != null && Integer.parseInt(pause) >= 0) {
					scenarioReader.append("/~pause:" + pause + "\n");
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure Pause Time Is Greater Than 0 Seconds.");
				}
			}
		});
		btnAddSoundFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = "";// = JOptionPane.showInputDialog(null,"Enter the Text You Want Said Via
										// Text-To-Speech.","Enter the Text You Want Said Via
										// Text-To-Speech",JOptionPane.QUESTION_MESSAGE);
				sound_chooser.setCurrentDirectory(new File("FactoryScenarios/AudioFiles/"));
				if (sound_chooser.showOpenDialog(btnAddSoundFile) == JFileChooser.APPROVE_OPTION) {
					fileName = sound_chooser.getSelectedFile().getName().toString();
					scenarioReader.append("/~sound:" + fileName + "\n");
				}

			}
		});
		btnAddTexttospeech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textToSpeech = JOptionPane.showInputDialog(null,
						"Enter the Text You Want Said Via Text-To-Speech.",
						"Enter the Text You Want Said Via Text-To-Speech", JOptionPane.QUESTION_MESSAGE);

				if (textToSpeech != null) {
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
		btnChooseScenario.getAccessibleContext().setAccessibleDescription("Choose an existing scenario to edit");

		btnCreateScenario.getAccessibleContext().setAccessibleName("Create a New Scenario");
		btnCreateScenario.getAccessibleContext()
				.setAccessibleDescription("Create a totally new scenario with new features.");

		btnCreateAudioFiles.getAccessibleContext().setAccessibleName("Create an Audio File");
		btnCreateAudioFiles.getAccessibleContext()
				.setAccessibleDescription("Create an audio file to use with your script");

		btnTestScenario.getAccessibleContext().setAccessibleName("Test the Scenario Open");
		btnTestScenario.getAccessibleContext()
				.setAccessibleDescription("Test the current open scenario file with the visual player");

		btnExportScenario.getAccessibleContext().setAccessibleName("Export Scenario");
		btnExportScenario.getAccessibleContext().setAccessibleDescription("Export Scenario into a text file");

		btnAddTexttospeech.getAccessibleContext().setAccessibleName("Add Text to Speech");
		btnAddTexttospeech.getAccessibleContext()
				.setAccessibleDescription("Add a Text to Speech comment that will be spoken by the computer");

		btnAddSoundFile.getAccessibleContext().setAccessibleName("Add Sound File");
		btnAddSoundFile.getAccessibleContext().setAccessibleDescription(
				"Add a Sound file that is played when the scenario file is executed at this spot.");

		btnAddPause.getAccessibleContext().setAccessibleName("Add a Pause");
		btnAddPause.getAccessibleContext()
				.setAccessibleDescription("Add a pause to your scenario file that lasts a certain number of seconds.");

		btnDisplayBrailleString.getAccessibleContext().setAccessibleName("Display Braille String");
		btnDisplayBrailleString.getAccessibleContext()
				.setAccessibleDescription("Displays a string that is displayed in braille.");

		btnAddRepeatString.getAccessibleContext().setAccessibleName("Add Repeat String");
		btnAddRepeatString.getAccessibleContext().setAccessibleDescription("Adds a String that is to be repeated ");

		btnAddRepeatButton.getAccessibleContext().setAccessibleName("Add Repeat Button");
		btnAddRepeatButton.getAccessibleContext().setAccessibleDescription("Choose an existing button to repeat");

		btnAddSkipButton.getAccessibleContext().setAccessibleName("Add Skip Button");
		btnAddSkipButton.getAccessibleContext().setAccessibleDescription(
				"Choose an existing button to skip and choose an identifier of where to skip to");

		btnSkipTo.getAccessibleContext().setAccessibleName("Add Skip To Clause");
		btnSkipTo.getAccessibleContext()
				.setAccessibleDescription("Type an identifier previously chosen when using add skip button");

		btnAddUserInput.getAccessibleContext().setAccessibleName("Add User Input");
		btnAddUserInput.getAccessibleContext().setAccessibleDescription("Asks user for input");

		btnAddResetButtons.getAccessibleContext().setAccessibleName("Reset Buttons");
		btnAddResetButtons.getAccessibleContext()
				.setAccessibleDescription("Resets the actions of all buttons that exist for the scenario");

		btnSkipToClause.getAccessibleContext().setAccessibleName("Skip To Clause");
		btnSkipToClause.getAccessibleContext().setAccessibleDescription("Choose an identifier to skip to");

		btnSkipTo_1.getAccessibleContext().setAccessibleName("Where to Skip To");
		btnSkipTo_1.getAccessibleContext()
				.setAccessibleDescription("Type the identifier previously chosen when using Skip to Clause Button");

		btnClearAllCells.getAccessibleContext().setAccessibleName("Clear All Cells");
		btnClearAllCells.getAccessibleContext().setAccessibleDescription("Clears All Cells that have been initialized");

		btnClearSpecificCell.getAccessibleContext().setAccessibleName("Clear Specific Cell");
		btnClearSpecificCell.getAccessibleContext().setAccessibleDescription(
				"Enter the number of the cell you want to be cleared that is already initialized");

		btnSetSpecificPin.getAccessibleContext().setAccessibleName("Set Specific Pin");
		btnSetSpecificPin.getAccessibleContext()
				.setAccessibleDescription("Sets a specific pin of a cell and provides it with an 8 character sequence");

		btnDisplayCellCharacter.getAccessibleContext().setAccessibleName("Display Character in Cell");
		btnDisplayCellCharacter.getAccessibleContext()
				.setAccessibleDescription("Display english alphabet on specific braille cell");

		btnRaiseOnePin.getAccessibleContext().setAccessibleName("Raise One Pin");
		btnRaiseOnePin.getAccessibleContext().setAccessibleDescription("Raises a specific pin of a Braille Cell");

		btnLowerOnePin.getAccessibleContext().setAccessibleName("Lower One Pin");
		btnLowerOnePin.getAccessibleContext().setAccessibleDescription("Lowers a specific pin of a Braille Cell");

		frame.setVisible(true);

	}
}
