package seating;

import map.*;

import java.awt.*;
import java.util.*;
import java.util.List;


public enum SeatSightAutomata
{
   EMPTY_SEAT('L', '=')
      {
         @Override
         public SeatSightAutomata next(Map <SeatSightAutomata, Integer> neighbors)
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
         public SeatSightAutomata next(Map <SeatSightAutomata, Integer> neighbors)
         {
            if (neighbors.containsKey(TAKEN_SEAT) && neighbors.get(TAKEN_SEAT) >= 5)
               return EMPTY_SEAT;
            else
               return this;
         }
      },
   NO_SEAT('.', ' '),
   ;
   
   // fields
   
   private final char _inputLetter, _visualLetter;
   
   // constructor
   
   
   SeatSightAutomata(char inputLetter, char visualLetter)
   {
      _inputLetter  = inputLetter;
      _visualLetter = visualLetter;
   }
   
   // instance methods
   
   
   public SeatSightAutomata next(Map <SeatSightAutomata, Integer> neighbors)
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
   
   
   public static SeatSightAutomata nextState(Point pos, GridMap <SeatSightAutomata, ?> gridMap)
   {
      return gridMap.get(pos).next(gridMap.countSeenFrom(pos, List.of(NO_SEAT), false));
   }
   
   
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
