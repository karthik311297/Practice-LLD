package lld.textpad;

import java.util.List;

public class InsertUndoHandler implements UndoHandler{
    private TextPad textPadRef;

    public InsertUndoHandler(TextPad textPadRef) {
        this.textPadRef = textPadRef;
    }

    @Override
    public void onUndo(int n, int m, List<String> lines) {
        if (n == m)
        {
            textPadRef.delete(n, false);
        }
        else
        {
            textPadRef.delete(n, m, false);
        }
    }
}
