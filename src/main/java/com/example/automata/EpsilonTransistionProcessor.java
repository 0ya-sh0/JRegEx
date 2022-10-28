package com.example.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EpsilonTransistionProcessor {
    Map<State, Set<State>> etable;
    Map<State, Set<State>> memo;

    public EpsilonTransistionProcessor(Map<State, Set<State>> etable) {
        this.etable = etable;
        this.memo = new HashMap<>();
    }

    public Set<State> process(State state) {
        if(memo.containsKey(state)) {
            return memo.get(state);
        }
        Set<State> res = new HashSet<>();
        dfs(state, res);
        memo.put(state, res);
        return res;
    }

    public Set<State> process(Set<State> states) {
        Set<State> result = new HashSet<>();
        for (State state : states) {
            result.addAll(process(state));
        }
        return result;
    }

    public void dfs(State state, Set<State> visited) {
        visited.add(state);
        Set<State> nextStates = getNextEpsilonStates(state);
        for (State nextState : nextStates) {
            if(!visited.contains(nextState)) {
                dfs(nextState, visited);
            }
        }
    }

    public Set<State> getNextEpsilonStates(State input) {
        if (!etable.containsKey(input))
            return new HashSet<>();
        return etable.get(input);
    }
}
