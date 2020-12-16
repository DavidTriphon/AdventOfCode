package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.cpu.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day05B
{
   private static final URL INPUT_FILE_LOC = Day05B.class.getResource("input/input5.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      String fileString = ReaderUtil.getFileString(INPUT_FILE_LOC);
      
      ArrayList <Long> opCodesArray = new ArrayList <>();
      Arrays.asList(fileString.split(",")).forEach((i) -> opCodesArray.add(Long.parseLong(i)));
      Long[] opCodes = opCodesArray.toArray(new Long[0]);
      
      Program program = new Program();
      program.setInput(new Long[] {5L});
      program.setMemory(opCodes);
      
      program.compute();
      
      return program.getOutput()[0];
   }
}
