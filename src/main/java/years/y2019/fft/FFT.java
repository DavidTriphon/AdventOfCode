package years.y2019.fft;

import java.util.*;


public class FFT
{
   private static final short[] PATTERN = new short[] {1, 0, -1, 0};
   
   
   public static int[] process(int[] digits)
   {
      int[] resDigits = new int[digits.length];
      
      for (int i = 0; i < resDigits.length; i++)
      {
         long sum = 0;
         
         int patternIndex = 0;
         
         for (int j = i; j < resDigits.length; )
         {
            for (int k = 0; k <= i && j < resDigits.length; k++, j++)
            {
               sum += digits[j] * PATTERN[patternIndex];
            }
            
            patternIndex = (patternIndex + 1) % PATTERN.length;
         }
         
         if (sum < 0)
            sum = -sum;
         
         resDigits[i] = (int) (sum % 10);
      }
      
      return resDigits;
   }
   
   
   public static int[] processWithLargeOffset(
      int[] digits, int repeats, int offset, int phases, int reportCount)
   {
      int[] result = new int[reportCount];
      int[] phaseDigits = new int[phases];
      
      int fullDigitsLength = digits.length * repeats;
      
      Arrays.fill(phaseDigits, digits[digits.length - 1]);
      
      for (int i = fullDigitsLength - 2; i >= offset; i--)
      {
         int digitIndex = i % digits.length;
         
         phaseDigits[0] += digits[digitIndex];
         
         for (int phase = 1; phase < phases; phase++)
         {
            phaseDigits[phase] = (phaseDigits[phase] + phaseDigits[phase - 1]) % 10;
         }
         
         if (i < offset + reportCount)
            result[i - offset] = phaseDigits[phases - 1];
      }
      
      return result;
   }
   
   
   public static int[] getDigitsFromString(String string)
   {
      return string.chars().map((i) -> Integer.parseInt("" + (char) i)).toArray();
   }
   
   
   public static String stringifyDigits(int[] digits)
   {
      StringBuilder sb = new StringBuilder();
      
      for (int digit : digits)
      {
         sb.append(digit);
      }
      
      return sb.toString();
   }
}
