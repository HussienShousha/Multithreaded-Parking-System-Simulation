import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLots {
    private semaPhore semaphore;

    public ParkingLots(int totalSpots) {
        semaphore = new semaPhore(totalSpots);
    }

    public void Simulation(String filePath) {
        List<Thread> carThreads = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String gate = parts[0];
                int id = Integer.parseInt(parts[1].split(" ")[1]);
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkDuration = Integer.parseInt(parts[3].split(" ")[1]);

                Car car = new Car(gate, id, arriveTime, parkDuration, semaphore);

                carThreads.add(car);
                car.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Thread thread : carThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Final report
        System.out.printf("Total Cars Served: %d\n", Car.getTotalCarsServed());
        System.out.println("Current Cars in Parking: 0");
        int[] servedAtGates = Car.getCarsServedAtGates();
        for (int i = 0; i < servedAtGates.length; i++) {
            System.out.printf("Gate %d served %d cars.\n", (i + 1), servedAtGates[i]);
        }
    }
}