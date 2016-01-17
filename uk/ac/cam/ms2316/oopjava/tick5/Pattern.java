package uk.ac.cam.ms2316.oopjava.tick5;

import uk.ac.cam.acr31.life.World;
//Compiles explicitly only with imported package

public class Pattern {
	
	private String name;
	private String author;
	private int width;
	private int height;
	private int startCol;
	private int startRow;
	private String cells;
	
	public String getName() { return name; }
	public String getAuthor() { return author; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getStartCol() { return startCol; }
	public int getStartRow() { return startRow; }
	public String getCells() { return cells; }

   
	public Pattern(String format) throws PatternFormatException {
		String[] formatString = format.split(":");
		if (formatString.length != 7) 
			throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in pattern (found "+formatString.length+").");
			
		name = formatString[0];
		author = formatString[1];
		
		try {
			width = Integer.parseInt(formatString[2]);
		} catch (Exception e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the width field as a number ('"+formatString[2]+"' given).");
		}

		try {
			height = Integer.parseInt(formatString[3]);
		} catch (Exception e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the height field as a number ('"+formatString[3]+"' given).");
		}
		
		try {
			startCol = Integer.parseInt(formatString[4]);
		} catch (Exception e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('"+formatString[4]+"' given).");
		}
		
		try {
			startRow = Integer.parseInt(formatString[5]);
		} catch (Exception e) {
			throw new PatternFormatException("Invalid pattern format: Could not interpret the startY field as a number ('"+formatString[5]+"' given).");
		}
		
		cells = formatString[6];	
   }

	public void initialise(World world) throws PatternFormatException {
		String[] cell = cells.split(" ");
			
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length(); j++) {
				if ( cell[i].charAt(j) == '1')
					world.setCell(j+startCol, i+startRow, true);
				else if ( cell[i].charAt(j) == '0')
					world.setCell(j+startCol, i+startRow, false);
				else throw new PatternFormatException("Invalid pattern format: Malformed pattern '"+cells+"'.");
			}
		}
	
   }
} 
