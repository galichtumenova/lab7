interface ICommand {
    void execute();
    void undo();
}

class Light {
    public void turnOn() {
        System.out.println("Свет включен");
    }
    public void turnOff() {
        System.out.println("Свет выключен");
    }
}

class Door {
    public void open() {
        System.out.println("Дверь открыта");
    }
    public void close() {
        System.out.println("Дверь закрыта");
    }
}

class Thermostat {
    private int temperature = 22;
    public void increaseTemperature() {
        temperature++;
        System.out.println("Температура увеличена до " + temperature + "°C");
    }
    public void decreaseTemperature() {
        temperature--;
        System.out.println("Температура уменьшена до " + temperature + "°C");
    }
}

class LightOnCommand implements ICommand {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.turnOn(); }
    public void undo() { light.turnOff(); }
}

class LightOffCommand implements ICommand {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.turnOff(); }
    public void undo() { light.turnOn(); }
}

class DoorOpenCommand implements ICommand {
    private Door door;
    public DoorOpenCommand(Door door) { this.door = door; }
    public void execute() { door.open(); }
    public void undo() { door.close(); }
}

class DoorCloseCommand implements ICommand {
    private Door door;
    public DoorCloseCommand(Door door) { this.door = door; }
    public void execute() { door.close(); }
    public void undo() { door.open(); }
}

class IncreaseTempCommand implements ICommand {
    private Thermostat thermostat;
    public IncreaseTempCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.increaseTemperature(); }
    public void undo() { thermostat.decreaseTemperature(); }
}

class DecreaseTempCommand implements ICommand {
    private Thermostat thermostat;
    public DecreaseTempCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.decreaseTemperature(); }
    public void undo() { thermostat.increaseTemperature(); }
}

import java.util.Stack;

class RemoteControl {
    private Stack<ICommand> history = new Stack<>();
    public void executeCommand(ICommand command) {
        command.execute();
        history.push(command);
    }
    public void undoLastCommand() {
        if (!history.isEmpty()) {
            ICommand command = history.pop();
            command.undo();
        } else {
            System.out.println("Нет команд для отмены");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();
        RemoteControl remote = new RemoteControl();

        ICommand lightOn = new LightOnCommand(light);
        ICommand doorOpen = new DoorOpenCommand(door);
        ICommand tempUp = new IncreaseTempCommand(thermostat);

        remote.executeCommand(lightOn);
        remote.executeCommand(doorOpen);
        remote.executeCommand(tempUp);

        remote.undoLastCommand();
        remote.undoLastCommand();
        remote.undoLastCommand();
        remote.undoLastCommand();
    }
}
