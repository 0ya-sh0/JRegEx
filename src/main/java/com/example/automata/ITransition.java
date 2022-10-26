package com.example.automata;

import java.util.Set;

public interface ITransition {
    public Set<Integer> nextStates(Integer currentState, Character symbol);

    public Set<Integer> nextStates(Set<Integer> currentStates, Character symbol);
}
