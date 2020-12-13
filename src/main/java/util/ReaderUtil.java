package util;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.regex.*;


public class ReaderUtil
{
   public static String RESOURCES_LOCATION      = "src/main/resources/";
   public static String TEST_RESOURCES_LOCATION = "src/test/resources/";
   
   public static String DEFAULT_DELIMITER = "\n";
   
   /// FILE TO STRING
   
   
   public static String getFileString(String fileLocation) throws IOException
   {
      return Files.readString(
         Path.of(fileLocation), StandardCharsets.US_ASCII).trim();
   }
   
   /// CONSUMER PARSER
   
   
   public static void parseFileWithMethod(String fileLocation, Consumer <String> method)
      throws IOException
   {
      parseFileWithMethod(fileLocation, DEFAULT_DELIMITER, method);
   }
   
   
   public static void parseFileWithMethod(
      String fileLocation, String delimiter, Consumer <String> method)
      throws IOException
   {
      Scanner inputReader = new Scanner(new BufferedReader(new FileReader(fileLocation)));
      
      if (delimiter != null)
         inputReader.useDelimiter(Pattern.compile(delimiter));
      
      while (inputReader.hasNext())
      {
         method.accept(inputReader.next().trim());
      }
   }
   
   /// FUNCTIONAL LIST PARSER
   
   
   public static <T> List <T> parseFileToList(String fileLocation, Function <String, T> translator)
      throws IOException
   {
      return parseFileToList(fileLocation, DEFAULT_DELIMITER, translator);
   }
   
   
   public static <T> List <T> parseFileToList(
      String fileLocation, String delimiter, Function <String, T> translator)
      throws IOException
   {
      Scanner inputReader = new Scanner(new BufferedReader(new FileReader(fileLocation)));
      
      ArrayList <T> results = new ArrayList <>();
      
      if (delimiter != null)
         inputReader.useDelimiter(Pattern.compile(delimiter));
      
      while (inputReader.hasNext())
      {
         results.add(translator.apply(inputReader.next().trim()));
      }
      
      return results;
   }
   
   /// FUNCTIONAL MAP PARSER
   
   
   public static <K, V> HashMap <K, V> parseFileToMap(
      String fileLocation, Function <String, V> translator,
      Function <V, K> keyMaker)
      throws IOException
   {
      return parseFileToMap(fileLocation, DEFAULT_DELIMITER, translator, keyMaker);
   }
   
   
   public static <K, V> HashMap <K, V> parseFileToMap(
      String fileLocation, String delimiter, Function <String, V> translator,
      Function <V, K> keyMaker)
      throws IOException
   {
      Scanner inputReader = new Scanner(new BufferedReader(new FileReader(fileLocation)));
      
      HashMap <K, V> map = new HashMap <>();
      
      if (delimiter != null)
         inputReader.useDelimiter(Pattern.compile(delimiter));
      
      while (inputReader.hasNext())
      {
         V value = translator.apply(inputReader.next().trim());
         K key = keyMaker.apply(value);
         map.put(key, value);
      }
      
      return map;
   }
}
