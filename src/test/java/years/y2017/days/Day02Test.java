package years.y2017.days;

import org.junit.jupiter.api.*;
import util.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static years.y2017.days.Day02.*;


class Day02Test
{
   public static final String EXAMPLE_1 =
      ReaderUtil.TEST_RESOURCES_LOCATION + "years/y2017/days/2/example1.txt";
   
   public static final String EXAMPLE_2 =
      ReaderUtil.TEST_RESOURCES_LOCATION + "years/y2017/days/2/example2.txt";
   
   
   @Test
   void part1Examples() throws IOException
   {
      assertEquals(18, checksumMinMax(getSpreadSheet(EXAMPLE_1)));
   }
   
   
   @Test
   void part1Main() throws IOException
   {
      assertEquals(58975, checksumMinMax(getSpreadSheet(INPUT_FILE_LOC)));
   }
   
   
   @Test
   void part2Examples() throws IOException
   {
      assertEquals(9, checksumDivisible(getSpreadSheet(EXAMPLE_2)));
   }
   
   
   @Test
   void part2Main() throws IOException
   {
      assertEquals(308, checksumDivisible(getSpreadSheet(INPUT_FILE_LOC)));
   }
}
