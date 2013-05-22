interface Command {
   public void execute();
}

//receiver in the common pattern-speak
class Light {
   public void on() {
      System.out.println("Light is On");
   }

   public void off() {
      System.out.println("Light is Off");
   }
}

//the concrete command in the common pattern-speak.
class LightOnCommand implements Command {
   Light light;

   public LightOnCommand(Light light) {
      this.light = light;
   }

   public void execute() {
      light.on();
   }
}

//invoker in the common pattern-speak
class SimpleRemoteControl {
   Command slot;
   
   public SimpleRemoteControl() {}

   public void setCommand(Command command) {
      slot = command;
   }

   public void buttonWasPressed() {
      slot.execute();
   }
}

//This is our Client in Common Pattern-speak
class RemoteControlTest {
   public static void main(String[] args) {
      SimpleRemoteControl remote = new SimpleRemoteControl();
      Light light = new Light();
      LightOnCommand lightOn = new LightOnCommand(light);

      remote.setCommand(lightOn);
      remote.buttonWasPressed();
   }
}

