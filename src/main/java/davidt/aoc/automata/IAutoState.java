package davidt.aoc.automata;

import davidt.aoc.map.*;


public interface IAutoState <AutoState extends IAutoState <AutoState>>
{
   AutoState next(Position pos, GridMap <AutoState, ?> map);
   
   static <AS extends IAutoState <AS>> AS autoRule(Position pos, GridMap <AS, ?> map)
   {
      return map.get(pos).next(pos, map);
   }
}
