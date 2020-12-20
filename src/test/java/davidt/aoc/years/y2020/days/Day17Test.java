package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static davidt.aoc.years.y2020.days.Day17.*;
import static org.junit.jupiter.api.Assertions.*;


class Day17Test
{
   public static final URL EXAMPLE_1 = Day17Test.class.getResource("input/17/example1.txt");
   
   
   @Test
   void exampleParsing() throws IOException
   {
      String expected = ".#.\n" +
                        "..#\n" +
                        "###";
      String inputData = ReaderUtil.getFileString(EXAMPLE_1);
      assertEquals(expected, inputData);
   }
   
   
   @Test
   void part1Examples() throws IOException
   {
      String inputData = ReaderUtil.getFileString(EXAMPLE_1);
      assertEquals(112, part1(inputData));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      String inputData = ReaderUtil.getFileString(INPUT_FILE_LOC);
      assertEquals(267, part1(inputData));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      String inputData = ReaderUtil.getFileString(EXAMPLE_1);
      assertEquals(848, part2(inputData));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      String inputData = ReaderUtil.getFileString(INPUT_FILE_LOC);
      assertEquals(1812, part2(inputData));
   }
}
