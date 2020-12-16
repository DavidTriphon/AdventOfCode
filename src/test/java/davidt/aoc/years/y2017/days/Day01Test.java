package davidt.aoc.years.y2017.days;

import davidt.aoc.util.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static davidt.aoc.years.y2017.days.Day01.*;
import static org.junit.jupiter.api.Assertions.*;


class Day01Test
{
   @Test
   void part1Examples()
   {
      assertEquals(3, countRepeatsSum("1122"));
      assertEquals(4, countRepeatsSum("1111"));
      assertEquals(0, countRepeatsSum("1234"));
      assertEquals(9, countRepeatsSum("91212129"));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      assertEquals(1047, countRepeatsSum(ReaderUtil.getFileString(INPUT_FILE_LOC)));
   }
   
   
   @Test
   void part2Examples()
   {
      assertEquals(6, countOppositesSum("1212"));
      assertEquals(0, countOppositesSum("1221"));
      assertEquals(4, countOppositesSum("123425"));
      assertEquals(12, countOppositesSum("123123"));
      assertEquals(4, countOppositesSum("12131415"));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      assertEquals(982, countOppositesSum(ReaderUtil.getFileString(INPUT_FILE_LOC)));
   }
}
