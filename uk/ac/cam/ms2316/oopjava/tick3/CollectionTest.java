package uk.ac.cam.ms2316.oopjava.tick3;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class CollectionTest {
	
   public static List<AuthorCount> processWithList(List<Pattern> patterns) {
      List<AuthorCount> list = new LinkedList<AuthorCount>();
      for(Pattern p : patterns) {
		  AuthorCount tmp =  new AuthorCount(p.getAuthor());
		  if (list.contains(tmp)) {
			  AuthorCount a = list.get( list.indexOf(tmp) );
			  a.inc();
		  } else {
			  list.add(tmp);
		  }    
      }
      //TODO: sort "list" and return it to caller.
      Collections.sort(list);
      return list;
   }

   public static List<AuthorCount> processWithMap(List<Pattern> patterns) {

      Map<String,AuthorCount> map = new HashMap<String,AuthorCount>();
      for(Pattern p : patterns) {
		  AuthorCount tmp =  new AuthorCount(p.getAuthor());
		  if ( map.containsKey(p.getAuthor()) ) {
			  AuthorCount a = map.get(p.getAuthor());
			  a.inc();
		  } else {
			  map.put(p.getAuthor(), tmp);
		  }
         //TODO: determine if 'map' contains author name as key
         //if so, increment associated AuthorCount object; if not add one.
      }
      //TODO: copy set of AuthorCount objects associated with 'map' into 
      //an ArrayList.  Sort the list of AuthorCount objects and return it 
      //to the caller.
      List<AuthorCount> list = new ArrayList<AuthorCount>(map.values());
      Collections.sort(list);
      return list;
   }

   public static void main(String[] args) {
      //TODO: write code in here to test both above implementations on the
      //pattern file http://www.cl.cam.ac.uk/teaching/current/OOProg/life.txt
   }
}
