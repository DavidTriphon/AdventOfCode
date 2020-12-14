package years.y2019.days;

import util.*;
import years.y2019.fft.*;

import java.io.*;


public class Day16B
{
   private static final String INPUT_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input16.txt";
   
   private static final int PHASE_COUNT = 100;
   
   private static final int SIGNAL_REPEAT_COUNT = 10000;
   
   
   public static void main(String... args) throws IOException
   {
      String signalString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      int[] digits = FFT.getDigitsFromString(signalString);
      int offset = Integer.parseInt(signalString.substring(0, 7));
      
      int[] result =
         FFT.processWithLargeOffset(digits, SIGNAL_REPEAT_COUNT, offset, PHASE_COUNT, 8);
      
      System.out.println(FFT.stringifyDigits(result));
   }
}
