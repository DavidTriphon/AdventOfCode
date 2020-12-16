package davidt.aoc.years.y2020.days;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static davidt.aoc.years.y2020.days.Day15.*;
import static org.junit.jupiter.api.Assertions.*;


class Day15Test
{
   
   @Test
   void part1Main() throws IOException
   {
      assertEquals(376, continueSequence(getInputSequence(INPUT_FILE_LOC), PART_ONE_COUNT));
   }
   
   
   @Test
   void part1Examples()
   {
      assertEquals(436, continueSequence(List.of(0, 3, 6), PART_ONE_COUNT));
      assertEquals(1, continueSequence(List.of(1, 3, 2), PART_ONE_COUNT));
      assertEquals(10, continueSequence(List.of(2, 1, 3), PART_ONE_COUNT));
      assertEquals(27, continueSequence(List.of(1, 2, 3), PART_ONE_COUNT));
      assertEquals(78, continueSequence(List.of(2, 3, 1), PART_ONE_COUNT));
      assertEquals(438, continueSequence(List.of(3, 2, 1), PART_ONE_COUNT));
      assertEquals(1836, continueSequence(List.of(3, 1, 2), PART_ONE_COUNT));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      assertEquals(323780, continueSequence(getInputSequence(INPUT_FILE_LOC), PART_TWO_COUNT));
   }
   
   
   @Test
   void part2Examples()
   {
      assertEquals(175594, continueSequence(List.of(0, 3, 6), PART_TWO_COUNT));
      assertEquals(2578, continueSequence(List.of(1, 3, 2), PART_TWO_COUNT));
      assertEquals(3544142, continueSequence(List.of(2, 1, 3), PART_TWO_COUNT));
      assertEquals(261214, continueSequence(List.of(1, 2, 3), PART_TWO_COUNT));
      assertEquals(6895259, continueSequence(List.of(2, 3, 1), PART_TWO_COUNT));
      assertEquals(18, continueSequence(List.of(3, 2, 1), PART_TWO_COUNT));
      assertEquals(362, continueSequence(List.of(3, 1, 2), PART_TWO_COUNT));
   }
}
