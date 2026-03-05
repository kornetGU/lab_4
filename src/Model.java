import Vehicle.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    // SUBSCRIBERS
    List<ModelObserver> observer = new ArrayList<>();
    public void addObserver(ModelObserver o) {observer.add(o);}
    public void notifyObservers() {
        for (ModelObserver o : observer) {
            o.onUpdate();
        }
    }
    //////////////

    // CARS
    private List<Vehicle> cars;

    public List<Vehicle> getCars(){
        return cars;
    }

    private CarFactory carFactory;
    //////////////

    // WORKSHOPS
    private Workshop<Volvo240> volvoWorkshop;

    private List<Workshop> workshops;

    public List<Workshop> getWorkshops(){
        return workshops;
    }
    //////////////

    // OTHER
    private final int delay = 50;

    public Model() {
        carFactory = new CarFactory();
        cars = carFactory.createVehicleOneOfEach();

        volvoWorkshop = new Workshop<Volvo240>(1,"VolvoWorkshop",300,300,"pics/VolvoBrand.jpg", Volvo240.class);
        workshops = new ArrayList<>();
        workshops.add(volvoWorkshop);

        setY();
    }


    void lowerRamp() {
        for (Vehicle car : cars) {
            if (car instanceof Ramp rampTruck) rampTruck.lowerRamp();
        }
    }

    void raiseRamp() {
        for (Vehicle car : cars) {
            if (car instanceof Ramp rampTruck) rampTruck.raiseRamp();
        }
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Turbo turboCar) turboCar.setTurboOn();
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Turbo turboCar) turboCar.setTurboOff();
        }
    }
    //////////////

    // MOVEMENT
    public void moveCars(){
        boolean moved = false;
        for (Vehicle car : cars) {

            if (!car.canMove()) continue;

            double oldX = car.getX();
            double oldY = car.getY();

            car.move();

            double newX = car.getX();
            double newY = car.getY();

            moved = newX != oldX || newY != oldY;

        }
        if(moved) notifyObservers();
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    public void setY(){
        double yOffset = 0;
        final double space = 100;
        for (Vehicle car : cars) {
            car.setY(yOffset);
            yOffset += car.getImage().getHeight() + space;
        }
    }
}