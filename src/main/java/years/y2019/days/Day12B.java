package years.y2019.days;

import util.*;
import years.y2019.nbody.*;

import java.io.*;


public class Day12B
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input12.txt";
   
   
   public static void main(String... args) throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      NBody nBody = NBody.fromString(fileString);
      
      System.out.print(nBody.getLoopTime());
   }
}
