package com.example.automata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EpsilonTransistionProcessor {
    Set<State> visited;
    Set<State> result;
    Map<State, Set<State>> etable;

    public EpsilonTransistionProcessor(Map<State, Set<State>> etable) {
        this.etable = etable;
    }

    public Set<State> process(State state) {
        return process(new HashSet<>(Arrays.asList(state)));
    }

    public Set<State> process(Set<State> state) {
        visited = new HashSet<>();
        result = new HashSet<>();
        result.addAll(state);
        boolean newAdditions;
        do {
            newAdditions = false;
            for (State State : state) {
                newAdditions = newAdditions || _fromState(State);
            }
        } while (newAdditions);
        return result;
    }

    private boolean _fromState(State state) {
        boolean newAdditions = false;
        visited.add(state);
        Set<State> nextStates = getEpsilonAddition(state);
        if (!nextStates.isEmpty())
            newAdditions = !result.containsAll(nextStates);
        result.addAll(nextStates);
        for (State State : nextStates) {
            if (!visited.contains(State))
                newAdditions = newAdditions || _fromState(State);
        }
        return newAdditions;
    }

    public Set<State> getEpsilonAddition(State input) {
        if (!etable.containsKey(input))
            return new HashSet<>();
        return etable.get(input);
    }
}
