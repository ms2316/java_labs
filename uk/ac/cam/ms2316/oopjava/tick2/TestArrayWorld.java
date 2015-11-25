package uk.ac.cam.ms2316.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestArrayWorld implements World {

   private int generation;
   private int width;
   private int height;
   private boolean[][] cells;

   public TestArrayWorld(int w, int h) {
      width = w;
      height = h;
	  generation = 0;
	  cells = new boolean[height][width];
   }

   protected TestArrayWorld(TestArrayWorld prev) {
      width = prev.width; //why can we do this if width is private?
      height = prev.height;
	  generation = prev.generation + 1;
	  cells = new boolean[height][width];
   }

   public boolean getCell(int col, int row) {
	  if (row < 0 || row >= height) return false;
	  if (col < 0 || col >= width) return false;
		
	  return cells[row][col];
   }
   
   public void setCell(int col, int row, boolean alive) {
      if (row >= 0 && row < height && col >= 0 && col < width)
			cells[row][col] = alive;
   }
   
   
   public int getWidth()  { return width; }
   public int getHeight()  { return height; }
   public int getGeneration()  { return generation; }
   public int getPopulation()  { return 0; }
   
   public void print(Writer w)  { 
	  PrintWriter pw = new PrintWriter(w);
	  pw.println("-");
	  
	  for (int row = 0; row < height; row++) { 
			for (int col = 0; col < width; col++) {
				pw.print(this.getCell(col, row) ? "#" : "_"); 
			}
		pw.println();
	  } 
	  pw.flush();
   }
   
   public void draw(Graphics g, int width, int height)  { /*Empty*/ }

   private TestArrayWorld nextGeneration() {
      TestArrayWorld world = new TestArrayWorld(this);
	  
	  for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				world.setCell(j,i,this.computeCell(j,i));
			}
	  }
	  
      return world;
   }

   public World nextGeneration(int log2StepSize)  { 
      TestArrayWorld world = this;
      //TODO: repeat the statement in curly brackets 2^log2StepSize times
	  int total = 1 << log2StepSize;
	  for (int i = 0; i < total; i++)
      {
         world = world.nextGeneration();
      }
      return world;
   }
     
    private boolean computeCell(int col, int row) {	
		boolean liveCell = this.getCell(col, row);
		int neighbours = this.countNeighbours(col, row);
		boolean nextCell = false;
		
		if ( (liveCell && (neighbours == 2 || neighbours == 3)) || (!liveCell && neighbours == 3) )
			nextCell = true;
			
		return nextCell;
	}
	
	private int countNeighbours(int col, int row) {
		int cnt = 0;
		if (this.getCell(col-1,row)) cnt++;
		if (this.getCell(col-1,row-1)) cnt++;
		if (this.getCell(col-1,row+1)) cnt++;
		if (this.getCell(col,row-1)) cnt++;
		if (this.getCell(col,row+1)) cnt++;
		if (this.getCell(col+1,row)) cnt++;
		if (this.getCell(col+1,row-1)) cnt++;
		if (this.getCell(col+1,row+1)) cnt++;
		return cnt;
	}

   //TODO: Add any other private methods which you find helpful      
}