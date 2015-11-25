package uk.ac.cam.ms2316.oopjava.tick1;

public class ExceptionTest {
   public static void main(String[] args) {
      System.out.print("C");
      try {
         a();
      } catch (Exception e) { //Bad practice
         System.out.print(e.getMessage());
      }
      System.out.println("A");
   }

   public static void a() throws Exception {
      System.out.print("S");
      b();
      System.out.print("J");
   }

   public static void b() throws Exception {
      System.out.print("T");
      if (1+2+3==6)
         throw new Exception("1"); //Bad practice
      System.out.print("V");
   }
}