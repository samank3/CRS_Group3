package enamel;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class HotkeyListener {

	JFrame frame;
	KeyboardFocusManager keyManager;
		
	public HotkeyListener(JFrame frame) {
		this.frame = frame;
		
		keyManager=KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {

				if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_N
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Create Scenario Hotkey Pressed");
					Authoring.btnCreateScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_O
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Import Scenario Hotkey Pressed");
					Authoring.btnChooseScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_S
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Export Scenario Hotkey Pressed");
					Authoring.btnExportScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_T
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Test Scenario Hotkey Pressed");
					Authoring.btnTestScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_Q
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Create Audio File Hotkey Pressed");
					Authoring.btnCreateAudioFiles.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_E
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Edit Selected Feature Hotkey Pressed");
					Authoring.btnEditSelectedFeature.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_UP
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Move Up Hotkey Pressed");
					Authoring.btnMoveUp.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_DOWN
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Move Down Hotkey Pressed");
					Authoring.btnMoveDown.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_X
						&& isFrameActive() && e.isControlDown()) {
					System.out.println("Remove Selected Feature Hotkey Pressed");
					Authoring.btnRemoveSelectedFeature.doClick();
					return true;
				}
				System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow().getName());
				return false;
			}

		});

	}
	
	
	public static boolean isFrameActive() {
		if(KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow().getName().contains("frame")) {
			return true;
		}else {
			return false;
		}
	}
	
}
