package davidt.aoc.years.y2019.days;

public class Day04B
{
   private static final int LOWER_PASSWORD = 130254;
   private static final int UPPER_PASSWORD = 678275;
   
   private static final int DIGIT_COUNT = 6;
   
   
   public static void main(String... args)
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer()
   {
      int validPasswordCount = 0;
      
      for (int currPass = LOWER_PASSWORD; currPass < UPPER_PASSWORD; currPass++)
      {
         boolean isIncremental = true, prevRepeat = false, repeatOnce = false, repeatLock = false;
         
         int prevDigit = getDigit(currPass, 0);
         
         for (int digit = 1; digit < DIGIT_COUNT; digit++)
         {
            int currDigit = getDigit(currPass, digit);
            
            if (currDigit > prevDigit)
               isIncremental = false;
            
            if (currDigit == prevDigit)
            {
               repeatOnce = !prevRepeat;
               
               prevRepeat = true;
            }
            else
            {
               if (repeatOnce)
               {
                  repeatLock = true;
               }
               
               prevRepeat = false;
            }
            
            prevDigit = currDigit;
         }
         
         if (isIncremental && (repeatLock || repeatOnce))
            validPasswordCount++;
      }
      
      return validPasswordCount;
   }
   
   
   private static int getDigit(int num, int digit)
   {
      return (num / (int) Math.pow(10, digit)) % 10;
   }
}
