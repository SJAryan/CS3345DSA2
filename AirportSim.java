public class AirportSim {

    public static void main(String[] args) {
       

        int landingTime = Integer.parseInt(args[0]);
        int takeOffTime = Integer.parseInt(args[1]);
        double landingRate = Double.parseDouble(args[2]);
        double takeOffRate = Double.parseDouble(args[3]);
        int totalSimTime = Integer.parseInt(args[4]);

        LinkedQueue<Airplane> landingQueue = new LinkedQueue<>();
        LinkedQueue<Airplane> takeOffQueue = new LinkedQueue<>();

        int runwayTimeRemaining = 0;
        int nextPlaneId = 1;
        
        int totalLandingWaitTime = 0;
        int totalTakeOffWaitTime = 0;
        int planesLanded = 0;
        int planesTakenOff = 0;
        int cumulativeLandingQueueLength = 0;
        int cumulativeTakeOffQueueLength = 0;
        int totalTics = 0;

        System.out.println("--- Starting Airport Simulation ---");

        for (int clock = 0; clock < totalSimTime; clock += 5) {
            totalTics++;

            if (runwayTimeRemaining > 0) {
                runwayTimeRemaining -= 5;
            }

            if (runwayTimeRemaining <= 0) {
                if (!landingQueue.isEmpty()) {
                    Airplane plane = landingQueue.dequeue();
                    int waitTime = clock - plane.getArrivalTime();
                    totalLandingWaitTime += waitTime;
                    planesLanded++;
                    runwayTimeRemaining = landingTime;
                    System.out.println("Time " + clock + ": Plane " + plane.getId() + " landed. (Waited " + waitTime + " mins)");
                } else if (!takeOffQueue.isEmpty()) {
                    Airplane plane = takeOffQueue.dequeue();
                    int waitTime = clock - plane.getArrivalTime();
                    totalTakeOffWaitTime += waitTime;
                    planesTakenOff++;
                    runwayTimeRemaining = takeOffTime;
                    System.out.println("Time " + clock + ": Plane " + plane.getId() + " took off. (Waited " + waitTime + " mins)");
                }
            }

            double rand1 = Math.random();
            double rand2 = Math.random();

            if (rand1 < landingRate) {
                landingQueue.enqueue(new Airplane(nextPlaneId++, clock, true));
            }
            if (rand2 < takeOffRate) {
                takeOffQueue.enqueue(new Airplane(nextPlaneId++, clock, false));
            }

            cumulativeLandingQueueLength += landingQueue.size();
            cumulativeTakeOffQueueLength += takeOffQueue.size();
        }

       
        System.out.println("\n--- Simulation Report ---");
        System.out.println("Total time simulated: " + totalSimTime + " minutes");
        System.out.println("Total planes landed: " + planesLanded);
        System.out.println("Total planes taken off: " + planesTakenOff);
        
        if (planesLanded > 0) {
            System.out.println("Average wait time for landing: " + (totalLandingWaitTime / planesLanded) + " minutes");
        }
        if (planesTakenOff > 0) {
            System.out.println("Average wait time for takeoff: " + (totalTakeOffWaitTime / planesTakenOff) + " minutes");
        }
        
        System.out.println("Average landing queue length: " + ((double) cumulativeLandingQueueLength / totalTics));
        System.out.println("Average takeoff queue length: " + ((double) cumulativeTakeOffQueueLength / totalTics));
    }
}