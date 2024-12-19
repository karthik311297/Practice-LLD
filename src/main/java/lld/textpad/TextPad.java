package lld.textpad;

import java.util.*;

public class TextPad {

    private final Clipboard clipboard;
    private final Stack<CommandSnapshot> commandSnapshots;
    private final Map<Command, UndoHandler> undoHandlerMap;
    private final Map<Command, RedoHandler> redoHandlerMap;
    private List<String> lines;
    private static final TextPad INSTANCE = new TextPad();

    public static TextPad getInstance() {
        return INSTANCE;
    }

    private TextPad() {
        this.commandSnapshots = new Stack<>();
        this.lines = new ArrayList<>();
        this.clipboard = new Clipboard();
        undoHandlerMap = Map.of(Command.INSERT, new InsertUndoHandler(this), Command.DELETE, new DeleteUndoHandler(this));
        redoHandlerMap = Map.of(Command.INSERT, new InsertRedoHandler(this), Command.DELETE, new DeleteRedoHandler(this));
    }

    public void display()
    {
        System.out.println("-----Displaying contents----");
        for(String s : lines)
        {
            System.out.println(s);
        }
    }

    public void display(int n, int m)
    {
        System.out.println(String.format("-----Displaying contents from line %s to %s----", n, m));
        for (int i = n; i <= m; i++)
        {
            System.out.println(lines.get(i));
        }
    }

    public void insert(int n , String text, boolean withSnap)
    {
        if(withSnap) {
            CommandSnapshot snapshot = new CommandSnapshot(Command.INSERT, n, n, Collections.singletonList(text));
            commandSnapshots.push(snapshot);
        }
        lines.add(n, text);
    }

    public void insert(int n , List<String> texts, boolean withSnap)
    {
        if(withSnap) {
            CommandSnapshot snapshot = new CommandSnapshot(Command.INSERT, n, n + texts.size() - 1, texts);
            commandSnapshots.push(snapshot);
        }
        lines.addAll(n, texts);
    }

    public void delete(int n, boolean withSnap)
    {
        String line = lines.get(n);
        if(withSnap) {
            CommandSnapshot snapshot = new CommandSnapshot(Command.DELETE, n, n, Collections.singletonList(line));
            commandSnapshots.push(snapshot);
        }
        lines.remove(n);
    }

    public void delete(int n, int m,boolean withSnap)
    {
        List<String> p1 = lines.subList(0, n);
        List<String> p2 = new ArrayList<>();
        if (m + 1 < lines.size()) p2 = lines.subList(m + 1, lines.size());
        List<String> deleted = lines.subList(n, m + 1);
        if(withSnap) {
            CommandSnapshot snapshot = new CommandSnapshot(Command.DELETE, n, m, deleted);
            commandSnapshots.push(snapshot);
        }
        lines = new ArrayList<>();
        lines.addAll(p1);
        lines.addAll(p2);
    }

    public void copy(int n, int m)
    {
        clipboard.clear();
        clipboard.setLines(lines.subList(n, m + 1));
    }

    public void paste(int n)
    {
        insert(n, clipboard.getLines(), true);
    }

    public void undo()
    {
        if(!commandSnapshots.isEmpty())
        {
            CommandSnapshot commandSnapshot = commandSnapshots.pop();
            UndoHandler undoHandler = undoHandlerMap.get(commandSnapshot.getCommand());
            undoHandler.onUndo(commandSnapshot.getN(), commandSnapshot.getM(), commandSnapshot.getLines());
        }
    }

    public void redo()
    {

    }
}
