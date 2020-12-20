package davidt.aoc.map;

import java.util.*;
import java.util.function.*;


public abstract class GridMap <T, M extends GridMap <T, M>> implements IDimensional
{
   // fields
   
   protected final int _dimensionCount;
   
   // constructor
   
   
   public GridMap(int dimensionCount)
   {
      _dimensionCount = dimensionCount;
      if (_dimensionCount <= 0)
         throw new IllegalArgumentException("The number of dimensions must be positive!");
   }
   
   // implemented methods from interface
   
   
   @Override
   public int dims()
   {
      return _dimensionCount;
   }
   
   // abstract methods to implement in subclasses
   
   
   public abstract T get(Position pos);
   
   public abstract void set(Position pos, T obj);
   
   public abstract List <Position> listPositions();
   
   // bounds do not represent the limits of valid positions
   // they represent when to stop searching because all values outside are the same
   // in some cases null for being outside space, in others infinitely a default value
   
   
   public abstract Position getLowerBound();
   
   public abstract Position getUpperBound();
   
   // this should perform a deep copy to be used by the davidt.aoc.automata ruleset iteration
   // methods
   public abstract M copy();
   
   // instance methods that apply the same to all subclasses
   
   
   public boolean isInBounds(Position pos)
   {
      Position lowerBound = getLowerBound();
      Position upperBound = getUpperBound().differenceWith(1);
      
      return (lowerBound.getMin(pos).equals(lowerBound)) &&
             (upperBound.getMax(pos).equals(upperBound));
   }
   
   
   public Position getBoundSize()
   {
      return getUpperBound().differenceWith(getLowerBound());
   }
   
   
   public Map <T, Integer> count()
   {
      HashMap <T, Integer> counts = new HashMap <>();
      
      for (Position pos : listPositions())
      {
         T obj = get(pos);
         if (!counts.containsKey(obj))
            counts.put(obj, 1);
         else
            counts.put(obj, counts.get(obj) + 1);
      }
      
      return counts;
   }
   
   
   public int countOf(T obj)
   {
      return (int) listPositions().stream().filter(pos -> obj.equals(get(pos))).count();
   }
   
   
   public Map <T, Integer> countNeighborsOf(Position pos, DirectionSet dirSet)
   {
      HashMap <T, Integer> neighbors = new HashMap <>();
      
      for (DirectionSet.Direction dir : dirSet.values())
      {
         Position neighborPos = pos.sumWith(dir.intWrapper());
         if (isInBounds(neighborPos))
         {
            T neighbor = get(neighborPos);
            neighbors.put(neighbor, neighbors.getOrDefault(neighbor, 0) + 1);
         }
      }
      
      return neighbors;
   }
   
   
   public T getFirstInLine(
      Position origin, DirectionSet.Direction dir, List <T> list, boolean isWhitelist)
   {
      if (origin == null)
         throw new NullPointerException("pos cannot be null");
   
      Position lookingAt = new Position(origin);
   
      do
      {
         lookingAt.addBy(dir.intWrapper());
      }
      while (isInBounds(lookingAt) &&
             !lookingAt.equals(origin) &&
             isWhitelist != list.contains(get(lookingAt)));
   
      if (isInBounds(lookingAt) && !lookingAt.equals(origin))
         return get(lookingAt);
      else
         return null;
   }
   
   
   public Map <T, Integer> countSeenFrom(
      Position origin, DirectionSet dirSet, List <T> list, boolean isWhitelist)
   {
      HashMap <T, Integer> thoseSeen = new HashMap <>();
      
      for (DirectionSet.Direction dir : dirSet.values())
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
   
   
   public void applyRule(BiFunction <Position, GridMap <T, M>, T> rule)
   {
      M copy = copy();
      
      for (Position pos : listPositions())
      {
         set(pos, rule.apply(pos, copy));
      }
   }
   
   
   public void applyRuleUntilStable(BiFunction <Position, GridMap <T, M>, T> rule, int stableRange)
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
   
   // public static methods
   
   // static methods
   
   
   public static <T> void fillFromString(
      GridMap <T, ?> map, String mapString, Function <Character, T> translator)
   {
      fillFromString(map, new Position(map.dims()), mapString, translator);
   }
   
   
   public static <T> void fillFromString(
      GridMap <T, ?> map, Position offset, String mapString, Function <Character, T> translator)
   {
      String[] rows = mapString.trim().split("\n");
      
      // trim every row
      for (int i = 0; i < rows.length; i++)
      {
         rows[i] = rows[i].trim();
      }
      
      int width = rows[0].length();
      int height = rows.length;
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            char c = rows[y].charAt(x);
            
            map.set(
               new Position(map.dims(), new int[] {x, y}).sumWith(offset), translator.apply(c));
         }
      }
   }
}
