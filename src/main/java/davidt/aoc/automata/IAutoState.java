package davidt.aoc.automata;

import davidt.aoc.map.*;

import java.awt.*;


public interface IAutoState <AutoState extends IAutoState <AutoState>>
{
   AutoState next(Point pos, GridMap <AutoState, ?> map);
   
   static <AS extends IAutoState <AS>> AS autoRule(Point pos, GridMap <AS, ?> map)
   {
      return map.get(pos).next(pos, map);
   }
}
