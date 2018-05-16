package enamel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Logging {

	private HashMap<String, Integer> log;
	
	String postFix = "";
	
	public Logging(String postFix) {
		log = new HashMap<>();
		this.postFix = postFix;
		File logFile = new File("Logs/AuthoringLogFile-"+postFix+".txt");
		
		if(logFile.exists()) {
			Scanner scanner;
			try {
				scanner = new Scanner(logFile);
				while(scanner.hasNextLine()) {
					String[] line = scanner.nextLine().split("\t");
					log.put(line[0], Integer.valueOf(line[1]));
				}
				
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void fine(String str) {
		Logger.getGlobal().fine(str);
		if(log.containsKey(str)) {
			int count = log.get(str);
			log.put(str, count + 1);
		}else {
			log.put(str, 1);
		}
	}
	
	public void severe(String str) {
		Logger.getGlobal().severe(str);
		if(log.containsKey(str)) {
			int count = log.get(str);
			log.put(str, count + 1);
		}else {
			log.put(str, 1);
		}
	}
	
	public void info(String str) {
		Logger.getGlobal().info(str);
		if(log.containsKey(str)) {
			int count = log.get(str);
			log.put(str, count + 1);
		}else {
			log.put(str, 1);
		}
	}
	
	public void saveAndReturn() {
		Set<Entry<String, Integer>> set = log.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        
            //System.out.println(entry.getKey()+" ==== "+entry.getValue());
            File logFile = new File("Logs/AuthoringLogFile-"+postFix+".txt");
            if(!logFile.exists()) {
            	try {
					logFile.createNewFile();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            StringBuffer buffer = new StringBuffer();
			FileWriter writer;
			try {
				writer = new FileWriter(logFile);
				for(Map.Entry<String, Integer> entry : list) {
					buffer.append(String.format(entry.getKey() + "\t" + entry.getValue() + "%n"));
				}
				writer.write(buffer.toString());
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        
        
	}
	
}
