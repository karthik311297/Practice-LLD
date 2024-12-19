package lld.trafficsignal;

import java.util.HashMap;
import java.util.Map;

public class TrafficLightSystem {

    private final Map<String, Road> roadMap = new HashMap<>();

    public void addRoadWithTrafficLight(Road road, TrafficLight trafficLight)
    {
        road.setTrafficLight(trafficLight);
        roadMap.put(road.getId(), road);
    }

    public void startTrafficSystem()
    {
        for(Road r : roadMap.values())
        {
            TrafficLight light = r.getTrafficLight();
            new Thread(() -> {
                while (true) {
                    Signal currentSignal = light.getCurrentSignal();
                    System.out.println("Staying in current signal " + currentSignal + " at road " + r.getId() + " for duration : " + light.getConfiguration().getDuration(currentSignal));
                    light.changeSignal(Signal.GREEN);
                    currentSignal = light.getCurrentSignal();
                    System.out.println("Signal at road: " + r.getId() + " Changed to: " + currentSignal);
                    System.out.println("Staying in current signal " + currentSignal + " at road " + r.getId() + " for duration : " + light.getConfiguration().getDuration(currentSignal));
                    light.changeSignal(Signal.AMBER);
                    currentSignal = light.getCurrentSignal();
                    System.out.println("Signal at road: " + r.getId() + " Changed to: " + currentSignal);
                    System.out.println("Staying in current signal " + currentSignal + " at road " + r.getId() + " for duration : " + light.getConfiguration().getDuration(currentSignal));
                    light.changeSignal(Signal.RED);
                    currentSignal = light.getCurrentSignal();
                    System.out.println("Signal at road: " + r.getId() + " Changed to: " + currentSignal);
                }
            }).start();
        }
    }
}
