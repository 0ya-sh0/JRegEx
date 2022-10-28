package com.example.automata;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class TransistionBuilder {
    Map<Pair<State, Character>, Set<State>> table = new HashMap<>();
    Map<State, Set<State>> etable = new HashMap<>();
    boolean isEpsilon = false;

    public TransistionBuilder add(State state, Character symbol, Collection<State> states) {
        table.put(new Pair<State, Character>(state, symbol), new HashSet<>(states));
        return this;
    }

    public TransistionBuilder add(Map<Pair<State, Character>, Set<State>> table) {
        this.table.putAll(table);
        return this;
    }

    public TransistionBuilder addEpsilon(State state, Collection<State> states) {
        isEpsilon = true;
        if (!etable.containsKey(state)) {
            etable.put(state, new HashSet<>(states));
            return this;
        }
        etable.get(state).addAll(states);
        return this;
    }

    public TransistionBuilder addEpsilon(Map<State, Set<State>> etable) {
        isEpsilon = true;
        this.etable.putAll(etable);
        return this;
    }

    public TransistionBuilder addTransistion(Transistion t) {
        t.addInto(this);
        return this;
    }

    public Transistion build() {
        if (isEpsilon)
            return new EpsilonTransistion(table, etable);
        return new Transistion(table);
    }
}
