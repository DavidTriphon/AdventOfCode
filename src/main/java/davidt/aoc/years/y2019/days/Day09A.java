package davidt.aoc.years.y2019.days;

import davidt.aoc.years.y2019.cpu.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day09A
{
   private static final URL INPUT_FILE_LOC = Day09A.class.getResource("input/input9.txt");
   
   
   public static void main(String... args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer() throws IOException
   {
      Long[] opCodes = Program.getMemoryFromFile(INPUT_FILE_LOC);
      
      Program program = new Program();
      
      program.setMemory(opCodes);
      program.setInput(new Long[] {1L});
      program.compute();
      System.out.println(Arrays.asList(program.getOutput()));
      return program.getOutput()[0];
   }
}
