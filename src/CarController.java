import javax.swing.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import Vehicle.*;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController extends JFrame {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer;

    // The frame that represents this instance View of the MVC pattern
    CarView view;
    Model model;
    public List<Vehicle> getCars(){return model.getCars();}

    DrawPanel drawPanel;

    CollisionHandler collisionHandler;

    int gasAmount = 0;

    public CarController() {
        this.view = new CarView("CarSim 2.0", this);
        this.drawPanel = view.getDrawPanel();

        this.model = new Model();
        model.addObserver(drawPanel);
        model.notifyCarObservers();
        model.notifyWorkshopObservers();

        collisionHandler = new CollisionHandler(this);
        timer = new Timer(delay, collisionHandler.getTimer());
        createListeners();
    }


    public List<Workshop> getWorkshops() {
        return model.getWorkshops();
    }

    public void startTimer() {
        timer.start();
    }

    public void createListeners()  {
        view.gasButton.addActionListener(e -> model.gas(gasAmount));

        // actionListener for the brake button only
        view.brakeButton.addActionListener(e -> model.brake(gasAmount));

        view.liftBedButton.addActionListener(e -> model.raiseRamp());

        view.lowerBedButton.addActionListener(e -> model.lowerRamp());

        view.turboOnButton.addActionListener(e -> model.turboOn());

        view.turboOffButton.addActionListener(e -> model.turboOff());

        view.startButton.addActionListener(e -> model.gas(1));

        view.stopButton.addActionListener(e -> {
            model.brakeAll();
        });

        view.addVehicleButton.addActionListener(e -> {
            addVehicleDialog(this);
        });

        view.removeVehicleButton.addActionListener(e -> {
            model.removeCar();
        });

        view.gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner)e.getSource()).getValue());
    }

    public void addVehicleDialog(CarController controller) {
        CarFactory factory = new CarFactory();
        String[] vehicleOptions = factory.getVehicleStrings().toArray(new String[0]);

        JComboBox<String> comboBox = new JComboBox<>(vehicleOptions);
        comboBox.addItem("Random");

        int result = JOptionPane.showConfirmDialog(
                this,
                comboBox,
                "Select a vehicle to add",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            if (!(model.getCars().size() >= 10)) {
                String selectedCar = (String) comboBox.getSelectedItem();
                if (selectedCar != null) {
                    model.addCar(selectedCar);
                }
            }
        }
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // Start the timer
        cc.startTimer();
    }
}
