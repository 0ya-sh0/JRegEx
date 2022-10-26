package com.example.automata;

import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class EpsilonTransistion extends Transistion {
    Map<Integer, Set<Integer>> etable;
    EpsilonTransistionProcessor processor;

    public EpsilonTransistion(Map<Pair<Integer, Character>, Set<Integer>> table, Map<Integer, Set<Integer>> etable) {
        super(table);
        this.etable = etable;
        this.processor = new EpsilonTransistionProcessor(etable);
    }

    @Override
    public Set<Integer> nextStates(Integer currentState, Character symbol) {
        Set<Integer> currentStates = processor.process(currentState);
        currentStates = super.nextStates(currentStates, symbol);
        return processor.process(currentStates);
    }

    @Override
    public Set<Integer> nextStates(Set<Integer> currentStates, Character symbol) {
        currentStates = processor.process(currentStates);
        currentStates = super.nextStates(currentStates, symbol);
        return processor.process(currentStates);
    }
}
