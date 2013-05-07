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
