package Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarFactory {
    private List<String> vehicleStrings;
    public List<String> getVehicleStrings(){return vehicleStrings;}

    public CarFactory(){
        vehicleStrings = new ArrayList<>();
        vehicleStrings.add("Saab");
        vehicleStrings.add("Volvo");
        vehicleStrings.add("Scania");
    }

    public Vehicle createVehicle(String vehicleType) {
        Random rand = new Random();
        Vehicle createdVehicle;

        switch (vehicleType) {
            case "Volvo" -> {
                createdVehicle = new Volvo240();
            }
            case "Saab" -> {
                createdVehicle = new Saab95();
            }
            case "Scania" -> {
                createdVehicle = new Scania();
            }
            default -> {
                createdVehicle = createVehicle(vehicleStrings.get
                                 (rand.nextInt(vehicleStrings.size())));
            }
        }
        return createdVehicle;
    }

    public List<Vehicle> createVehicleOneOfEach(){
        List<Vehicle> cars = new ArrayList<>();
        for (String modelName : vehicleStrings) {
            cars.add(createVehicle(modelName));
        }
        return cars;
    }

}
