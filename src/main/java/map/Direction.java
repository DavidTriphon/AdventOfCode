package map;

import java.awt.*;
import java.util.*;


public enum Direction
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
      Arrays.stream(Direction.values()).forEach(Direction::setNeighbors);
   }
   
   
   public final int x, y;
   private Direction _ccw, _cw;
   
   
   Direction(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   
   // Only to be called once after all enums have been constructed\
   // do not call anywhere other than the static block!
   private void setNeighbors()
   {
      Direction[] directions = Direction.values();
      this._ccw = directions[(this.ordinal() + directions.length - 1) % directions.length];
      this._cw  = directions[(this.ordinal() + 1) % directions.length];
   }
   
   
   public Direction ccw90()
   {
      return _ccw._ccw;
   }
   
   
   public Direction cw90()
   {
      return _cw._cw;
   }
   
   
   public Direction ccw45()
   {
      return _ccw;
   }
   
   
   public Direction cw45()
   {
      return _cw;
   }
   
   
   public void move(Point pos)
   {
      move(pos, 1);
   }
   
   
   public void move(Point pos, int mag)
   {
      pos.x += x * mag;
      pos.y += y * mag;
   }
}
