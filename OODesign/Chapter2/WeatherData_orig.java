/*
*  Interfaces: Subject, Observer, DisplayElement
*  Classes: WeatherData(implements Subject), CurrentConditionDisplay(implements Observer, DisplayElement) 
*
*
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

   public WeatherData() {
      observers = new ArrayList<Observer>();
   }

   public void registerObserver(Observer o) {
      observers.add(o);
   }

   public void removeObserver(Observer o) {
      int i = observers.indexOf(o);
      if (i >= 0 ) {
         observers.remove(i);
      }
   }

   public void notifyObservers() {
      for (int i = 0; i < observers.size(); i++) {
         Observer observer = observers.get(i);
         observer.update(temperature, humidity, pressure);
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
