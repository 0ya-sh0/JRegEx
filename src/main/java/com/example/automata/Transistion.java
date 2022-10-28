package com.example.automata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class Transistion {

    Map<Pair<State, Character>, Set<State>> table;
    State initial;
    Set<State> finalStates;

    public Transistion(Map<Pair<State, Character>, Set<State>> table) {
        this.table = table;
    }

    public Set<State> nextStates(State currentState, Character symbol) {

        Pair<State, Character> key = new Pair<State, Character>(currentState, symbol);

        if (!table.containsKey(key)) {
            return new HashSet<>();
        }
        return table.get(key);
    }

    public Set<State> nextStates(Set<State> currentStates, Character symbol) {
        Set<State> result = new HashSet<>();
        for (State State : currentStates) {
            result.addAll(nextStates(State, symbol));
        }
        return result;
    }

    public void addInto(TransistionBuilder t) {
        t.add(table);
    }

    public Set<State> currentStates(Set<State> currentState) {
        return currentState;
    }

    public void printDot(StringBuilder sbr) {
        for (Pair<State, Character> key : table.keySet()) {
            State src = key.getValue0();
            Character label = key.getValue1();
            for (State dest : table.get(key)) {
                sbr.append(src.uid + " -> " + dest.uid + " [label=\"" + label + "\"]\n");
            }
        }
    }

    public void setInitiaAndlFinalStates(State initial, Set<State> finalState) {
        this.initial = initial;
        this.finalStates = finalState;
    }
}
