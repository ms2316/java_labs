package uk.ac.cam.ms2316.oopjava.tick2;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestPackedWorld implements World {

   private int generation;
   private int width;
   private int height;
   private long cells;

   public TestPackedWorld() {
      width = 8;
      height = 8;
	  generation = 0;
	  cells = 0;
   }

   protected TestPackedWorld(TestPackedWorld prev) {
      width = 8;
      height = 8;
	  generation = prev.generation + 1;
	  cells = 0;
   }

   
   public boolean getCell(int col, int row) {
		if (col >= 0 && col < width && row >= 0 && row < height)
			return PackedLong.get(cells, row*width + col);
		else
			return false;
	}
	
	public void setCell(int col, int row, boolean value) {
		if (col >= 0 && col < width && row >= 0 && row < height) 
			cells = PackedLong.set(cells, row*width + col, value);
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

   private TestPackedWorld nextGeneration() {
      TestPackedWorld world = new TestPackedWorld(this);
	  
	  for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				world.setCell(j,i,this.computeCell(j,i));
			}
	  }
	  
      return world;
   }

   public World nextGeneration(int log2StepSize)  { 
      TestPackedWorld world = this;
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