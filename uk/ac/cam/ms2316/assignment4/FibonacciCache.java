package uk.ac.cam.ms2316.assignment4;

public class FibonacciCache {
   public static long[] fib = new long[20];

   public static void store() {
		if (fib.length > 0)
			fib[0] = 1;
		if (fib.length > 1)
			fib[1] = 1;
		for (int i = 2; i < fib.length; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
   }

   public static void reset() {
      for (int i = 0; i < fib.length; i++) {
		fib[i] = 0;
	  }
   }
 
   public static long get(int i) throws Exception/*TODO: declare the checked exception*/ {
      //TODO: return the value of the element in fib found at index i
      //      e.g. "get(3)" should return the fourth element of fib
		try {
			return fib[i];
		} catch (Exception e) {
			throw new Exception("Index out of bounds!!");
		}
      //TODO: your code should throw an Exception with a suitable message
      // if the value requested is out of bounds of the array
   }

   public static void main(String[] args) {
      //TODO: catch exceptions as appropriate and print error messages
	  try {
		store();
		int i = Integer.decode(args[0]);
		System.out.println(get(i));
	} catch (NumberFormatException e) {
		System.out.println("Invalid number");
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
   }
  
}