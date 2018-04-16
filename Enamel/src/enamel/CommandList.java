package enamel;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CommandList {

	public DefaultListModel<String> listModel;
	public JList<String> list;
	public ArrayList<Integer> locations;
	public ArrayList<Integer> featureType;
	public int featType;
	
	public CommandList(JList<String> list) {
		this.list = list;
		listModel = new DefaultListModel<String>();
		locations = new ArrayList<>();
		featureType = new ArrayList<>();
	}
	
	public void setLastFeatureType(int index) {
		featType = index;
	}
	
	public void add(String item) {
		listModel.addElement(item);
		locations.add(listModel.getSize() -1);
		featureType.add(featType);
		list.setModel(listModel);
	}
	
	public void remove(int index) {
		listModel.remove(index);
		locations.remove(index);
		featureType.remove(index);
		list.setModel(listModel);
	}
	
	public String get(int index) {
		return listModel.get(index);
	}
	
	public int getType(int index) {
		return featureType.get(index);
	}
	
	public void reset() {
		listModel = new DefaultListModel<String>();
		featureType = new ArrayList<Integer>();
		locations = new ArrayList<Integer>();
		list.setModel(listModel);
	}
	
	public void replace(int index, String str) {
		listModel.set(index, str);
		list.setModel(listModel);
	}
	
	public int size() {
		return listModel.size();
	}
	
	public void moveUp(int index) {
		
		if(listModel.getElementAt(index -1).equals("/~endrepeat")) {
			String temp = listModel.get(index);
			listModel.set(index, listModel.get(index-1));
			listModel.set(index -1, temp);
			temp = listModel.get(index-1);
			listModel.set(index -1, listModel.get(index-2));
			listModel.set(index -2, temp);
			temp = listModel.get(index-2);
			listModel.set(index -2, listModel.get(index-3));
			listModel.set(index -3, temp);
			list.setModel(listModel);
			
			int temp2 = locations.get(index);
			locations.set(index, locations.get(index-1));
			locations.set(index -1, temp2);
			temp2 = locations.get(index-1);
			locations.set(index -1, locations.get(index-2));
			locations.set(index -2, temp2);
			temp2 = locations.get(index-2);
			locations.set(index -2, locations.get(index-3));
			locations.set(index -3, temp2);
			
			int temp3 = featureType.get(index);
			featureType.set(index, featureType.get(index-1));
			featureType.set(index -1, temp3);
			temp3 = featureType.get(index-1);
			featureType.set(index -1, featureType.get(index-2));
			featureType.set(index -2, temp3);
			temp3 = featureType.get(index-2);
			featureType.set(index -2, featureType.get(index-3));
			featureType.set(index -3, temp3);
			list.setModel(listModel);
			return;
		}

		if(listModel.getElementAt(index).equals("/~repeat")) {
			moveDown(index -1);
			return;
		}
		
		String temp = listModel.get(index);
		listModel.set(index, listModel.get(index-1));
		listModel.set(index -1, temp);
		
		int temp2 = locations.get(index);
		locations.set(index, locations.get(index-1));
		locations.set(index -1, temp2);
		
		int temp3 = featureType.get(index);
		featureType.set(index, featureType.get(index-1));
		featureType.set(index -1, temp3);
		
		list.setModel(listModel);
	}
	
	public void moveDown(int index) {
		
		if(listModel.getElementAt(index +1).equals("/~repeat")) {
			String temp = listModel.get(index);
			listModel.set(index, listModel.get(index +1));
			listModel.set(index +1, temp);
			temp = listModel.get(index + 1);
			listModel.set(index + 1, listModel.get(index +2));
			listModel.set(index +2, temp);
			temp = listModel.get(index + 2);
			listModel.set(index + 2, listModel.get(index +3));
			listModel.set(index + 3, temp);
			
			int temp2 = locations.get(index);
			locations.set(index, locations.get(index+1));
			locations.set(index +1, temp2);
			temp2 = locations.get(index+1);
			locations.set(index +1, locations.get(index+2));
			locations.set(index +2, temp2);
			temp2 = locations.get(index+2);
			locations.set(index +2, locations.get(index+3));
			locations.set(index +3, temp2);
			
			int temp3 = featureType.get(index);
			featureType.set(index, featureType.get(index+1));
			featureType.set(index +1, temp3);
			temp3 = featureType.get(index+1);
			featureType.set(index +1, featureType.get(index+2));
			featureType.set(index +2, temp3);
			temp3 = featureType.get(index+2);
			featureType.set(index +2, featureType.get(index+3));
			featureType.set(index +3, temp3);
			
			list.setModel(listModel);
			return;
		}
		
		if(listModel.getElementAt(index).equals("/~repeat")) {
			moveUp(index +3);
			return;
		}
		
		String temp = listModel.get(index);
		listModel.set(index, listModel.get(index+1));
		listModel.set(index +1, temp);
		
		int temp2 = locations.get(index);
		locations.set(index, locations.get(index+1));
		locations.set(index +1, temp2);
		
		int temp3 = featureType.get(index);
		featureType.set(index, featureType.get(index+1));
		featureType.set(index +1, temp3);
		
		list.setModel(listModel);
	}
	
}
