package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.fft.*;

import java.io.*;
import java.net.*;


public class Day16B
{
   private static final URL INPUT_FILE_LOC = Day16B.class.getResource("input/input16.txt");
   
   private static final int PHASE_COUNT = 100;
   
   private static final int SIGNAL_REPEAT_COUNT = 10000;
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      String signalString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      int[] digits = FFT.getDigitsFromString(signalString);
      int offset = Integer.parseInt(signalString.substring(0, 7));
      
      int[] result =
         FFT.processWithLargeOffset(digits, SIGNAL_REPEAT_COUNT, offset, PHASE_COUNT, 8);
      
      return Long.parseLong(FFT.stringifyDigits(result).substring(0, 8));
   }
}
