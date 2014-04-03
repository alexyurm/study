/*
*  This example shows how to use Java's built-in Observer Pattern
*
*   -   Observable is a class, thus creating 2 problems:
*
*   1)  If a class has already extended another class, no way you can subclass the Observable
*   2)  the setChange is a protected method, therefore we cannot compose a Observable instance into your
*       own object. This violates the principle 2: favour composition over inheritance(prefer loosely-coupled structure).
*
*   -   
*
*
*
*
*
*/


import java.util.Observable;
import java.util.Observer;

interface DisplayElement {
   public void display();
}

class WeatherData extends Observable {
   private float temperature;
   private float humidity;
   private float pressure;

   public WeatherData() {} //Our constructor no longer needs to create a data structure to hold Observers
   
   /*
   *  -  For the Observable to send notifications, 
   *
   *     You first must call the setChanged() method to signify that the state has changed in your object.
   *
   *     Then call either notifyObserbers() or notifyObservers(Object arg)
   *
   *  -  Why do we use setChanged() method?
   *
   *     Because:
   *
   *     setChanged() {
   *         changed = true;
   *     }
   *
   *  -  notifyObservers(Object arg) {
   *        if (changed) {
   *           for every observer on the list {
   *              call update(this, arg)
   *           }
   *           changed = false;
   *        }
   *     }
   *
   *  Why is the necessary? The setChanged() method is meant to give you more flexibility in how you update observers by allowing you to optimize 
   *  the notifications. E.g.
   *  If the temperature readings were constantly fluctuating, we might want to send out notifications only if the temperature changes more than 
   *  half of a degree and we could call setChanged() only after that. If the functionality is useful to you, you may also want to use 
   *  the clearChanged() method, which sets the changed state back to false, and the hasChanged() method, which tells you the current state of 
   *  the changed flag.
   *
   *
   */
   public void measurementsChanged() {
      setChanged(); //the method in the Observable Class
      notifyObservers();   
   }

   public void setMeasurements(float temperature, float humidity, float pressure) {
      this.temperature = temperature;
      this.humidity = humidity;
      this.pressure = pressure;
      measurementsChanged();
   }

   public float getTemperature() {
      return temperature;
   }

   public float getHumidity() {
      return humidity;
   }

   public float getPressure() {
      return pressure;
   }
}

class CurrentConditionDisplay implements Observer, DisplayElement {
   Observable observable; //?? Is this observable private? public? or protected?
   private float temperature;
   private float humidity;

   public CurrentConditionDisplay(Observable observable) {
      this.observable = observable;
      observable.addObserver(this);
   }

   //update is the only method in the Observer interface
   public void update(Observable obs, Object arg) {
      if ( obs instanceof WeatherData ) {
         WeatherData weatherData = (WeatherData)obs;
         this.temperature = weatherData.getTemperature();
         this.humidity = weatherData.getHumidity();
         display();
      }
   }

   public void display() {
      System.out.println("Current condition: " + temperature + "F degrees and " + humidity + "% humidity");
   }
}
