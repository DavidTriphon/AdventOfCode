package davidt.aoc.years.y2019.vacuum;

import davidt.aoc.map.*;
import davidt.aoc.years.y2019.cpu.*;
import davidt.aoc.years.y2019.days.*;

import java.io.*;
import java.net.*;


public class VacuumRobot
{
   // public constants
   
   public static final char TILE_SCAFFOLD    = '#';
   public static final char TILE_SPACE       = '.';
   public static final char TILE_ROBOT_UP    = '^';
   public static final char TILE_ROBOT_DOWN  = 'v';
   public static final char TILE_ROBOT_LEFT  = '<';
   public static final char TILE_ROBOT_RIGHT = '>';
   public static final char TILE_ROBOT_LOST  = 'X';
   public static final char TILE_CROSS       = 'O';
   
   // private constants
   
   private static final URL PROGRAM_FILE_LOC = Day17A.class.getResource("input/input17.txt");
   
   private static final Grid2DFlatPrinter <Character> PRINTER =
      new Grid2DFlatPrinter <>(Object::toString, false);
   
   // fields
   
   private final Program _program = new Program();
   
   private FiniteGridMap <Character> _map;
   
   // constructor
   
   
   public VacuumRobot()
   {
      // this should never have problems, and I don't want the constructor throwing exceptions
      try
      {
         _program.setMemory(Program.getMemoryFromFile(PROGRAM_FILE_LOC));
      }
      catch (IOException exc)
      {
         System.exit(-1);
      }
   
      // run once to put output
      _program.compute();
   
      // run once to update davidt.aoc.map
      setMap();
   }
   
   // public methods
   
   
   public FiniteGridMap <Character> getMap()
   {
      return _map.copy();
   }
   
   
   public String getMapString()
   {
      return PRINTER.toMapString(_map);
   }
   
   
   public int getCrossingsCount()
   {
      return _map.countOf(TILE_CROSS);
   }
   
   
   public int getCrossingAlignmentSum()
   {
      int sum = 0;
   
      for (int x = 1; x < _map.getBoundSize().getX() - 1; x++)
      {
         for (int y = 1; y < _map.getBoundSize().getY() - 1; y++)
         {
            if (_map.get(new Position(new int[] {x, y})) == TILE_CROSS)
               sum += x * y;
         }
      }
   
      return sum;
   }
   
   // private methods
   
   
   private void setMap()
   {
      _map = FiniteGridMap.fromString(getOutputMap(), (c) -> c);
      identifyIntersections();
   }
   
   
   private void identifyIntersections()
   {
      for (int x = 1; x < _map.getBoundSize().getX() - 1; x++)
      {
         for (int y = 1; y < _map.getBoundSize().getY() - 1; y++)
         {
            Position pos = new Position(new int[] {x, y});
         
            if (_map.get(pos) != TILE_SPACE)
            {
               boolean isCross = true;
            
               for (CompassDir dir : CompassDir.compassValues())
               {
                  if (_map.get(pos.sumWith(dir)) == TILE_SPACE)
                     isCross = false;
               }
            
               if (isCross)
                  _map.set(pos, TILE_CROSS);
            }
         }
      }
   }
   
   
   private String getOutputMap()
   {
      Long[] output = _program.getOutput();
      
      StringBuilder sb = new StringBuilder();
      
      for (long out : output)
      {
         sb.append((char) out);
      }
      
      return sb.toString();
   }
}
