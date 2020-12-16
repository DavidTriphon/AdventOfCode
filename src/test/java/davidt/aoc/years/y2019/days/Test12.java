package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.math.*;
import davidt.aoc.years.y2019.nbody.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.*;

import static org.junit.jupiter.api.Assertions.*;


public class Test12
{
   private static final String FILE_LOC_PREFIX = "input/12/";
   
   private static final String EXPECTED = "exp.txt";
   private static final String START    = "start.txt";
   private static final String TIME     = "time.txt";
   private static final String PLOT     = "plot.csv";
   
   private static final String  STEP_REGEX   = "After (\\d+) steps?:";
   private static final Pattern STEP_PATTERN = Pattern.compile(STEP_REGEX);
   
   private static final String  ENERGY_STEP_REGEX   = "Energy after (\\d+) steps?:";
   private static final Pattern ENERGY_STEP_PATTERN = Pattern.compile(ENERGY_STEP_REGEX);
   
   private static final String  ENERGY_TOTAL_REGEX   =
      "Sum of total energy: \\d+( \\+ \\d+)* = (\\d+)";
   private static final Pattern ENERGY_TOTAL_PATTERN = Pattern.compile(ENERGY_TOTAL_REGEX);
   
   private static final int PRINT_STEP_COUNT = 1000;
   
   
   @Test
   public void testFuncA()
   {
      testFunc("a");
   }
   
   
   @Test
   public void testFuncB()
   {
      testFunc("b");
   }
   
   
   @Test
   public void printB() throws IOException, URISyntaxException
   {
      String fileString = ReaderUtil.getFileString(getFileURL("b", START));
      
      NBody nBody = NBody.fromString(fileString);
      
      Writer out = new BufferedWriter(new OutputStreamWriter(
         new FileOutputStream(new File(getFileURL("b", PLOT).toURI())), StandardCharsets.UTF_8));
      
      for (int step = 0; step <= PRINT_STEP_COUNT; step++)
      {
         out.write(String.format("%d, ", step));
         
         for (int place = 0; place < Coord3D.INDEX_COUNT; place++)
         {
            for (Body body : nBody.getBodies())
            {
               out.write(String.format("%d, ", body.getPos().get(place)));
            }
         }
         
         out.write('\n');
         
         nBody.timeStep();
      }
      
      out.close();
   }
   
   
   @Test
   public void testTimeB()
   {
      try
      {
         NBody nBody = NBody.fromString(ReaderUtil.getFileString(getFileURL("b", START)));
         
         // the clone is expected to be the nbody
         assertEquals(nBody, new NBody(nBody), "A clone should be equal");
   
         long expLoopTime =
            Long.parseLong(ReaderUtil.getFileString(getFileURL("b", TIME)));
         
         long actLoopTime = nBody.getLoopTime();
         
         assertEquals(expLoopTime, actLoopTime, "Loop time is not correct");
      }
      catch (IOException exc)
      {
         fail(exc);
      }
   }
   
   
   private static URL getFileURL(String name, String type)
   {
      return Test12.class.getResource(String.format("%s%s_%s", FILE_LOC_PREFIX, name, type));
   }
   
   
   private static void testFunc(String filename)
   {
      try
      {
         URL expectedFileURL = getFileURL(filename, EXPECTED);
         URL startFileURL = getFileURL(filename, START);
   
         NBody nBody = NBody.fromString(ReaderUtil.getFileString(startFileURL));
         Expected exp = getExpected(ReaderUtil.getFileString(expectedFileURL));
   
         for (int step = 0; step <= exp.endStep; nBody.timeStep(), step++)
         {
            if (exp.map.containsKey(step))
            {
               NBody expNBody = exp.map.get(step);
         
               assertEquals(expNBody, nBody,
                  String.format("At step %d NBody did not match expected state.", step)
               );
            }
            
            if (step == exp.endStep)
            {
               assertEquals(exp.energyAmount, nBody.getEnergy(), "Energy Equation is not correct");
            }
         }
      }
      catch (IOException exc)
      {
         fail(exc);
      }
   }
   
   
   private static Expected getExpected(String fileString) throws IOException
   {
      Expected exp = new Expected();
      
      int currentStep = -1;
      NBody nBody = null;
      
      for (String line : fileString.split("\n"))
      {
         Matcher m;
         
         if ((m = STEP_PATTERN.matcher(line)).matches())
         {
            if (currentStep != -1)
               exp.map.put(currentStep, nBody);
            nBody       = new NBody();
            currentStep = Integer.parseInt(m.group(1));
         }
         else if ((m = Body.PATTERN.matcher(line)).matches())
         {
            nBody.addBody(Body.fromString(line));
         }
         else if ((m = ENERGY_STEP_PATTERN.matcher(line)).matches())
         {
            exp.endStep = Integer.parseInt(m.group(1));
         }
         else if ((m = ENERGY_TOTAL_PATTERN.matcher(line)).matches())
         {
            exp.energyAmount = Integer.parseInt(m.group(m.groupCount()));
         }
      }
      
      return exp;
   }
   
   
   private static class Expected
   {
      public HashMap <Integer, NBody> map = new HashMap <>();
      
      public int endStep      = -1;
      public int energyAmount = -1;
   }
}
