package days;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class Days2020Tests
{
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