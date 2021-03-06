package davidt.aoc.years.y2019.arcade;

import davidt.aoc.map.*;
import davidt.aoc.years.y2019.cpu.*;

import java.io.*;
import java.net.*;
import java.util.function.*;


public class Arcade
{
   // constants
   
   public static final int TILE_EMPTY  = 0;
   public static final int TILE_WALL   = 1;
   public static final int TILE_BLOCK  = 2;
   public static final int TILE_PADDLE = 3;
   public static final int TILE_BALL   = 4;
   
   public static final long JOYSTICK_LEFT    = -1;
   public static final long JOYSTICK_NEUTRAL = 0;
   public static final long JOYSTICK_RIGHT   = 1;
   
   private static final long SEGMENT_X = -1;
   private static final long SEGMENT_Y = 0;
   
   private static final int  QUARTER_INDEX = 0;
   private static final long QUARTER_COUNT = 2L;
   
   private static final URL PROGRAM_FILE_LOC =
      Arcade.class.getResource("/davidt/aoc/years/y2019/days/input/input13.txt");
   
   private static final Function <Integer, String> TRANSLATOR = (i) ->
   {
      switch (i)
      {
         case TILE_EMPTY:
            return "  ";
         case TILE_WALL:
            return "XX";
         case TILE_BLOCK:
            return "[]";
         case TILE_PADDLE:
            return "--";
         case TILE_BALL:
            return "()";
         default:
            return "??";
      }
   };
   
   private static final Grid2DFlatPrinter <Integer> PRINTER =
      new Grid2DFlatPrinter <>(TRANSLATOR, true);
   
   // fields
   
   private final Program _program = new Program();
   
   private final InfiniteGridMap <Integer> _screen = new InfiniteGridMap <>(2, TILE_EMPTY);
   
   private int  _outIndex       = 0;
   private long _segmentDisplay = 0;
   
   // constructors
   
   
   public Arcade()
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
   }
   
   // public methods
   
   
   public void run()
   {
      _program.compute();
      
      Long[] out = _program.getOutput();
      
      while (_outIndex < out.length)
      {
         long x = out[_outIndex++];
         long y = out[_outIndex++];
         long tile = out[_outIndex++];
         
         if (x == SEGMENT_X && y == SEGMENT_Y)
         {
            _segmentDisplay = tile;
         }
         else
         {
            _screen.set(new Position(new int[] {(int) x, (int) y}), (int) tile);
         }
      }
   }
   
   
   public void hackQuarters()
   {
      Long[] mem = _program.getMemory();
      mem[QUARTER_INDEX] = QUARTER_COUNT;
      _program.setMemory(mem);
   }
   
   
   public void setJoystick(long value)
   {
      _program.setInput(new Long[] {value});
   }
   
   
   public boolean isPlaying()
   {
      return !isWon() && !isLost();
   }
   
   
   public boolean isWon()
   {
      return _screen.countOf(TILE_BLOCK) == 0;
   }
   
   
   public boolean isLost()
   {
      return _program.isHalted();
   }
   
   
   public long getCurrentScore()
   {
      return _segmentDisplay;
   }
   
   
   public int getBallX()
   {
      for (Position pos : _screen.listPositions())
      {
         if (_screen.get(pos).equals(TILE_BALL))
         {
            return pos.getX();
         }
      }
      
      throw new IllegalStateException("The ball is not on the screen!");
   }
   
   
   public int getPaddleX()
   {
      for (Position pos : _screen.listPositions())
      {
         if (_screen.get(pos).equals(TILE_PADDLE))
         {
            return pos.getX();
         }
      }
      
      throw new IllegalStateException("The paddle is not on the screen!");
   }
   
   
   public InfiniteGridMap <Integer> getScreen()
   {
      return _screen.copy();
   }
   
   
   public String getScreenString()
   {
      return PRINTER.toMapString(_screen);
   }
}
