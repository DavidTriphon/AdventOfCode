package years.y2019.days;

import util.*;
import years.y2019.nbody.*;

import java.io.*;


public class Day12
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input12.txt";
   
   private static final int STEP_COUNT = 1000;
   
   
   public static void main(String... args) throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      NBody nBody = NBody.fromString(fileString);
      
      nBody.timeStep(STEP_COUNT);
      
      System.out.println(nBody.getEnergy());
   }
}
