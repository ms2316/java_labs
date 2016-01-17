package uk.ac.cam.ms2316.oopjava.tick4;

public class PackedWorld extends WorldImpl {
	
	private long cells;
	
	public PackedWorld() {
		super(8,8);
		cells = 0;
	}
	
	public PackedWorld(PackedWorld prev) {
		super(prev);
		cells = 0;
	}
	
	
	public boolean getCell(int col, int row) {
		if (col >= 0 && col < this.getWidth() && row >= 0 && row < this.getHeight())
			return PackedLong.get(cells, row*(this.getWidth()) + col);
		else
			return false;
	}
	
	public void setCell(int col, int row, boolean value) {
		if (col >= 0 && col < this.getWidth() && row >= 0 && row < this.getHeight()) 
			cells = PackedLong.set(cells, row*(this.getWidth()) + col, value);
	}
   
    protected PackedWorld nextGeneration() {
      PackedWorld world = new PackedWorld(this);
	  
	  for (int i = 0; i < this.getHeight(); i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				world.setCell(j,i,this.computeCell(j,i));
			}
	  }
	  
      return world;
   }
}
