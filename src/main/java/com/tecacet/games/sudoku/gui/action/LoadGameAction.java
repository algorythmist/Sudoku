package com.tecacet.games.sudoku.gui.action;

import com.tecacet.games.sudoku.gui.SudokuController;
import com.tecacet.games.sudoku.io.FileSystemGameReader;
import com.tecacet.games.sudoku.io.GameReader;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

public class LoadGameAction extends AbstractAction {

    private JFileChooser fc;

    private final SudokuController controller;
    private final Component parent;
    private final GameReader reader;

    public LoadGameAction(SudokuController controller, Component parent) {
        super("Open Game");
        this.controller = controller;
        this.parent = parent;
        reader = new FileSystemGameReader();
        try {
            fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        } catch (SecurityException se) {
            // applets in general do not allow filesystem access
            this.setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            controller.loadGame(file.getAbsolutePath(), reader);
        }
    }

}
