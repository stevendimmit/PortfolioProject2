import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new LinkedList<>();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Vehicle Info.");

        while (true) {
            System.out.println("Enter Make (or type done to finish): ");
            String make = scnr.nextLine();
            
            if (make.equalsIgnoreCase("done")) {
                break;
            }

            System.out.println("Enter Model: ");
            String model = scnr.nextLine();

            double mpg = getValidMpg(scnr); // Get a valid MPG value

            // Create a Vehicle object with the valid MPG value
            vehicles.add(new Vehicle(make, model, mpg));
            System.out.println("Vehicle info added, add another vehicle or type done to sort and write to file");
        }

        // Sort vehicles by MPG before printing and writing to file
        Collections.sort(vehicles, new MpgComparator());

        System.out.println("List of Vehicles:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        // Write the sorted list to file on desktop
        writeToFile(vehicles);

        scnr.close();
    }

    private static double getValidMpg(Scanner scnr) {
        double mpg = -1;
        boolean validMpg = false;

        while (!validMpg) {
            System.out.println("Enter MPG: ");
            String mpgInput = scnr.nextLine();

            try {
                mpg = Double.parseDouble(mpgInput);  
                
              
                if (mpg <= 0 || mpg > 200) {
                    throw new IllegalArgumentException("Invalid MPG value. Must be between 0 and 200.");
                }

                validMpg = true; // Exit loop if MPG is valid
            } catch (NumberFormatException e) {
                // Handle cases where input is not a number
                System.out.println("Invalid MPG. Please enter a numeric value.");
            } catch (IllegalArgumentException e) {
                // Handle cases where MPG value is out of range
                System.out.println("Car not added. Make sure MPG is correct.");
                System.out.println("Error details: " + e.getMessage());
            }
        }

        return mpg;
    }

    private static void writeToFile(List<Vehicle> vehicles) {
        // Define the path to the file on the desktop
        String desktopPath = System.getProperty("user.home") + "/Desktop/vehicles.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(desktopPath))) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.toString());
                writer.newLine();
            }
            System.out.println("Vehicle list written to file: " + desktopPath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
