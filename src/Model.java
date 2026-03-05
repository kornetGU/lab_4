import Vehicle.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Model {

    // CARS
    private Volvo240 volvo ;
    private Scania scania ;
    private Saab95 saab ;

    private List<Vehicle> cars;

    // WORKSHOPS
    private Workshop<Volvo240> volvoWorkshop;

    private List<Workshop> workshops;

    // OTHER
    private final int delay = 50;

    public Model() {
        volvo = new Volvo240();
        scania = new Scania();
        saab = new Saab95();

        cars = new ArrayList<>();
        cars.add(scania);
        cars.add(saab);
        cars.add(volvo);

        volvoWorkshop = new Workshop<Volvo240>(1,"VolvoWorkshop",300,300,"pics/VolvoBrand.jpg", Volvo240.class);
        workshops = new ArrayList<>();
        workshops.add(volvoWorkshop);

        setY();
    }

    public List<Vehicle> getCars(){
        return cars;
    }

    public List<Workshop> getWorkshops(){
        return workshops;
    }

    // Calls the gas method for each car once
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

    public void setY(){
        double yOffset = 0;
        final double space = 100;
        for (Vehicle car : cars) {
            car.setY(yOffset);
            yOffset += car.getImage().getHeight() + space;
        }
    }
}