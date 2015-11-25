package uk.ac.cam.ms2316.assignment2;

class TinyLife {

	public static void main(String[] args) throws java.io.IOException {
		play(Long.decode(args[0]));
	}
	
	public static boolean getCell(long world, int col, int row) {
		if (col >= 0 && col < 8 && row >= 0 && row < 8)
			return PackedLong.get(world, row*8 + col);
		else
			return false;
	}
	
	public static long setCell(long world, int col, int row, boolean value) {
		if (col >= 0 && col < 8 && row >= 0 && row < 8) 
			return PackedLong.set(world, row*8 + col, value);
		else 
			return world;
	}
	
	public static void print(long world) { 
		System.out.println("-"); 
		for (int row = 0; row < 8; row++) { 
			for (int col = 0; col < 8; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_"); 
			}
		System.out.println(); 
		} 
	}
	
	public static int countNeighbours(long world, int col, int row) {
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
	
	public static boolean computeCell(long world,int col,int row) {	
		boolean liveCell = getCell(world, col, row);
		int neighbours = countNeighbours(world, col, row);
		boolean nextCell = false;
		
		if ( (liveCell && (neighbours == 2 || neighbours == 3)) || (!liveCell && neighbours == 3) )
			nextCell = true;
			
		return nextCell;
	}
	
	public static long nextGeneration(long world) {
		long nextWorld = world;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				nextWorld = setCell(nextWorld,i,j,computeCell(world,i,j));
			}
		}
		
		return nextWorld;
	}
	
	public static void play(long world) throws java.io.IOException {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}
}