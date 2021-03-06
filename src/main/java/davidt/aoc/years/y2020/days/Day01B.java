package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day01B
{
   private static final URL INPUT_FILE_LOC = Day01B.class.getResource("input/input1.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      List <Integer> expensesList = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Integer::parseInt);
      
      expensesList.sort(Comparator.comparingInt(a -> a));
      
      //System.out.println(expensesList);
      
      int a;
      int b;
      int c = -1;
      
      do
      {
         c++;
         a = c + 1;
         b = expensesList.size();
         
         int abSum = 2020 - expensesList.get(c);
         
         do
         {
            int target = abSum - expensesList.get(a);
            if (expensesList.contains(target))
            {
               b = expensesList.indexOf(target);
            }
            else
            {
               b = SearchUtil.binarySearch(expensesList, a, b, target);
               
               if (expensesList.get(b) > target)
                  b--;
               
               if (a < b)
               {
                  target = abSum - expensesList.get(b);
                  if (expensesList.contains(target))
                  {
                     a = expensesList.indexOf(target);
                  }
                  else
                  {
                     a = SearchUtil.binarySearch(expensesList, a, b, target);
                     
                     if (expensesList.get(a) < target)
                        a++;
                  }
               }
            }
         }
         while (expensesList.get(a) + expensesList.get(b) != abSum && a < b);
      }
      while (expensesList.get(a) + expensesList.get(b) + expensesList.get(c) != 2020);
      
      System.out.printf("a: %d: %d\n", a, expensesList.get(a));
      System.out.printf("b: %d: %d\n", b, expensesList.get(b));
      System.out.printf("c: %d: %d\n", c, expensesList.get(c));
      System.out.printf(
         "a * b * c: %d\n", expensesList.get(a) * expensesList.get(b) * expensesList.get(c));
      
      return expensesList.get(a) * expensesList.get(b) * expensesList.get(c);
   }
}
