package years.y2019.days;

import util.*;

import java.io.*;


public class Day01B
{
   private static final String inputFileLoc =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input1.txt";
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      BufferedReader inputReader = new BufferedReader(new FileReader(inputFileLoc));
      
      String line;
      
      long totalFuel = 0;
      
      while ((line = inputReader.readLine()) != null)
      {
         int fuelPartial = Integer.parseInt(line);
         
         while ((fuelPartial = (fuelPartial / 3) - 2) > 0)
         {
            totalFuel += fuelPartial;
         }
      }
      
      return totalFuel;
   }
}