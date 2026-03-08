import Vehicle.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    // SUBSCRIBERS
    List<ModelObserver> observer = new ArrayList<>();
    public void addObserver(ModelObserver o) {observer.add(o);}

    public void notifyCarObservers() {
        for (ModelObserver o : observer) {
            o.updateCars(carInfo());
        }
    }

    public void notifyWorkshopObservers() {
        for (ModelObserver o : observer) {
            o.updateWorkshops(workshopInfo());
        }
    }

    // Only information that gets sent to drawPanel.
    private Map<String, Point> carInfo(){
        Map<String, Point> carInfo = new HashMap<>();
        for (Vehicle car : cars) {
            carInfo.put(car.getId() + "_" + car.getModelName(),
                    new Point((int)car.getX(), (int)car.getY()));
        }
        return carInfo;
    }

    private Map<String, Point> workshopInfo(){
        Map<String, Point> workshopInfo = new HashMap<>();
        for (Workshop workshop : workshops) {
            workshopInfo.put(workshop.getName() ,new Point((int)workshop.getX(),(int)workshop.getY()));
        }
        return workshopInfo;
    }

    // CARS
    private List<Vehicle> cars;

    public List<Vehicle> getCars(){
        return cars;
    }

    private CarFactory carFactory;

    public void addCar(String modelName) {
        Vehicle car = carFactory.createVehicle(modelName);
        cars.add(car);
        car.setY(0);
        notifyCarObservers();
    }

    public void removeCar(){
        cars.remove(cars.size() - 1 );
    }

    // WORKSHOPS
    private Workshop<Volvo240> volvoWorkshop;

    private List<Workshop> workshops;

    public List<Workshop> getWorkshops(){
        return workshops;
    }

    // OTHER
    private final int delay = 50;

    public Model() {
        carFactory = new CarFactory();
        cars = carFactory.createVehicleOneOfEach();

        volvoWorkshop = new Workshop<Volvo240>(1,"VolvoWorkshop",300,300,Volvo240.class);
        workshops = new ArrayList<>();
        workshops.add(volvoWorkshop);

        setY();
    }

    public void lowerRamp() {
        for (Vehicle car : cars) {
            if (car instanceof Ramp rampTruck) rampTruck.lowerRamp();
        }
    }

    public void raiseRamp() {
        for (Vehicle car : cars) {
            if (car instanceof Ramp rampTruck) rampTruck.raiseRamp();
        }
    }

    public void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Turbo turboCar) turboCar.setTurboOn();
        }
    }

    public void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Turbo turboCar) turboCar.setTurboOff();
        }
    }

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

            if (newX != oldX || newY != oldY) moved = true ;

        }
        if(moved) notifyCarObservers();
    }

    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    public void brakeAll(){
        for (Vehicle car : cars) {
            car.stopEngine();
            car.brake(1);
        }
    }

    public void setY(){
        double yOffset = 0;
        final double space = 100;
        for (Vehicle car : cars) {
            car.setY(yOffset);
            yOffset += 160;
        }
    }
}