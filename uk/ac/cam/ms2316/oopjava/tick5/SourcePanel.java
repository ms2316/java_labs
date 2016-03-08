package uk.ac.cam.ms2316.oopjava.tick5;

import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.event.*;


public abstract class SourcePanel extends JPanel {
	
	private JRadioButton current;
	
	protected abstract boolean setSourceNone();
    protected abstract boolean setSourceFile();
    protected abstract boolean setSourceLibrary();
    
    
	
	public SourcePanel() {
		super();
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		final JRadioButton none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
		final JRadioButton file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
		final JRadioButton library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
		//handlers
		
		none.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setSourceNone()) {
					current = none; 
				} else {
					current.setSelected(true); 
				}
			}
		});
		
		file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setSourceFile()) {
					//successful: file found and patterns loaded
					current = file; 
				} else {
					//unsuccessful: re-enable previous source choice
					current.setSelected(true); 
				}
			}
		});
		
		library.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setSourceLibrary()) {					
					current = library; 
				} else {
					current.setSelected(true); 
				}
			}
		});
		
		
		//add RadioButtons to this JPanel
		add(none);
		add(file);
		add(library);
		//create a ButtonGroup containing all four buttons
		//Only one Button in a ButtonGroup can be selected at once
		ButtonGroup group = new ButtonGroup();
		group.add(none);
		group.add(file);
		group.add(library);
		
		current = null;
	}
	
}
