/*
*   This example demonstrates the usage of Command Pattern
*
*   Inheritance map:
*
*   -   A single object: Light
*
*   -   Command(interface) <-------------LightOnCommand(concrete command), LightOffCommand(concrete command), NoCommand(concrete command)
*
*   -   Light(the so called receiver)
*
*   -   SimpleRemoteControl(invoker)
*
*   -   
*
*
*
*
*
*
*
*/



interface Command {
   public void execute();
   public void undo();
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
   
   public void undo() {
      light.off();
   }
}

//the concrete command in the common pattern-speak.
class LightOffCommand implements Command {
   Light light;

   public LightOffCommand(Light light) {
      this.light = light;
   }

   public void execute() {
      light.off();
   }

   public void undo() {
      light.on();
   }
}

//the concrete command
class NoCommand implements Command {
   public void execute() {} //do nothing.
   public void undo() {} //do nothing
}

//invoker in the command pattern-speak
class SimpleRemoteControl {
   Command slot;
   
   public SimpleRemoteControl() {}

   public void setCommand(Command command) {
      slot = command;
   }

   public void buttonWasPressed() {
      slot.execute();
   }

   public void undoButtonWasPressed() {
      slot.undo();
   }
}

class RemoteControl {
   Command[] onCommands;
   Command[] offCommands;
   Command undoCommand;

   public RemoteControl() {
      onCommands = new Command[7];
      offCommands = new Command[7];
   
      Command noCommand = new NoCommand();
      for (int i = 0; i < 7; i++) {
         //In the constructor all we need to do is instantiate and initialize the on and off arrays.
         onCommands[i] = noCommand;
         offCommands[i] = noCommand;
      }
      undoCommand = noCommand;
   }

   public void setCommand(int slot, Command onCommand, Command offCommand) {
      onCommands[slot] = onCommand;
      offCommands[slot] = offCommand;
   }

   public void onButtonWasPushed(int slot) {
      onCommands[slot].execute();
      undoCommand = onCommands[slot];
   }

   public void offButtonWasPushed(int slot) {
      offCommands[slot].execute();
   }

   public void undoButtonWasPushed() {
      undoCommand.undo();
   }

   public String toString() {
      StringBuffer stringBuff = new StringBuffer();
      stringBuff.append("\n----- Remote Control -------\n");
      for (int i = 0; i < onCommands.length; i++) {
         stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    " + offCommands[i].getClass().getName() + "\n");
      }
      return stringBuff.toString(); //we have overwritten toString() to print out each slot and its corresponding command. You'll use this when
                                    //we test the remote control.
   }
}

//This is our Client in Common Pattern-speak
class RemoteControlTest {
   public static void main(String[] args) {
      SimpleRemoteControl remote = new SimpleRemoteControl(); //the invoker
      Light light = new Light(); //the receiver
      LightOnCommand lightOn = new LightOnCommand(light); //the command

      remote.setCommand(lightOn);
      remote.buttonWasPressed();
      remote.undoButtonWasPressed();
   }
}
