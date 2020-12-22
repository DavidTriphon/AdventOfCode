package davidt.aoc.years.y2020.days;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;


public class Day19
{
   public static final URL INPUT_FILE_LOC = Day19.class.getResource("input/input19.txt");
   
   public static List <Rule> REPLACEMENT_RULES = List.of(
      new RuleRef(8, "42 | 42 8"),
      new RuleRef(11, "42 31 | 42 11 31")
   );
   
   
   public static void main(String[] args) throws IOException
   {
      Input19 input = parseInput(INPUT_FILE_LOC);
      
      System.out.println("A : " + part1(input));
      System.out.println("B : " + part2(input));
   }
   
   
   public static Input19 parseInput(URL inputURL) throws IOException
   {
      List <Scanner> inputSections =
         ReaderUtil.parseFileToList(inputURL, "\n\n",
            ReaderUtil.getScannerFactory(ReaderUtil.DEFAULT_DELIMITER)
         );
      Map <Integer, Rule> rules =
         ReaderUtil.iterateScannerToMap(inputSections.get(0), Rule::getRule, Rule::getIndex);
      List <String> messages = ReaderUtil.iterateScannerToList(inputSections.get(1), (s) -> s);
      
      return new Input19(rules, messages);
   }
   
   
   public static long part1(Input19 input)
   {
      return countMatches(input);
   }
   
   
   public static long part2(Input19 input)
   {
      updateRules(input);
      return countMatches(input);
   }
   
   
   public static void updateRules(Input19 input)
   {
      for (Rule rule : REPLACEMENT_RULES)
      {
         input.rules.put(rule.getIndex(), rule);
      }
   }
   
   
   public static long countMatches(Input19 input)
   {
      int matches = 0;
      
      for (String message : input.messages)
      {
         if (input.rules.get(0).matches(message, input.rules))
            matches++;
      }
      
      return matches;
   }
   
   
   public static class Input19
   {
      public final Map <Integer, Rule> rules;
      public final List <String>       messages;
      
      
      public Input19(Map <Integer, Rule> rules, List <String> messages)
      {
         this.rules = rules;
         this.messages = messages;
      }
   }
   
   
   public static abstract class Rule
   {
      protected String _repr;
      protected int    _index;
      
      
      protected Rule(int index, String repr)
      {
         _index = index;
         _repr = repr;
      }
      
      
      public int getIndex()
      {
         return _index;
      }
      
      
      public boolean matches(String message, Map <Integer, Rule> rules)
      {
         return find(message, 0, rules).contains(message.length());
      }
      
      
      public abstract List <Integer> find(String message, int index, Map <Integer, Rule> rules);
      
      
      @Override
      public String toString()
      {
         return String.format("RuleChar{\"%d: %s}", _index, _repr);
      }
      
      
      public static Rule getRule(String rule)
      {
         String[] parts = rule.split(": ");
         int index = Integer.parseInt(parts[0]);
         if (parts[1].charAt(0) == '"')
            return new RuleChar(index, parts[1]);
         else
            return new RuleRef(index, parts[1]);
      }
   }
   
   
   public static class RuleChar extends Rule
   {
      private final char _letter;
      
      
      public RuleChar(int index, String repr)
      {
         super(index, repr);
         _letter = _repr.charAt(1);
      }
      
      
      @Override
      public List <Integer> find(
         String message, int index, Map <Integer, Rule> rules)
      {
         if (index >= message.length() || message.charAt(index) != _letter)
            return List.of(); // empty means no match
         else
            return List.of(index + 1); // +1 means match, offset your index by +1.
      }
   }
   
   
   public static class RuleRef extends Rule
   {
      private final List <List <Integer>> _sequences;
      
      
      public RuleRef(int index, String repr)
      {
         super(index, repr);
         _sequences = new ArrayList <>();
         
         for (String sequence : _repr.split(" \\| "))
         {
            List <Integer> ruleList = new ArrayList <>();
            Arrays.stream(sequence.split(" ")).mapToInt(Integer::parseInt).forEach(ruleList::add);
            _sequences.add(ruleList);
         }
      }
      
      
      @Override
      public List <Integer> find(
         String message, int index, Map <Integer, Rule> rules)
      {
         List <Integer> offsets = new ArrayList <>();
         
         for (List <Integer> ruleList : _sequences)
         {
            offsets.addAll(findHelper(message, index, rules, ruleList, 0));
         }
         
         return offsets;
      }
      
      
      private List <Integer> findHelper(
         String message, int index, Map <Integer, Rule> rules,
         List <Integer> ruleList, int ruleIndex)
      {
         List <Integer> offsets = new ArrayList <>();
         
         if (ruleIndex >= ruleList.size())
            return List.of(index);
         
         for (int offset : rules.get(ruleList.get(ruleIndex)).find(message, index, rules))
         {
            offsets.addAll(findHelper(message, offset, rules, ruleList, ruleIndex + 1));
         }
         
         return offsets;
      }
   }
}
