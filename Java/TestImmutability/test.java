import java.util.Data;

/**
* Planet is an immutable class, since there is no way to change 
* its state after construction.
*/

public final class Planet { //this class is final
   private final double fMass; //Final primitive date is always immutable
   private final String fName; //An immutable object field(String never change state).
   /**
   *  A mutable object field. In this case, the state of this mutable field
   *  is to be changed only by this class. (In other cases, it makes perfect sense to allow
   *  the state of a field to be changed outside the native class; this is the case when a )
   *  field acts as a "pointer" to an object created elsewhere.
   */
   private final Date fDateOfDiscovery;

   public Planet (double aMass, String aName, Date aDateOfDiscovery) {
      fMass = aMass;
      fName = aName;
      //make a private copy of aDateOfDiscovery
      //this is the only way to keep the fDateOfDiscovery
      //field private, and shields this class from any change that
      //the caller may tamke to the original fDateOfDiscovery object
      fDateOfDiscovery = new Date(aDateOfDiscovery.getTime());
   }

   /**
   *  Return a primitive value.
   *
   *  The caller can do whatever they want with the return value, without 
   *  affecting the internals of this class. Why? Because this is a primitive
   *  value. The caller sees its "own" double that simply has the same value as
   *  fMass.
   */
   public double getMass(){
      return fMass;
   }

   /**
   *  Return an immutable object.
   *  
   *  The caller gets a direct reference to the internal field. But this is not dangerous, since String is immutable and cannot be changed.
   */
   public String getName() {
      return fName;
   }

   /**
   *  Returns a mutable object - likely bad style.
   *  
   *  The caller gets a direct reference to the internal field. This is usually dangerous, since the Data object state can be changed both by
   *  this class and its caller. That is, this class is no longer in complete control of fDate.
   */
   //public Date getDateOfDiscovery() {
   //   return fDateOfDiscovery;
   //}

   public Date getDateOfDiscovery() {
      return new Date(fDateOfDiscovery.getTime());
   }
}
