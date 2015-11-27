package uk.ac.cam.ms2316.oopjava.tick3;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;

public class LoaderLife {
	//NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS
	public static void main(String[] args) throws java.io.IOException {
		if (args.length == 0 || args.length > 3) {
			System.out.println("LoaderLife [optional worldType] [patternSource] [option patternIndex]");
			return;
		}

		String worldType = "";
		
		//3 args expected
		if (args[0].equals("--array") || args[0].equals("--long") || args[0].equals("--aging") )  {
			worldType = args[0];
			if (args.length < 3) {
				System.out.println("Please specify a pattern source.");
				return;
			}
		}
			
		//loading data 
		List<Pattern> l;
		String src;
		if (worldType.equals("")) {
			src = args[0];
		} else {
			src = args[1];
		}
		if ( src.startsWith("http://") ) {
			try {
				l = PatternLoader.loadFromURL(src);
			} catch (IOException e) {
				System.out.println("An error occurred loading from URL: " + src);
				return;
			}
		} else {
			try {
				l = PatternLoader.loadFromDisk(src);
			} catch (IOException e) {
				System.out.println("An error occurred loading from file: " + src);
				return;
			}
		}
		//loading data ends
		
		
		if (args.length == 1) {
			//only print
			for (int i = 0; i < l.size(); i++) {
				Pattern p = l.get(i);
				System.out.println( i + "\t" + p.getName() + "\t" + p.getAuthor() );
			}
		} else {
			//gameplay
			
			int getpos;
			if (worldType.equals("")) {
				worldType = "--array";
				getpos = 1;
			} else {
				getpos = 2;
			}
			
			int index;
			try {
				index = Integer.parseInt(args[getpos]);
			} catch (Exception e) {
				System.out.println("Could not interpret patternIndex as an integer (given '" + args[getpos] + "').");
				return;
			}
			
			if (index >= l.size()) {
				System.out.println("Chosen index number not present in list.");
				return;
			}
			
			//Play section
			try {
				Pattern p = l.get(index);
				
				World world = null;
				if (worldType.equals("--array")) {
					world = new ArrayWorld(p.getWidth(), p.getHeight());
				} else if (worldType.equals("--long")) {
					world = new PackedWorld(); 
				} else if (worldType.equals("--aging")) {
					world = new AgingWorld(p.getWidth(), p.getHeight());
				}
				
				p.initialise(world);
				play(world);
			} catch (PatternFormatException e) {
				System.out.println(e.getMessage());
			}
			//Play section ends
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
