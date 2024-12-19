package lld.textpad;

public class InsertRedoHandler implements RedoHandler{
    private TextPad textPadRef;

    public InsertRedoHandler(TextPad textPadRef) {
        this.textPadRef = textPadRef;
    }
}
