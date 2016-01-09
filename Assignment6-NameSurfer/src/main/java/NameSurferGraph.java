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

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}

	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entryList.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		if (!entryList.contains(entry)) entryList.add(entry);
		update();
	}



	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawVerticalLines();
		drawHorizontalLines();
		drawDecadeLabels();
		drawDbEntires();
	}

	private void drawDbEntires() {
		Iterator<NameSurferEntry> it = entryList.iterator();
		while (it.hasNext()) {
			drawOneDbEntry(it.next());
		}
	}


	private void drawOneDbEntry(NameSurferEntry entry) {
		Color color = setColor(entryList.indexOf(entry));
		for (int i = 0; i < NDECADES - 1; i++ ){
			double x0 = i * getWidth() / 11;
			double y0 = (getHeight() - 2 * GRAPH_MARGIN_SIZE) / 1000.0 * entry.getRank(i) + GRAPH_MARGIN_SIZE;
			if (entry.getRank(i) == 0) y0 = getHeight() - GRAPH_MARGIN_SIZE;
			double x1 = (i + 1) * getWidth() / 11;
			double y1 = (getHeight() - 2 * GRAPH_MARGIN_SIZE) / 1000.0 * entry.getRank(i + 1) + GRAPH_MARGIN_SIZE;
			if (entry.getRank(i + 1) == 0) y1 = getHeight() - GRAPH_MARGIN_SIZE;
			GLine line = new GLine(x0, y0, x1, y1);
			line.setColor(color);
			add(line);

			String nameAndRank = entry.getName() + " " + entry.getRank(i);
			if (entry.getRank(i) == 0) nameAndRank = entry.getName() + "*";
			GLabel label = new GLabel(nameAndRank, x0, y0);
			label.setColor(color);
			add(label);
			if (i == NDECADES - 2) {
				nameAndRank = entry.getName() + " " + entry.getRank(i + 1);
				if (entry.getRank(i + 1) == 0) nameAndRank = entry.getName() + "*";
				label = new GLabel(nameAndRank, x1, y1);
				label.setColor(color);
				add(label);
			}
		}
	}

	private Color setColor(int n) {
		switch (n % 4) {
		case  0: return Color.BLACK;
		case  1: return Color.RED;
		case  2: return Color.BLUE;
		case  3: return Color.MAGENTA;
		default: return Color.BLACK;
		}
	}

	private void drawDecadeLabels() {
		for (int i = 0; i < NDECADES; i++) {
			double x = i * getWidth() / NDECADES;
			String decadeLabelText = "" + (i * 10 + START_DECADE);
			GLabel decadeLabel = new GLabel(decadeLabelText, x, getHeight());
			add(decadeLabel);
		}
	}

	private void drawHorizontalLines() {
		GLine topHorizontalLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(topHorizontalLine);
		GLine bottomHorizontalLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(bottomHorizontalLine);
	}

	private void drawVerticalLines() {
		for (int i = 1; i <= NDECADES; i++) {
			double x0 = i * getWidth() / NDECADES;
			double x1 = x0;
			GLine line = new GLine(x0, 0 , x1, getWidth());
			add(line);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

	private ArrayList<NameSurferEntry> entryList = new ArrayList<NameSurferEntry>();
}
