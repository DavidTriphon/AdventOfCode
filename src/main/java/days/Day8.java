package days;

import handheld.*;
import util.*;

import java.io.*;


public class Day8
{
   public static final String INPUT_FILE_LOC = ReaderUtil.RESOURCES_LOCATION + "input8.txt";
   
   
   public static void main(String[] args) throws IOException
   {
      CPU cpu = new CPU(ReaderUtil.parseFileToList(INPUT_FILE_LOC, Instruction::fromString));
      
      cpu.runUntilRepeat();
      
      System.out.println(cpu.getAccumulator());
   }
}
