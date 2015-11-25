package uk.ac.cam.ms2316.oopjava.tick3;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class RefactorLife {
	//NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS
	public static void main(String[] args) throws java.io.IOException {
		if (args.length != 1 && args.length != 2) {
			System.out.println("RefactorLife [optional worldType] [pattern]");
			return;
		}

		String worldType = args.length == 2 ? args[0] : "--array";
		if (args.length == 1 && (args[0].equals("--array") || args[0].equals("--long") || args[0].equals("--aging") ) ) {
			System.out.println("Please specify a pattern.");
			return;
		}
		
		try {
			Pattern p = new Pattern(args[args.length == 2 ? 1 : 0]);
		 
			World world = null;
			if (worldType.equals("--array")) {
			  world = new ArrayWorld(p.getWidth(), p.getHeight());
			} else if (worldType.equals("--long")) {
			  world = new PackedWorld(); 
			} else if (worldType.equals("--aging")) {
			  world = new AgingWorld(p.getWidth(), p.getHeight());
			} 
			else {
			  System.out.println("Invalid worldType");
			  return; 
			}
			
			p.initialise(world);
			play(world);
		} catch (PatternFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void play(World world) throws java.io.IOException {
		WorldViewer viewer = new WorldViewer();
		int userResponse = 0;
		while (userResponse != 'q') {
			world.print( new OutputStreamWriter(System.out) );
			viewer.show(world);
			userResponse = System.in.read();
			world = world.nextGeneration(0);
		}
		viewer.close();
	}
}
