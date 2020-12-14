package years.y2019.days;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class Days2019Tests
{
   public static final String YEHEF =
      "X   XXXXX X  X XXXX XXXX \n" +
      "X   XX    X  X X    X    \n" +
      " X X XXX  XXXX XXX  XXX  \n" +
      "  X  X    X  X X    X    \n" +
      "  X  X    X  X X    X    \n" +
      "  X  XXXX X  X XXXX X    \n";
   
   public static final String HJALJZFH =
      " X  X   XX  XX  X      XX XXXX XXXX X  X   \n" +
      " X  X    X X  X X       X    X X    X  X   \n" +
      " XXXX    X X  X X       X   X  XXX  XXXX   \n" +
      " X  X    X XXXX X       X  X   X    X  X   \n" +
      " X  X X  X X  X X    X  X X    X    X  X   \n" +
      " X  X  XX  X  X XXXX  XX  XXXX X    X  X   \n";
   
   
   @Test
   void day01A() throws IOException
   {
      assertEquals(3412496, Day01A.getAnswer());
   }
   
   
   @Test
   void day01B() throws IOException
   {
      assertEquals(5115845, Day01B.getAnswer());
   }
   
   
   @Test
   void day02A() throws IOException
   {
      assertEquals(6627023, Day02A.getAnswer());
   }
   
   
   @Test
   void day02B() throws IOException
   {
      assertEquals(4019, Day02B.getAnswer());
   }
   
   
   @Test
   void day03A() throws IOException
   {
      assertEquals(806, Day03A.getAnswer());
   }
   
   
   @Test
   void day03B() throws IOException
   {
      assertEquals(66076, Day03B.getAnswer());
   }
   
   
   @Test
   void day04A()
   {
      assertEquals(2090, Day04A.getAnswer());
   }
   
   
   @Test
   void day04B()
   {
      assertEquals(1419, Day04B.getAnswer());
   }
   
   
   @Test
   void day05A() throws IOException
   {
      assertEquals(9938601, Day05A.getAnswer());
   }
   
   
   @Test
   void day05B() throws IOException
   {
      assertEquals(4283952, Day05B.getAnswer());
   }
   
   
   @Test
   void day06A() throws IOException
   {
      assertEquals(144909, Day06A.getAnswer());
   }
   
   
   @Test
   void day06B() throws IOException
   {
      assertEquals(259, Day06B.getAnswer());
   }
   
   
   @Test
   void day07A() throws IOException
   {
      assertEquals(366376, Day07A.getAnswer());
   }
   
   
   @Test
   void day07B() throws IOException
   {
      assertEquals(21596786, Day07B.getAnswer());
   }
   
   
   @Test
   void day08A() throws IOException
   {
      assertEquals(2193, Day08A.getAnswer());
   }
   
   
   @Test
   void day08B() throws IOException
   {
      assertEquals(YEHEF, Day08B.getAnswer());
   }
   
   
   @Test
   void day09A() throws IOException
   {
      assertEquals(3280416268L, Day09A.getAnswer());
   }
   
   
   @Test
   void day09B() throws IOException
   {
      assertEquals(80210, Day09B.getAnswer());
   }
   
   
   @Test
   void day10A() throws IOException
   {
      assertEquals(253, Day10A.getAnswer());
   }
   
   
   @Test
   void day10B() throws IOException
   {
      assertEquals(815, Day10B.getAnswer());
   }
   
   
   @Test
   void day11A() throws IOException
   {
      assertEquals(1785, Day11A.getAnswer());
   }
   
   
   @Test
   void day11B() throws IOException
   {
      assertEquals(HJALJZFH, Day11B.getAnswer());
   }
   
   
   @Test
   void day12A() throws IOException
   {
      assertEquals(12082, Day12A.getAnswer());
   }
   
   
   @Test
   void day12B() throws IOException
   {
      assertEquals(295693702908636L, Day12B.getAnswer());
   }
   
   
   @Test
   void day13A() throws IOException
   {
      assertEquals(260, Day13A.getAnswer());
   }
   
   
   @Test
   void day13B() throws IOException
   {
      assertEquals(12952, Day13B.getAnswer());
   }
   
   
   @Test
   void day14A() throws IOException
   {
      assertEquals(892207, Day14A.getAnswer());
   }
   
   
   @Test
   void day14B() throws IOException
   {
      assertEquals(1935265, Day14B.getAnswer());
   }
   
   
   @Test
   void day15A() throws IOException
   {
      assertEquals(242, Day15A.getAnswer());
   }
   
   
   @Test
   void day15B() throws IOException
   {
      assertEquals(276, Day15B.getAnswer());
   }
   
   
   @Test
   void day16A() throws IOException
   {
      assertEquals(67481260, Day16A.getAnswer());
   }
   
   
   @Test
   void day16B() throws IOException
   {
      assertEquals(42178738, Day16B.getAnswer());
   }
   
   
   @Test
   void day17A()
   {
      assertEquals(12512, Day17A.getAnswer());
   }
}
