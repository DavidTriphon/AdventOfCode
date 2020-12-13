package years.y2020.days;

import handheld.*;
import util.*;

import java.io.*;


public class Day08A
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION +
      "years/y2020/input8.txt";
   
   
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
