package com.example.automata;

public class State {
    int uid;
    static int count = 0;

    State() {
        this.uid = count;
        count++;
    }

    State(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "" + uid;
    }
}
