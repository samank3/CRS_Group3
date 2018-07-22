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

				if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F1
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Create Scenario Hotkey Pressed");
					Authoring2.btnCreateScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F2
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Import Scenario Hotkey Pressed");
					Authoring2.btnChooseScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F3
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Export Scenario Hotkey Pressed");
					Authoring2.btnExportScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F4
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Test Scenario Hotkey Pressed");
					Authoring2.btnTestScenario.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F5
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Create Audio File Hotkey Pressed");
					Authoring2.btnCreateAudioFiles.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F6
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Edit Selected Feature Hotkey Pressed");
					Authoring2.btnEditSelectedFeature.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F7
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Move Up Hotkey Pressed");
					Authoring2.btnMoveUp.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F8
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Move Down Hotkey Pressed");
					Authoring2.btnMoveDown.doClick();
					return true;
				}else if(e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_F9
						&& isFrameActive() && e.isShiftDown()) {
					System.out.println("Remove Selected Feature Hotkey Pressed");
					Authoring2.btnRemoveSelectedFeature.doClick();
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
