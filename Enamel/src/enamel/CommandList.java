package enamel;



import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CommandList {

	public DefaultListModel<String> listModel;
	public JList<String> list;
	//public HashMap<Integer, Integer> map; // Index of Location, Int of Feature
	public ArrayList<Integer> locations;
	public ArrayList<Integer> featureType;
	public int featType;
	
	public CommandList(JList<String> list) {
		this.list = list;
		listModel = new DefaultListModel<String>();
		//map = new HashMap<Integer, Integer>();
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
		System.out.println(featType);
		list.setModel(listModel);
	}
	
	public void remove(int index) {
		listModel.remove(index);
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
		String temp = listModel.get(index);
		listModel.set(index, listModel.get(index-1));
		listModel.set(index -1, temp);
		list.setModel(listModel);
	}
	
	public void moveDown(int index) {
		String temp = listModel.get(index);
		listModel.set(index, listModel.get(index+1));
		listModel.set(index +1, temp);
		list.setModel(listModel);
	}
	
}
