package uk.ac.cam.ms2316.oopjava.tick5;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.*;
import uk.ac.cam.acr31.life.World;

public abstract class PatternPanel extends JPanel {

	private JList guiList;
	private Pattern currentPattern;
	private List<Pattern> patternList;
	
	protected abstract void onPatternChange();
	
	public PatternPanel() {
		super();
		setLayout(new BorderLayout());
		guiList = new JList();
		
		guiList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && (patternList != null)) {
					int sel = guiList.getSelectedIndex();
					if (sel != -1) {
						currentPattern = patternList.get(sel);
						onPatternChange();
					}
				}
			}
		});
		
		add(new JScrollPane(guiList));
		currentPattern = null;
	}
	
	public Pattern getCurrentPattern() {
		return currentPattern;
	}
	
	public void setPatterns(List<Pattern> list) {
		patternList = list;
		
		if (list == null) {
			currentPattern = null; //if list is null, then no valid pattern
			guiList.setListData(new String[]{}); //no list item to select
			return;
		}
		
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			names.add( (list.get(i)).getName() + " (" + (list.get(i)).getAuthor() + ")" );
		}
		
		guiList.setListData(names.toArray());
		currentPattern = list.get(0); //select first element in list
		guiList.setSelectedIndex(0); //select first element in guiList
	}
}
