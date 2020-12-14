package years.y2019.vacuum;

import map.*;
import util.*;
import years.y2019.cpu.*;

import java.awt.*;
import java.io.*;


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
   
   private static final String PROGRAM_FILE_LOC =
      ReaderUtil.RESOURCES_LOCATION + "years/y2019/input17.txt";
   
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
      
      // run once to update map
      setMap();
   }
   
   // public methods
   
   
   public FiniteGridMap <Character> getMap()
   {
      return _map.copy();
   }
   
   
   public String getMapString()
   {
      return _map.toMapString((c) -> c);
   }
   
   
   public int getCrossingsCount()
   {
      return _map.count().get(TILE_CROSS);
   }
   
   
   public int getCrossingAlignmentSum()
   {
      int sum = 0;
      
      for (int x = 1; x < _map.width - 1; x++)
      {
         for (int y = 1; y < _map.height - 1; y++)
         {
            if (_map.get(new Point(x, y)) == TILE_CROSS)
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
      for (int x = 1; x < _map.width - 1; x++)
      {
         for (int y = 1; y < _map.height - 1; y++)
         {
            Point pos = new Point(x, y);
            
            if (_map.get(pos) != TILE_SPACE)
            {
               boolean isCross = true;
               
               for (Direction dir : Direction.values())
               {
                  Point offset = dir.offset(pos);
                  if (_map.get(offset) == TILE_SPACE)
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
