package enamel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

public class AuthoringTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testImportScenario() {
		File testFile = new File("FactoryScenarios/Scenario_1.txt");
		Authoring.file_chooser = new JFileChooser();
		Authoring.file_chooser.setSelectedFile(testFile);
		String fileName = Authoring.file_chooser.getSelectedFile().getName();
		assertEquals("Scenario_1.txt", fileName);
	}

	@Test
	public void testExportScenario() {
		File testFile = new File("FactoryScenarios/Scenario_test.txt");
		Authoring.file_chooser = new JFileChooser();
		Authoring.file_chooser.setSelectedFile(testFile);
		File currentFile = Authoring.file_chooser.getSelectedFile();

		String testString = "Cell 1 \n Button 4 \n This is a test case \n";

		try {
			if (!currentFile.exists()) {
				currentFile.createNewFile();
			}

			FileWriter fw = new FileWriter(currentFile);
			fw.write(testString);
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(true, currentFile.exists());
	}

	@Test
	public void testCreateScenarios() {
		Authoring.scenarioReader = new JTextArea();
		Authoring.testCreateScenarios();
		assertEquals("Cell 1\nButton 4\n", Authoring.scenarioReader.getText());
	}

	@Test
	public void testAddNewEvent() {
		Authoring.scenarioReader = new JTextArea();
		Authoring.commands = new CommandList(new JList<String>());
		Authoring.addUserInputString();
		assertEquals("/~user-input", Authoring.commands.get(0).toString());
		//assertEquals("/~user-input\n", Authoring.scenarioReader.getText());
	}
	
	@Test
	public void testTextToSpeech() {
		Authoring.commands = new CommandList(new JList<String>());
		AuthUtil.triggerScenario(Authoring.commands, AuthUtil.TEXT_TO_SPEECH, AuthUtil.CREATE,
				0);
		assertEquals("Hello Test",Authoring.commands.get(0).toString());
	}
	
	@Test
	public void testAddPause() {
		Authoring.commands = new CommandList(new JList<String>());
		AuthUtil.triggerScenario(Authoring.commands, AuthUtil.ADD_PAUSE, AuthUtil.CREATE, 0);
		assertEquals("/~pause:5", Authoring.commands.get(0).toString());
	}
	
	@Test
	public void testDisplayBrailleString() {
		Authoring.commands = new CommandList(new JList<String>());
		Authoring.commands.add("Cell 1");
		Authoring.commands.add("Button 4");
		AuthUtil.triggerScenario(Authoring.commands, AuthUtil.DISPLAY_BRAILLE_STRING, AuthUtil.CREATE, 0);
		assertEquals("/~disp-string:Test", Authoring.commands.get(2).toString());
	}
	
	@Test
	public void testSetSpecificCell() {
		Authoring.commands = new CommandList(new JList<String>());
		Authoring.cellNum = 1;
		Authoring.buttonNum = 4;
		AuthUtil.triggerScenario(Authoring.commands, AuthUtil.SET_SPECIFIC_CELL, AuthUtil.CREATE, 0);
		assertEquals("/~disp-cell-pins:0 10101010", Authoring.commands.get(0).toString());
	}
	
	@Test
	public void testResetButtons() {
		Authoring.commands = new CommandList(new JList<String>());
		AuthUtil.triggerScenario(Authoring.commands, AuthUtil.RESET_BUTTONS, AuthUtil.CREATE, 0);
		assertEquals("/~reset-buttons", Authoring.commands.get(0).toString());
	}

	@Test
	public void testValidScenario() throws FileNotFoundException {
		File correctFormat = new File("FactoryScenarios/Scenario_1.txt");
		File wrongFormat = new File("FactoryScenarios/Scenario_wrong.txt");
		int count = 0;
		boolean result = false;
		Scanner scanner = new Scanner(wrongFormat);

		while (scanner.hasNextLine()) {
			// Check if first two lines follow the specific plan.
			String line = scanner.nextLine();
			if (count == 0) {
				if (line.startsWith("Cell")) {
					result = true;

				}
			}
			if (count == 1) {
				if (line.startsWith("Button")) {
					result = true;
				}
			}

		}
		scanner.close();
		assertEquals(result, false);

		scanner = new Scanner(correctFormat);
		while (scanner.hasNextLine()) {
			// Check if first two lines follow the specific plan.
			String line = scanner.nextLine();
			if (count == 0) {
				if (line.startsWith("Cell")) {
					result = true;

				}
			}
			if (count == 1) {
				if (line.startsWith("Button")) {
					result = true;
				}
			}

		}

		scanner.close();
		assertEquals(result, true);

	}

	@Test
	public void testAudioCreation() throws LineUnavailableException, InterruptedException {
		// Last Test
		AudioRecorder recorder = new AudioRecorder("tester1212", 1);
		recorder.start();
		File testFile = new File("FactoryScenarios/AudioFiles/tester1212.wav");
		assertEquals(true, testFile.exists());
	}

	// Tests the following scenario:
	/*
	 * A script was created using Authoring.java and exported as "MyExample.txt".
	 * This method tests to see if the Authoring app has created the correct script
	 * for the following commands: Cell:6, Button: 4, Display string "hello", Repeat
	 * button "2", Skip button "2" to "3", Pause for 5 seconds, Clear all cells
	 * "MyExample.txt" is then compared with the expected output
	 */
	@Test
	public void testSavedFile() throws FileNotFoundException {

		Scanner in = new Scanner(new FileReader("FactoryScenarios/MyExample.txt"));
		StringBuilder sb = new StringBuilder();
		while (in.hasNext()) {
			sb.append(in.next());
		}
		in.close();
		String outString = sb.toString();

		String expected = "Cell6Button4/~disp-string:hello/~repeat-button:2/~skip-button:02/~skip:3/~pause:5/~disp-clearAll";

		assertEquals(expected, outString);

	}

}
