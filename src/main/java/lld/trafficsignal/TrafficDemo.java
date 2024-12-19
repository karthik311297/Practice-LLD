package lld.trafficsignal;

public class TrafficDemo {
    /*
    The traffic signal system should control the flow of traffic at an intersection with multiple roads.
    The system should support different types of signals, such as red, yellow, and green.
    The duration of each signal should be configurable and adjustable based on traffic conditions.
    The system should handle the transition between signals smoothly, ensuring safe and efficient traffic flow.
    The system should be able to detect and handle emergency situations, such as an ambulance or fire truck approaching the intersection.
    The system should be scalable and extensible to support additional features and functionality.
     */

    /*
    TrafficSystem

    Road - road id, TrafficLight

    Signal - Enum - red, green amber

    TrafficLight - TrafficLightConfiguration
        changeSignal()

    TrafficLightConfiguration (RED, GREEN,AMBER)
        getDuration(Signal signal)

     */
    public static void main(String[] args) {
        TrafficLightSystem trafficLightSystem = new TrafficLightSystem();
        Road road1 = new Road("1");
        Road road2 = new Road("2");
        TrafficLightConfiguration trafficLightConfiguration1 = new TrafficLightConfiguration(1000, 2000, 3000);
        TrafficLightConfiguration trafficLightConfiguration2 = new TrafficLightConfiguration(2000, 2000, 1000);
        TrafficLight trafficLight1 = new TrafficLight(trafficLightConfiguration1);
        TrafficLight trafficLight2 = new TrafficLight(trafficLightConfiguration2);
        trafficLightSystem.addRoadWithTrafficLight(road1, trafficLight1);
        trafficLightSystem.addRoadWithTrafficLight(road2, trafficLight2);
        trafficLightSystem.startTrafficSystem();

    }
}
