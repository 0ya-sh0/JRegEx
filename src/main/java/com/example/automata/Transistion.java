package com.example.automata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class Transistion implements ITransition {

    Map<Pair<State, Character>, Set<State>> table;

    public Transistion(Map<Pair<State, Character>, Set<State>> table) {
        this.table = table;
    }

    @Override
    public Set<State> nextStates(State currentState, Character symbol) {

        Pair<State, Character> key = new Pair<State, Character>(currentState, symbol);

        if (!table.containsKey(key)) {
            return new HashSet<>();
        }
        return table.get(key);
    }

    @Override
    public Set<State> nextStates(Set<State> currentStates, Character symbol) {
        Set<State> result = new HashSet<>();
        for (State State : currentStates) {
            result.addAll(nextStates(State, symbol));
        }
        return result;
    }

    @Override
    public void addInto(TransistionBuilder t) {
        t.add(table);
    }
}
