package davidt.aoc.map;

import java.util.*;
import java.util.function.*;


public class InfiniteGridMap <T> extends GridMap <T, InfiniteGridMap <T>>
{
   // fields
   
   private final HashMap <Position, T> _map;
   private final Position              _lowerBound;
   private final Position              _upperBound;
   private       T                     _defaultValue;
   private       boolean               _dirtyBoundsFlag = true;
   private       List <Position>       _positions;
   
   // constructors
   
   
   public InfiniteGridMap(int dimensionCount, T defaultValue)
   {
      super(dimensionCount);
      _map = new HashMap <>();
      _defaultValue = defaultValue;
      _lowerBound = new Position(dimensionCount);
      _upperBound = new Position(dimensionCount);
   }
   
   // Implemented GridMap methods
   
   
   @Override
   public T get(Position pos)
   {
      if (pos == null)
         return _defaultValue;
      IDimensional.checkDimsMatch(this, pos);
      if (_map.containsKey(pos))
         return _map.get(pos);
      
      return _defaultValue;
   }
   
   
   @Override
   public void set(Position pos, T obj)
   {
      IDimensional.checkDimsMatch(this, pos);
      _dirtyBoundsFlag = true;
      if (obj.equals(_defaultValue))
         _map.remove(pos);
      else
         _map.put(new Position(pos), obj);
   }
   
   
   @Override
   public List <Position> listPositions()
   {
      if (_dirtyBoundsFlag)
      {
         Position lower = getLowerBound().differenceWith(1);
         Position upper = getUpperBound().sumWith(1);
         _positions = new ArrayList <>();
         
         Position size = upper.differenceWith(lower);
         int positionCount = size.calcContainedSpace();
         
         for (int i = 0; i < positionCount; i++)
         {
            _positions.add(Position.getPosOfIndexIn(i, size).sumWith(lower));
         }
      }
      return _positions;
   }
   
   
   @Override
   public Position getLowerBound()
   {
      updateBounds();
      return new Position(_lowerBound);
   }
   
   
   @Override
   public Position getUpperBound()
   {
      updateBounds();
      return new Position(_upperBound);
   }
   
   
   @Override
   public InfiniteGridMap <T> copy()
   {
      InfiniteGridMap <T> copy = new InfiniteGridMap <>(_dimensionCount, _defaultValue);
   
      for (Position pos : _map.keySet())
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
   
      counts.put(_defaultValue, Integer.MAX_VALUE); // infinite
   
      return counts;
   }
   
   
   @Override
   public int countOf(T obj)
   {
      if (obj.equals(_defaultValue))
         return Integer.MAX_VALUE; // infinite
      return super.countOf(obj);
   }
   
   
   @Override
   public Map <T, Integer> countNeighborsOf(Position pos, DirectionSet dirSet)
   {
      if (pos == null)
      {
         return Map.of(_defaultValue, dirSet.values().size());
      }
      
      return super.countNeighborsOf(pos, dirSet);
   }
   
   
   @Override
   public T getFirstInLine(
      Position origin, DirectionSet.Direction dir, List <T> list, boolean isWhitelist)
   {
      T result = super.getFirstInLine(origin, dir, list, isWhitelist);
      
      if (result != null)
         return result;
      
      if (isWhitelist == list.contains(_defaultValue))
         return _defaultValue;
      
      return null;
   }
   
   
   @Override
   public void applyRule(BiFunction <Position, GridMap <T, InfiniteGridMap <T>>, T> rule)
   {
      InfiniteGridMap <T> copy = copy();
   
      setDefaultValue(rule.apply(null, copy));
   
      for (Position pos : listPositions())
      {
         set(pos, rule.apply(pos, copy));
      }
   }
   
   // overridden Object methods
   
   
   @Override
   public int hashCode()
   {
      return Objects.hash(_map, _defaultValue);
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
   
      for (Position pos : _map.keySet())
      {
         if (!get(pos).equals(that.get(pos)))
            return false;
      }
   
      for (Position pos : that._map.keySet())
      {
         if (!get(pos).equals(that.get(pos)))
            return false;
      }
   
      return true;
   }
   
   
   @Override
   public String toString()
   {
      return String.format(
         "InfiniteGridMap{lowerBound=%s; upperBound=%s; entryCount=%d, defaultValue=%s}",
         getLowerBound(), getUpperBound(), _map.keySet().size(), _defaultValue
      );
   }
   
   // instance methods
   
   
   public T getDefaultValue()
   {
      return _defaultValue;
   }
   
   
   public void setDefaultValue(T newDefault)
   {
      _defaultValue = newDefault;
   
      for (Position pos : _map.keySet())
      {
         if (get(pos).equals(_defaultValue))
            _map.remove(pos);
      }
   
      _dirtyBoundsFlag = true;
   }
   
   // private helper methods
   
   
   private void updateBounds()
   {
      if (_dirtyBoundsFlag)
      {
         if (_map.values().isEmpty())
         {
            // a 3 wide space centered on zero sounds good to me as a default
            _lowerBound.setAll(-1);
            _upperBound.setAll(1);
         }
         else
         {
            _lowerBound.setAll(Integer.MAX_VALUE);
            _upperBound.setAll(Integer.MIN_VALUE);
   
            for (Position pos : _map.keySet())
            {
               _lowerBound.makeMin(pos);
               _upperBound.makeMax(pos);
            }
   
            // the upper bound should be exclusive. max() is inclusive.
            _upperBound.addBy(1);
         }
         _dirtyBoundsFlag = false;
      }
   }
}
