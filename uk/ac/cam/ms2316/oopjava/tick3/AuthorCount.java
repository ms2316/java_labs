package uk.ac.cam.ms2316.oopjava.tick3;

public class AuthorCount implements Comparable<AuthorCount> {

   private String author;
   private int count;
	
   public AuthorCount(String author) {
      this.author = author;
      this.count = 1;
   }
	
   public void inc() {
      count++;
   }

   @Override
   public int hashCode() {
      return author.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      return ( (obj instanceof AuthorCount) &&  ((AuthorCount)obj).author.equals(this.author) );
   }

   @Override
   public int compareTo(AuthorCount o) {
      //TODO: return -1 if 'this' rank orders before 'o', 
      //      return 0 if 'this' and 'o' are equal,
      //      return 1 if 'this' rank orders after 'o'
      //      to do so order first by 'count' (largest first), and if counts
      //      are equal, then order by 'name' as a tie-breaker.
      
      if (this.count > o.count || ((this.count == o.count) && (this.author.compareTo(o.author) < 0) )) return (-1);
      else if (this.count < o.count || ((this.count == o.count) && (this.author.compareTo(o.author) > 0) )) return 1;
      else return 0;
   }

   @Override
   public String toString() {
	  return String.format("%-20s%3s", this.author, Integer.toString(this.count)); 
	  
      //TODO: return a string whose:
      //      first 20 characters contains the author name (left-aligned) 
      //      and final 3 characters contains the count (right-aligned)
      //Hint: You might find String.format(...) helpful here.
   }
}
