package davidt.aoc.years.y2019.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2019.asteroids.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class Test10
{
   private static final String FILE_LOC_PREFIX = "input/10/";
   private static final String FILE_LOC_SUFFIX = ".txt";
   
   
   @Test
   public void asteroidTestA()
   {
      testAsteroidMap("A", 5, 8, 33);
   }
   
   
   @Test
   public void asteroidTestB()
   {
      testAsteroidMap("B", 1, 2, 35);
   }
   
   
   @Test
   public void asteroidTestC()
   {
      testAsteroidMap("C", 6, 3, 41);
   }
   
   
   @Test
   public void asteroidTestD()
   {
      testAsteroidMap("D", 11, 13, 210);
   }
   
   
   private static void testAsteroidMap(String fileLoc, int expX, int expY, int expVisible)
   {
      try
      {
         String fileString = ReaderUtil
            .getFileString(getInputURL(fileLoc));
   
         AsteroidMap map = AsteroidMap.fromString(fileString);
   
         Map.Entry <Integer, Integer> pos = map.mostVisibleAsteroid();
   
         assertEquals(pos.getKey(), expX);
         assertEquals(pos.getValue(), expY);
         assertEquals(map.visibility(pos), expVisible);
      }
      catch (IOException exc)
      {
         fail(exc);
      }
   }
   
   
   private static URL getInputURL(String inputName)
   {
      return Test10.class.getResource(FILE_LOC_PREFIX + inputName + FILE_LOC_SUFFIX);
   }
}
