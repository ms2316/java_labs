package uk.ac.cam.ms2316.assignment1;

public class SetBit {
   public static void main(String [] args) {
      long currentValue = Long.decode(args[0]);
      int position = Integer.parseInt(args[1]);
      boolean value = Boolean.parseBoolean(args[2]);
      currentValue = PackedLong.set(currentValue,position,value);       
      System.out.println(currentValue);
   }
}