package com.example.automata;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class TransistionBuilder {
    Map<Pair<Integer, Character>, Set<Integer>> table = new HashMap<>();
    Map<Integer, Set<Integer>> etable = new HashMap<>();
    boolean isEpsilon = false;

    public TransistionBuilder add(Integer state, Character symbol, Collection<Integer> states) {
        table.put(new Pair<Integer, Character>(state, symbol), new HashSet<>(states));
        return this;
    }

    public TransistionBuilder addEpsilon(Integer state, Collection<Integer> states) {
        isEpsilon = true;
        etable.put(state, new HashSet<>(states));
        return this;
    }

    public ITransition build() {
        if (isEpsilon)
            return new EpsilonTransistion(table, etable);
        return new Transistion(table);
    }
}
