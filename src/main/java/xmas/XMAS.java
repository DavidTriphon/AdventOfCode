package xmas;

import java.util.*;


public class XMAS
{
   public static long findNonSumNumber(List <Long> numbers, int preambleLength)
   {
      boolean foundNumber = false;
      
      int i;
      
      for (i = preambleLength; i < numbers.size() && !foundNumber; i++)
      {
         foundNumber = true;
         
         for (int j = i - preambleLength; j < i && foundNumber; j++)
         {
            for (int k = j + 1; k < i && foundNumber; k++)
            {
               if (numbers.get(j) + numbers.get(k) == numbers.get(i))
               {
                  foundNumber = false;
               }
            }
         }
      }
      i--;
      
      return numbers.get(i);
   }
   
   
   public static long findWeaknessSum(List <Long> numbers, long targetSum)
   {
      int startIndex = 0;
      int endIndex = 0;
      long sum = numbers.get(0);
      
      while (sum < targetSum)
      {
         sum += numbers.get(++endIndex);
         
         while (sum > targetSum)
         {
            sum -= numbers.get(startIndex++);
         }
      }
      
      long min = numbers.get(startIndex);
      long max = numbers.get(endIndex);
      
      for (int i = startIndex + 1; i <= endIndex; i++)
      {
         if (numbers.get(i) < min)
            min = numbers.get(i);
         
         if (numbers.get(i) > max)
            max = numbers.get(i);
      }
      
      return min + max;
   }
}
