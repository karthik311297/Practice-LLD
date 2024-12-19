package lld.trafficsignal;

public class TrafficLight {

    private TrafficLightConfiguration configuration;
    private Signal currentSignal;

    public TrafficLight(TrafficLightConfiguration configuration) {
        this.configuration = configuration;
        currentSignal = Signal.RED;
    }

    public TrafficLightConfiguration getConfiguration() {
        return configuration;
    }

    public synchronized void changeSignal(Signal nextSignal)
    {
        try {
            Thread.sleep(configuration.getDuration(currentSignal));
        } catch (InterruptedException e) {

        }
        currentSignal = nextSignal;
    }

    public Signal getCurrentSignal()
    {
        return currentSignal;
    }
}
