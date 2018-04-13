package enamel;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AuthUtil {

	// Static Methods.

	public static ArrayList<String> populateFeatures() {
		ArrayList<String> list = new ArrayList<>();

		list.add("Choose Features to Add");
		list.add("Text-to-Speech");
		list.add("Add Sound File");
		list.add("Add Pause");
		list.add("Display Braille String");
		list.add("Add Repeat String");
		list.add("Add Repeat Button");
		list.add("Skip Button/SkipTo");
		list.add("Add User Input");
		list.add("Reset Buttons");
		list.add("Add Skip/Skip To");
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
		if(i>Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}else {
			return (int)i;
		}
	}
	
	public static boolean isNumberValid(double i) {
		// Returns if Number is Valid.
		if(i>Integer.MAX_VALUE) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean fileSaveCheck(boolean isEditMade) {
		System.out.println(isEditMade);
		if(isEditMade) {
			// Ask to Save the File
			JFileChooser file_chooser = new JFileChooser();
			file_chooser.setAcceptAllFileFilterUsed(false);
			file_chooser.setFileFilter(new FileNameExtensionFilter("Scenario File(.txt)", "txt"));
			Object[] options = {"Yes", "No", "Cancel"};
			int saveDialog = JOptionPane.showOptionDialog(null, "Would you like to save this scenario file?", "Save Changes?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
			if(saveDialog == 0) {
				//YES
				//Check if File location is set, if it is set then modify that file. If no set then call the saveFileDialog
				if(!Authoring2.saveFileLocation.equals("")) {
					Authoring2.saveFileDialog();
				}else {
					// Save file to its existing location.
				}
				return true;
			}else if(saveDialog == 1) {
				// NO
				return true;
			}else {
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
		} else if (index == 1) {
			result = "Add text that should be spoken out loud in the braille program.";
		} else if (index == 2) {
			result = "Play a specific sound file in the braille program.";
		} else if (index == 3) {
			result = "Pause the braille program for a specific amount of seconds.";
		} else if (index == 4) {
			result = "Display a specified string using the braille characters.";
		} else if (index == 5) {
			result = "Repeated text that should be spoken out loud in the braille program.";
		} else if (index == 6) {
			result = "Set a button that repeats the text set in the repeat text feature.";
		} else if (index == 7) {
			result = "Set a button to skip to a specified line in the scenario file code.";
		} else if (index == 8) {
			result = "Prompts the user in braille program for an input.";
		} else if (index == 9) {
			result = "Resets all button functions and makes them non-functioning";
		} else if (index == 10) {
			result = "Skip to a specified loction within the braille scenario code.";
		} else if (index == 11) {
			result = "Clear all cells in braille program from their values and reset to default.";
		} else if (index == 12) {
			result = "Clear a specific cell in the braille program from its value and reset to default";
		} else if (index == 13) {
			result = "Set the value of a specific cell and its pins";
		} else if (index == 14) {
			result = "Display an english alphabet into a cell of the braille program. ";
		} else if (index == 15) {
			result = "Raise the pin of the specified braille cell.";
		} else if (index == 16) {
			result = "Lower the pin of the specified braille cell.";
		} else {
			result = "Choose a feature to add from the dropdown menu above.";
		}

		description.setText(result);

	}

	public static void triggerScenario(CommandList cmd, int index) {

		if (index == 0) {
			JOptionPane.showMessageDialog(null, "Error, select a valid feature to add.");
		} else if (index == 1) {
			String textToSpeech = JOptionPane.showInputDialog(null, "Enter the Text You Want Said Via Text-To-Speech.",
					"Enter the Text You Want Said Via Text-To-Speech", JOptionPane.QUESTION_MESSAGE);

			if (textToSpeech != null) {
				cmd.add(textToSpeech);
			}
		} else if (index == 2) {
			String fileName = "";// = JOptionPane.showInputDialog(null,"Enter the Text You Want Said Via
			// Text-To-Speech.","Enter the Text You Want Said Via
			// Text-To-Speech",JOptionPane.QUESTION_MESSAGE);
			Authoring2.sound_chooser.setCurrentDirectory(new File("FactoryScenarios/AudioFiles/"));
			if (Authoring2.sound_chooser.showOpenDialog(Authoring2.btnAddFeature) == JFileChooser.APPROVE_OPTION) {
				fileName = Authoring2.sound_chooser.getSelectedFile().getName().toString();
				cmd.add("/~sound:" + fileName);
			}
		} else if (index == 3) {
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
		} else if (index == 4) {
			String brailleString = JOptionPane.showInputDialog(null, "Enter the String to be Displayed In Braille",
					"Enter the String to be Displayed in Braille", JOptionPane.QUESTION_MESSAGE);

			if (brailleString != null) {
				cmd.add("/~disp-string:" + brailleString);
			}
		} else if (index == 5) {
			String textToRepeat = JOptionPane.showInputDialog(null, "Enter the Text to Repeat",
					"Enter the Text to Repeat.", JOptionPane.QUESTION_MESSAGE);

			if (textToRepeat != null) {
				cmd.add("/~repeat");
				cmd.add(textToRepeat);
				cmd.add("/~endrepeat");
			}
		} else if (index == 6) {
			String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Repeated",
					"Enter the Button Number You Want Repeated.", JOptionPane.QUESTION_MESSAGE);

			if (buttonIndex != null && Integer.parseInt(buttonIndex) > 0
					&& Integer.parseInt(buttonIndex) <= Authoring2.buttonNum) {
				cmd.add("/~repeat-button:" + (Integer.parseInt(buttonIndex) - 1));
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
			}
		} else if (index == 7) {
			String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Skipped.",
					"Enter the Button Number You Want Skipped.", JOptionPane.QUESTION_MESSAGE);
			String identifier = JOptionPane.showInputDialog(null, "Where Do You Want to Skip to? Enter an Identifier.",
					"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE);

			if (buttonIndex != null && Integer.parseInt(buttonIndex) > 0
					&& Integer.parseInt(buttonIndex) <= Authoring2.buttonNum) {
				cmd.add("/~skip-button:" + (Integer.parseInt(buttonIndex) - 1) + " " + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
			}
			// TODO: Do Something here as this is pretty messed up.
			String identifier2 = JOptionPane.showInputDialog(null, "Enter the Identifier You Previously Used.",
					"Enter the Identifier You Previously Used.", JOptionPane.QUESTION_MESSAGE);

			if (identifier2 != null) {
				cmd.add("/~" + identifier2);
			}

		} else if (index == 8) {
			Authoring2.addUserInputString();
		} else if (index == 9) {
			cmd.add("/~reset-buttons");
		} else if (index == 10) {
			String buttonIndex = JOptionPane.showInputDialog(null, "Enter the Button Number You Want Skipped.",
					"Enter the Button Number You Want Skipped.", JOptionPane.QUESTION_MESSAGE);
			String identifier = JOptionPane.showInputDialog(null, "Where Do You Want to Skip to? Enter an Identifier.",
					"Where Do You Want to Skip to? Enter an Indentifier.", JOptionPane.QUESTION_MESSAGE);

			if (buttonIndex != null && Integer.parseInt(buttonIndex) > 0
					&& Integer.parseInt(buttonIndex) <= Authoring2.buttonNum) {
				cmd.add("/~skip-button:" + (Integer.parseInt(buttonIndex) - 1) + " " + identifier);
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Button Number Is Valid And Exists");
			}

			// TODO: Do something here please.

			String identifier2 = JOptionPane.showInputDialog(null, "Enter Identifier Where to Skip to.",
					"Enter Identifier Where to Skip to.", JOptionPane.QUESTION_MESSAGE);

			if (identifier != null) {
				cmd.add("/~" + identifier2);
			}

		} else if (index == 11) {
			cmd.add("/~disp-clearAll");
		} else if (index == 12) {
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Cleared.",
					"Enter the Cell Number You Want Cleared.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && Integer.parseInt(cellIndex) > 0
					&& Integer.parseInt(cellIndex) <= Authoring2.cellNum) {
				cmd.add("/~disp-clear-cell:" + (Integer.parseInt(cellIndex) - 1));
			} else {
				JOptionPane.showMessageDialog(null, "Error, Please Make Sure the Cell Number Is Valid And Exists");
			}
		} else if (index == 13) {
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
					"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE);

			String sequence = JOptionPane.showInputDialog(null,
					"Enter the 8-Character Sequence Consisting of 0's and 1's.",
					"Enter the  8-Character Sequence Consisting of 0's and 1's.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && Integer.parseInt(cellIndex) > 0
					&& Integer.parseInt(cellIndex) <= Authoring2.cellNum && sequence.length() == 8
					&& sequence.matches("[01]+")) {
				cmd.add("/~disp-cell-pins:" + (Integer.parseInt(cellIndex) - 1) + " " + sequence);
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, Please Make Sure the Cell Number Is Valid And Exists and the 8-Character Sequence is Valid.");
			}
		} else if (index == 14) {
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Displayed.",
					"Enter the Cell Number You Want Displayed.", JOptionPane.QUESTION_MESSAGE);

			String alphabet = JOptionPane.showInputDialog(null, "Enter a Valid English Alphabet.",
					"Enter a Valid English Alphabet.", JOptionPane.QUESTION_MESSAGE);
			char c = alphabet.charAt(0);

			if (cellIndex != null && Integer.parseInt(cellIndex) > 0
					&& Integer.parseInt(cellIndex) <= Authoring2.cellNum && alphabet.length() == 1
					&& Character.isLetter(c)) {
				cmd.add("/~disp-cell-char:" + (Integer.parseInt(cellIndex) - 1) + " " + alphabet);
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, Please Make Sure the Cell Number Is Valid And Exists and a Valid English Alphabet is Selected.");
			}
		} else if (index == 15) {
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Raised.",
					"Enter the Cell Number You Want Raised.", JOptionPane.QUESTION_MESSAGE);

			String pinIndex = JOptionPane.showInputDialog(null, "Enter the Pin Number to Raise.",
					"Enter the  Pin Number to Raise.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && Integer.parseInt(cellIndex) > 0
					&& Integer.parseInt(cellIndex) <= Authoring2.cellNum && Integer.parseInt(pinIndex) > 0
					&& Integer.parseInt(pinIndex) <= 8) {
				cmd.add("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) - 1) + " "
						+ (Integer.parseInt(pinIndex) - 1));
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, Please Make Sure the Cell Number Is Valid And Exists and the Pin Number is between 1 and 8.");
			}
		} else if (index == 16) {
			String cellIndex = JOptionPane.showInputDialog(null, "Enter the Cell Number You Want Lowered.",
					"Enter the Cell Number You Want Lowered.", JOptionPane.QUESTION_MESSAGE);

			String pinIndex = JOptionPane.showInputDialog(null, "Enter the Pin Number to Lower.",
					"Enter the  Pin Number to Lower.", JOptionPane.QUESTION_MESSAGE);

			if (cellIndex != null && Integer.parseInt(cellIndex) > 0
					&& Integer.parseInt(cellIndex) <= Authoring2.cellNum && Integer.parseInt(pinIndex) > 0
					&& Integer.parseInt(pinIndex) <= 8) {
				cmd.add("/~disp-cell-raise:" + (Integer.parseInt(cellIndex) - 1) + " "
						+ (Integer.parseInt(pinIndex) - 1));
			} else {
				JOptionPane.showMessageDialog(null,
						"Error, Please Make Sure the Cell Number Is Valid And Exists and the Pin Number is between 1 and 8.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error, select a valid feature to add.");
		}
		Authoring2.saveFile = true;
	}

}
