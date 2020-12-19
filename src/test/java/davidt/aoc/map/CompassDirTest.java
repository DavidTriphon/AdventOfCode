package davidt.aoc.map;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class CompassDirTest
{
   
   @Test
   void ccw90()
   {
      assertEquals(CompassDir.LEFT, CompassDir.UP.ccw90());
      assertEquals(CompassDir.DOWN, CompassDir.LEFT.ccw90());
      assertEquals(CompassDir.RIGHT, CompassDir.DOWN.ccw90());
      assertEquals(CompassDir.UP, CompassDir.RIGHT.ccw90());
      
      assertEquals(CompassDir.UP_LEFT, CompassDir.UP_RIGHT.ccw90());
      assertEquals(CompassDir.DOWN_LEFT, CompassDir.UP_LEFT.ccw90());
      assertEquals(CompassDir.DOWN_RIGHT, CompassDir.DOWN_LEFT.ccw90());
      assertEquals(CompassDir.UP_RIGHT, CompassDir.DOWN_RIGHT.ccw90());
   }
   
   
   @Test
   void cw90()
   {
      assertEquals(CompassDir.RIGHT, CompassDir.UP.cw90());
      assertEquals(CompassDir.DOWN, CompassDir.RIGHT.cw90());
      assertEquals(CompassDir.LEFT, CompassDir.DOWN.cw90());
      assertEquals(CompassDir.UP, CompassDir.LEFT.cw90());
   
      assertEquals(CompassDir.DOWN_RIGHT, CompassDir.UP_RIGHT.cw90());
      assertEquals(CompassDir.DOWN_LEFT, CompassDir.DOWN_RIGHT.cw90());
      assertEquals(CompassDir.UP_LEFT, CompassDir.DOWN_LEFT.cw90());
      assertEquals(CompassDir.UP_RIGHT, CompassDir.UP_LEFT.cw90());
   }
   
   
   @Test
   void ccw45()
   {
      assertEquals(CompassDir.UP_LEFT, CompassDir.UP.ccw45());
      assertEquals(CompassDir.LEFT, CompassDir.UP_LEFT.ccw45());
      assertEquals(CompassDir.DOWN_LEFT, CompassDir.LEFT.ccw45());
      assertEquals(CompassDir.DOWN, CompassDir.DOWN_LEFT.ccw45());
      assertEquals(CompassDir.DOWN_RIGHT, CompassDir.DOWN.ccw45());
      assertEquals(CompassDir.RIGHT, CompassDir.DOWN_RIGHT.ccw45());
      assertEquals(CompassDir.UP_RIGHT, CompassDir.RIGHT.ccw45());
      assertEquals(CompassDir.UP, CompassDir.UP_RIGHT.ccw45());
   }
   
   
   @Test
   void cw45()
   {
      assertEquals(CompassDir.UP_RIGHT, CompassDir.UP.cw45());
      assertEquals(CompassDir.RIGHT, CompassDir.UP_RIGHT.cw45());
      assertEquals(CompassDir.DOWN_RIGHT, CompassDir.RIGHT.cw45());
      assertEquals(CompassDir.DOWN, CompassDir.DOWN_RIGHT.cw45());
      assertEquals(CompassDir.DOWN_LEFT, CompassDir.DOWN.cw45());
      assertEquals(CompassDir.LEFT, CompassDir.DOWN_LEFT.cw45());
      assertEquals(CompassDir.UP_LEFT, CompassDir.LEFT.cw45());
      assertEquals(CompassDir.UP, CompassDir.UP_LEFT.cw45());
   }
   
   
   @Test
   void zeroSum()
   {
      // the sum of all directions must be zero
      Position pos1 = new Position(2);
      Arrays.stream(CompassDir.values()).forEach(pos1::addBy);
      assertEquals(new Position(2), pos1);
   }
   
   
   @Test
   void unitMagnitude()
   {
      // the magnitude of direction must be 1 on any axis changed
      Arrays.stream(CompassDir.values()).forEach(
         (dir) ->
         {
            Position pos2 = new Position(2);
            pos2.addBy(dir);
         
            int absX = Math.abs(pos2.getX());
            int absY = Math.abs(pos2.getY());
         
            assertTrue(absX == 0 || absX == 1);
            assertTrue(absY == 0 || absY == 1);
         }
      );
   }
}
