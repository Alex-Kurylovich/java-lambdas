package com.insightfullogic.java8.book.chapter8;

import com.insightfullogic.java8.book.chapter8.classes.Close;
import com.insightfullogic.java8.book.chapter8.classes.Macro;
import com.insightfullogic.java8.book.chapter8.classes.MockEditor;
import com.insightfullogic.java8.book.chapter8.classes.Open;
import com.insightfullogic.java8.book.chapter8.classes.Save;
import org.junit.Test;

public class TestChapter8Macros {

    @Test
    public void classBasedCommand() {
        MockEditor editor = new MockEditor();

        Macro macro = new Macro();
        macro.record(new Open(editor));
        macro.record(new Save(editor));
        macro.record(new Close(editor));
        macro.run();

        editor.check();
    }

    @Test
    public void lambdaBasedCommand() {
        MockEditor editor = new MockEditor();

        Macro macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();

        editor.check();
    }

    @Test
    public void referenceBasedCommand() {
        MockEditor editor = new MockEditor();

        Macro macro = new Macro();
        macro.record(editor::open);
        macro.record(editor::save);
        macro.record(editor::close);
        macro.run();

        editor.check();
    }

}
