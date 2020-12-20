package davidt.aoc.years.y2020.homework;

import java.util.concurrent.atomic.*;


public class BackwardsMath
{
   public static long compute(String equation)
   {
      return compute(equation, new AtomicInteger());
   }
   
   
   public static long compute(String equation, AtomicInteger index)
   {
      long result = getValue(equation, index);
      
      while (index.incrementAndGet() < equation.length() &&
             equation.charAt(index.get()) != ')')
      {
         index.incrementAndGet();
         char operator = equation.charAt(index.getAndAdd(2));
         
         long value = getValue(equation, index);
         
         switch (operator)
         {
            case '+':
               result += value;
               break;
            case '*':
               result *= value;
               break;
         }
      }
      
      return result;
   }
   
   
   public static long getValue(String equation, AtomicInteger index)
   {
      char next = equation.charAt(index.get());
      if (next == '(')
      {
         index.incrementAndGet();
         return compute(equation, index);
      }
      else
      {
         return Character.digit(next, 10);
      }
   }
}
