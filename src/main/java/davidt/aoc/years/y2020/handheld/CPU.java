package davidt.aoc.years.y2020.handheld;

import java.util.*;


public class CPU
{
   private final List <Instruction> _program;
   
   private CPUMemory _memory = new CPUMemory();
   
   
   public CPU(List <Instruction> program)
   {
      _program = program;
   }
   
   
   public List <Instruction> getProgram()
   {
      return _program;
   }
   
   
   public int getAccumulator()
   {
      return _memory.accumulator;
   }
   
   
   public int getInstructionAddress()
   {
      return _memory.instructionAddress;
   }
   
   
   public void resetMemory()
   {
      _memory = new CPUMemory();
   }
   
   
   public boolean isHalted()
   {
      return _memory.instructionAddress >= _program.size();
   }
   
   
   public void runOnce()
   {
      _program.get(_memory.instructionAddress++).execute(_memory);
   }
   
   
   public void runUntilEnd()
   {
      while (!isHalted())
      {
         runOnce();
      }
   }
   
   
   public void runUntilRepeat()
   {
      HashSet <Integer> visitedAddresses = new HashSet <>();
      
      while (!isHalted() && !visitedAddresses.contains(_memory.instructionAddress))
      {
         visitedAddresses.add(_memory.instructionAddress);
         runOnce();
      }
   }
}
