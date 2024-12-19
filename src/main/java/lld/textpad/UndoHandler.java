package lld.textpad;

import java.util.List;

public interface UndoHandler {
    default void onUndo(int n, int m, List<String> lines)
    {
        throw new UnsupportedOperationException();
    }
}
