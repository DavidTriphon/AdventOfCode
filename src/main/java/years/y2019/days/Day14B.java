package years.y2019.days;

import util.*;
import years.y2019.nano.*;

import java.io.*;


public class Day14B
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input14.txt";
   
   private static final long MINED_ORE = 1000000000000L;
   
   
   public static void main(String... args) throws IOException
   {
      NanoFactory fact = new NanoFactory(
         ReaderUtil.parseFileToList(INPUT_FILE_LOC, Recipe::fromString));
      
      System.out.println(fact.getMaxFuelFromOre(MINED_ORE));
   }
}
