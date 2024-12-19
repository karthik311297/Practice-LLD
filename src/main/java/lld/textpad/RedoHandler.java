package lld.textpad;

import java.util.List;

public interface RedoHandler {
    default void onRedo(int n, int m, List<String> lines)
    {
        throw new UnsupportedOperationException();
    }
}
