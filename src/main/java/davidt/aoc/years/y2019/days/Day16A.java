package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.fft.*;

import java.io.*;
import java.net.*;


public class Day16A
{
   private static final URL INPUT_FILE_LOC = Day16A.class.getResource("input/input16.txt");
   
   private static final int PHASE_COUNT = 100;
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      int[] digits = FFT.getDigitsFromString(ReaderUtil.getFileString(INPUT_FILE_LOC));
      
      for (int phase = 0; phase < PHASE_COUNT; phase++)
      {
         digits = FFT.process(digits);
      }
      
      return Long.parseLong(FFT.stringifyDigits(digits).substring(0, 8));
   }
}
