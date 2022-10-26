package com.example.automata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class Transistion implements ITransition {

    Map<Pair<Integer, Character>, Set<Integer>> table;

    public Transistion(Map<Pair<Integer, Character>, Set<Integer>> table) {
        this.table = table;
    }

    @Override
    public Set<Integer> nextStates(Integer currentState, Character symbol) {

        Pair<Integer, Character> key = new Pair<Integer, Character>(currentState, symbol);

        if (!table.containsKey(key)) {
            return new HashSet<>();
        }
        return table.get(key);
    }

    @Override
    public Set<Integer> nextStates(Set<Integer> currentStates, Character symbol) {
        Set<Integer> result = new HashSet<>();
        for (Integer integer : currentStates) {
            result.addAll(nextStates(integer, symbol));
        }
        return result;
    }
}
