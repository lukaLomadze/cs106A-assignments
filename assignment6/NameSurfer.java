
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	private JTextField input;
	private JLabel label;
	private JButton graph;
	private JButton clear;
	private NameSurferDataBase dataBase;
	private NameSurferGraph graphics;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		try {
			dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		graphics = new NameSurferGraph();

		initialization();
		add(graphics);

	}

	// initialization - this method initialize interactors
	private void initialization() {
		label = new JLabel("Name");
		add(label, SOUTH);
		input = new JTextField(10);
		add(input, SOUTH);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		add(graph, SOUTH);
		add(clear, SOUTH);
		input.addActionListener(this);
		addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			graphics.clear();
		} else {
			NameSurferEntry output = dataBase.findEntry(input.getText().toUpperCase());
			if (output != null) {
				graphics.addEntry(output);
			}

		}
		input.setText("");
	}
}
