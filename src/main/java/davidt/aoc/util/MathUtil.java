package davidt.aoc.util;

public class MathUtil
{
   public static long gcd(long a, long b)
   {
      if (b == 0)
         return a;
      return gcd(b, a % b);
   }
   
   
   public static long lcm(long a, long b)
   {
      return a * b / gcd(a, b);
   }
   
   
   public static long lcm(long[] vals)
   {
      long lcm = vals[0];
      
      for (int i = 1; i < vals.length; i++)
      {
         lcm = lcm(lcm, vals[i]);
      }
      
      return lcm;
   }
   
   
   public static long binomial(int n, int k)
   {
      long result = 1;
      
      int j = 1;
      
      for (int i = k + 1; i <= n; i++)
      {
         result *= i;
         
         while (result % j == 0 && j <= (n - k))
         {
            result /= j++;
         }
      }
      
      return result;
   }
   
   
   public static long factorial(int n)
   {
      long retVal = 1;
      
      for (int i = 2; i <= n; i++)
      {
         retVal *= i;
      }
      
      return retVal;
   }
}
