package davidt.aoc.years.y2017.days;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static davidt.aoc.years.y2017.days.Day02.*;
import static org.junit.jupiter.api.Assertions.*;


class Day02Test
{
   public static final URL EXAMPLE_1 = Day02Test.class.getResource("input/02/example1.txt");
   public static final URL EXAMPLE_2 = Day02Test.class.getResource("input/02/example2.txt");
   
   
   @Test
   void part1Examples() throws IOException
   {
      assertEquals(18, checksumMinMax(getSpreadSheet(EXAMPLE_1)));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      assertEquals(58975, checksumMinMax(getSpreadSheet(INPUT_FILE_URL)));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      assertEquals(9, checksumDivisible(getSpreadSheet(EXAMPLE_2)));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      assertEquals(308, checksumDivisible(getSpreadSheet(INPUT_FILE_URL)));
   }
}
