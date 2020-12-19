package davidt.aoc.map;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class PositionTest
{
   
   @Test
   void getDimensionCount()
   {
      assertEquals(0, new Position(new int[] {}).dims());
      assertEquals(1, new Position(new int[] {1}).dims());
      assertEquals(2, new Position(new int[] {1, 2}).dims());
      assertEquals(3, new Position(new int[] {1, 2, 3}).dims());
      assertEquals(4, new Position(new int[] {1, 2, 3, 4}).dims());
   }
   
   
   @Test
   void get()
   {
      Position pos0 = new Position(new int[] {});
      assertThrows(IllegalArgumentException.class, () -> pos0.get(0));
      
      Position pos1 = new Position(new int[] {1});
      assertEquals(1, pos1.get(0));
      assertThrows(IllegalArgumentException.class, () -> pos1.get(1));
      
      Position pos2 = new Position(new int[] {1, 2});
      assertEquals(1, pos2.get(0));
      assertEquals(2, pos2.get(1));
      assertThrows(IllegalArgumentException.class, () -> pos2.get(2));
      
      Position pos3 = new Position(new int[] {1, 2, 3});
      assertEquals(1, pos3.get(0));
      assertEquals(2, pos3.get(1));
      assertEquals(3, pos3.get(2));
      assertThrows(IllegalArgumentException.class, () -> pos3.get(3));
      
      Position pos4 = new Position(new int[] {1, 2, 3, 4});
      assertEquals(1, pos4.get(0));
      assertEquals(2, pos4.get(1));
      assertEquals(3, pos4.get(2));
      assertEquals(4, pos4.get(3));
      assertThrows(IllegalArgumentException.class, () -> pos4.get(4));
   }
   
   
   @Test
   void getX()
   {
      assertThrows(IllegalArgumentException.class, () -> new Position(new int[] {}).getX());
      
      assertEquals(1, new Position(new int[] {1}).getX());
      assertEquals(1, new Position(new int[] {1, 2}).getX());
      assertEquals(1, new Position(new int[] {1, 2, 3}).getX());
      assertEquals(1, new Position(new int[] {1, 2, 3, 4}).getX());
   }
   
   
   @Test
   void setX()
   {
   }
   
   
   @Test
   void getY()
   {
      assertThrows(IllegalArgumentException.class, () -> new Position(new int[] {}).getY());
      assertThrows(IllegalArgumentException.class, () -> new Position(new int[] {1}).getY());
      
      assertEquals(2, new Position(new int[] {1, 2}).getY());
      assertEquals(2, new Position(new int[] {1, 2, 3}).getY());
      assertEquals(2, new Position(new int[] {1, 2, 3, 4}).getY());
   }
   
   
   @Test
   void setY()
   {
   }
   
   
   @Test
   void getZ()
   {
      assertThrows(IllegalArgumentException.class, () -> new Position(new int[] {}).getZ());
      assertThrows(IllegalArgumentException.class, () -> new Position(new int[] {1}).getZ());
      assertThrows(IllegalArgumentException.class, () -> new Position(new int[] {1, 2}).getZ());
      
      assertEquals(3, new Position(new int[] {1, 2, 3}).getZ());
      assertEquals(3, new Position(new int[] {1, 2, 3, 4}).getZ());
   }
   
   
   @Test
   void setZ()
   {
   }
   
   
   @Test
   void set()
   {
   }
   
   
   @Test
   void testSet()
   {
   }
   
   
   @Test
   void addBy()
   {
   }
   
   
   @Test
   void addByScalar()
   {
   }
   
   
   @Test
   void subtractBy()
   {
   }
   
   
   @Test
   void subtractByScalar()
   {
   }
   
   
   @Test
   void multiplyBy()
   {
   }
   
   
   @Test
   void divideBy()
   {
   }
   
   
   @Test
   void moduloBy()
   {
   }
   
   
   @Test
   void makeOpposite()
   {
   }
   
   
   @Test
   void copy()
   {
   }
   
   
   @Test
   void sumWith()
   {
   }
   
   
   @Test
   void differenceWith()
   {
   }
   
   
   @Test
   void productOf()
   {
   }
   
   
   @Test
   void quotientOf()
   {
   }
   
   
   @Test
   void remainderOf()
   {
   }
   
   
   @Test
   void getOpposite()
   {
      Position pos = new Position(new int[] {1, 1, 1});
      assertEquals(new Position(new int[] {-1, -1, -1}), pos.getOpposite());
      assertEquals(new Position(new int[] {1, -1, 1}), pos.getOpposite(1));
   }
   
   
   @Test
   void indexPosBounds()
   {
      Position pos = new Position(new int[] {1, 2, 3});
      Position bounds = new Position(new int[] {10, 10, 10});
      int index = 321;
      
      assertEquals(index, Position.getIndexOfPosIn(pos, bounds));
      
      assertEquals(pos, Position.getPosOfIndexIn(index, bounds));
   }
}
