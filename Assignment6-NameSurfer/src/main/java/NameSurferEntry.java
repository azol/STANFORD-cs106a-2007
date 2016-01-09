/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		name = line.substring(0, line.indexOf(' '));

//		String numericDataLine = line.substring(line.indexOf(' ') + 1);
//		StringTokenizer tokenizer = new StringTokenizer(numericDataLine);
//		while (tokenizer.hasMoreTokens()) {
//			String token = tokenizer.nextToken();
//			ranks.add(Integer.parseInt(token));
//		}

		String numericDataLine = line.substring(line.indexOf(' ') + 1);
		StringTokenizer tokenizer = new StringTokenizer(numericDataLine);
		for (int i = 0; i < rank.length; i++) {
			String token = tokenizer.nextToken();
			rank[i] = Integer.parseInt(token);
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		//		return ranks.get(decade);
		//		return 0;
		return rank[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
//		String ranksString = "";
//		Iterator<Integer> it = ranks.iterator();
//		while (it.hasNext()) {
//			ranksString += it.next() + " ";
//		}
//		ranksString = ranksString.substring(0, ranksString.length() - 1); //removes last empty space
//		return name + " "  + "[" + ranksString + "]";

		// Or we could use for loop calling
		// ranks.get(i);

		String ranksString = "";

		for (int i = 0; i < rank.length; i++) {
			ranksString += rank[i] + " ";
		}
		ranksString = ranksString.substring(0, ranksString.length() - 1); //removes last empty space
		return name + " "  + "[" + ranksString + "]";

		// Or just
		// return name + " "  + rank/ranks;

	}

	private String name;
//	private ArrayList<Integer> ranks = new ArrayList<Integer>();
	private int[] rank = new int[NDECADES];
}
