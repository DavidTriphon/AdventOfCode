package main.map;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;


public class FiniteGridMap <T> extends GridMap <T, FiniteGridMap <T>>
{
   // fields
   
   public final  int        width;
   public final  int        height;
   private final Object[][] _map;
   
   // constructor
   
   
   public FiniteGridMap(int width, int height)
   {
      this(width, height, true);
   }
   
   
   public FiniteGridMap(int width, int height, boolean yIsDown)
   {
      super(yIsDown);
      this.width  = width;
      this.height = height;
      _map        = new Object[width][height];
   }
   
   
   public FiniteGridMap(int width, int height, T defaultValue)
   {
      this(width, height);
      
      for (int x = 0; x < width; x++)
      {
         for (int y = 0; y < height; y++)
         {
            _map[x][y] = defaultValue;
         }
      }
   }
   
   // implemented methods
   
   
   @SuppressWarnings("unchecked")
   @Override
   public T get(Point pos)
   {
      checkBounds(pos);
      return (T) _map[pos.x][pos.y];
   }
   
   
   @Override
   public void set(Point pos, T obj)
   {
      checkBounds(pos);
      _map[pos.x][pos.y] = obj;
   }
   
   
   @Override
   public List <Point> listPositions()
   {
      return IntStream.range(0, width * height).mapToObj(
         (i) -> new Point(i % width, i / width)).collect(
         Collectors.toUnmodifiableList());
   }
   
   
   @Override
   public int getXLowerBound()
   {
      return 0;
   }
   
   
   @Override
   public int getXUpperBound()
   {
      return width;
   }
   
   
   @Override
   public int getYLowerBound()
   {
      return 0;
   }
   
   
   @Override
   public int getYUpperBound()
   {
      return height;
   }
   
   
   @Override
   public FiniteGridMap <T> copy()
   {
      FiniteGridMap <T> copy = new FiniteGridMap <>(width, height, _yIsDown);
      
      for (Point pos : listPositions())
      {
         copy.set(pos, get(pos));
      }
      
      return copy;
   }
   
   // Object Implementations
   
   
   @Override
   public int hashCode()
   {
      return Objects.hash(width, height, _yIsDown);
   }
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof FiniteGridMap))
         return false;
      FiniteGridMap <?> that = (FiniteGridMap <?>) o;
      if (width != that.width || height != that.height || _yIsDown != that._yIsDown)
         return false;
      
      for (Point pos : listPositions())
      {
         if (!get(pos).equals(that.get(pos)))
            return false;
      }
      
      return true;
   }
   
   
   @Override
   public String toString()
   {
      return "FiniteGridMap{\n" + toMapString((o) -> o.toString().charAt(0)) + "}";
   }
   
   // private helper methods
   
   
   private void checkBounds(Point pos)
   {
      if (!(0 <= pos.x && pos.x < width))
         throw new IllegalArgumentException(
            String.format("X pos (%d) is out of bounds !(0 <= x && x < %d)", pos.x, width));
      if (!(0 <= pos.y && pos.y < height))
         throw new IllegalArgumentException(
            String.format("Y pos (%d) is out of bounds !(0 <= y && y < %d)", pos.y, height));
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
      
      FiniteGridMap <T> map = new FiniteGridMap <>(width, height);
      
      for (int y = 0; y < height; y++)
      {
         for (int x = 0; x < width; x++)
         {
            char c = rows[y].charAt(x);
            
            map.set(new Point(x, y), translator.apply(c));
         }
      }
      
      return map;
   }
}
