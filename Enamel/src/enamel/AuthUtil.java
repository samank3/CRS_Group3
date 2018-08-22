package enamel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AuthUtil {

	// Static Methods.

	public static final int EDIT = 0;
	public static final int CREATE = 1;
	public static final int TEXT_TO_SPEECH = 1;
	public static final int ADD_SOUND_FILE = 2;
	public static final int ADD_PAUSE = 3;
	public static final int DISPLAY_BRAILLE_STRING = 4;
	public static final int ADD_REPEAT_STRING = 5;
	public static final int ADD_REPEAT_BUTTON = 6;
	public static final int SKIP_BUTTON = 7;
	public static final int ADD_SKIP = 8;
	public static final int INSERT_IDENTIFIER = 9;
	public static final int ADD_USER_INPUT = 10;
	public static final int RESET_BUTTONS = 11;
	public static final int CLEAR_ALL_CELLS = 12;
	public static final int CLEAR_SPECIFIC_CELL = 13;
	public static final int SET_SPECIFIC_CELL = 14;
	public static final int DISPLAY_CELL_CHARACTER = 15;
	public static final int RAISE_ONE_PIN = 16;
	public static final int LOWER_ONE_PIN = 17;
	public static final int EDIT_BUTTON = 18;
	public static final int EDIT_CELL = 19;
	private static final Logging log = new Logging("Actions");

	public static ArrayList<String> populateFeatures() {
		ArrayList<String> list = new ArrayList<>();

		list.add("Choose Features to Add");
		list.add("Text-to-Speech");
		list.add("Add Sound File");
		list.add("Add Pause");
		list.add("Display Braille String");
		list.add("Add Repeat String");
		list.add("Add Repeat Button");
		list.add("Skip from Button");
		list.add("Add Skip Clause");
		list.add("Insert Identifier");
		list.add("Add User Input");
		list.add("Reset Buttons");
		list.add("Clear All Cells");
		list.add("Clear Specific Cell");
		list.add("Set Specific Cell");
		list.add("Display Cell Character");
		list.add("Raise One Pin");
		list.add("Lower One Pin");

		return list;
	}

	public static int validNumber(double i) {
		// Returns valid number. If number is valid, same number is returned.
		// If number is invalid, a valid substitute number is returned.
		if (i > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return (int) i;
		}
	}

	public static boolean isNumberValid(String str) {
		// Returns if Number is Valid.

		try {
			double d = Double.parseDouble(str);
			if (d > Integer.MAX_VALUE) {
				return false;
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean fileSaveCheck(boolean isEditMade) {
		if (isEditMade) {
			// Ask to Save the File
			JFileChooser file_chooser = new JFileChooser();
			file_chooser.setAcceptAllFileFilterUsed(false);
			file_chooser.setFileFilter(new FileNameExtensionFilter("Scenario File(.txt)", "txt"));
			Object[] options = { "Yes", "No", "Cancel" };
			int saveDialog = JOptionPane.showOptionDialog(null, "Would you like to save this scenario file?",
					"Save Changes?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
					options[0]);

			if (saveDialog == 0) {
				// YES
				// Check if File location is set, if it is set then modify that file. If no set
				// then call the saveFileDialog
				if (Authoring.saveFileLocation.equals("")) { // If saveFileLocatin = something
					Authoring.saveFileDialog();
				} else {
					// Save file to its existing location.
					File loc = new File(Authoring.saveFileLocation);
					try {
						FileWriter fw = new FileWriter(loc);
						Authoring.scenarioReader.setText("");
						for (int i = 0; i < Authoring.commands.size(); i++) {
							Authoring.scenarioReader.append(Authoring.commands.get(i) + "\n");
						}
						fw.write(Authoring.scenarioReader.getText());
						fw.close();
						Authoring.saveFile = false;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Authoring2.saveFileDialog();
				}
				return true;
			} else if (saveDialog == 1) {
				// NO
				return true;
			} else {
				// CANCEL
				return false;
			}

		}
		return true;
	}

	public static void setDescription(JTextArea description, int index) {

		String result = "";

		if (index == 0) {
			result = "Choose a feature to add from the dropdown menu above.";
		} else if (index == TEXT_TO_SPEECH) {
			result = "Add text that should be spoken out loud in the braille program.";
		} else if (index == ADD_SOUND_FILE) {
			result = "Play a specific sound file in the braille program.";
		} else if (index == ADD_PAUSE) {
			result = "Pause the braille program for a specific amount of seconds.";
		} else if (index == DISPLAY_BRAILLE_STRING) {
			result = "Display a specified string using the braille characters.";
		} else if (index == ADD_REPEAT_STRING) {
			result = "Repeated text that should be spoken out loud in the braille program.";
		} else if (index == ADD_REPEAT_BUTTON) {
			result = "Set a button that repeats the text set in the repeat text feature.";
		} else if (index == SKIP_BUTTON) {
			result = "Set a button to skip to a specified line in the scenario file code. Use Insert Identifier to set identifier.";
		} else if (index == ADD_USER_INPUT) {
			result = "Prompts the user in braille program for an input.";
		} else if (index == RESET_BUTTONS) {
			result = "Resets all button functions and makes them non-functioning";
		} else if (index == ADD_SKIP) {
			result = "Skip to a specified location within the braille scenario code. Use Insert Identifier to set identifier.";
		} else if (index == CLEAR_ALL_CELLS) {
			result = "Clear all cells in braille program from their values and reset to default.";
		} else if (index == CLEAR_SPECIFIC_CELL) {
			result = "Clear a specific cell in the braille program from its value and reset to default";
		} else if (index == SET_SPECIFIC_CELL) {
			result = "Set the value of a specific cell and its pins";
		} else if (index == DISPLAY_CELL_CHARACTER) {
			result = "Display an english alphabet into a cell of the braille program. ";
		} else if (index == RAISE_ONE_PIN) {
			result = "Raise the pin of the specified braille cell.";
		} else if (index == LOWER_ONE_PIN) {
			result = "Lower the pin of the specified braille cell.";
		} else if (index == INSERT_IDENTIFIER) {
			result = "This function should be used to direct add-skip or skip-button to a specific identifier. Do not use as a standalone command.";
		} else {
			result = "Choose a feature to add from the dropdown menu above.";
		}

		description.setText(result);

	}

	public static void editScenario(CommandList cmd, int location, int featureType) {
		if (featureType == TEXT_TO_SPEECH) {
			String textToSpeech = (String) JOptionPane.showInputDialog(null,
					"Enter the Text You Want Said Via Text-To-Speech.",
					"Enter the Text You Want Said Via Text-To-Speech", JOptionPane.QUESTION_MESSAGE, null, null,
					cmd.get(location));

			if (textToSpeech != null && !textToSpeech.trim().equals("")) {
				// cmd.add(textToSpeech);
				cmd.replace(location, textToSpeech);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Valid Text is Entered");
			}
		} else if (featureType == EDIT_CELL) {

			String cellNumm = cmd.get(location).replace("Cell ", "").trim();

			String gottenCells = (String) JOptionPane.showInputDialog(null, "Enter Number of Braille Cells to Use",
					"Enter Number of Braille Cells to Use", JOptionPane.QUESTION_MESSAGE, null, null, cellNumm);

			if (gottenCells == null) {
				return;
			}

			while ((gottenCells.equals("")) || (gottenCells == null) || !AuthUtil.isNumberValid(gottenCells)) {
				gottenCells = (String) JOptionPane.showInputDialog(null,
						"Error! Enter a valid number of Braille Cells to Use", "Enter Number of Braille Cells to Use",
						JOptionPane.QUESTION_MESSAGE, null, null, cellNumm);
			}

			Authoring.cellNum = Integer.valueOf(gottenCells);
			cmd.replace(location, "Cell " + Authoring.cellNum);
		} else if (featureType == EDIT_BUTTON) {

			String buttonNumm = cmd.get(location).replace("Button ", "").trim();

			String gottenButtons = (String) JOptionPane.showInputDialog(null, "Enter Number of Buttons to Use",
					"Enter Number of Buttons to Use", JOptionPane.QUESTION_MESSAGE, null, null, buttonNumm);

			while ((gottenButtons.equals("")) || (gottenButtons == null) || !AuthUtil.isNumberValid(gottenButtons)) {
				gottenButtons = (String) JOptionPane.showInputDialog(null,
						"Error! Enter a valid number of Buttons to Use", "Enter Number of Buttons to Use",
						JOptionPane.QUESTION_MESSAGE, null, null, buttonNumm);
			}

			Authoring.buttonNum = Integer.valueOf(gottenButtons);
			cmd.replace(location, "Button " + Authoring.buttonNum);

		} else if (featureType == ADD_SOUND_FILE) {
			String fileName = "";// = JOptionPane.showInputDialog(null,"Enter the Text You Want Said Via
			// Text-To-Speech.","Enter the Text You Want Said Via
			// Text-To-Speech",JOptionPane.QUESTION_MESSAGE);
			JFileChooser ss = new JFileChooser();
			ss.setDialogTitle("Choose Sound File Name");
			ss.setAcceptAllFileFilterUsed(false);
			ss.setFileFilter(new FileNameExtensionFilter("Audio File(.wav)", "wav"));
			ss.setCurrentDirectory(new File("FactoryScenarios/AudioFiles/"));
			if (ss.showOpenDialog(Authoring.btnAddFeature) == JFileChooser.APPROVE_OPTION) {
				fileName = ss.getSelectedFile().getName().toString();
				cmd.replace(location, "/~sound:" + fileName);
			}
		} else if (featureType == ADD_PAUSE) {

			String pauseTime = cmd.get(location).replace("/~pause:", "").trim();

			String pause = (String) JOptionPane.showInputDialog(null,
					"Enter the Number of Seconds to Pause the Scenario for.",
					"Enter the Number of Seconds to Pause the Scenario for.", JOptionPane.QUESTION_MESSAGE, null, null,
					pauseTime);
			try {
				if (pause != null && Integer.parseInt(pause) >= 0) {
					cmd.replace(location, "/~pause:" + pause);
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pause Time is Greater than 0.");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pause Time Is a Valid Time (less than "
						+ Integer.MAX_VALUE + ") and is an Integer.");
			}
		} else if (featureType == DISPLAY_BRAILLE_STRING) {

			String str = cmd.get(location).replace("/~disp-string:", "").trim();

			String brailleString = (String) JOptionPane.showInputDialog(null,
					"Enter the String to be Displayed In Braille", "Enter the String to be Displayed in Braille",
					JOptionPane.QUESTION_MESSAGE, null, null, str);

			if (brailleString != null && !brailleString.trim().equals("") && !isNumberValid(brailleString)
					&& Character.isLetter(brailleString.charAt(0))) {
				cmd.replace(location, "/~disp-string:" + brailleString);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure A Valid String is Entered");
			}
		} else if (featureType == ADD_REPEAT_STRING) {

			// String str = cmd.get(location).replace("/~pause:", "").trim();

			if (cmd.get(location).startsWith("/~repeat")) {
				location++;
			} else if (cmd.get(location).startsWith("/~endrepeat")) {
				location--;
			}

			String textToRepeat = (String) JOptionPane.showInputDialog(null, "Enter the Text to Repeat",
					"Enter the Text to Repeat.", JOptionPane.QUESTION_MESSAGE, null, null, cmd.get(location));

			if (textToRepeat != null && !textToRepeat.trim().equals("")) {
				// cmd.add("/~repeat");
				cmd.replace(location, textToRepeat);
				// cmd.add("/~endrepeat");
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Valid Text is Entered.");
			}
		} else if (featureType == ADD_REPEAT_BUTTON) {

			String str = cmd.get(location).replace("/~repeat-button:", "").trim();

			String buttonIndex = (String) JOptionPane.showInputDialog(null, "Enter the Button Number You Want Repeated",
					"Enter the Button Number You Want Repeated.", JOptionPane.QUESTION_MESSAGE, null, null, str);

			if (buttonIndex != null && isNumberValid(buttonIndex) && !buttonIndex.trim().equals("")) {

				if (Integer.parseInt(buttonIndex) > 0 && Integer.parseInt(buttonIndex) <= Authoring.buttonNum) {
					cmd.replace(location, "/~repeat-button:" + (Integer.parseInt(buttonIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Button Number Is Valid And Exists");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
			}
		} else if (featureType == SKIP_BUTTON) {

			String[] arr = cmd.get(location).replace("/~skip-button:", "").trim().split(" ");

			String buttonIndex = (String) JOptionPane.showInputDialog(null, "Enter the Button Number You Want Skipped.",
					"Enter the Button Number You Want Skipped.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[0]) + 1);

			if (buttonIndex != null && !buttonIndex.trim().equals("") && isNumberValid(buttonIndex)) {
				if (Integer.parseInt(buttonIndex) > 0 && Integer.parseInt(buttonIndex) <= Authoring.buttonNum) {
					// Do Nothing
				} else {
					// ERROR
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Button Number Is Valid And Exists");
					return;
				}
			} else {
				// ERROR
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
				return;
			}

			String identifier = (String) JOptionPane.showInputDialog(null,
					"Where Do You Want to Skip to? Enter an Identifier.",
					"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE, null, null,
					arr[1]);

			if (identifier != null && !identifier.trim().equals("")) {
				cmd.replace(location, "/~skip-button:" + (Integer.parseInt(buttonIndex) - 1) + " " + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Identifier is a Valid String.");
			}
		} else if (featureType == INSERT_IDENTIFIER) {

			String str = cmd.get(location).replace("/~", "").trim();

			String identifier = (String) JOptionPane.showInputDialog(null, "Enter the Identifier You Previously Used.",
					"Enter the Identifier You Previously Used.", JOptionPane.QUESTION_MESSAGE, null, null, str);

			if (identifier != null && !identifier.trim().equals("")) {
				cmd.replace(location, "/~" + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Enter a valid identifier string.");
			}
		} else if (featureType == ADD_USER_INPUT) {
			// Do Nothing
		} else if (featureType == RESET_BUTTONS) {
			// Do Nothing
		} else if (featureType == ADD_SKIP) {

			String str = cmd.get(location).replace("/~skip:", "").trim();

			String identifier = (String) JOptionPane.showInputDialog(null,
					"Where Do You Want to Skip to? Enter an Identifier.",
					"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE, null, null,
					str);

			if (identifier != null && !identifier.trim().equals("")) {
				cmd.replace(location, "/~skip:" + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Identifier String is Valid.");
			}
		} else if (featureType == CLEAR_ALL_CELLS) {
			// Do Nothing
		} else if (featureType == CLEAR_SPECIFIC_CELL) {

			String str = cmd.get(location).replace("/~disp-clear-cell:", "").trim();

			String cellIndex = (String) JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Cleared.",
					"Enter the Cell Number You Want Cleared.", JOptionPane.QUESTION_MESSAGE, null, null, str);

			if (cellIndex != null && isNumberValid(cellIndex) && !cellIndex.trim().equals("")) {

				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					cmd.replace(location, "/~disp-clear-cell:" + (Integer.parseInt(cellIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
			}
		} else if (featureType == SET_SPECIFIC_CELL) {

			String[] arr = cmd.get(location).replace("/~disp-cell-pins:", "").trim().split(" ");

			String cellIndex = (String) JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
					"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[0]) + 1);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {

				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String sequence = (String) JOptionPane.showInputDialog(null,
					"Enter the 8-Character Sequence Consisting of 0's and 1's.",
					"Enter the  8-Character Sequence Consisting of 0's and 1's.", JOptionPane.QUESTION_MESSAGE, null,
					null, arr[1]);

			if (cellIndex != null && Integer.parseInt(cellIndex) > 0
					&& Integer.parseInt(cellIndex) <= Authoring.cellNum && sequence.length() == 8
					&& sequence.matches("[01]+")) {
				cmd.replace(location, "/~disp-cell-pins:" + (Integer.parseInt(cellIndex) - 1) + " " + sequence);
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, Please Make Sure the Cell Number Is Valid And Exists and the 8-Character Sequence is Valid.");
			}
		} else if (featureType == DISPLAY_CELL_CHARACTER) {

			String[] arr = cmd.get(location).replace("/~disp-cell-char:", "").trim().split(" ");

			String cellIndex = (String) JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
					"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[0]) + 1);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					// do nothing
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String alphabet = (String) JOptionPane.showInputDialog(null, "Enter a Valid English Alphabet.",
					"Enter a Valid English Alphabet.", JOptionPane.QUESTION_MESSAGE, null, null, arr[1]);

			if (alphabet != null && !alphabet.trim().equals("") && alphabet.length() == 1) {
				char c = alphabet.charAt(0);
				if (Character.isLetter(c)) {
					cmd.replace(location, "/~disp-cell-char:" + (Integer.parseInt(cellIndex) - 1) + " " + alphabet);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure a Valid English Alphabet is Selected.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure a Valid English Alphabet is Selected.");
			}
		} else if (featureType == RAISE_ONE_PIN) {

			String[] arr = cmd.get(location).replace("/~disp-cell-raise:", "").trim().split(" ");

			String cellIndex = (String) JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Raised.",
					"Enter the Cell Number You Want Raised.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[0]) + 1);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					// Do nothing
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String pinIndex = (String) JOptionPane.showInputDialog(null, "Enter the Pin Number to Raise.",
					"Enter the  Pin Number to Raise.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[1]) + 1);

			if (pinIndex != null && !pinIndex.trim().equals("") && isNumberValid(pinIndex)) {
				if (Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
					// DO STUFF
					cmd.replace(location, "/~disp-cell-raise:" + (Integer.parseInt(cellIndex) - 1) + " "
							+ (Integer.parseInt(pinIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Pin Number is between 1 and 8.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pin Number is between 1 and 8.");
			}
		} else if (featureType == LOWER_ONE_PIN) {

			String[] arr = cmd.get(location).replace("/~disp-cell-lower:", "").trim().split(" ");

			String cellIndex = (String) JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Lowered.",
					"Enter the Cell Number You Want Lowered.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[0]) + 1);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					// Do nothing
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String pinIndex = (String) JOptionPane.showInputDialog(null, "Enter the Pin Number to Lower.",
					"Enter the  Pin Number to Lower.", JOptionPane.QUESTION_MESSAGE, null, null,
					Integer.valueOf(arr[1]) + 1);

			if (pinIndex != null && !pinIndex.trim().equals("") && isNumberValid(pinIndex)) {
				if (Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
					// DO STUFF
					cmd.replace(location, "/~disp-cell-lower:" + (Integer.parseInt(cellIndex) - 1) + " "
							+ (Integer.parseInt(pinIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Pin Number is between 1 and 8.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pin Number is between 1 and 8.");
			}
		} else {
			return;
		}
	}

	public static void triggerScenario(CommandList cmd, int index, int option, int listLocation) {

		cmd.setLastFeatureType(index);

		if (index == 0) {
			JOptionPane.showMessageDialog(null, "Error, select a valid feature to add.");
		} else if (index == TEXT_TO_SPEECH) {
			log.info("TEXT_TO_SPEECH Executed");
			String textToSpeech = JOptionPane.showInputDialog(null, "Enter the Text You Want Said Via Text-To-Speech.",
					"Enter the Text You Want Said Via Text-To-Speech", JOptionPane.QUESTION_MESSAGE);

			if (textToSpeech != null && !textToSpeech.trim().equals("")) {
				cmd.add(textToSpeech);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Valid Text is Entered");
			}
		} else if (index == ADD_SOUND_FILE) {
			log.info("ADD_SOUND_FILE Executed");
			String fileName = "";// = JOptionPane.showInputDialog(null,"Enter the Text You Want Said Via
			// Text-To-Speech.","Enter the Text You Want Said Via
			// Text-To-Speech",JOptionPane.QUESTION_MESSAGE);
			JFileChooser ss = new JFileChooser();
			ss.setDialogTitle("Choose Sound File Name");
			ss.setAcceptAllFileFilterUsed(false);
			ss.setFileFilter(new FileNameExtensionFilter("Audio File(.wav)", "wav"));
			ss.setCurrentDirectory(new File("FactoryScenarios/AudioFiles/"));
			if (ss.showOpenDialog(Authoring.btnAddFeature) == JFileChooser.APPROVE_OPTION) {
				fileName = ss.getSelectedFile().getName().toString();
				cmd.add("/~sound:" + fileName);
			}
		} else if (index == ADD_PAUSE) {
			log.info("ADD_PAUSE Executed");
			String pause = JOptionPane.showInputDialog(null, "Enter the Number of Seconds to Pause the Scenario for.",
					"Enter the Number of Seconds to Pause the Scenario for.", JOptionPane.QUESTION_MESSAGE);
			try {
				if (pause != null && Integer.parseInt(pause) >= 0) {
					cmd.add("/~pause:" + pause);
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pause Time is Greater than 0.");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pause Time Is a Valid Time (less than "
						+ Integer.MAX_VALUE + ") and is an Integer.");
			}
		} else if (index == DISPLAY_BRAILLE_STRING) {
			log.info("DISPLAY_BRAILLE_STRING Executed");
			String brailleString = JOptionPane.showInputDialog(null, "Enter the String to be Displayed In Braille",
					"Enter the String to be Displayed in Braille", JOptionPane.QUESTION_MESSAGE);

			if (brailleString != null && !brailleString.equals("") && !isNumberValid(brailleString)
					&& Character.isLetter(brailleString.charAt(0))) {
				cmd.add("/~disp-string:" + brailleString);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure A Valid String is Entered");
			}
		} else if (index == ADD_REPEAT_STRING) {
			log.info("ADD_REPEAT_STRING Executed");
			String textToRepeat = JOptionPane.showInputDialog(null, "Enter the Text to Repeat",
					"Enter the Text to Repeat.", JOptionPane.QUESTION_MESSAGE);

			if (textToRepeat != null && !textToRepeat.trim().equals("")) {
				cmd.add("/~repeat");
				cmd.add(textToRepeat);
				cmd.add("/~endrepeat");
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Valid Text is Entered.");
			}
		} else if (index == ADD_REPEAT_BUTTON) {
			log.info("ADD_REPEAT_BUTTON Executed");
			String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Repeated",
					"Enter the Button Number You Want Repeated.", JOptionPane.QUESTION_MESSAGE);

			if (buttonIndex != null && isNumberValid(buttonIndex) && !buttonIndex.trim().equals("")) {

				if (Integer.parseInt(buttonIndex) > 0 && Integer.parseInt(buttonIndex) <= Authoring.buttonNum) {
					cmd.add("/~repeat-button:" + (Integer.parseInt(buttonIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Button Number Is Valid And Exists");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
			}
		} else if (index == SKIP_BUTTON) {
			log.info("SKIP_BUTTON Executed");
			String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Skipped.",
					"Enter the Button Number You Want Skipped.", JOptionPane.QUESTION_MESSAGE);

			if (buttonIndex != null && !buttonIndex.trim().equals("") && isNumberValid(buttonIndex)) {
				if (Integer.parseInt(buttonIndex) > 0 && Integer.parseInt(buttonIndex) <= Authoring.buttonNum) {
					// Do Nothing
				} else {
					// ERROR
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure the Button Number Is Valid And Exists");
					return;
				}
			} else {
				// ERROR
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
				return;
			}

			String identifier = JOptionPane.showInputDialog(null, "Where Do You Want to Skip to? Enter an Identifier.",
					"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE);

			if (identifier != null && !identifier.trim().equals("")) {
				cmd.add("/~skip-button:" + (Integer.parseInt(buttonIndex) - 1) + " " + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Identifier is a Valid String.");
			}
		} else if (index == INSERT_IDENTIFIER) {
			log.info("INSERT_IDENTIFIER Executed");
			String identifier = JOptionPane.showInputDialog(null, "Enter the Identifier You Previously Used.",
					"Enter the Identifier You Previously Used.", JOptionPane.QUESTION_MESSAGE);

			if (identifier != null && !identifier.trim().equals("")) {
				cmd.add("/~" + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Enter a valid identifier string.");
			}
		} else if (index == ADD_USER_INPUT) {
			log.info("ADD_USER_INPUT Executed");
			Authoring.addUserInputString();
		} else if (index == RESET_BUTTONS) {
			log.info("RESET_BUTTONS Executed");
			cmd.add("/~reset-buttons");
		} else if (index == ADD_SKIP) {
			log.info("ADD_SKIP Executed");
			String identifier = JOptionPane.showInputDialog(null, "Where Do You Want to Skip to? Enter an Identifier.",
					"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE);

			if (identifier != null && !identifier.trim().equals("")) {
				cmd.add("/~skip:" + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Identifier String is Valid.");
			}

		} else if (index == CLEAR_ALL_CELLS) {
			log.info("CLEAR_ALL_CELLS Executed");
			cmd.add("/~disp-clearAll");
		} else if (index == CLEAR_SPECIFIC_CELL) {
			log.info("CLEAR_SPECIFIC_CELL Executed");
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Cleared.",
					"Enter the Cell Number You Want Cleared.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {

				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					cmd.add("/~disp-clear-cell:" + (Integer.parseInt(cellIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
			}
		} else if (index == SET_SPECIFIC_CELL) {
			log.info("SET_SPECIFIC_CELL Executed");
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
					"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {

				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String sequence = JOptionPane.showInputDialog(null,
					"Enter the 8-Character Sequence Consisting of 0's and 1's.",
					"Enter the  8-Character Sequence Consisting of 0's and 1's.", JOptionPane.QUESTION_MESSAGE);

			if (sequence != null && sequence.length() == 8 && sequence.matches("[01]+")) {
				cmd.add("/~disp-cell-pins:" + (Integer.parseInt(cellIndex) - 1) + " " + sequence);
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, Please Make Sure the Cell Number Is Valid And Exists and the 8-Character Sequence is Valid.");
			}
		} else if (index == DISPLAY_CELL_CHARACTER) {
			log.info("DISPLAY_CELL_CHARACTER Executed");
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
					"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					// do nothing
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String alphabet = JOptionPane.showInputDialog(null, "Enter a Valid English Alphabet.",
					"Enter a Valid English Alphabet.", JOptionPane.QUESTION_MESSAGE);

			if (alphabet != null && !alphabet.trim().equals("") && alphabet.length() == 1) {
				char c = alphabet.charAt(0);
				if (Character.isLetter(c)) {
					cmd.add("/~disp-cell-char:" + (Integer.parseInt(cellIndex) - 1) + " " + alphabet);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, Please Make Sure a Valid English Alphabet is Selected.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure a Valid English Alphabet is Selected.");
			}
		} else if (index == RAISE_ONE_PIN) {
			log.info("RAISE_ONE_PIN Executed");
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Raised.",
					"Enter the Cell Number You Want Raised.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					// Do nothing
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String pinIndex = JOptionPane.showInputDialog(null, "Enter the Pin Number to Raise.",
					"Enter the  Pin Number to Raise.", JOptionPane.QUESTION_MESSAGE);

			if (pinIndex != null && !pinIndex.trim().equals("") && isNumberValid(pinIndex)) {
				if (Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
					// DO STUFF
					cmd.add("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) - 1) + " "
							+ (Integer.parseInt(pinIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Pin Number is between 1 and 8.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pin Number is between 1 and 8.");
			}
		} else if (index == LOWER_ONE_PIN) {
			log.info("LOWER_ONE_PIN Executed");
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Lowered.",
					"Enter the Cell Number You Want Lowered.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && !cellIndex.trim().equals("") && isNumberValid(cellIndex)) {
				if (Integer.parseInt(cellIndex) > 0 && Integer.parseInt(cellIndex) <= Authoring.cellNum) {
					// Do nothing
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists.");
				return;
			}

			String pinIndex = JOptionPane.showInputDialog(null, "Enter the Pin Number to Raise.",
					"Enter the  Pin Number to Raise.", JOptionPane.QUESTION_MESSAGE);

			if (pinIndex != null && !pinIndex.trim().equals("") && isNumberValid(pinIndex)) {
				if (Integer.parseInt(pinIndex) > 0 && Integer.parseInt(pinIndex) <= 8) {
					// DO STUFF
					cmd.add("/~disp-cell-lower:" + (Integer.parseInt(cellIndex) - 1) + " "
							+ (Integer.parseInt(pinIndex) - 1));
				} else {
					JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Pin Number is between 1 and 8.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure Pin Number is between 1 and 8.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error, select a valid feature to add.");
		}
		Authoring.saveFile = true;
	}

}
