package map;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.*;


public abstract class GridMap <T, M extends GridMap <T, M>>
{
   // fields
   
   protected final boolean _yIsDown;
   
   // constructor
   
   
   public GridMap(boolean yIsDown)
   {
      _yIsDown = yIsDown;
   }
   
   // abstract methods to implement in subclasses
   
   
   public abstract T get(Point pos);
   
   public abstract void set(Point pos, T obj);
   
   public abstract List <Point> listPositions();
   
   // bounds do not represent the limits of valid positions
   // they represent when to stop searching because all values outside are the same
   // in some cases null for being outside space, in others infinitely a default value
   
   
   public abstract int getXLowerBound();
   
   public abstract int getXUpperBound();
   
   public abstract int getYLowerBound();
   
   public abstract int getYUpperBound();
   
   // this should perform a deep copy to be used by the automata ruleset iteration methods
   public abstract M copy();
   
   // implemented methods that apply the same to all subclasses
   
   
   public boolean isInBounds(Point pos)
   {
      return getXLowerBound() <= pos.x && pos.x < getXUpperBound()
         && getYLowerBound() <= pos.y && pos.y < getYUpperBound();
   }
   
   
   public Map <T, Integer> count()
   {
      HashMap <T, Integer> counts = new HashMap <>();
      
      for (Point pos : listPositions())
      {
         T obj = get(pos);
         if (!counts.containsKey(obj))
            counts.put(obj, 1);
         else
            counts.put(obj, counts.get(obj) + 1);
      }
      
      return counts;
   }
   
   
   public Map <T, Integer> countNeighborsOf(Point pos)
   {
      HashMap <T, Integer> neighbors = new HashMap <>();
      
      for (Direction dir : Direction.values())
      {
         Point neighborPos = new Point(pos);
         dir.move(neighborPos);
         if (isInBounds(neighborPos))
         {
            T neighbor = get(neighborPos);
            if (!neighbors.containsKey(neighbor))
               neighbors.put(neighbor, 1);
            else
               neighbors.put(neighbor, neighbors.get(neighbor) + 1);
         }
      }
      
      return neighbors;
   }
   
   
   public T getFirstInLine(Point origin, Direction dir, List <T> list, boolean isWhitelist)
   {
      if (origin == null)
         throw new NullPointerException("pos cannot be null");
      
      Point lookingAt = new Point(origin);
      
      do
      {
         dir.move(lookingAt);
      }
      while (isInBounds(lookingAt) && isWhitelist != list.contains(get(lookingAt)));
      
      if (isInBounds(lookingAt))
         return get(lookingAt);
      else
         return null;
   }
   
   public Map<T, Integer> countSeenFrom(Point origin, List<T> list, boolean isWhitelist)
   {
      HashMap <T, Integer> thoseSeen = new HashMap <>();
   
      for (Direction dir : Direction.values())
      {
         T thatSeen = getFirstInLine(origin, dir, list, isWhitelist);
         
         if (thatSeen != null)
         {
            if (!thoseSeen.containsKey(thatSeen))
               thoseSeen.put(thatSeen, 1);
            else
               thoseSeen.put(thatSeen, thoseSeen.get(thatSeen) + 1);
         }
      }
      
      return thoseSeen;
   }
   
   
   public void applyRule(BiFunction <Point, GridMap <T, M>, T> rule)
   {
      M copy = copy();
      
      for (Point pos : listPositions())
      {
         set(pos, rule.apply(pos, copy));
      }
   }
   
   
   public void applyRuleUntilStable(BiFunction <Point, GridMap <T, M>, T> rule, int stableRange)
   {
      ArrayList <M> previousStates = new ArrayList <>();
      
      do
      {
         previousStates.add(copy());
         if (previousStates.size() > stableRange)
            previousStates.remove(0);
         
         applyRule(rule);
      }
      while (!previousStates.contains(this));
   }
   
   
   public String toMapString(Function <T, Character> translator)
   {
      // generate string grid using translator
      StringBuilder sb = new StringBuilder();
      
      int minX = getXLowerBound();
      int maxX = getXUpperBound();
      int minY = getYLowerBound();
      int maxY = getYUpperBound();
      
      if (_yIsDown)
         for (int y = minY; y < maxY; y++)
         {
            mapStringHelperX(y, sb, minX, maxX, translator);
         }
      else
         for (int y = maxY - 1; minY <= y; y--)
         {
            mapStringHelperX(y, sb, minX, maxX, translator);
         }
      
      return sb.toString();
   }
   
   // private methods
   
   
   // for use by toMapString only
   private void mapStringHelperX(
      int y, StringBuilder sb, int minX, int maxX, Function <T, Character> translator)
   {
      for (int x = minX; x < maxX; x++)
      {
         sb.append(translator.apply(get(new Point(x, y))));
      }
      
      sb.append('\n');
   }
}
