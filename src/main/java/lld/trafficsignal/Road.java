package lld.trafficsignal;

public class Road {

    private String id;

    private TrafficLight trafficLight;

    public Road(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }
}
