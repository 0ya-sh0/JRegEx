package com.example.automata;

import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class EpsilonTransistion extends Transistion {
    Map<State, Set<State>> etable;
    EpsilonTransistionProcessor processor;

    public EpsilonTransistion(Map<Pair<State, Character>, Set<State>> table, Map<State, Set<State>> etable) {
        super(table);
        this.etable = etable;
        this.processor = new EpsilonTransistionProcessor(etable);
    }

    @Override
    public Set<State> nextStates(Set<State> currentStates, Character symbol) {
        currentStates = processor.process(currentStates);
        currentStates = super.nextStates(currentStates, symbol);
        return processor.process(currentStates);
    }

    @Override
    public void addInto(TransistionBuilder t) {
        super.addInto(t);
        t.addEpsilon(etable);
    }

    @Override
    public Set<State> currentStates(Set<State> currentState) {
        return processor.process(currentState);
    }

}
