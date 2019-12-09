package com.C0Compiler.JavaCC;

public class SymbolItem {
    public enum SymbolType
    {
        intSym,
        functionSym
    }

    private String name;
    private SymbolType type;
    private int val;
    private int level;
    private int adr;
    private int size;
    private int returnType;

    public SymbolItem(SymbolType type)
    {
        this.type = type;
        this.name = "";
        this.val = 0;
        this.level = 0;
        this.adr = 0;
        this.size = 0;
        this.returnType = 0;
    }

    public String getName() {
        return name;
    }

    public SymbolType getType() {
        return type;
    }

    public int getVal() {
        return val;
    }

    public int getLevel() {
        return level;
    }

    public int getAdr() {
        return adr;
    }

    public int getSize() {
        return size;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAdr(int adr) {
        this.adr = adr;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }
}
