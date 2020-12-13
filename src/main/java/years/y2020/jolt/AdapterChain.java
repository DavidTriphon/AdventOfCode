package main.years.y2020.jolt;

import java.util.*;


public class AdapterChain
{
   public static int findAdapterRating(List <Integer> adapterRatings)
   {
      adapterRatings.sort(Comparator.naturalOrder());
      
      int currentJoltage = 0;
      
      int oneJoltJumps = 0;
      int threeJoltJumps = 1; // add one for the final 3 volt jump in your device
      
      for (int adapterRating : adapterRatings)
      {
         int jump = adapterRating - currentJoltage;
         
         if (1 <= jump && jump <= 3)
         {
            if (jump == 1)
               oneJoltJumps++;
            else if (jump == 3)
               threeJoltJumps++;
         }
         else
         {
            break;
         }
         
         currentJoltage = adapterRating;
      }
      
      return oneJoltJumps * threeJoltJumps;
   }
   
   
   public static long countAdapterCombinations(List <Integer> adapterRatings)
   {
      adapterRatings.add(0);
      adapterRatings.sort(Comparator.naturalOrder());
      
      long combinations = 1; // start at 1 to multiply by
   
      int startIndex = 0;
      int endIndex;
      
      long currentJoltage = 0;
      
      for (int i = 1; i < adapterRatings.size(); i++)
      {
         long jump = adapterRatings.get(i) - currentJoltage;
         
         if (jump == 3 || i + 1 == adapterRatings.size())
         {
            endIndex = i;
            
            combinations *= countRecursiveCombos(adapterRatings, startIndex, endIndex);
            
            startIndex = i;
         }
         
         currentJoltage = adapterRatings.get(i);
      }
      
      return combinations;
   }
   
   
   private static long countRecursiveCombos(List <Integer> adapterRatings, int start, int end)
   {
      if (start == end)
         return 1;
      
      long combos = 0;
      
      for (int i = start + 1; i <= end; i++)
      {
         if (adapterRatings.get(i) - adapterRatings.get(start) <= 3)
            combos += countRecursiveCombos(adapterRatings, i, end);
      }
      
      return combos;
   }
}
