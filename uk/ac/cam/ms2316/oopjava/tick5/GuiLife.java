package uk.ac.cam.ms2316.oopjava.tick5;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.Timer;
import java.awt.event.*;
import uk.ac.cam.acr31.life.World;

public class GuiLife extends JFrame {

   private PatternPanel patternPanel;
   private ControlPanel controlPanel;
   private GamePanel gamePanel;
   private static World world;
   private int timeDelay = 500; //delay between updates (millisecs)
   private int timeStep = 0;    //progress by (2 ^ timeStep) each time

   
	
   public GuiLife() {
      super("GuiLife");
      setSize(640, 480);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      JComponent optionsPanel = createOptionsPanel();
      add(optionsPanel, BorderLayout.WEST);
      JComponent gamePanel = createGamePanel();
      add(gamePanel, BorderLayout.CENTER);
   }

   private JComponent createOptionsPanel() {
      Box result = Box.createVerticalBox();
      result.add(createSourcePanel());
      result.add(createPatternPanel());
      result.add(createControlPanel());
      return result;
   }

   private void addBorder(JComponent component, String title) {
      Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      Border tb = BorderFactory.createTitledBorder(etch,title);
      component.setBorder(tb);
   }

   private JComponent createGamePanel() {
      JPanel holder = new JPanel();
      addBorder(holder,Strings.PANEL_GAMEVIEW);
      gamePanel = new GamePanel();
      holder.add(gamePanel);
      return new JScrollPane(holder);
   }

   private JComponent createSourcePanel() {
      SourcePanel result = new SourcePanel();
      addBorder(result,Strings.PANEL_SOURCE);
      return result; 
   }

   private JComponent createPatternPanel() {
	  PatternPanel result = new PatternPanel();
	  addBorder(result,Strings.PANEL_PATTERN);
	  patternPanel = result; // REFERENCES 14 TASK
	  return result; 
   }
   
   private JComponent createControlPanel() {
		controlPanel = new ControlPanel() {       
			protected void onSpeedChange(int value) {
				playTimer.setDelay(1+(100-value)*10);       
			}
			protected void onStepChange(int value) {
				timeStep = value;   
			}
			protected void onZoomChange(int value) {
				gamePanel.setZoom(value);   
			}
		};    
		addBorder(controlPanel,Strings.PANEL_CONTROL);
		return controlPanel;
	}
   
   private Timer playTimer = new Timer(timeDelay, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         doTimeStep();
      }
   });

   void doTimeStep() {
      if (world != null) {
         world = world.nextGeneration(timeStep);
         gamePanel.display(world);
      }
   }

   public static void main(String[] args) {
      GuiLife gui = new GuiLife();
      try {
		String url="http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt";
		List<Pattern> list = PatternLoader.loadFromURL(url);
		gui.patternPanel.setPatterns(list);
		world = gui.controlPanel.initialiseWorld(list.get(1));
		gui.gamePanel.display(world);
	  } catch (IOException ioe) {
		//TODO: don't leave empty exception handlers!
		System.out.println("Unable to load Patterns for www.cl.cam.ac.uk/...");
	  } catch (PatternFormatException e) {
		System.out.println("Invalid pattern format");
	  }
	  gui.playTimer.start();
      gui.setVisible(true);
   }
}
