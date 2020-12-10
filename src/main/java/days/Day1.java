package days;

import util.*;

import java.io.*;
import java.util.*;


public class Day1
{
   private static final String INPUT_FILE_LOC = "input1.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      ArrayList <Integer> expensesList = new ArrayList <>();
      
      ReaderUtil.parseFileWithMethod(
         INPUT_FILE_LOC,
         (String line) -> expensesList.add(Integer.parseInt(line))
      );
      
      expensesList.sort(Comparator.comparingInt(a -> a));
      
      System.out.println(expensesList);
      
      int a = 0;
      int b = expensesList.size();
      
      do
      {
         int target = 2020 - expensesList.get(a);
         b = SearchUtil.binarySearch(expensesList, a, b, target);
         
         if (expensesList.get(b) > target)
            b--;
         
         if (expensesList.get(a) + expensesList.get(b) != 2020)
         {
            target = 2020 - expensesList.get(b);
            a = SearchUtil.binarySearch(expensesList, a, b, target);
            
            if (expensesList.get(a) < target)
               a++;
         }
      }
      while (expensesList.get(a) + expensesList.get(b) != 2020);
   
      System.out.printf("a: %d: %d\n", a, expensesList.get(a));
      System.out.printf("b: %d: %d\n", b, expensesList.get(b));
      System.out.printf("a * b: %d\n", expensesList.get(a) * expensesList.get(b));
   }
}
