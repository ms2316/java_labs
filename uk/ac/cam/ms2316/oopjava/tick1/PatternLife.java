package uk.ac.cam.ms2316.oopjava.tick1;

public class PatternLife {
	//NAME:AUTHOR:WIDTH:HEIGHT:STARTCOL:STARTROW:CELLS
	public static void main(String[] args) throws java.io.IOException {
		if (args.length == 0) {
			System.out.println("Please specify a pattern.");
			return;
		}
		try {
			Pattern p = new Pattern(args[0]);
			boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
			p.initialise(world);
			play(world);
		} catch (PatternFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean getCell(boolean[][] world, int col, int row) {
		if (row < 0 || row > world.length - 1) return false;
		if (col < 0 || col > world[row].length - 1) return false;
		
		return world[row][col];
	}
	
	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if (row >= 0 && row < world.length && col >= 0 && col < world[row].length)
			world[row][col] = value;
	}
	
	public static void print(boolean[][] world) { 
		System.out.println("-"); 
		for (int row = 0; row < world.length; row++) { 
			for (int col = 0; col < world[row].length; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_"); 
			}
		System.out.println(); 
		} 
	}
	
	public static int countNeighbours(boolean[][] world, int col, int row) {
		int cnt = 0;
		if (getCell(world,col-1,row)) cnt++;
		if (getCell(world,col-1,row-1)) cnt++;
		if (getCell(world,col-1,row+1)) cnt++;
		if (getCell(world,col,row-1)) cnt++;
		if (getCell(world,col,row+1)) cnt++;
		if (getCell(world,col+1,row)) cnt++;
		if (getCell(world,col+1,row-1)) cnt++;
		if (getCell(world,col+1,row+1)) cnt++;
		return cnt;
	}
	
	public static boolean computeCell(boolean[][] world, int col,int row) {	
		boolean liveCell = getCell(world, col, row);
		int neighbours = countNeighbours(world, col, row);
		boolean nextCell = false;
		
		if ( (liveCell && (neighbours == 2 || neighbours == 3)) || (!liveCell && neighbours == 3) )
			nextCell = true;
			
		return nextCell;
	}
	
	public static boolean[][] nextGeneration(boolean[][] world) {
		boolean[][] nextWorld = new boolean[world.length][];
		for (int i = 0; i < world.length; i++) 
			nextWorld[i] = new boolean[world[i].length];
			
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				setCell(nextWorld,j,i,computeCell(world,j,i));
			}
		}
		
		return nextWorld;
	}
	
	public static void play(boolean[][] world) throws java.io.IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}
}