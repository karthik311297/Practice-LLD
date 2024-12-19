package lld.textpad;

import java.util.Arrays;
import java.util.List;

public class Clipboard {

    private String lines;

    public List<String> getLines() {
        return Arrays.asList(lines.split("\n"));
    }

    public void setLines(List<String> lines) {
        this.lines = String.join("\n", lines.toArray(new String[0]));
    }

    public void clear()
    {
        lines = "";
    }
}
