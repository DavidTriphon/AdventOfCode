package davidt.aoc.map;

import davidt.aoc.util.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class FiniteGridMapTest
{
   public static final URL TEST_MAP_1 = FiniteGridMapTest.class.getResource("input/testMap1.txt");
   
   
   @Test
   void get()
   {
      FiniteGridMap <Integer> map = new FiniteGridMap <>(new Position(new int[] {10, 5}), 7);
      
      assertEquals(7, map.get(new Position(2)));
      assertThrows(IllegalArgumentException.class, () -> map.get(new Position(1)));
      assertThrows(IllegalArgumentException.class, () -> map.get(new Position(3)));
      assertThrows(IllegalArgumentException.class, () -> map.get(new Position(new int[] {-1, -1})));
      assertThrows(IllegalArgumentException.class, () -> map.get(new Position(new int[] {10, 5})));
      assertEquals(7, map.get(new Position(new int[] {9, 4})));
   }
   
   
   @Test
   void set()
   {
      int width = 10;
      int height = 5;
      
      FiniteGridMap <Integer> map =
         new FiniteGridMap <>(new Position(new int[] {width, height}), 7);
      
      for (int x = 0; x < width; x++)
      {
         for (int y = 0; y < height; y++)
         {
            Position pos = new Position(new int[] {x, y});
            map.set(pos, x + y);
         }
      }
      
      for (int x = 0; x < width; x++)
      {
         for (int y = 0; y < height; y++)
         {
            Position pos = new Position(new int[] {x, y});
            assertEquals(x + y, map.get(pos));
         }
      }
   }
   
   
   @Test
   void listPositions()
   {
      int width = 10;
      int height = 5;
      
      FiniteGridMap <Integer> map =
         new FiniteGridMap <>(new Position(new int[] {width, height}), 7);
      
      List <Position> positionList = map.listPositions();
      
      assertEquals(width * height, positionList.size());
      
      for (int i = 0; i < positionList.size(); i++)
      {
         Position pos1 = positionList.get(i);
         
         for (int dim = 0; dim < pos1.dims(); dim++)
         {
            assertTrue(map.getUpperBound().get(dim) > pos1.get(dim));
            assertTrue(map.getLowerBound().get(dim) <= pos1.get(dim));
         }
         
         for (int j = i + 1; j < positionList.size(); j++)
         {
            assertNotEquals(positionList.get(i), positionList.get(j));
         }
      }
   }
   
   
   @Test
   void getLowerBound()
   {
      int width = 10;
      int height = 5;
      
      FiniteGridMap <Integer> map =
         new FiniteGridMap <>(new Position(new int[] {width, height}), 7);
      
      assertEquals(new Position(new int[] {0, 0}), map.getLowerBound());
   }
   
   
   @Test
   void getUpperBound()
   {
      int width = 10;
      int height = 5;
      
      FiniteGridMap <Integer> map =
         new FiniteGridMap <>(new Position(new int[] {width, height}), 7);
      
      assertEquals(new Position(new int[] {width, height}), map.getUpperBound());
   }
   
   
   @Test
   void copy()
   {
      int width = 10;
      int height = 5;
      
      FiniteGridMap <Integer> map =
         new FiniteGridMap <>(new Position(new int[] {width, height}), 7);
      Position answerPos = new Position(new int[] {3, 4});
      map.set(answerPos, 42);
      FiniteGridMap <Integer> copy = map.copy();
      
      assertEquals(map, copy);
      assertEquals(42, copy.get(answerPos));
   }
   
   
   @Test
   void fromString() throws IOException
   {
      FiniteGridMap <Character> map =
         FiniteGridMap.fromString(ReaderUtil.getFileString(TEST_MAP_1));
      
      assertEquals(new Position(new int[] {12, 3}), map.getBoundSize());
      
      assertEquals("test", get4Letters(map, new Position(new int[] {0, 0})));
      assertEquals("test", get4Letters(map, new Position(new int[] {4, 0})));
      assertEquals("test", get4Letters(map, new Position(new int[] {8, 0})));
      assertEquals("make", get4Letters(map, new Position(new int[] {0, 1})));
      assertEquals("file", get4Letters(map, new Position(new int[] {4, 1})));
      assertEquals("true", get4Letters(map, new Position(new int[] {8, 1})));
      assertEquals("bake", get4Letters(map, new Position(new int[] {0, 2})));
      assertEquals("some", get4Letters(map, new Position(new int[] {4, 2})));
      assertEquals("cake", get4Letters(map, new Position(new int[] {8, 2})));
   }
   
   
   private String get4Letters(FiniteGridMap <Character> map, Position pos)
   {
      char c1 = map.get(pos);
      char c2 = map.get(pos.copy().add(new Position(new int[] {1, 0})));
      char c3 = map.get(pos.copy().add(new Position(new int[] {2, 0})));
      char c4 = map.get(pos.copy().add(new Position(new int[] {3, 0})));
      
      return String.format("%c%c%c%c", c1, c2, c3, c4);
   }
}
