package davidt.aoc.years.y2020.homework;

import java.util.*;


public class BackwardsMath
{
   public static long compute(String equation)
   {
      String postfix = getPostfix(equation);
   
      ArrayDeque <Long> values = new ArrayDeque <>();
   
      for (int i = 0; i < postfix.length(); i++)
      {
         char ch = postfix.charAt(i);
      
         switch (ch)
         {
            case '+':
            {
               values.push(values.pop() + values.pop());
            }
            break;
            case '*':
            {
               values.push(values.pop() * values.pop());
            }
            break;
            default:
            {
               values.push((long) Character.digit(ch, 10));
            }
            break;
         }
      }
   
      return values.pop();
   }
   
   
   public static String getPostfix(String equation)
   {
      ArrayList <Character> postfixEqList = new ArrayList <>();
      
      int insertionPoint = 0;
      
      for (int i = 0; i < equation.length(); )
      {
         char ch = equation.charAt(i++);
         
         if (ch == ' ')
            continue;
         
         while (insertionPoint < postfixEqList.size() &&
            getWeight(ch) >= getWeight(postfixEqList.get(insertionPoint)))
         {
            insertionPoint++;
         }
         
         if (shouldRemove(ch))
            postfixEqList.remove(insertionPoint);
         else
            postfixEqList.add(insertionPoint, ch);
         
         while (requireDirectInsertNext(ch))
         {
            if (equation.charAt(i) == ' ')
            {
               i++;
               continue;
            }
            ch = equation.charAt(i++);
            postfixEqList.add(insertionPoint, ch);
         }
      }
      
      StringBuilder sb = new StringBuilder();
      postfixEqList.forEach(sb::append);
      return sb.toString();
   }
   
   
   private static int getWeight(char ch)
   {
      switch (ch)
      {
         case '(':
            return 5;
         case ')':
            return 4;
         case '*':
            return 3;
         case '+':
            return 2;
         default:
            return 1;
      }
   }
   
   
   private static boolean requireDirectInsertNext(char insert)
   {
      return insert == '*' || insert == '+' || insert == '(';
   }
   
   
   private static boolean shouldRemove(char ch)
   {
      return ch == ')';
   }
}
