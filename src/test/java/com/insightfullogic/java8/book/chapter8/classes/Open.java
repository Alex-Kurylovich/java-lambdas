package com.insightfullogic.java8.book.chapter8.classes;

import com.insightfullogic.java8.book.chapter8.interfaces.Action;

public class Open implements Action {

    private final Editor editor;

    public Open(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void perform() {
        editor.open();
    }
}
