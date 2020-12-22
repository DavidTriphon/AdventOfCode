package davidt.aoc.map;

import java.util.*;


public enum CompassDir implements IDimensional.Gettable <Integer>
{
   UP(0, 1),
   UP_RIGHT(1, 1),
   RIGHT(1, 0),
   DOWN_RIGHT(1, -1),
   DOWN(0, -1),
   DOWN_LEFT(-1, -1),
   LEFT(-1, 0),
   UP_LEFT(-1, 1),
   ;
   
   
   static
   {
      // sets the neighbors for all of the enums after construction
      Arrays.stream(CompassDir.values()).forEach(CompassDir::setNeighbors);
   }
   
   
   public final int x, y;
   private CompassDir _ccw, _cw;
   
   
   CompassDir(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   
   // Only to be called once after all enums have been constructed\
   // do not call anywhere other than the static block!
   private void setNeighbors()
   {
      CompassDir[] compassDirs = CompassDir.values();
      this._ccw = compassDirs[(this.ordinal() + compassDirs.length - 1) % compassDirs.length];
      this._cw = compassDirs[(this.ordinal() + 1) % compassDirs.length];
   }
   
   
   public CompassDir left()
   {
      return ccw90();
   }
   
   
   public CompassDir right()
   {
      return cw90();
   }
   
   
   public CompassDir down()
   {
      return opposite();
   }
   
   
   public CompassDir ccw90()
   {
      return _ccw._ccw;
   }
   
   
   public CompassDir cw90()
   {
      return _cw._cw;
   }
   
   
   public CompassDir ccw45()
   {
      return _ccw;
   }
   
   
   public CompassDir cw45()
   {
      return _cw;
   }
   
   
   public CompassDir opposite()
   {
      return left().left();
   }
   
   
   @Override
   public int dims()
   {
      return 2;
   }
   
   
   @Override
   public Integer get(int dim)
   {
      IDimensional.checkDimIndexArg(this, dim);
      if (dim == 0)
         return x;
      else
         return y;
   }
   
   
   @Override
   public Integer[] get()
   {
      return new Integer[] {x, y};
   }
   
   
   public static CompassDir[] compassValues()
   {
      return new CompassDir[] {UP, RIGHT, LEFT, DOWN};
   }
}
