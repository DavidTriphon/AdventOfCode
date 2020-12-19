package davidt.aoc.map;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class DirectionSetTest
{
   @Test
   public void oneDimensional()
   {
      assertEquals(3, new DirectionSet(1, true, false).values().size());
      assertEquals(3, new DirectionSet(1, true, true).values().size());
      assertEquals(2, new DirectionSet(1, false, false).values().size());
      assertEquals(2, new DirectionSet(1, false, true).values().size());
   }
   
   
   @Test
   public void twoDimensional()
   {
      assertEquals(5, new DirectionSet(2, true, false).values().size());
      assertEquals(9, new DirectionSet(2, true, true).values().size());
      assertEquals(4, new DirectionSet(2, false, false).values().size());
      assertEquals(8, new DirectionSet(2, false, true).values().size());
   }
   
   
   @Test
   public void threeDimensional()
   {
      assertEquals(7, new DirectionSet(3, true, false).values().size());
      assertEquals(27, new DirectionSet(3, true, true).values().size());
      assertEquals(6, new DirectionSet(3, false, false).values().size());
      assertEquals(26, new DirectionSet(3, false, true).values().size());
   }
}
