package lld.textpad;

import java.util.Arrays;

public class Demo {

    /*
    code a TextPad with following functionality:

        display() – to display the entire content
        display(n, m) – to display from line n to m
        insert(n, text) – to insert text at line n
        delete(n) – delete line n
        delete(n, m) – delete from line n to m
        copy(n, m) – copy contents from line n to m to clipboard
        paste(n) – paste contents from clipboard to line n
        undo() – undo last command
        redo() – redo last command
     */

    public static void main(String[] args) {
        TextPad textPad = TextPad.getInstance();
        textPad.insert(0, "hello", true);
        textPad.insert(0, "tata", true);
        textPad.insert(2, "bye bye", true);
        textPad.insert(3, "what's up", true);
        textPad.insert(2, "karthik", true);
        textPad.display();
        textPad.delete(1, 2, true);
        textPad.display();
        textPad.undo();
        textPad.copy(0, 3);
        textPad.display();
        textPad.insert(3, "lld", true);
        textPad.display();
        textPad.paste(5);
        textPad.display();
        textPad.delete(3, true);
        textPad.display();
        textPad.display(3, 6);
        textPad.insert(4, Arrays.asList("kart", "iyer", "gunner"), true);
        textPad.display();
        textPad.undo();
        textPad.display();
    }
}
