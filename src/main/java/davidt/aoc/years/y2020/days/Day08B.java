package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.handheld.*;

import java.io.*;
import java.net.*;


public class Day08B
{
   public static final URL INPUT_FILE_LOC = Day08B.class.getResource("input/input8.txt");
   
   
   public static void main(String[] args) throws IOException
   {
      System.out.println(getAnswer());
   }
   
   
   public static int getAnswer() throws IOException
   {
      CPU cpu = new CPU(ReaderUtil.parseFileToList(INPUT_FILE_LOC, Instruction::fromString));
      
      for (Instruction instruction : cpu.getProgram())
      {
         switch (instruction.getOpCode())
         {
            case JUMP:
            {
               instruction.setOpCode(OpCode.NO_OPERATION);
            }
            break;
            case NO_OPERATION:
            {
               instruction.setOpCode(OpCode.JUMP);
            }
            break;
         }
         
         cpu.runUntilRepeat();
         
         if (cpu.isHalted())
         {
            System.out.println(cpu.getAccumulator());
            break;
         }
         
         switch (instruction.getOpCode())
         {
            case JUMP:
            {
               instruction.setOpCode(OpCode.NO_OPERATION);
            }
            break;
            case NO_OPERATION:
            {
               instruction.setOpCode(OpCode.JUMP);
            }
            break;
         }
         
         cpu.resetMemory();
      }
      
      return cpu.getAccumulator();
   }
}
