package davidt.aoc.years.y2020.days;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;

import static davidt.aoc.years.y2020.days.Day19.*;
import static org.junit.jupiter.api.Assertions.*;


class Day19Test
{
   public static final URL EXAMPLE_1 = Day19Test.class.getResource("input/19/example1.txt");
   public static final URL EXAMPLE_2 = Day19Test.class.getResource("input/19/example2.txt");
   
   
   @Test
   void parsingExamples() throws IOException
   {
      Input19 input = parseInput(EXAMPLE_1);
      assertEquals(6, input.rules.size());
      assertEquals(5, input.messages.size());
   }
   
   
   @Test
   void part1Examples() throws IOException
   {
      assertEquals(2, part1(parseInput(EXAMPLE_1)));
      assertEquals(3, part1(parseInput(EXAMPLE_2)));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      assertEquals(299, part1(parseInput(INPUT_FILE_LOC)));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      Input19 input = parseInput(EXAMPLE_2);
      updateRules(input);
      assertFalse(input.rules.get(0).matches(input.messages.get(0), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(1), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(2), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(3), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(4), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(5), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(6), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(7), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(8), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(9), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(10), input.rules));
      assertFalse(input.rules.get(0).matches(input.messages.get(11), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(12), input.rules));
      assertFalse(input.rules.get(0).matches(input.messages.get(13), input.rules));
      assertTrue(input.rules.get(0).matches(input.messages.get(14), input.rules));
      assertEquals(12, part2(input));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      assertEquals(414, part2(parseInput(INPUT_FILE_LOC)));
   }
}
