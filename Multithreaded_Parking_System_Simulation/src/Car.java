
public class Car extends Thread
{
    private String NumberOfGate;
    private int id;
    private int arriveTime;
    private int parkDuration;
    private semaPhore semaphore;
    private static int totalCarsServed = 0;
    private static int[] carsServedAtGates = new int[3];
    private static final Object lock = new Object();


    public Car(String gate, int id, int arriveTime, int parkDuration, semaPhore semaphore) {
        this.NumberOfGate = gate;
        this.id = id;
        this.arriveTime = arriveTime;
        this.parkDuration = parkDuration;
        this.semaphore = semaphore;
    }

    @Override
    public void run()
    {
        try {
            Thread.sleep(arriveTime * 1000);
            System.out.printf("Car %d from %s arrived at time %d\n", id, NumberOfGate, arriveTime);
            semaphore.P();
            System.out.printf("Car %d from %s parked. (Parking Status: %d spots occupied)\n", id, NumberOfGate, semaphore.getInitial() -  semaphore.getNumberOfAvaliableSpots());
            int gateIndex = Integer.parseInt(NumberOfGate.split(" ")[1]) - 1;
            synchronized (lock) {
                carsServedAtGates[gateIndex]++;
            }
            Thread.sleep(parkDuration * 1000);
            System.out.printf("Car %d from %s left after %d units of time. (Parking Status: %d spots occupied)\n", id, NumberOfGate, parkDuration, semaphore.getInitial() - semaphore.getNumberOfAvaliableSpots() - 1);
            semaphore.V();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static int[] getCarsServedAtGates() {
        return carsServedAtGates;
    }
    public static int getTotalCarsServed() {
        return totalCarsServed;
    }

}
