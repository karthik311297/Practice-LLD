package lld.textpad;

import java.util.List;

public class DeleteUndoHandler implements UndoHandler{
    private TextPad textPadRef;

    public DeleteUndoHandler(TextPad textPadRef) {
        this.textPadRef = textPadRef;
    }

    @Override
    public void onUndo(int n, int m, List<String> lines) {
        textPadRef.insert(n, lines, false);
    }
}
