package lld.textpad;

public class DeleteRedoHandler implements RedoHandler{
    private TextPad textPadRef;

    public DeleteRedoHandler(TextPad textPadRef) {
        this.textPadRef = textPadRef;
    }
}
