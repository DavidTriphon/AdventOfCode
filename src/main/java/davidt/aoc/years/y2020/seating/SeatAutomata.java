package davidt.aoc.years.y2020.seating;

import davidt.aoc.automata.*;
import davidt.aoc.map.*;

import java.util.*;


public enum SeatAutomata implements IAutoState <SeatAutomata>
{
   EMPTY_SEAT('L', '=')
      {
         @Override
         protected SeatAutomata next(Map <SeatAutomata, Integer> neighbors)
         {
            if (!neighbors.containsKey(TAKEN_SEAT) || neighbors.get(TAKEN_SEAT) == 0)
               return TAKEN_SEAT;
            else
               return this;
         }
      },
   TAKEN_SEAT('#', 'X')
      {
         @Override
         protected SeatAutomata next(Map <SeatAutomata, Integer> neighbors)
         {
            if (neighbors.containsKey(TAKEN_SEAT) && neighbors.get(TAKEN_SEAT) >= 4)
               return EMPTY_SEAT;
            else
               return this;
         }
      },
   NO_SEAT('.', ' '),
   ;
   
   // constants
   
   private final DirectionSet NEIGHBORS = new DirectionSet(2, false, true);
   
   // fields
   
   private final char _inputLetter, _visualLetter;
   
   // constructor
   
   
   SeatAutomata(char inputLetter, char visualLetter)
   {
      _inputLetter = inputLetter;
      _visualLetter = visualLetter;
   }
   
   // instance methods
   
   
   @Override
   public SeatAutomata next(Position pos, GridMap <SeatAutomata, ?> map)
   {
      return next(map.countNeighborsOf(pos, NEIGHBORS));
   }
   
   
   protected SeatAutomata next(Map <SeatAutomata, Integer> neighbors)
   {
      return this;
   }
   
   
   public char getInputLetter()
   {
      return _inputLetter;
   }
   
   
   public char getVisualLetter()
   {
      return _visualLetter;
   }
   
   // object overrides
   
   
   @Override
   public String toString()
   {
      return _inputLetter + "";
   }
   
   // static methods
   
   
   public static SeatAutomata getStateFromLetter(char letter)
   {
      for (SeatAutomata state : values())
      {
         if (state._inputLetter == letter)
            return state;
      }
      
      throw new IllegalArgumentException(letter + " is not a state character.");
   }
}
