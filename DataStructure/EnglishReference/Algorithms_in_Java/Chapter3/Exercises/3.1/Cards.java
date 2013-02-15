public class Card
{
   int suit;
   int num;

   //test whether c1 has a higher value than the other
   boolean compare (Card c1, Card c2)
   {
      if (c1.num > c2.num)
      {
         return true;
      }
      else if (c1.num == c2.num )
      {
         if (c1.suit > c2.suit)
         {
            return true;
         }
         else
         {
            return false;
         }
      }
      else
      {
         return false;
      }
   }

   String toString(Card c)
   {
   
   }
}

