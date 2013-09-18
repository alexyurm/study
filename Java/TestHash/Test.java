import java.util.*;

class CustomerID {
   private long crmID;
   private int nameSpace;

   public CustomerID(long crmID, int nameSpace) {
      super();
      this.crmID = crmID;
      this.nameSpace = nameSpace;
   }

   public boolean equals(Object obj) {
      //null instance Object will always return false
      if (!(obj instanceof Object)) {
         return false;
      }
      if (obj == this) {
         return true;
      }
   
      return this.crmID == ((CustomerID)obj).crmID && 
              this.nameSpace == ((CustomerID)obj).nameSpace;
   }

   //If we do not override the hashCode method, we will get null
   //in the following main test program.
   public int hashCode() {
      int result = 0;
      result = (int)(crmID/12) + nameSpace;
      System.out.println(result);
      return result;
   }

   public static void main(String[] args) {
      Map m = new HashMap();

      m.put(new CustomerID(1L, 0), "Jeff Smith");
      System.out.println(m.get(new CustomerID(1L, 0)));
   }
      
}
