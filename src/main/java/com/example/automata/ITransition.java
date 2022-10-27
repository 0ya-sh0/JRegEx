package com.example.automata;

import java.util.Set;

public interface ITransition {

    public Set<State> currentStates(Set<State> currentState);

    public Set<State> nextStates(State currentState, Character symbol);

    public Set<State> nextStates(Set<State> currentStates, Character symbol);

    public void addInto(TransistionBuilder t);

    public void printDot(StringBuilder sbr);
}
