package lld.textpad;

import java.util.List;

public class CommandSnapshot {
    private int n;
    private int m;
    private List<String> lines;
    private Command command;

    public CommandSnapshot(Command command, int n, int m, List<String> lines) {
        this.n = n;
        this.m = m;
        this.lines = lines;
        this.command = command;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public List<String> getLines() {
        return lines;
    }

    public Command getCommand() {
        return command;
    }
}
