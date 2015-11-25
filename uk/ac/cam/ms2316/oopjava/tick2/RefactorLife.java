package uk.ac.cam.ms2316.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
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
		if (args.length == 1 && (args[0].equals("--array") || args[0].equals("--long")) ) {
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
			} else {
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
		int userResponse = 0;
		while (userResponse != 'q') {
			world.print( new OutputStreamWriter(System.out) );
			userResponse = System.in.read();
			world = world.nextGeneration(0);
		}
	}
}