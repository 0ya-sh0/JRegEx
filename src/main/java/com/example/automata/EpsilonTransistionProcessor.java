package com.example.automata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EpsilonTransistionProcessor {
    Set<Integer> visited;
    Set<Integer> result;
    Map<Integer, Set<Integer>> etable;

    public EpsilonTransistionProcessor(Map<Integer, Set<Integer>> etable) {
        this.etable = etable;
    }

    public Set<Integer> process(Integer state) {
        return process(new HashSet<>(Arrays.asList(state)));
    }

    public Set<Integer> process(Set<Integer> state) {
        visited = new HashSet<>();
        result = new HashSet<>();
        result.addAll(state);
        boolean newAdditions;
        do {
            newAdditions = false;
            for (Integer integer : state) {
                newAdditions = newAdditions || _fromState(integer);
            }
        } while (newAdditions);
        return result;
    }

    private boolean _fromState(Integer state) {
        boolean newAdditions = false;
        visited.add(state);
        Set<Integer> nextStates = getEpsilonAddition(state);
        if (!nextStates.isEmpty())
            newAdditions = true;
        result.addAll(nextStates);
        for (Integer integer : nextStates) {
            if (!visited.contains(integer))
                newAdditions = newAdditions || _fromState(integer);
        }
        return newAdditions;
    }

    public Set<Integer> getEpsilonAddition(Integer input) {
        if (!etable.containsKey(input))
            return new HashSet<>();
        return etable.get(input);
    }
}
