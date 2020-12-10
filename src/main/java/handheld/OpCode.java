package handheld;

import java.util.*;


public enum OpCode
{
   NO_OPERATION("nop")
      {
         @Override
         public void execute(CPUMemory _memory, List <Operand> operands)
         {
         
         }
      },
   ACCUMULATOR("acc")
      {
         @Override
         public void execute(CPUMemory _memory, List <Operand> operands)
         {
            _memory.accumulator += operands.get(0).value();
         }
      },
   JUMP("jmp")
      {
         @Override
         public void execute(CPUMemory _memory, List <Operand> operands)
         {
            _memory.instructionAddress += operands.get(0).value() - 1;
         }
      };
   
   public final String name;
   
   
   OpCode(String name)
   {
      this.name = name;
   }
   
   
   public static OpCode getOpCodeFromString(String opCodeString)
   {
      for (OpCode opCode : values())
      {
         if (opCode.name.equals(opCodeString))
            return opCode;
      }
      
      throw new IllegalArgumentException(
         String.format("'%s' is not a valid opCode name.", opCodeString));
   }
   
   
   public abstract void execute(CPUMemory _memory, List <Operand> operands);
}
