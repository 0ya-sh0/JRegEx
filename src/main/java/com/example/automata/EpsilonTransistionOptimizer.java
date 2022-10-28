package com.example.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;
public class EpsilonTransistionOptimizer {
    State initial;
    Set<State> finalStates;
    Map<Pair<State, Character>, Set<State>> table;
    Map<State, Set<State>> etable;

    public EpsilonTransistionOptimizer(State initial, Set<State> finalStates,
            Map<Pair<State, Character>, Set<State>> table, Map<State, Set<State>> etable) {
        this.initial = initial;
        this.finalStates = finalStates;
        this.table = table;
        this.etable = etable;
    }

    public void optimize() {
        removeExtraStates1();
        removeExtraStates2();
        removeExtraStates3();
    }

    void removeExtraStates1() {
        Set<State> extraStates = findExtraStates1();
        for (State state : extraStates) {
            State incoming = findIncomingEpsilonState(state);
            Set<State> outgoing = findOutGoindStates(state);
            for (State out : outgoing) {
                etable.get(incoming).add(out);
            }
            etable.get(incoming).remove(state);
            etable.remove(state);
        }
    }

    void removeExtraStates2() {
        Set<State> extraStates = findExtraStates2();
        for (State stateToBeRemoved : extraStates) {
            Set<State> outgoingStates = findOutGoindStates(stateToBeRemoved);
            for (State srcState : etable.keySet()) {
                if (etable.get(srcState).contains(stateToBeRemoved)) {
                    etable.get(srcState).addAll(outgoingStates);
                    etable.get(srcState).remove(stateToBeRemoved);
                }
            }
            etable.remove(stateToBeRemoved);
        }
    }

    void removeExtraStates3() {
        Set<State> extraStates = findExtraStates3();
        for (State stateToBeRemoved : extraStates) {
            State outgoingEpsilon = (State) etable.get(stateToBeRemoved).toArray()[0];
            etable.remove(stateToBeRemoved);
            for (Pair<State, Character> key : table.keySet()) {
                if (table.get(key).contains(stateToBeRemoved)) {
                    table.get(key).add(outgoingEpsilon);
                    table.get(key).remove(stateToBeRemoved);
                }
            }

        }
    }

    State findIncomingEpsilonState(State s) {
        for (State src : etable.keySet()) {
            if (etable.get(src).contains(s)) {
                return src;
            }
        }
        return null;
    }

    Set<State> findOutGoindStates(State s) {
        if (etable.containsKey(s)) {
            return etable.get(s);
        }
        return new HashSet<>();
    }

    Set<State> findExtraStates1() {
        Set<State> states = findAll();
        // state with no normal transistions
        states = removeWithNormalTransistions(states);
        // single epsilon into itself
        states = removeWithMultipleEpsilonInto(states);
        // not a initial or final state
        states.remove(initial);
        states.removeAll(finalStates);

        return states;
    }

    Set<State> findExtraStates2() {
        Set<State> states = findAll();
        states.remove(initial);
        states.removeAll(finalStates);
        states = removeWithNormalTransistions(states);
        states = keepEpsilonOutDegree1(states);

        return states;
    }

    Set<State> findExtraStates3() {
        Set<State> states = findAll();
        states.remove(initial);
        states.removeAll(finalStates);
        states = removeNormalOutgoing(states);
        states = keepEpsilonOutDegree1(states);
        states = removeAnyIncomingEpsilon(states);
        return states;
    }

    private Set<State> removeNormalOutgoing(Set<State> states) {
        for (Pair<State, Character> key : table.keySet()) {
            if (table.get(key).size() != 0) {
                states.remove(key.getValue0());
            }
        }
        return states;
    }

    private Set<State> removeAnyIncomingEpsilon(Set<State> states) {
        Set<State> result = new HashSet<>(states);
        for (State srcState : etable.keySet()) {
            result.removeAll(etable.get(srcState));
        }
        return result;
    }

    Set<State> keepEpsilonOutDegree1(Set<State> states) {
        HashSet<State> result = new HashSet<>();
        for (State state : states) {
            if (etable.containsKey(state) && etable.get(state).size() == 1) {
                result.add(state);
            }
        }
        return result;
    }

    private Set<State> removeWithMultipleEpsilonInto(Set<State> states) {
        HashMap<State, Integer> inDegree = new HashMap<>();
        for (State state : states) {
            inDegree.put(state, Integer.valueOf(0));
        }

        for (State src : etable.keySet()) {
            for (State dest : etable.get(src)) {
                Integer i = inDegree.get(dest);
                if (i != null) {
                    inDegree.put(dest, i + 1);
                }
            }
        }

        for (State state : inDegree.keySet()) {
            if (inDegree.get(state) != 1)
                states.remove(state);
        }
        return states;
    }

    Set<State> removeWithNormalTransistions(Set<State> input) {
        for (Pair<State, Character> key : table.keySet()) {
            if (table.get(key).size() != 0) {
                input.remove(key.getValue0());
            }
            for (State state : table.get(key)) {
                input.remove(state);
            }
        }
        return input;
    }

    Set<State> findAll() {
        Set<State> result = new HashSet<>();
        for (Pair<State, Character> key : table.keySet()) {
            result.add(key.getValue0());
            for (State state : table.get(key)) {
                result.add(state);
            }
        }
        for (State src : etable.keySet()) {
            result.add(src);
            for (State state : etable.get(src)) {
                result.add(state);
            }
        }
        return result;
    }

}
