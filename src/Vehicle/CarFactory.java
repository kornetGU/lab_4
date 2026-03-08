package Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarFactory {
    private List<String> vehicleStrings;
    public List<String> getVehicleStrings(){return vehicleStrings;}

    public CarFactory(){
        vehicleStrings = new ArrayList<>();
        vehicleStrings.add("Saab95");
        vehicleStrings.add("Volvo240");
        vehicleStrings.add("Scania");
    }

    /**
     * Create a vehicle based on string.
     * If new vehicletype is introduced, u need to update addToImageRegistry in DrawPanel.
     * @param vehicleType String to match with cars model
     * @return a Vehicle
     */
    public Vehicle createVehicle(String vehicleType) {
        Random rand = new Random();
        Vehicle createdVehicle;

        switch (vehicleType) {
            case "Volvo240" -> {
                createdVehicle = new Volvo240();
            }
            case "Saab95" -> {
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
