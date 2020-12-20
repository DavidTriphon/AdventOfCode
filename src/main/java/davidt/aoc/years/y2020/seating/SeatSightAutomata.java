package davidt.aoc.years.y2020.seating;

import davidt.aoc.automata.*;
import davidt.aoc.map.*;

import java.util.*;


public enum SeatSightAutomata implements IAutoState <SeatSightAutomata>
{
   EMPTY_SEAT('L', '=')
      {
         @Override
         protected SeatSightAutomata next(Map <SeatSightAutomata, Integer> neighbors)
         {
            return (neighbors.getOrDefault(TAKEN_SEAT, 0) == 0) ? TAKEN_SEAT : EMPTY_SEAT;
         }
      },
   TAKEN_SEAT('#', 'X')
      {
         @Override
         protected SeatSightAutomata next(Map <SeatSightAutomata, Integer> neighbors)
         {
            return (neighbors.getOrDefault(TAKEN_SEAT, 0) >= 5) ? EMPTY_SEAT : TAKEN_SEAT;
         }
      },
   NO_SEAT('.', ' '),
   ;
   
   // constants
   
   private static final DirectionSet NEIGHBORS = new DirectionSet(2, false, true);
   
   // fields
   
   private final char _inputLetter, _visualLetter;
   
   // constructor
   
   
   SeatSightAutomata(char inputLetter, char visualLetter)
   {
      _inputLetter = inputLetter;
      _visualLetter = visualLetter;
   }
   
   // instance methods
   
   
   @Override
   public SeatSightAutomata next(Position pos, GridMap <SeatSightAutomata, ?> map)
   {
      return next(map.countSeenFrom(pos, NEIGHBORS, List.of(NO_SEAT), false));
   }
   
   
   protected SeatSightAutomata next(Map <SeatSightAutomata, Integer> neighbors)
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
   
   
   public static SeatSightAutomata getStateFromLetter(char letter)
   {
      for (SeatSightAutomata state : values())
      {
         if (state._inputLetter == letter)
            return state;
      }
      
      throw new IllegalArgumentException(letter + " is not a state character.");
   }
}
