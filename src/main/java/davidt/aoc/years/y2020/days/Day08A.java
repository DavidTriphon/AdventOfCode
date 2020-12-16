package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.handheld.*;

import java.io.*;
import java.net.*;


public class Day08A
{
   public static final URL INPUT_FILE_LOC = Day08A.class.getResource("input/input8.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      CPU cpu = new CPU(ReaderUtil.parseFileToList(INPUT_FILE_LOC, Instruction::fromString));
      
      cpu.runUntilRepeat();
      
      return cpu.getAccumulator();
   }
}
