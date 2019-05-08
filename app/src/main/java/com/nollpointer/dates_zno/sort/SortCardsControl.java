package com.nollpointer.dates_zno.sort;

import java.util.List;

public interface SortCardsControl {

    void setQuestions(List<String> list);

    void setAnswerSequence(int[] sequence);

    boolean check();

    void setCheckMode(boolean state);

    void setColors(int g, int r);
}