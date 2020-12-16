package davidt.aoc.years.y2020.xmas;

import davidt.aoc.util.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.util.*;


class XMASTest
{
   public static final URL INPUT_FILE_LOC = XMASTest.class.getResource("input9T.txt");
   
   
   @Test
   void findNonSumNumber() throws IOException
   {
      List <Long> numbers = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Long::parseLong);
      
      Assertions.assertEquals(127L, XMAS.findNonSumNumber(numbers, 5));
   }
   
   
   @Test
   void findWeaknessSum() throws IOException
   {
      List <Long> numbers = ReaderUtil.parseFileToList(INPUT_FILE_LOC, Long::parseLong);
      
      long nonSumNumber = XMAS.findNonSumNumber(numbers, 5);
      
      Assertions.assertEquals(127L, nonSumNumber);
      
      long weaknessSum = XMAS.findWeaknessSum(numbers, nonSumNumber);
      
      Assertions.assertEquals(62L, weaknessSum);
   }
}
