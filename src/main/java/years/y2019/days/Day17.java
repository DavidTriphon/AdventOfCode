package years.y2019.days;

import years.y2019.vacuum.*;


public class Day17
{
   public static void main(String... args)
   {
      VacuumRobot robot = new VacuumRobot();
      
      System.out.println(robot.getMapString());
      
      System.out.println(robot.getCrossingsCount());
      
      System.out.println(robot.getCrossingAlignmentSum());
   }
}
