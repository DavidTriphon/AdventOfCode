package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.cpu.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day05A
{
   private static final URL INPUT_FILE_LOC = Day05A.class.getResource("input/input5.txt");
   
   
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
      program.setInput(new Long[] {1L});
      program.setMemory(opCodes);
      
      program.compute();
      
      System.out.println(Arrays.asList(program.getOutput()));
      
      return program.getOutput()[program.getOutput().length - 1];
   }
}
