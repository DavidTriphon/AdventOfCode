package years.y2020.boarding;

public class BoardingPass
{
   public static final char FRONT = 'F';
   public static final char BACK = 'B';
   public static final char LEFT = 'L';
   public static final char RIGHT = 'R';
   
   public final String code;
   public final int row;
   public final int column;
   public final int seatID;
   
   public BoardingPass(String code)
   {
      this.code = code;
      
      String row = code.substring(0, 7);
      String column = code.substring(7);
      
      row = row.replace(FRONT, '0');
      row = row.replace(BACK, '1');
      this.row = Integer.parseInt(row, 2);
   
      column = column.replace(LEFT, '0');
      column = column.replace(RIGHT, '1');
      this.column = Integer.parseInt(column, 2);
      
      seatID = this.row * 8 + this.column;
   }
}
