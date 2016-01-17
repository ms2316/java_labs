package uk.ac.cam.ms2316.oopjava.tick5;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import uk.ac.cam.acr31.life.World;

public class PatternPanel extends JPanel {

	private JList guiList;
	
	public PatternPanel() {
		super();
		setLayout(new BorderLayout());
		guiList = new JList();
		add(new JScrollPane(guiList));
	}
	
	public void setPatterns(List<Pattern> list) {
		ArrayList<String> names = new ArrayList<String>();
		//TODO: Using a for loop which iterates over the items
		// in "list" and adds the pattern name and pattern
		// author to "names". For example, if the pattern
		// name and author is "Glider" and "Richard Guy 1970"
		// then you should add the string:
		//
		// "Glider (Richard Guy 1970)"
		//
		// to "names" using the method "add" on "names".
		
		for (int i = 0; i < list.size(); i++) {
			names.add( (list.get(i)).getName() + " (" + (list.get(i)).getAuthor() + ")" );
		}
		
		guiList.setListData(names.toArray());
	}
}
