package davidt.aoc.map;

import org.junit.jupiter.api.*;

import java.util.*;

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
   void equals()
   {
      Position posA = new Position(1);
      Position pos1 = new Position(1);
      Position pos2 = new Position(2);
      Position pos3 = new Position(3);
      Position posB = new Position(1, 1);
      Position posC = new Position(1, 2);
      Position posD = new Position(1, 2);
      
      // equals for identity
      assertEquals(posA, posA);
      assertEquals(pos1, pos1);
      assertEquals(pos2, pos2);
      assertEquals(pos3, pos3);
      
      // equals (&not) for dims
      assertEquals(posA, pos1);
      assertNotEquals(pos1, pos2);
      assertNotEquals(pos2, pos3);
      
      // different nums
      assertNotEquals(posA, posB);
      assertNotEquals(posA, posC);
      assertNotEquals(posA, posD);
      
      assertNotEquals(posB, posC);
      assertNotEquals(posB, posD);
      
      assertEquals(posC, posD);
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
      assertEquals(new Position(new int[] {-1, -1, -1}), pos.copy().negate());
      assertEquals(new Position(new int[] {1, -1, 1}), pos.copy().negate(1));
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
   
   
   @Test
   void listContainedPositions()
   {
      Position pos = new Position(new int[] {2, 3, 5});
      List <Position> containedPositions = pos.listContainedPositions();
      
      assertEquals(2 * 3 * 5, containedPositions.size());
      for (int i = 0; i < containedPositions.size(); i++)
      {
         assertNotEquals(2, containedPositions.get(i).getX());
         assertNotEquals(3, containedPositions.get(i).getY());
         assertNotEquals(5, containedPositions.get(i).getZ());
         for (int j = i + 1; j < containedPositions.size(); j++)
         {
            assertNotEquals(containedPositions.get(j), containedPositions.get(i));
         }
      }
   }
}
