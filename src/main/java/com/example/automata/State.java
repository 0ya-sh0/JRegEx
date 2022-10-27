package com.example.automata;

public class State {
    Integer uid;
    static Integer count = 0;

    State() {
        this.uid = count++;
    }

    State(Integer uid) {
        this.uid = uid;
    }
}
