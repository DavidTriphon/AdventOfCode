package davidt.aoc.map;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.*;


public class InfiniteGridMap <T> extends GridMap <T, InfiniteGridMap <T>>
{
   // fields
   
   private final HashMap <Point, T> _map;
   private       T                  _defaultValue;
   private       boolean            _dirtyBoundsFlag = true;
   
   private int _minX = 0, _maxX = 0, _minY = 0, _maxY = 0;
   
   // constructors
   
   
   public InfiniteGridMap(T defaultValue)
   {
      this(defaultValue, false);
   }
   
   
   public InfiniteGridMap(T defaultValue, boolean yIsDown)
   {
      super(yIsDown);
      _map          = new HashMap <>();
      _defaultValue = defaultValue;
   }
   
   // Implemented GridMap methods
   
   
   @Override
   public T get(Point pos)
   {
      if (pos == null)
         return _defaultValue;
      if (_map.containsKey(pos))
         return _map.get(pos);
      
      return _defaultValue;
   }
   
   
   @Override
   public void set(Point pos, T obj)
   {
      if (obj.equals(_defaultValue))
         _map.remove(pos);
      else
         _map.put(new Point(pos), obj);
   }
   
   
   @Override
   public List <Point> listPositions()
   {
      return List.copyOf(_map.keySet());
   }
   
   
   @Override
   public int getXLowerBound()
   {
      updateBounds();
      return _minX;
   }
   
   
   @Override
   public int getXUpperBound()
   {
      updateBounds();
      return _maxX + 1;
   }
   
   
   @Override
   public int getYLowerBound()
   {
      updateBounds();
      return _minY;
   }
   
   
   @Override
   public int getYUpperBound()
   {
      updateBounds();
      return _maxY + 1;
   }
   
   
   @Override
   public InfiniteGridMap <T> copy()
   {
      InfiniteGridMap <T> copy = new InfiniteGridMap <>(_defaultValue, _yIsDown);
      
      for (Point pos : listPositions())
      {
         copy.set(pos, get(pos));
      }
      
      return copy;
   }
   
   // overridden GridMap methods
   
   
   @Override
   public Map <T, Integer> count()
   {
      Map <T, Integer> counts = super.count();
      
      counts.put(_defaultValue, Integer.MAX_VALUE);
      
      return counts;
   }
   
   
   @Override
   public T getFirstInLine(Point origin, Direction dir, List <T> list, boolean isWhitelist)
   {
      T result = super.getFirstInLine(origin, dir, list, isWhitelist);
      
      if (result != null)
         return result;
      
      if (isWhitelist == list.contains(_defaultValue))
         return _defaultValue;
      
      return null;
   }
   
   
   @Override
   public void applyRule(BiFunction <Point, GridMap <T, InfiniteGridMap <T>>, T> rule)
   {
      InfiniteGridMap <T> copy = copy();
   
      setDefaultValue(rule.apply(null, copy));
      
      super.applyRule(rule);
      
      for (Point pos : listBorderPositions())
      {
         set(pos, rule.apply(pos, copy));
      }
   }
   
   // overridden Object methods
   
   
   @Override
   public int hashCode()
   {
      return 0;
   }
   
   
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof InfiniteGridMap))
         return false;
      InfiniteGridMap <?> that = (InfiniteGridMap <?>) o;
      if (!_defaultValue.equals(that._defaultValue))
         return false;
      
      for (Point pos : listPositions())
      {
         if (!get(pos).equals(that.get(pos)))
            return false;
      }
   
      for (Point pos : that.listPositions())
      {
         if (!get(pos).equals(that.get(pos)))
            return false;
      }
   
      return true;
   }
   
   
   @Override
   public String toString()
   {
      return "InfiniteGridMap{\n" + toMapString((o) -> o.toString().charAt(0)) + "}";
   }
   
   // instance methods
   
   
   public T getDefaultValue()
   {
      return _defaultValue;
   }
   
   
   public void setDefaultValue(T newDefault)
   {
      _defaultValue = newDefault;
      
      for (Point pos : listPositions())
      {
         if (get(pos).equals(_defaultValue))
            _map.remove(pos);
      }
      
      _dirtyBoundsFlag = true;
   }
   
   
   public List <Point> listBorderPositions()
   {
      Set <Point> positions = new HashSet <>();
      
      for (Point pos : listPositions())
      {
         for (Direction dir : Direction.values())
         {
            Point neighborPos = new Point(pos);
            dir.move(neighborPos);
            positions.add(neighborPos);
         }
      }
      
      for (Point pos : listPositions())
      {
         positions.remove(pos);
      }
      
      return List.copyOf(positions);
   }
   
   // private helper methods
   
   
   private void updateBounds()
   {
      if (_dirtyBoundsFlag)
      {
         if (_map.values().isEmpty())
         {
            _minX = _maxX = 0;
            _minY = _maxY = 0;
         }
         else
         {
            _minX = Integer.MAX_VALUE;
            _maxX = Integer.MIN_VALUE;
            _minY = Integer.MAX_VALUE;
            _maxY = Integer.MIN_VALUE;
            
            for (Point pos : listPositions())
            {
               if (pos.x < _minX)
                  _minX = pos.x;
               else if (_maxX < pos.x)
                  _maxX = pos.x;
               if (pos.y < _minY)
                  _minY = pos.y;
               else if (_maxY < pos.y)
                  _maxY = pos.y;
            }
         }
         _dirtyBoundsFlag = false;
      }
   }
}
