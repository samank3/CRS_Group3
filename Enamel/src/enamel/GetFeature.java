package enamel;

public class GetFeature {

	public static int type(String s) {
		if(s == null || s.equals("")) {
			return -1;
		}
		
		if(s.startsWith("Cell")) {
			int cells = Integer.valueOf(s.replace("Cell ", "").trim());
			Authoring.cellNum = cells;
			return AuthUtil.EDIT_CELL;
		}else if(s.startsWith("Button")) {
			int buttons = Integer.valueOf(s.replace("Button ", "").trim());
			Authoring.buttonNum = buttons;
			return AuthUtil.EDIT_BUTTON;
		}else if(s.startsWith("/~sound")) {
			return AuthUtil.ADD_SOUND_FILE;
		}else if(s.startsWith("/~pause")) {
			return AuthUtil.ADD_PAUSE;
		}else if(s.startsWith("/~disp-string")) {
			return AuthUtil.DISPLAY_BRAILLE_STRING;
		}else if(s.startsWith("/~repeat")) {
			return AuthUtil.ADD_REPEAT_STRING;
		}else if(s.startsWith("/~repeat-button")) {
			return AuthUtil.ADD_REPEAT_BUTTON;
		}else if(s.startsWith("/~skip-button")) {
			return AuthUtil.SKIP_BUTTON;
		}else if(s.startsWith("/~skip")) {
			return AuthUtil.ADD_SKIP;
		}else if(s.startsWith("/~user-input")) {
			return AuthUtil.ADD_USER_INPUT;
		}else if(s.startsWith("/~reset-buttons")) {
			return AuthUtil.RESET_BUTTONS;
		}else if(s.startsWith("/~disp-clearAll")) {
			return AuthUtil.CLEAR_ALL_CELLS;
		}else if(s.startsWith("/~disp-cell-clear")) {
			return AuthUtil.CLEAR_SPECIFIC_CELL;
		}else if(s.startsWith("/~disp-cell-pins")) {
			return AuthUtil.SET_SPECIFIC_CELL;
		}else if(s.startsWith("/~disp-cell-char")) {
			return AuthUtil.DISPLAY_CELL_CHARACTER;
		}else if(s.startsWith("/~disp-cell-raise")) {
			return AuthUtil.RAISE_ONE_PIN;
		}else if(s.startsWith("/~disp-cell-lower")) {
			return AuthUtil.LOWER_ONE_PIN;
		}else if(s.startsWith("/~")) {
			return AuthUtil.INSERT_IDENTIFIER;
		}else {
			return AuthUtil.TEXT_TO_SPEECH;
		}
		
		
	}
	
}
