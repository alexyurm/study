class Point
{
   double x, y;
   Point()
   {
      x = Math.random();
      y = Math.random();
   } 

   Point(double x, double y)
   {
      this.x = x;
      this.y = y;
   }

   double r()
   {
      return Math.sqrt(x*x + y*y);
   }

   double theta()
   {
      return Math.atan2(y, x);
   }

   double distance(Point p)
   {
      double dx = x - p.x;
      double dy = y - p.y;
      return Math.sqrt(dx*dx + dy*dy);
   }
   
   static double distance(Point a, Point b)
   {
      double dx = a.x - b.x;
      double dy = a.y - b.y;
      return Math.sqrt(dx*dx + dy*dy);
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public static void main(String args[])
   {
      Point p1 = new Point();
      Point p2 = new Point(1.0, 2.3);      
      
      if (p1.distance(p2) == p2.distance(p1))
      {
         System.out.println("They are the same");
      }
      return;
   }
} 
