/*
*  Interfaces: Subject, Observer, DisplayElement
*  Classes: WeatherData(implements Subject), CurrentConditionDisplay(implements Observer, DisplayElement) 
*
*  This program illustrates the Observer pattern = Publishers(Subject) + Subscribers(Observer)
*
*  -  Two interfaces: Subject and Observer(Principle 1 and 2)
*
*  -  Two concrete classes: WeatherData(implements Subject) and CurrentConditionDisplay(implements Observer)
*
*  -  WeatherData has an array of observers. This shows the principle 3: Favor composition over inheritance; and 4: Strive for loosely
*     coupled designs between objects that interact. The power of loose coupling when two objects are loosely coupled, they can interact,
*     but have little knowledge of each other.
*
*     The usage of principle 3 is easy to understand. How about 4? because Subject knows nothing about how Observer's update method gets im-
*     plemented but only the method outlook. Note: update() is called inside notifyObservers().
*
*  -  Somemethods used in ArrayList
*
*     -  add (object based)
*     -  remove (index based)
*     -  indexOf (object based)
*     -  get(index based)
*
*/
import java.util.*;


interface Subject {
   public void registerObserver(Observer o);
   public void removeObserver(Observer o);
   public void notifyObservers();
}

interface Observer {
   public void update(float temp, float humidity, float pressure);
}

interface DisplayElement {
   public void display();
}

class WeatherData implements Subject {
   private ArrayList<Observer> observers;
   private float temperature;
   private float humidity;
   private float pressure;

   public WeatherData() { //It uses an array of observers to construct a WeatherData object.
      observers = new ArrayList<Observer>();
   }

   public void registerObserver(Observer o) {
      observers.add(o);
   }

   public void removeObserver(Observer o) {
      int i = observers.indexOf(o); //the indexOf method of ArrayList returns the index of an element
      if (i >= 0 ) {
         observers.remove(i); //the remove method of ArrayList
      }
   }

   public void notifyObservers() {
      for (int i = 0; i < observers.size(); i++) {
         Observer observer = observers.get(i); //the get method of ArrayList
         observer.update(temperature, humidity, pressure); //this shows the "loosely-couple" principle 4 because Subject knows nothing about how 
         //observer's update method gets implemented but only the interface.
      }
   }

   public void measurementsChanged() {
      notifyObservers();
   }

   public void setMeasurements(float temperature, float humidity, float pressure) {
      this.temperature = temperature;
      this.humidity = humidity;
      this.pressure = pressure;
      measurementsChanged();
   }

   //other WeatherData methods here.
}

class CurrentConditionDisplay implements Observer, DisplayElement {
   private float temperature;
   private float humidity;
   private Subject weatherData;

   public CurrentConditionDisplay(Subject weatherData) {
      this.weatherData = weatherData;
      weatherData.registerObserver(this); //Interesting...
   }

   public void update(float temperature, float humidity, float pressure) {
      this.temperature = temperature;
      this.humidity = humidity;
      display();
   }

   public void display() {
      System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "%  humidity");
   }
}

class WeatherStation {
   public static void main(String[] args) {
      WeatherData weatherData =  new WeatherData(); //First, we create the weatherdata object
   
      CurrentConditionDisplay currentDisplay = new CurrentConditionDisplay(weatherData); //Create the three displays and pass them the WeatherData object.
      weatherData.setMeasurements(80, 65, 30.4f);
  }
}
