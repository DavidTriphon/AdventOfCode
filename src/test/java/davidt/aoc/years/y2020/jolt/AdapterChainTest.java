package davidt.aoc.years.y2020.jolt;

import davidt.aoc.util.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.util.*;


class AdapterChainTest
{
   public static final URL INPUT_FILE_LOC_1 = AdapterChainTest.class.getResource("input10T1.txt");
   public static final URL INPUT_FILE_LOC_2 = AdapterChainTest.class.getResource("input10T2.txt");
   
   
   @Test
   void findAdapterRating1() throws IOException
   {
      List <Integer> adapterRatings =
         ReaderUtil.parseFileToList(INPUT_FILE_LOC_1, Integer::parseInt);
      
      Assertions.assertEquals(35, AdapterChain.findAdapterRating(adapterRatings));
   }
   
   
   @Test
   void findAdapterRating2() throws IOException
   {
      List <Integer> adapterRatings =
         ReaderUtil.parseFileToList(INPUT_FILE_LOC_2, Integer::parseInt);
      
      Assertions.assertEquals(220, AdapterChain.findAdapterRating(adapterRatings));
   }
   
   
   @Test
   void countAdapterCombinations1() throws IOException
   {
      List <Integer> adapterRatings =
         ReaderUtil.parseFileToList(INPUT_FILE_LOC_1, Integer::parseInt);
      
      Assertions.assertEquals(8, AdapterChain.countAdapterCombinations(adapterRatings));
   }
   
   
   @Test
   void countAdapterCombinations2() throws IOException
   {
      List <Integer> adapterRatings =
         ReaderUtil.parseFileToList(INPUT_FILE_LOC_2, Integer::parseInt);
      
      Assertions.assertEquals(19208, AdapterChain.countAdapterCombinations(adapterRatings));
   }
}
