package years.y2019.days;

import years.y2019.vacuum.*;


public class Day17A
{
   public static void main(String... args)
   {
      System.out.println(getAnswer());
   }
   
   
   public static long getAnswer()
   {
      VacuumRobot robot = new VacuumRobot();
      
      System.out.println(robot.getMapString());
      
      System.out.println(robot.getCrossingsCount());
      
      System.out.println(robot.getCrossingAlignmentSum());
      
      return robot.getCrossingAlignmentSum();
   }
}
