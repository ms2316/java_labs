package uk.ac.cam.ms2316.oopjava.tick3;

public class ArrayWorld extends WorldImpl {
	
	private boolean[][] cells;
	
	public ArrayWorld(int w, int h) {
		super(w,h);
		cells = new boolean[this.getHeight()][this.getWidth()];
	}
	
	public ArrayWorld(ArrayWorld prev) {
		super(prev);
		cells = new boolean[this.getHeight()][this.getWidth()];
	}
	
	
	public boolean getCell(int col, int row) {
	  if (row < 0 || row >= this.getHeight()) return false;
	  if (col < 0 || col >= this.getWidth()) return false;
		
	  return cells[row][col];
    }
   
    public void setCell(int col, int row, boolean alive) {
      if (row >= 0 && row < this.getHeight() && col >= 0 && col < this.getWidth())
			cells[row][col] = alive;
    }
   
    protected ArrayWorld nextGeneration() {
      ArrayWorld world = new ArrayWorld(this);
	  
	  for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				world.setCell(j,i,this.computeCell(j,i));
			}
	  }
	  
      return world;
    }

}
