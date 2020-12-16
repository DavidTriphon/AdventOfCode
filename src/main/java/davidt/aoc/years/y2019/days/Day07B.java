package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.cpu.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day07B
{
   private static final URL INPUT_FILE_LOC = Day07B.class.getResource("input/input7.txt");
   
   private static final int PHASE_MIN = 5;
   private static final int PHASE_MAX = 9;
   private static final int PHASE_CNT = PHASE_MAX - PHASE_MIN + 1;
   
   
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
      
      ArrayList <ArrayList <Long>> phaseCombinations = new ArrayList <>();
      
      generatePhaseCombos(phaseCombinations, new ArrayList <>());
      
      ArrayList <Long> bestPhaseCombo = null;
      long highestOutput = 0;
      
      for (ArrayList <Long> phaseCombo : phaseCombinations)
      {
         ArrayList <Program> programs = new ArrayList <>();
         
         for (long phase : phaseCombo)
         {
            Long[] inputs = new Long[] {phase};
            Program program = new Program();
            programs.add(program);
            
            program.setMemory(opCodes);
            program.setInput(inputs);
            program.compute();
         }
         
         long currentSignalStrength = 0;
         
         while (!programs.get(0).isHalted())
         {
            for (Program program : programs)
            {
               program.setInput(new Long[] {currentSignalStrength});
               program.compute();
               Long[] output = program.getOutput();
               currentSignalStrength = output[output.length - 1];
            }
         }
         
         if (currentSignalStrength > highestOutput)
         {
            highestOutput  = currentSignalStrength;
            bestPhaseCombo = phaseCombo;
         }
      }
      
      System.out.println(bestPhaseCombo);
      System.out.println(highestOutput);
      
      return highestOutput;
   }
   
   
   private static void generatePhaseCombos(
      ArrayList <ArrayList <Long>> phaseCombinations,
      ArrayList <Long> currentCombination)
   {
      if (currentCombination.size() == PHASE_CNT)
      {
         phaseCombinations.add(new ArrayList <>(currentCombination));
      }
      else
      {
         for (long phase = PHASE_MIN; phase <= PHASE_MAX; phase++)
         {
            if (!currentCombination.contains(phase))
            {
               currentCombination.add(phase);
               generatePhaseCombos(phaseCombinations, currentCombination);
               currentCombination.remove(currentCombination.size() - 1);
            }
         }
      }
   }
}
