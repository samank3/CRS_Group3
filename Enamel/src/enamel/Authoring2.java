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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.ComponentOrientation;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class Authoring2 {
	private static JFrame frame;
	public static JButton btnChooseScenario;
	public static JFileChooser file_chooser;
	public static JFileChooser sound_chooser;
	public static JFileChooser sound_opener;
	public static JTextArea scenarioReader;
	public static JList<String> lstCommands;
	public static CommandList commands;
	public static JComboBox<String> cmbFeatures;
	public static JButton btnCreateScenario;
	public static JButton btnCreateAudioFiles;
	public static JButton btnTestScenario;
	public static JButton btnExportScenario;
	public static JButton btnEditSelectedFeature;
	public static JButton btnMoveUp;
	public static JButton btnMoveDown;
	public static JButton btnRemoveSelectedFeature;
	public static JTextArea txtDescription;
	public static JButton btnAddFeature;
	private static JPanel pnlCreateScenarios;
	public static int buttonNum;
	public static int cellNum;
	public static int commandsSize;
	public static boolean testResult;
	public static boolean saveFile;
	public static String saveFileLocation;

	public static boolean openFileDialog() {
		file_chooser.setDialogTitle("Open Scenario File");
		if (file_chooser.showOpenDialog(btnChooseScenario) == JFileChooser.APPROVE_OPTION) {

			StringBuffer buff = new StringBuffer();
			scenarioReader.setText("");
			commands.reset();
			int lineCounter = 0;
			try {
				Scanner scanner = new Scanner(file_chooser.getSelectedFile());

				while (scanner.hasNextLine()) {
					// Check if first two lines follow the specific plan.
					String line = scanner.nextLine();
					if (lineCounter == 0) {
						if (!line.startsWith("Cell")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							JOptionPane.showMessageDialog(null, "Please select a valid scenario file.");
							scanner.close();
							return false;
						}
					}
					if (lineCounter == 1) {
						if (!line.startsWith("Button")) {
							System.out.println("File: " + file_chooser.getSelectedFile() + " is not a valid format");
							JOptionPane.showMessageDialog(null, "Please select a valid scenario file.");
							scanner.close();
							return false;
						}
					}
					lineCounter += 1;
					buff.append(line + "\n");
					try {
					if(commands.get(commands.size() - 1).equals("/~repeat") || commands.get(commands.size()- 2).equals("/~repeat") ||
							commands.get(commands.size() - 3).equals("/~repeat")) {
						commands.setLastFeatureType(GetFeature.type("/~repeat"));
					}else {
					commands.setLastFeatureType(GetFeature.type(line));
					}
					}catch(Exception e) {
						commands.setLastFeatureType(GetFeature.type(line));
					}
					commands.add(line);
					saveFileLocation = file_chooser.getSelectedFile().toString();
					saveFile = false;
				}
				commandsSize = commands.size();
				scanner.close();
				// scenarioReader.setText(buff.toString());
				enableEditTools();
				System.out.println("File: " + file_chooser.getSelectedFile() + " has been imported");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Error Opening File");
			JOptionPane.showMessageDialog(null, "Error Opening File");
		}

		return true;
	}

	private static void enableEditTools() {
		btnTestScenario.setEnabled(true);
		cmbFeatures.setEnabled(true);
		btnExportScenario.setEnabled(true);
		txtDescription.setEnabled(true);
		// btnEditSelectedFeature.setEnabled(true);
		btnAddFeature.setEnabled(true);
		// btnMoveUp.setEnabled(true);
		// btnMoveDown.setEnabled(true);
		// btnRemoveSelectedFeature.setEnabled(true);
	}

	public static void testCreateScenarios() {
		scenarioReader.setText("");
		scenarioReader.append("Cell " + 1 + "\n");
		scenarioReader.append("Button " + 4 + "\n");
	}

	public static void addUserInputString() {
		commands.add("/~user-input");
	}

	public static boolean saveFileDialog() {
		file_chooser.setDialogTitle("Save Scenario File");
		if (file_chooser.showSaveDialog(btnChooseScenario) == JFileChooser.APPROVE_OPTION) {
			File currentFile = new File(file_chooser.getSelectedFile().toString() + ".txt");

			try {
				if (!currentFile.exists()) {
					currentFile.createNewFile();
				}

				FileWriter fw = new FileWriter(currentFile);
				scenarioReader.setText("");
				for (int i = 0; i < commands.size(); i++) {
					scenarioReader.append(commands.get(i) + "\n");
				}
				fw.write(scenarioReader.getText());
				fw.close();
				saveFileLocation = file_chooser.getSelectedFile().toString();
				saveFile = false;

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
		frame = new JFrame();
		frame.getContentPane().setMaximumSize(new Dimension(149, 23));
		frame.setBounds(100, 100, 658, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Authoring Application");

		file_chooser = new JFileChooser();
		file_chooser.setAcceptAllFileFilterUsed(false);
		file_chooser.setFileFilter(new FileNameExtensionFilter("Scenario File(.txt)", "txt"));
		sound_chooser = new JFileChooser();
		file_chooser.setCurrentDirectory(new java.io.File("C:"));
		file_chooser.setDialogTitle("Open Scenario File");
		sound_opener = new JFileChooser();
		sound_opener.setDialogTitle("Choose Sound File Name");
		sound_opener.setAcceptAllFileFilterUsed(false);
		sound_opener.setFileFilter(new FileNameExtensionFilter("Audio File(.wav)", "wav"));

		btnChooseScenario = new JButton("Import Scenario");
		btnChooseScenario.setLocation(new Point(100, 100));
		btnChooseScenario.setVisible(true);
		btnCreateScenario = new JButton("Create Scenario");
		lstCommands = new JList<String>();
		commands = new CommandList(lstCommands);
		btnCreateScenario.setPreferredSize(new Dimension(95, 23));
		btnCreateScenario.setMinimumSize(new Dimension(95, 23));
		btnCreateScenario.setMaximumSize(new Dimension(95, 23));
		btnTestScenario = new JButton("Test Scenario");
		btnTestScenario.setEnabled(false);
		btnCreateAudioFiles = new JButton("Create Audio File");
		pnlCreateScenarios = new JPanel();
		pnlCreateScenarios.setVisible(false);
		btnExportScenario = new JButton("Export Scenario");
		btnExportScenario.setEnabled(false);
		cmbFeatures = new JComboBox<String>();
		cmbFeatures.setEnabled(false);
		txtDescription = new JTextArea();
		txtDescription.setEnabled(false);
		txtDescription.setText("Choose a feature to add from the dropdown menu above.");
		txtDescription.setEditable(false);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		btnAddFeature = new JButton("Add Feature to Scenario");
		btnAddFeature.setEnabled(false);
		saveFile = false;
		JLabel lblAddFeatures = new JLabel("Add Features");
		lblAddFeatures.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditSelectedFeature = new JButton("Edit Selected Feature");
		btnEditSelectedFeature.setEnabled(false);
		btnEditSelectedFeature.setPreferredSize(new Dimension(149, 23));
		btnEditSelectedFeature.setMinimumSize(new Dimension(149, 23));
		btnEditSelectedFeature.setMaximumSize(new Dimension(149, 23));
		btnMoveUp = new JButton("Move Up");
		btnMoveUp.setEnabled(false);
		btnMoveDown = new JButton("Move Down");
		btnMoveDown.setEnabled(false);
		btnRemoveSelectedFeature = new JButton("Remove Selected Feature");
		btnRemoveSelectedFeature.setEnabled(false);
		JLabel lblEditFeatures = new JLabel("Edit Features");
		lblEditFeatures.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnChooseScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openFileDialog();
			}

		});

		lstCommands.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				if (!arg0.getValueIsAdjusting()) {

					int currIndex = lstCommands.getSelectedIndex();

					// System.out.println(lstCommands.getSelectedIndex());
					try {
						int repeatLocation = 0;
						if (commands.get(lstCommands.getSelectedIndex()).equals("/~repeat")) {
							repeatLocation = lstCommands.getSelectedIndex();
						}
						if (commands.get(lstCommands.getSelectedIndex() - 1).equals("/~repeat")) {
							repeatLocation = lstCommands.getSelectedIndex() - 1;
						}
						if (commands.get(lstCommands.getSelectedIndex() - 2).equals("/~repeat")) {
							repeatLocation = lstCommands.getSelectedIndex() - 2;
						}

						// 4 CASES 1.) WHERE THEY'RE MIN AND MAX , 2.) WHERE ITS RIGHT IN THE MIDDLE,
						// 3.) WHERE ITS TOP, 4.) WHERE ITS BOTTOM
						if (repeatLocation != 0) {
							if (repeatLocation == 2 && repeatLocation + 2 == commands.size() - 1) {
								btnEditSelectedFeature.setEnabled(true);
								btnMoveUp.setEnabled(false);
								btnMoveDown.setEnabled(false);
								btnRemoveSelectedFeature.setEnabled(true);
							} else if (repeatLocation == 2) {
								btnEditSelectedFeature.setEnabled(true);
								btnMoveUp.setEnabled(false);
								btnMoveDown.setEnabled(true);
								btnRemoveSelectedFeature.setEnabled(true);
							} else if (repeatLocation + 2 == commands.size() - 1) {
								btnEditSelectedFeature.setEnabled(true);
								btnMoveUp.setEnabled(true);
								btnMoveDown.setEnabled(false);
								btnRemoveSelectedFeature.setEnabled(true);
							} else {
								btnEditSelectedFeature.setEnabled(true);
								btnMoveUp.setEnabled(true);
								btnMoveDown.setEnabled(true);
								btnRemoveSelectedFeature.setEnabled(true);
							}
							return;
						}
					} catch (Exception e) {

					}

					if (currIndex == -1) {
						btnMoveUp.setEnabled(false);
						btnMoveDown.setEnabled(false);
						btnRemoveSelectedFeature.setEnabled(false);
						btnEditSelectedFeature.setEnabled(false);
						return;
					}

					if (currIndex == 0 || currIndex == 1) {
						btnMoveUp.setEnabled(false);
						btnMoveDown.setEnabled(false);
						btnRemoveSelectedFeature.setEnabled(false);
						btnEditSelectedFeature.setEnabled(true);
						return;
					}

					if (currIndex == 2) {
						btnMoveUp.setEnabled(false);
						if (lstCommands.getSelectedIndex() == commands.size() - 1) {
							btnMoveDown.setEnabled(false);
						} else {
							btnMoveDown.setEnabled(true);
						}
						btnRemoveSelectedFeature.setEnabled(true);
						btnEditSelectedFeature.setEnabled(true);
						return;
					}

					if (currIndex == commands.size() - 1) {
						btnMoveDown.setEnabled(false);
						btnMoveUp.setEnabled(true);
						btnRemoveSelectedFeature.setEnabled(true);
						btnEditSelectedFeature.setEnabled(true);
						return;
					}

					btnMoveUp.setEnabled(true);
					btnMoveDown.setEnabled(true);
					btnRemoveSelectedFeature.setEnabled(true);
					btnEditSelectedFeature.setEnabled(true);
				}
			}

		});

		btnCreateScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				file_chooser.setSelectedFile(null);

				if (AuthUtil.fileSaveCheck(saveFile) == false) {
					return;
				}

				String gottenCells = JOptionPane.showInputDialog(null, "Enter Number of Braille Cells to Use",
						"Enter Number of Braille Cells to Use", JOptionPane.QUESTION_MESSAGE);

				if (gottenCells == null) {
					return;
				}

				while ((gottenCells.equals("")) || (gottenCells == null) || !AuthUtil.isNumberValid(gottenCells)) {
					gottenCells = JOptionPane.showInputDialog(null,
							"Error! Enter a valid number of Braille Cells to Use",
							"Enter Number of Braille Cells to Use", JOptionPane.QUESTION_MESSAGE);

				}

				String gottenButtons = JOptionPane.showInputDialog(null, "Enter Number of Buttons to Use",
						"Enter Number of Buttons to Use", JOptionPane.QUESTION_MESSAGE);

				while ((gottenButtons.equals("")) || (gottenButtons == null) || !AuthUtil.isNumberValid(gottenButtons)) {
					gottenButtons = JOptionPane.showInputDialog(null, "Error! Enter a valid number of Buttons to Use",
							"Enter Number of Buttons to Use", JOptionPane.QUESTION_MESSAGE);
				}

				int cells = Integer.parseInt(gottenCells);
				int buttons = Integer.parseInt(gottenButtons);

				commands.reset();
				commands.setLastFeatureType(AuthUtil.EDIT_CELL);
				commands.add("Cell " + cells);
				commands.setLastFeatureType(AuthUtil.EDIT_BUTTON);
				commands.add("Button " + buttons);
				buttonNum = buttons;
				cellNum = cells;
				enableEditTools();
				testResult = true;
				saveFile = true;

			}
		});

		btnTestScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String fileToCheck;
				if (file_chooser.getSelectedFile() != null && !file_chooser.getSelectedFile().toString().isEmpty() && 
						commandsSize == commands.size() && saveFile == false) {
					fileToCheck = file_chooser.getSelectedFile().getAbsolutePath().toString();
				} else {
					File currentFile = new File("FactoryScenarios/Scenario_temp.txt");
					try {
						if (!currentFile.exists()) {
							currentFile.createNewFile();
						}
						scenarioReader.setText("");
						for (int i = 0; i < commands.size(); i++) {
							scenarioReader.append(commands.get(i) + "\n");
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

		btnCreateAudioFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String gottenFileName = JOptionPane.showInputDialog(null, "Enter Name of
				// Audio File to Create",
				// "Enter Name of Audio File to Create", JOptionPane.QUESTION_MESSAGE);
				String gottenFileName = "";
				sound_opener.setDialogTitle("Save Audio File");

				if (sound_opener.showSaveDialog(btnCreateAudioFiles) == JFileChooser.APPROVE_OPTION) {
					File currentFile = sound_opener.getSelectedFile();

					try {
						if (!currentFile.exists()) {
							// currentFile.createNewFile();
						}

						gottenFileName = currentFile.toString();

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {
					System.out.println("Error Saving File");
					JOptionPane.showMessageDialog(null, "Error Saving File");
					return;
				}

				String gottenDuration = JOptionPane.showInputDialog(null,
						"Enter The Duration of the Audio File in Seconds",
						"Enter The Duration of the Audio File in Seconds", JOptionPane.QUESTION_MESSAGE);
				// gottenFileName == null ||
				while ((gottenDuration.equals("")) || (gottenDuration == null) || !AuthUtil.isNumberValid(gottenDuration)) {
					gottenDuration = JOptionPane.showInputDialog(null, "Error! Enter a valid duration in seconds.",
							"Enter duration of the audio file in seconds.", JOptionPane.QUESTION_MESSAGE);
				}
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
						} 
					catch (LineUnavailableException | InterruptedException e1) {
						e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Recording Cancelled");
					}
			}
		});

		btnExportScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileDialog();
			}
		});

		JScrollPane scrollPane = new JScrollPane(lstCommands, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		frame.getContentPane().add(scrollPane);

		cmbFeatures.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AuthUtil.setDescription(txtDescription, cmbFeatures.getSelectedIndex());
			}

		});

		for (int i = 0; i < AuthUtil.populateFeatures().size(); i++) {
			cmbFeatures.addItem(AuthUtil.populateFeatures().get(i));
		}

		btnAddFeature.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AuthUtil.triggerScenario(commands, cmbFeatures.getSelectedIndex(), AuthUtil.CREATE,
						lstCommands.getSelectedIndex());
			}

		});

		btnMoveUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int repeatLocation = 0;
				int currIndex = lstCommands.getSelectedIndex();
				if (commands.get(lstCommands.getSelectedIndex()).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex();
				}
				if (commands.get(lstCommands.getSelectedIndex() - 1).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex() - 1;
				}
				if (commands.get(lstCommands.getSelectedIndex() - 2).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex() - 2;
				}

				commands.moveUp(repeatLocation == 0 ? currIndex : repeatLocation);
			}

		});

		btnMoveDown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int repeatLocation = 0;
				int currIndex = lstCommands.getSelectedIndex();
				if (commands.get(lstCommands.getSelectedIndex()).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex();
				}
				if (commands.get(lstCommands.getSelectedIndex() - 1).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex() - 1;
				}
				if (commands.get(lstCommands.getSelectedIndex() - 2).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex() - 2;
				}
				commands.moveDown(repeatLocation == 0 ? currIndex : repeatLocation);
			}

		});

		btnRemoveSelectedFeature.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int repeatLocation = 0;
				int currIndex = lstCommands.getSelectedIndex();
				if (commands.get(lstCommands.getSelectedIndex()).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex();
				}
				if (commands.get(lstCommands.getSelectedIndex() - 1).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex() - 1;
				}
				if (commands.get(lstCommands.getSelectedIndex() - 2).equals("/~repeat")) {
					repeatLocation = lstCommands.getSelectedIndex() - 2;
				}

				if (repeatLocation != 0) {
					commands.remove(repeatLocation);
					commands.remove(repeatLocation);
					commands.remove(repeatLocation);
				} else {
					commands.remove(currIndex);
				}

			}

		});

		btnEditSelectedFeature.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = lstCommands.getSelectedIndex();
				AuthUtil.editScenario(commands, index, commands.getType(index));
			}

		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnRemoveSelectedFeature, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnMoveDown, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnMoveUp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEditSelectedFeature, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnCreateScenario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnChooseScenario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnExportScenario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnTestScenario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnCreateAudioFiles, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(txtDescription, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(btnAddFeature, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
						.addComponent(cmbFeatures, 0, 197, Short.MAX_VALUE)
						.addComponent(lblEditFeatures, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(lblAddFeatures, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnlCreateScenarios, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE).addGap(0)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(
								Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(3)
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
								.addComponent(btnCreateAudioFiles, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblAddFeatures).addGap(5)
								.addComponent(cmbFeatures, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAddFeature, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, 72,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblEditFeatures, GroupLayout.PREFERRED_SIZE, 17,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnEditSelectedFeature, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnMoveUp, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnMoveDown, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnRemoveSelectedFeature, GroupLayout.PREFERRED_SIZE, 31,
										GroupLayout.PREFERRED_SIZE)
								.addGap(113)
								.addComponent(pnlCreateScenarios, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
								.addGap(24))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
								.addContainerGap()));

		scrollPane.setViewportView(lstCommands);
		pnlCreateScenarios.setLayout(null);

		JLabel lblScenarioFeatures = new JLabel("Scenario Features");
		lblScenarioFeatures.setHorizontalAlignment(SwingConstants.CENTER);
		lblScenarioFeatures.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblScenarioFeatures.setBounds(0, 0, 197, 29);
		pnlCreateScenarios.add(lblScenarioFeatures);

		scenarioReader = new JTextArea();
		scenarioReader.setBounds(58, 402, 84, 141);
		pnlCreateScenarios.add(scenarioReader);
		scenarioReader.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scenarioReader.setBorder(UIManager.getBorder("FileChooser.listViewBorder"));
		scenarioReader.setWrapStyleWord(true);
		scenarioReader.setLineWrap(true);
		scenarioReader.setVisible(true);

		frame.getContentPane().setLayout(groupLayout);

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

		frame.setVisible(true);

	}
}
