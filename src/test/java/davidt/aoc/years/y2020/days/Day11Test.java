package davidt.aoc.years.y2020.days;

import davidt.aoc.map.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static davidt.aoc.years.y2020.days.Day11.*;
import static org.junit.jupiter.api.Assertions.*;


class Day11Test
{
   public static final URL EXAMPLE_1 = Day11Test.class.getResource("input/11/example1.txt");
   
   
   @Test
   void exampleParsing() throws IOException
   {
      FiniteGridMap <Character> map = Day11.parseInput(EXAMPLE_1);
      assertEquals(0, map.countOf(Day11.TAKEN_SEAT));
      assertEquals(71, map.countOf(Day11.EMPTY_SEAT));
      assertEquals(29, map.countOf(Day11.NO_SEAT));
   }
   
   
   @Test
   void part1Examples() throws IOException
   {
      FiniteGridMap <Character> map = Day11.parseInput(EXAMPLE_1);
      assertEquals(37, part1(map));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      FiniteGridMap <Character> map = Day11.parseInput(INPUT_FILE_LOC);
      assertEquals(2368, part1(map));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      FiniteGridMap <Character> map = Day11.parseInput(EXAMPLE_1);
      assertEquals(26, part2(map));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      FiniteGridMap <Character> map = Day11.parseInput(INPUT_FILE_LOC);
      assertEquals(2124, part2(map));
   }
}
