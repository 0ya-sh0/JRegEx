package com.example.parser;

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
}
