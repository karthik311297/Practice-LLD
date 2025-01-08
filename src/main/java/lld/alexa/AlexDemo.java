package lld.alexa;

public class AlexDemo {
    /*

    enum BatteryStatus
        PLUGGED_IN, CHARGED

    Input
        String value
    extended by :
        Voice, Text

    Output
        String value
    extended by :
        Sound, Display

    InputProcessor
        String processInputAndGetContent(Input)
    extended by :
        VoiceInputProcessor, TextInputProcessor

    OutputProcessor
        Output processAndGetOutput(String)
    extended by :
        SoundOutputProcessor, DisplayOutputProcessor

    abstract AlexaDevice
        InputProcessor
        OutputProcessor
        BatteryStatus
        int batteryPercent
        abstract Output getOutput(Input input) - it internally calls inputprocessor to get the actual question,
                                        then gets the answer and using outputprocessor gives the output in required format.

    extended by :
        Echo(VoiceInputProcessor, SoundOutputProcessor)
        EchoDot(VoiceInputProcessor, SoundOutputProcessor)
        EchoShow(TextInputProcessor, DisplayOutputProcessor)


    KnowledgeBase (has the answers to questions other than battery question)
        Map<String, String>

     */
}
