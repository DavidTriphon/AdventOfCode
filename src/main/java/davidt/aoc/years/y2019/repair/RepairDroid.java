package davidt.aoc.years.y2019.repair;

import davidt.aoc.map.*;
import davidt.aoc.years.y2019.cpu.*;
import davidt.aoc.years.y2019.days.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.*;


public class RepairDroid
{
   // public constants
   
   public static final int TILE_UNKNOWN  = 0;
   public static final int TILE_PATH     = 1;
   public static final int TILE_BLOCKED  = 2;
   public static final int TILE_ROBOT    = 3;
   public static final int TILE_OXYGEN   = 4;
   public static final int TILE_DEAD_END = 5;
   
   public static final int RESPONSE_BLOCKED = 0;
   public static final int RESPONSE_MOVED   = 1;
   public static final int RESPONSE_FOUND   = 2;
   
   // private constants
   
   private static final long MOVE_NORTH = 1;
   private static final long MOVE_SOUTH = 2;
   private static final long MOVE_EAST  = 3;
   private static final long MOVE_WEST  = 4;
   
   private static final URL PROGRAM_FILE_LOC = Day15A.class.getResource("input/input15.txt");
   
   private static final Map <CompassDir, Long> DIR_MAP = Map.ofEntries(
      Map.entry(CompassDir.UP, MOVE_NORTH),
      Map.entry(CompassDir.DOWN, MOVE_SOUTH),
      Map.entry(CompassDir.RIGHT, MOVE_EAST),
      Map.entry(CompassDir.LEFT, MOVE_WEST)
   );
   
   private static final Function <Integer, String> TRANSLATOR = (i) ->
   {
      switch (i)
      {
         case TILE_UNKNOWN:
            return "~~";
         case TILE_PATH:
            return "  ";
         case TILE_BLOCKED:
            return "##";
         case TILE_ROBOT:
            return "[]";
         case TILE_OXYGEN:
            return "OO";
         case TILE_DEAD_END:
            return "..";
         default:
            return "??";
      }
   };
   
   private static final Grid2DFlatPrinter <Integer> PRINTER =
      new Grid2DFlatPrinter <>(TRANSLATOR, false);
   
   // fields
   
   private final Program _program = new Program();
   
   private final InfiniteGridMap <Integer> _locationInfo = new InfiniteGridMap <>(2, TILE_UNKNOWN);
   
   private final Position _currentPos = new Position(2);
   
   private Position _oxygenLoc = null;
   
   // constructor
   
   
   public RepairDroid()
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
      
      _locationInfo.set(_currentPos, TILE_PATH);
   }
   
   // public methods
   
   
   public void autoFindOxygen()
   {
      CompassDir dir = CompassDir.UP;
      
      while (!foundOxygen())
      {
         if (move(dir.right()) != RepairDroid.RESPONSE_BLOCKED)
         {
            move(dir.left());
         }
         
         if (move(dir.left()) != RepairDroid.RESPONSE_BLOCKED)
         {
            move(dir.right());
         }
         
         if (move(dir) == RepairDroid.RESPONSE_BLOCKED)
         {
            dir = dir.right();
            
            if (move(dir) == RepairDroid.RESPONSE_BLOCKED)
            {
               dir = dir.down();
            }
         }
      }
   }
   
   
   public void autoExploreMap()
   {
      CompassDir dir = CompassDir.UP;
      
      do
      {
         if (move(dir) == RepairDroid.RESPONSE_BLOCKED)
         {
            dir = dir.right();
         }
         else
         {
            dir = dir.left();
         }
      }
      while (!_currentPos.isOrigin());
   }
   
   
   public int timeToOxygenation()
   {
      InfiniteGridMap <Integer> current = getMap();
      current.set(_currentPos, TILE_PATH);
      
      int time = 0;
      
      while (current.countOf(TILE_PATH) + current.countOf(TILE_DEAD_END) > 0)
      {
         InfiniteGridMap <Integer> next = current.copy();
   
         for (Position pos : current.listPositions())
         {
            if (current.get(pos) == TILE_OXYGEN)
            {
               for (CompassDir dir : CompassDir.compassValues())
               {
                  Position nextToPos = pos.copy().add(dir);
            
                  int nextDoor = current.get(nextToPos);
            
                  if (nextDoor == TILE_PATH || nextDoor == TILE_DEAD_END)
                     next.set(nextToPos, TILE_OXYGEN);
               }
            }
         }
   
         System.out.println(PRINTER.toMapString(getMap()));
   
         current = next;
   
         time++;
      }
      
      return time;
   }
   
   
   public int move(CompassDir moveDir)
   {
      _program.setInput(new Long[] {DIR_MAP.get(moveDir)});
      _program.compute();
      
      Long[] output = _program.getOutput();
      int response = (int) (long) output[output.length - 1];
      
      switch (response)
      {
         case RESPONSE_BLOCKED:
         {
            _locationInfo.set(_currentPos.copy().add(moveDir), TILE_BLOCKED);
         }
         break;
         case RESPONSE_MOVED:
         {
            Position previousPos = new Position(_currentPos);
            _currentPos.add(moveDir);
            if (_locationInfo.get(_currentPos) == TILE_PATH)
               _locationInfo.set(previousPos, TILE_DEAD_END);
            _locationInfo.set(_currentPos, TILE_PATH);
         }
         break;
         case RESPONSE_FOUND:
         {
            Position previousPos = new Position(_currentPos);
            _currentPos.add(moveDir);
            if (_locationInfo.get(_currentPos) == TILE_PATH)
               _locationInfo.set(previousPos, TILE_DEAD_END);
            _locationInfo.set(_currentPos, TILE_PATH);
            _oxygenLoc = new Position(_currentPos);
         }
         break;
      }
      
      return response;
   }
   
   
   public Position getRobotPos()
   {
      return new Position(_currentPos);
   }
   
   
   public boolean foundOxygen()
   {
      return _oxygenLoc != null;
   }
   
   
   public Position getOxygenPos()
   {
      return new Position(_oxygenLoc);
   }
   
   
   public String getFormattedMap()
   {
      return PRINTER.toMapString(getMap());
   }
   
   
   public InfiniteGridMap <Integer> getMap()
   {
      InfiniteGridMap <Integer> copy = _locationInfo.copy();
      copy.set(_currentPos, TILE_ROBOT);
      if (foundOxygen())
         copy.set(_oxygenLoc, TILE_OXYGEN);
      return copy;
   }
}
