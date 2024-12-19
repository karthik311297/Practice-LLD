package lld.trafficsignal;

import java.util.HashMap;
import java.util.Map;

public class TrafficLightConfiguration {
    private Map<Signal, Integer> durations;

    public TrafficLightConfiguration(int redDuration, int amberDuration, int greenDuration)
    {
        durations = new HashMap<>();
        durations.put(Signal.RED, redDuration);
        durations.put(Signal.AMBER, amberDuration);
        durations.put(Signal.GREEN, greenDuration);
    }

    public int getDuration(Signal signal)
    {
        return durations.get(signal);
    }
}
