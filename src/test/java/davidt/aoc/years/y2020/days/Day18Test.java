package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;
import davidt.aoc.years.y2020.homework.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static davidt.aoc.years.y2020.days.Day18.*;
import static org.junit.jupiter.api.Assertions.*;


class Day18Test
{
   public static final URL EXAMPLE_1 = getURLHelper(1);
   public static final URL EXAMPLE_2 = getURLHelper(2);
   public static final URL EXAMPLE_3 = getURLHelper(3);
   public static final URL EXAMPLE_4 = getURLHelper(4);
   public static final URL EXAMPLE_5 = getURLHelper(5);
   public static final URL EXAMPLE_6 = getURLHelper(6);
   
   
   @Test
   void part1Examples() throws IOException
   {
      assertEquals(71, WeirdMath.compute(ReaderUtil.getFileString(EXAMPLE_1)));
      assertEquals(51, WeirdMath.compute(ReaderUtil.getFileString(EXAMPLE_2)));
      assertEquals(26, WeirdMath.compute(ReaderUtil.getFileString(EXAMPLE_3)));
      assertEquals(437, WeirdMath.compute(ReaderUtil.getFileString(EXAMPLE_4)));
      assertEquals(12240, WeirdMath.compute(ReaderUtil.getFileString(EXAMPLE_5)));
      assertEquals(13632, WeirdMath.compute(ReaderUtil.getFileString(EXAMPLE_6)));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      assertEquals(701339185745L, part1(ReaderUtil.parseFileToList(INPUT_FILE_LOC)));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      assertEquals(231, BackwardsMath.compute(ReaderUtil.getFileString(EXAMPLE_1)));
      assertEquals(51, BackwardsMath.compute(ReaderUtil.getFileString(EXAMPLE_2)));
      assertEquals(46, BackwardsMath.compute(ReaderUtil.getFileString(EXAMPLE_3)));
      assertEquals(1445, BackwardsMath.compute(ReaderUtil.getFileString(EXAMPLE_4)));
      assertEquals(669060, BackwardsMath.compute(ReaderUtil.getFileString(EXAMPLE_5)));
      assertEquals(23340, BackwardsMath.compute(ReaderUtil.getFileString(EXAMPLE_6)));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      assertEquals(4208490449905L, part2(ReaderUtil.parseFileToList(INPUT_FILE_LOC)));
   }
   
   
   public static URL getURLHelper(int exampleNum)
   {
      return Day18Test.class.getResource(String.format("input/18/example%d.txt", exampleNum));
   }
}
