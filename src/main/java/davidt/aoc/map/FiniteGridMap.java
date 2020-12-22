package davidt.aoc.map;

import java.util.*;
import java.util.function.*;


public class FiniteGridMap <T> extends GridMap <T, FiniteGridMap <T>>
{
   // fields
   
   private final Position _size;
   private final Object[] _map;
   
   // constructor
   
   
   public FiniteGridMap(Position size)
   {
      super(size.dims());
      this._size = new Position(size);
      _map = new Object[size.calcContainedSpace()];
   }
   
   
   public FiniteGridMap(Position size, T defaultValue)
   {
      this(size);
      Arrays.fill(_map, defaultValue);
   }
   
   // implemented methods
   
   
   @SuppressWarnings("unchecked")
   @Override
   public T get(Position pos)
   {
      IDimensional.checkDimsMatch(this, pos);
      checkBounds(pos);
      return (T) _map[Position.getIndexOfPosIn(pos, getUpperBound())];
   }
   
   
   @Override
   public void set(Position pos, T obj)
   {
      IDimensional.checkDimsMatch(this, pos);
      checkBounds(pos);
      _map[Position.getIndexOfPosIn(pos, getUpperBound())] = obj;
   }
   
   
   @Override
   public List <Position> listPositions()
   {
      return getBoundSize().listContainedPositions();
   }
   
   
   @Override
   public Position getLowerBound()
   {
      return new Position(_dimensionCount);
   }
   
   
   @Override
   public Position getUpperBound()
   {
      return new Position(_size);
   }
   
   
   @Override
   public FiniteGridMap <T> copy()
   {
      FiniteGridMap <T> copy = new FiniteGridMap <>(_size);
   
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
      return Arrays.hashCode(_size.get());
   }
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof FiniteGridMap))
         return false;
      FiniteGridMap <?> that = (FiniteGridMap <?>) o;
      if (!_size.equals(that._size))
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
      return String.format("FiniteGridMap{size=%s}", Arrays.toString(_size.get()));
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
   
   
   public static FiniteGridMap <Character> fromString(String mapString)
   {
      return fromString(new Position(2), new Position(2), mapString, c -> c);
   }
   
   
   public static <T> FiniteGridMap <T> fromString(
      String mapString,
      Function <Character, T> translator)
   {
      return fromString(new Position(2), new Position(2), mapString, translator);
   }
   
   
   public static <T> FiniteGridMap <T> fromString(
      Position size,
      Position offset,
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
      
      size.set(new Integer[] {width, height});
      
      FiniteGridMap <T> map = new FiniteGridMap <>(size);
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            char c = rows[y].charAt(x);
            map.set(
               offset.copy().add(new Position(offset.dims(), new int[] {x, y})),
               translator.apply(c)
            );
         }
      }
      
      return map;
   }
}
