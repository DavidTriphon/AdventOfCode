package davidt.aoc.map;

public enum Polarity
{
   // neutral needs to be first so that increasing dimension count doesn't
   // change a Direction's numeric.
   NEUTRAL(0),
   POSITIVE(1),
   NEGATIVE(-1),
   ;
   
   public final int magnitude;
   
   
   Polarity(int magnitude)
   {
      this.magnitude = magnitude;
   }
   
   
   public static int getNumeric(Polarity... pols)
   {
      int coefficient = 1;
      int sum = 0;
      
      for (Polarity polarity : pols)
      {
         sum += polarity.magnitude * coefficient;
         coefficient *= 3;
      }
      
      return sum;
   }
   
   
   public static int getNumeric(int dimension, Polarity polarity)
   {
      return (int) Math.pow(3, dimension) * polarity.magnitude;
   }
}
