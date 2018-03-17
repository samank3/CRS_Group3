package enamel;



import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CommandList {



	public DefaultListModel<String> listModel;
	public JList<String> list;
	
	public CommandList(JList<String> list) {
		this.list = list;
		listModel = new DefaultListModel<String>();
	}
	
	public void add(String item) {
		listModel.addElement(item);
		list.setModel(listModel);
	}
	
	public void remove(int index) {
		listModel.remove(index);
		list.setModel(listModel);
	}
	
	public String get(int index) {
		return listModel.get(index);
	}
	
	public void reset() {
		listModel = new DefaultListModel<String>();
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
