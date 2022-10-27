package com.example.parser;

import com.example.automata.Nfa;

enum ExprType {
    OR,
    AND,
    STAR,
    SYMBOL
}

public abstract class Expr {
    ExprType type;

    Expr(ExprType t) {
        type = t;
    }

    public abstract Nfa toNfa();
}

class EOr extends Expr {
    Expr left, right;

    EOr(Expr l, Expr r) {
        super(ExprType.OR);
        left = l;
        right = r;
    }

    public String toString() {
        return left.toString() + "|" + right.toString();
    }

    @Override
    public Nfa toNfa() {
        return Nfa.or(left.toNfa(), right.toNfa());
    }
}

class EAnd extends Expr {
    Expr left, right;

    EAnd(Expr l, Expr r) {
        super(ExprType.AND);
        left = l;
        right = r;
    }

    public String toString() {
        return left.toString() + right.toString();
    }

    @Override
    public Nfa toNfa() {
        return Nfa.and(left.toNfa(), right.toNfa());
    }
}

class EStar extends Expr {
    Expr expr;

    EStar(Expr e) {
        super(ExprType.STAR);
        expr = e;
    }

    public String toString() {
        return "(" + expr + ")*";
    }

    @Override
    public Nfa toNfa() {
        return Nfa.star(expr.toNfa());
    }
}

class ESymbol extends Expr {
    Character value;

    ESymbol(Character ch) {
        super(ExprType.SYMBOL);
        value = ch;
    }

    public String toString() {
        return value.toString();
    }

    @Override
    public Nfa toNfa() {
        return Nfa.fromCharacter(value);
    }
}
