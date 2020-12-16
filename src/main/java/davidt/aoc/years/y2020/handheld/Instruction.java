package davidt.aoc.years.y2020.handheld;

import java.util.*;


public class Instruction
{
   private final List <Operand> _operands;
   private       OpCode         _opCode;
   
   
   public Instruction(OpCode opCode, List <Operand> operands)
   {
      _opCode   = opCode;
      _operands = operands;
   }
   
   
   public OpCode getOpCode()
   {
      return _opCode;
   }
   
   
   public void setOpCode(OpCode opCode)
   {
      _opCode = opCode;
   }
   
   
   public Operand getOperand(int index)
   {
      return _operands.get(index);
   }
   
   
   public void setOperand(int index, Operand operand)
   {
      _operands.set(index, operand);
   }
   
   
   public void execute(CPUMemory memory)
   {
      _opCode.execute(memory, _operands);
   }
   
   
   public static Instruction fromString(String instructionString)
   {
      String[] instructionParts = instructionString.split(" ");
      OpCode opCode = OpCode.getOpCodeFromString(instructionParts[0]);
      ArrayList <Operand> operands = new ArrayList <>();
      
      for (int i = 1; i < instructionParts.length; i++)
      {
         operands.add(new Operand(Integer.parseInt(instructionParts[i])));
      }
      
      return new Instruction(opCode, List.copyOf(operands));
   }
}
