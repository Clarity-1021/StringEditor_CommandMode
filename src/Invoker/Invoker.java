package Invoker;

import Commands.ICommand;

public class Invoker {
    private ICommand showLine;
    private ICommand addFirst;
    private ICommand addLast;
    private ICommand deleteFirst;
    private ICommand deleteLast;
    private ICommand listCommands;
    private ICommand undo;
    private ICommand redo;
    private ICommand defineMacro;
    private ICommand macro;

    public void setShowLine(ICommand showLine) {
        this.showLine = showLine;
    }

    public void setAddFirst(ICommand addFirst) {
        this.addFirst = addFirst;
    }

    public void setAddLast(ICommand addLast) {
        this.addLast = addLast;
    }

    public void setDeleteFirst(ICommand deleteFirst) {
        this.deleteFirst = deleteFirst;
    }

    public void setDeleteLast(ICommand deleteLast) {
        this.deleteLast = deleteLast;
    }

    public void setListCommands(ICommand listCommands) {
        this.listCommands = listCommands;
    }

    public void setUndo(ICommand undo) {
        this.undo = undo;
    }

    public void setRedo(ICommand redo) {
        this.redo = redo;
    }

    public void setDefineMacro(ICommand defineMacro) {
        this.defineMacro = defineMacro;
    }

    public void setMacro(ICommand macro) {
        this.macro = macro;
    }


    public void showLine() {
        showLine.execute();
    }

    public void addFirst() {
        addFirst.execute();
    }

    public void addLast() {
        addLast.execute();
    }

    public void deleteFirst() {
        deleteFirst.execute();
    }

    public void deleteLast() {
        deleteLast.execute();
    }

    public void listCommands() {
        listCommands.execute();
    }

    public void undo() {
        undo.execute();
    }

    public void redo() {
        redo.execute();
    }

    public void defineMacro() {
        defineMacro.execute();
    }

    public void macro() {
        macro.execute();
    }
}
