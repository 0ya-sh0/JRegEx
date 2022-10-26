package com.example.automata;

import java.util.HashSet;
import java.util.Set;

public class Nfa implements INfa {

    final Integer initialState;
    Set<Integer> finalStates;
    Set<Integer> currentStates;
    ITransition transition;

    public Nfa(ITransition transition, Integer initialState,
            Set<Integer> finalStates) {
        this.transition = transition;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    @Override
    public boolean run(String input) {
        currentStates = new HashSet<>();
        currentStates.add(initialState);

        for (Character ch : input.toCharArray()) {
            currentStates = transition.nextStates(currentStates, ch);
        }

        Set<Integer> intersection = new HashSet<>(currentStates);
        intersection.retainAll(finalStates);

        return (!intersection.isEmpty());
    }
}
