package years.y2019.days;

import util.*;
import years.y2019.fft.*;

import java.io.*;


public class Day16
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input16.txt";
   
   private static final int PHASE_COUNT = 100;
   
   
   public static void main(String... args) throws IOException
   {
      int[] digits = FFT.getDigitsFromString(ReaderUtil.getFileString(INPUT_FILE_LOC));
      
      for (int phase = 0; phase < PHASE_COUNT; phase++)
      {
         digits = FFT.process(digits);
      }
      
      System.out.println(FFT.stringifyDigits(digits).substring(0, 8));
   }
}