package davidt.aoc.map;

import java.util.*;
import java.util.function.*;


public class FiniteGridMap <T> extends GridMap <T, FiniteGridMap <T>>
{
   // fields
   
   private final int[] _dimensionSizes;
   private final Object[] _map;
   
   // constructor
   
   
   public FiniteGridMap(int[] dimensionSizes)
   {
      super(dimensionSizes.length);
      this._dimensionSizes = Arrays.copyOf(dimensionSizes, dimensionSizes.length);
      
      int size = 1;
      
      for (int length : dimensionSizes)
      {
         size *= length;
      }
      
      _map = new Object[size];
   }
   
   
   public FiniteGridMap(T defaultValue, int[] dimensionSizes)
   {
      this(dimensionSizes);
      
      Arrays.fill(_map, defaultValue);
   }
   
   // implemented methods
   
   
   @SuppressWarnings("unchecked")
   @Override
   public T get(Position pos)
   {
      checkBounds(pos);
      return (T) _map[Position.getIndexOfPosIn(pos, getUpperBound())];
   }
   
   
   @Override
   public void set(Position pos, T obj)
   {
      checkBounds(pos);
      _map[Position.getIndexOfPosIn(pos, getUpperBound())] = obj;
   }
   
   
   @Override
   public List <Position> listPositions()
   {
      ArrayList <Position> positions = new ArrayList <>();
      
      for (int i = 0; i < _map.length; i++)
      {
         int[] position = new int[_dimensionCount];
         int remainder = i;
         
         for (int dim = 0; dim < _dimensionCount; dim++)
         {
            position[dim] = remainder % _dimensionSizes[dim];
            remainder /= _dimensionSizes[dim];
         }
         positions.add(new Position(position));
      }
      
      return positions;
   }
   
   
   @Override
   public Position getLowerBound()
   {
      return new Position(new int[_dimensionCount]);
   }
   
   
   @Override
   public Position getUpperBound()
   {
      return new Position(_dimensionSizes);
   }
   
   
   @Override
   public FiniteGridMap <T> copy()
   {
      FiniteGridMap <T> copy = new FiniteGridMap <>(_dimensionSizes);
   
      for (Position pos : listPositions())
      {
         copy.set(pos, get(pos));
      }
   
      return copy;
   }
   
   // Object Implementations
   
   
   @Override
   public int hashCode()
   {
      return Arrays.hashCode(_dimensionSizes);
   }
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof FiniteGridMap))
         return false;
      FiniteGridMap <?> that = (FiniteGridMap <?>) o;
      if (!Arrays.equals(_dimensionSizes, that._dimensionSizes))
         return false;
   
      for (Position pos : listPositions())
      {
         if (!get(pos).equals(that.get(pos)))
            return false;
      }
   
      return true;
   }
   
   
   @Override
   public String toString()
   {
      return String.format("FiniteGridMap{size=%s}", Arrays.toString(_dimensionSizes));
   }
   
   // private helper methods
   
   
   private void checkBounds(Position pos)
   {
      Position lowerBound = getLowerBound();
      Position upperBound = getUpperBound();
      
      for (int dim = 0; dim < _dimensionCount; dim++)
      {
         if (pos.get(dim) < lowerBound.get(dim))
         {
            throw new IllegalArgumentException(String.format(
               "%d in the %d dimension is below the lower bound %d",
               pos.get(dim), dim, lowerBound.get(dim)
            ));
         }
         else if (upperBound.get(dim) <= pos.get(dim))
         {
            throw new IllegalArgumentException(String.format(
               "%d in the %d dimension is above the upper bound %d",
               pos.get(dim), dim, upperBound.get(dim)
            ));
         }
      }
   }
   
   // static methods
   
   
   public static <T> FiniteGridMap <T> fromString(
      String mapString,
      Function <Character, T> translator)
   {
      String[] rows = mapString.trim().split("\n");
      
      // trim every row
      for (int i = 0; i < rows.length; i++)
      {
         rows[i] = rows[i].trim();
      }
      
      int width = rows[0].length();
      int height = rows.length;
      
      FiniteGridMap <T> map = new FiniteGridMap <>(new int[] {width, height});
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            char c = rows[y].charAt(x);
   
            map.set(new Position(new int[] {x, y}), translator.apply(c));
         }
      }
      
      return map;
   }
}
