package map;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class DirectionTest
{
   
   @Test
   void ccw90()
   {
      assertEquals(Direction.LEFT, Direction.UP.ccw90());
      assertEquals(Direction.DOWN, Direction.LEFT.ccw90());
      assertEquals(Direction.RIGHT, Direction.DOWN.ccw90());
      assertEquals(Direction.UP, Direction.RIGHT.ccw90());
      
      assertEquals(Direction.UP_LEFT, Direction.UP_RIGHT.ccw90());
      assertEquals(Direction.DOWN_LEFT, Direction.UP_LEFT.ccw90());
      assertEquals(Direction.DOWN_RIGHT, Direction.DOWN_LEFT.ccw90());
      assertEquals(Direction.UP_RIGHT, Direction.DOWN_RIGHT.ccw90());
   }
   
   
   @Test
   void cw90()
   {
      assertEquals(Direction.RIGHT, Direction.UP.cw90());
      assertEquals(Direction.DOWN, Direction.RIGHT.cw90());
      assertEquals(Direction.LEFT, Direction.DOWN.cw90());
      assertEquals(Direction.UP, Direction.LEFT.cw90());
      
      assertEquals(Direction.DOWN_RIGHT, Direction.UP_RIGHT.cw90());
      assertEquals(Direction.DOWN_LEFT, Direction.DOWN_RIGHT.cw90());
      assertEquals(Direction.UP_LEFT, Direction.DOWN_LEFT.cw90());
      assertEquals(Direction.UP_RIGHT, Direction.UP_LEFT.cw90());
   }
   
   
   @Test
   void ccw45()
   {
      assertEquals(Direction.UP_LEFT, Direction.UP.ccw45());
      assertEquals(Direction.LEFT, Direction.UP_LEFT.ccw45());
      assertEquals(Direction.DOWN_LEFT, Direction.LEFT.ccw45());
      assertEquals(Direction.DOWN, Direction.DOWN_LEFT.ccw45());
      assertEquals(Direction.DOWN_RIGHT, Direction.DOWN.ccw45());
      assertEquals(Direction.RIGHT, Direction.DOWN_RIGHT.ccw45());
      assertEquals(Direction.UP_RIGHT, Direction.RIGHT.ccw45());
      assertEquals(Direction.UP, Direction.UP_RIGHT.ccw45());
   }
   
   
   @Test
   void cw45()
   {
      assertEquals(Direction.UP_RIGHT, Direction.UP.cw45());
      assertEquals(Direction.RIGHT, Direction.UP_RIGHT.cw45());
      assertEquals(Direction.DOWN_RIGHT, Direction.RIGHT.cw45());
      assertEquals(Direction.DOWN, Direction.DOWN_RIGHT.cw45());
      assertEquals(Direction.DOWN_LEFT, Direction.DOWN.cw45());
      assertEquals(Direction.LEFT, Direction.DOWN_LEFT.cw45());
      assertEquals(Direction.UP_LEFT, Direction.LEFT.cw45());
      assertEquals(Direction.UP, Direction.UP_LEFT.cw45());
   }
   
   
   @Test
   void zeroSum()
   {
      // the sum of all directions must be zero
      Point pos1 = new Point(0, 0);
      Arrays.stream(Direction.values()).forEach((dir) -> dir.move(pos1));
      assertEquals(new Point(0, 0), pos1);
   }
   
   
   @Test
   void unitMagnitude()
   {
      // the magnitude of direction must be 1 on any axis changed
      Arrays.stream(Direction.values()).forEach(
         (dir) ->
         {
            Point pos2 = new Point(0, 0);
            dir.move(pos2);
            
            int absX = Math.abs(pos2.x);
            int absY = Math.abs(pos2.y);
            
            assertTrue(absX == 0 || absX == 1);
            assertTrue(absY == 0 || absY == 1);
         }
      );
   }
}