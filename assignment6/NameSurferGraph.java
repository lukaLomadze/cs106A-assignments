
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
	private ArrayList<NameSurferEntry> enteredNames;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		enteredNames = new ArrayList<NameSurferEntry>();
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		enteredNames.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		if (!enteredNames.contains(entry)) {
			enteredNames.add(entry);
		}
		update();
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawDisplay();
		drawDiagrams();
	}

	// drawDiagrams - this method draws entire diagram
	private void drawDiagrams() {
		Color[] colors = { Color.black, Color.red, Color.green, Color.BLUE };
		for (int i = 0; i < enteredNames.size(); i++) {
			drawNames(enteredNames.get(i), colors[i % colors.length]);
			drawdiagramLines(enteredNames.get(i), colors[i % colors.length]);
		}
	}

	// drawdiagramLines this method draws rating for one name
	private void drawdiagramLines(NameSurferEntry person, Color colors) {
		for (int k = 1; k < NDECADES; k++) {
			int startX = getWidth() * (k - 1) / 11;
			int endX = getWidth() * k / 11;
			double startY = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * person.getRank(k - 1) / 1000.0;
			double endY = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * person.getRank(k) / 1000.0;
			if (person.getRank(k - 1) == 0) {
				startY = getHeight() - 20;
			}
			if (person.getRank(k) == 0) {
				endY = getHeight() - 20;
			}
			GLine line = new GLine(startX, startY, endX, endY);
			line.setColor(colors);
			add(line);
		}
	}

	// drawNames - this method draws names according to rating
	private void drawNames(NameSurferEntry person, Color colors) {
		for (int k = 1; k <= NDECADES; k++) {
			int x = getWidth() * (k - 1) / 11;
			double y = GRAPH_MARGIN_SIZE + (getHeight() - 2 * GRAPH_MARGIN_SIZE) * person.getRank(k - 1) / 1000.0;
			String rank = person.getRank(k - 1) + "";
			if (person.getRank(k - 1) == 0) {
				y = getHeight() - 20;
				rank = "*";
			}
			GLabel name = new GLabel(person.getName() + rank);
			name.setColor(colors);
			name.setFont("-12");
			add(name, x + 5, y - 5);
		}

	}

	// drawDisplay - this method draws initial screen
	private void drawDisplay() {
		for (int i = 0; i < NDECADES; i++) {
			int x = getWidth() * i / 11;
			drawLine(x, 0, x, getHeight());
			drawlabel(x, START_DECADE + i * 10);
		}
		drawLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		drawLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
	}

	// drawlabel - this method draws years
	private void drawlabel(int x, int decade) {
		GLabel label = new GLabel(decade + "");
		add(label, x + 5, getHeight() - (GRAPH_MARGIN_SIZE - label.getDescent()) / 2 + 3);
	}

	// drawLine - this method draws initial lines
	private void drawLine(int x1, int y1, int x2, int y2) {
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);

	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
