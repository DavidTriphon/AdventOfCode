package years.y2020.days;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class Days2020Tests
{
   @Test
   void day10A() throws IOException
   {
      assertEquals(2244, Day10A.getAnswer());
   }
   
   
   @Test
   void day10B() throws IOException
   {
      assertEquals(3947645370368L, Day10B.getAnswer());
   }
   
   
   @Test
   void day11A() throws IOException
   {
      assertEquals(2368, Day11A.getAnswer());
   }
   
   
   @Test
   void day11B() throws IOException
   {
      assertEquals(2124, Day11B.getAnswer());
   }
}