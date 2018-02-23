package enamel;

<<<<<<< HEAD
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

public class AuthoringTest {

	private Authoring a;
	
	@Before
	public void setUp() throws Exception {
		a = new Authoring();
	}

	@Test
	public void testImportScenario() {
		File testFile = new File("FactoryScenarios/Scenario_1.txt");
		Authoring.file_chooser = new JFileChooser();
		Authoring.file_chooser.setSelectedFile(testFile);
		String fileName = Authoring.file_chooser.getSelectedFile().getName();
		assertEquals("Scenario_1.txt",fileName);
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
		
		
		assertEquals(true,currentFile.exists());
	}
	
	@Test
	public void testCreateScenarios() {
		Authoring.scenarioReader = new JTextArea();
		Authoring.testCreateScenarios();
		assertEquals("Cell 1\nButton 4\n",Authoring.scenarioReader.getText());
	}
	
	@Test
	public void testAddNewEvent() {
		Authoring.btnAddUserInput = new JButton();
		Authoring.scenarioReader = new JTextArea();
		Authoring.addUserInputString();
		assertEquals("/~user-input\n",Authoring.scenarioReader.getText());
	}
	
	

}
=======
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthoringTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
>>>>>>> 74503419b39f3fb71392dd6ae1ad1abc67ae65d5
