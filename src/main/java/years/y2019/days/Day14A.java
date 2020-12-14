package years.y2019.days;

import util.*;
import years.y2019.nano.*;

import java.io.*;


public class Day14A
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input14.txt";
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      NanoFactory fact = new NanoFactory(
         ReaderUtil.parseFileToList(INPUT_FILE_LOC, Recipe::fromString));
      
      return fact.getOreCostOfFuel();
   }
}
