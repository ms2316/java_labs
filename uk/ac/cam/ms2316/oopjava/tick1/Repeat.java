package uk.ac.cam.ms2316.oopjava.tick1;

public class Repeat {

   public static void main(String[] args) {
      System.out.println(parseAndRep(args));
   }

   /*
    * Return the first string repeated by the number of times
    * specified by the integer in the second string, for example 
    *   
    *    parseAndRep(new String[]{"one","3"}) 
    *
    * should return "one one one". Adjacent copies of the repeated 
    * string should be separated by a single space.
    *
    * Return a suitable error message in a string when there are 
    * not enough arguments in 'args' or the second argument is 
    * not a valid positive integer. For example:
    *
    *  - parseAndRep(new String[]{"one"}) should return 
    *    "Error: insufficient arguments"
    *
    *  - parseAndRep(new String[]{"one","five"}) should return 
    *    "Error: second argument is not a positive integer"
    */
	
	public static String parseAndRep(String[] args) {
		if (args.length < 2) return "Error: insufficient arguments";
		else {
			try {
				int cnt = Integer.parseInt(args[1]);
				if (cnt < 1) throw new Exception();
				String str = new String(new char[cnt]).replace("\0", args[0]+" "); 
				return( str.substring(0, str.length()-1) );
			} catch (Exception err) {
				return "Error: second argument is not a positive integer";
			}
		}
	}
   
}