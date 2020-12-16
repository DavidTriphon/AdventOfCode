package davidt.aoc.years.y2020.security;

import davidt.aoc.util.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;


public class Passport
{
   
   public static final Field FIELD_BIRTH_YEAR  = new Field("byr", "\\d{4}",
      Field.createYearValidator(1920, 2002)
   );
   public static final Field FIELD_ISSUE_YEAR  = new Field("iyr", "\\d{4}",
      Field.createYearValidator(2010, 2020)
   );
   public static final Field FIELD_EXPIRE_YEAR = new Field("eyr", "\\d{4}",
      Field.createYearValidator(2020, 2030)
   );
   public static final Field FIELD_HEIGHT      = new Field("hgt", "(\\d+)(cm|in)",
      (matcher) ->
      {
         int measure = Integer.parseInt(matcher.group(1));
         String units = matcher.group(2);
         if (units.equals("cm"))
            return 150 <= measure && measure <= 193;
         else if (units.equals("in"))
            return 59 <= measure && measure <= 76;
         else
            throw new IllegalStateException(
               "Somehow the units are not cm or in: " + matcher.group());
      }
   );
   public static final Field FIELD_HAIR_COLOR  = new Field("hcl", "#([0-9]|[a-f]){6}");
   public static final Field FIELD_EYE_COLOR   = new Field("ecl", "amb|blu|brn|gry|grn|hzl|oth");
   public static final Field FIELD_PASSPORT_ID = new Field("pid", "\\d{9}");
   public static final Field FIELD_COUNTRY_ID  = new Field("cid", ".*");
   
   public static final List <Field> REQUIRED_FIELDS = List.of(
      FIELD_BIRTH_YEAR, FIELD_ISSUE_YEAR, FIELD_EXPIRE_YEAR, FIELD_HEIGHT, FIELD_HAIR_COLOR,
      FIELD_EYE_COLOR, FIELD_PASSPORT_ID
   );
   
   private final HashMap <String, String> _fields = new HashMap <>();
   
   
   public void addEntry(String key, String value)
   {
      _fields.put(key, value);
   }
   
   
   public String getEntry(String fieldName)
   {
      return _fields.get(fieldName);
   }
   
   
   public String getEntry(Field field)
   {
      return _fields.get(field.name);
   }
   
   
   public boolean hasRequiredFields()
   {
      for (Field field : REQUIRED_FIELDS)
      {
         if (!_fields.containsKey(field.name))
         {
            return false;
         }
      }
      
      return true;
   }
   
   
   public boolean hasValidValues()
   {
      if (!hasRequiredFields())
         return false;
      
      for (Field field : REQUIRED_FIELDS)
      {
         if (!field.isValidFieldValue(_fields.get(field.name)))
            return false;
      }
      
      return true;
   }
   
   
   @Override
   public String toString()
   {
      return _fields.toString();
   }
   
   
   public static List <Passport> getPassportsFromInput(URL fileLocation) throws IOException
   {
      ArrayList <Passport> passports = new ArrayList <>();
      
      ReaderUtil.parseFileWithMethod(fileLocation, "\n\n", (line) ->
         {
            Passport passport = new Passport();
            
            String[] parts = line.split("([ \n])+");
            for (String entry : parts)
            {
               String[] keyValue = entry.split(":");
               passport.addEntry(keyValue[0], keyValue[1]);
            }
            
            passports.add(passport);
         }
      );
      
      return passports;
   }
   
   
   public static class Field
   {
      public final  String  name;
      private final Pattern _pattern;
      
      private final Function <Matcher, Boolean> _valueChecker;
      
      
      public Field(String name, String regex)
      {
         this(name, regex, (m) -> true);
      }
      
      
      public Field(String name, String regex, Function <Matcher, Boolean> valueChecker)
      {
         this.name     = name;
         _pattern      = Pattern.compile(regex);
         _valueChecker = valueChecker;
      }
      
      
      public boolean isValidFieldValue(String value)
      {
         Matcher matcher = _pattern.matcher(value);
   
         if (matcher.matches())
         {
            return _valueChecker.apply(matcher);
         }
   
         return false;
      }
      
      
      private static Function <Matcher, Boolean> createYearValidator(int minYear, int maxYear)
      {
         return (matcher) ->
         {
            int year = Integer.parseInt(matcher.group());
            return minYear <= year && year <= maxYear;
         };
      }
   }
}
