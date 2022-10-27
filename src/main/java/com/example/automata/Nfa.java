package com.example.automata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Nfa {

    final State initialState;
    Set<State> finalStates;
    Set<State> currentStates;
    ITransition transition;

    public Nfa(ITransition transition, State initialState,
            Set<State> finalStates) {
        this.transition = transition;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public boolean run(String input) {
        currentStates = new HashSet<>();
        currentStates.add(initialState);
        for (Character ch : input.toCharArray()) {
            currentStates = transition.nextStates(currentStates, ch);
        }
        currentStates = transition.currentStates(currentStates);
        Set<State> intersection = new HashSet<>(currentStates);
        intersection.retainAll(finalStates);

        return (!intersection.isEmpty());
    }

    public static Nfa or(Nfa left, Nfa right) {
        State nInitialState = new State();
        State nFinalState = new State();
        TransistionBuilder builder = new TransistionBuilder();
        builder.addTransistion(left.transition);
        builder.addTransistion(right.transition);
        builder.addEpsilon(nInitialState, Arrays.asList(left.initialState, right.initialState));
        for (State finState : left.finalStates) {
            builder.addEpsilon(finState, Arrays.asList(nFinalState));
        }
        for (State finState : right.finalStates) {
            builder.addEpsilon(finState, Arrays.asList(nFinalState));
        }
        ITransition nTransistion = builder.build();
        return new Nfa(nTransistion, nInitialState, new HashSet<>(Arrays.asList(nFinalState)));
    }

    public static Nfa and(Nfa left, Nfa right) {
        State nInitialState = new State();
        State nFinalState = new State();
        TransistionBuilder builder = new TransistionBuilder();
        builder.addTransistion(left.transition);
        builder.addTransistion(right.transition);

        builder.addEpsilon(nInitialState, Arrays.asList(left.initialState));
        for (State finState : left.finalStates) {
            builder.addEpsilon(finState, Arrays.asList(right.initialState));
        }
        for (State finState : right.finalStates) {
            builder.addEpsilon(finState, Arrays.asList(nFinalState));
        }
        ITransition nTransistion = builder.build();
        return new Nfa(nTransistion, nInitialState, new HashSet<>(Arrays.asList(nFinalState)));
    }

    public static Nfa star(Nfa value) {
        State nInitialState = new State();
        State nFinalState = new State();
        TransistionBuilder builder = new TransistionBuilder();
        builder.addTransistion(value.transition);

        builder.addEpsilon(nInitialState, Arrays.asList(value.initialState, nFinalState));

        for (State finState : value.finalStates) {
            builder.addEpsilon(finState, Arrays.asList(value.initialState, nFinalState));
        }
        ITransition nTransistion = builder.build();
        return new Nfa(nTransistion, nInitialState, new HashSet<>(Arrays.asList(nFinalState)));
    }

    public static Nfa fromCharacter(Character ch) {
        State nInitialState = new State();
        State nFinalState = new State();
        TransistionBuilder builder = new TransistionBuilder();
        builder.add(nInitialState, ch, Arrays.asList(nFinalState));
        ITransition nTransistion = builder.build();
        return new Nfa(nTransistion, nInitialState, new HashSet<>(Arrays.asList(nFinalState)));
    }
}
