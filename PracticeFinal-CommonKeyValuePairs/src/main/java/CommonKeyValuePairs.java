/*
 * File: CommonKeyValuePairs.java
 * ------------------------------
 */

import acm.program.*;
import java.util.*;

public class CommonKeyValuePairs extends ConsoleProgram {

	public void run() {
		HashMap<String,String> hashmap1 = new HashMap<String,String>();
		hashmap1.put("Alice", "Healthy");
		hashmap1.put("Mary", "Ecstatic");
		hashmap1.put("Bob", "Happy");
		hashmap1.put("Chuck", "Fine");
		hashmap1.put("Felix", "Sick");
		HashMap<String,String> hashmap2 = new HashMap<String,String>();
		hashmap2.put("Mary", "Ecstatic");
		hashmap2.put("Felix", "Healthy");
		hashmap2.put("Ricardo", "Superb");
		hashmap2.put("Tam", "Fine");
		hashmap2.put("Bob", "Happy");

		println("hashmap1:");
		println("Alice -> Healthy");
		println("Mary -> Ecstatic");
		println("Bob -> Happy");
		println("Chuck -> Fine");
		println("Felix -> Sick");

		println("hashmap2:");
		println("Mary -> Ecstatic");
		println("Felix -> Healthy");
		println("Ricardo -> Superb");
		println("Tam -> Fine");
		println("Bob -> Happy");

		println("Pairs in common:");
		println(commonKeyValuePairs(hashmap1, hashmap2));
	}

	/**
	 * Method: commonKeyValuePairs(map1, map2)
	 *
	 * Returns a count of the number of common key/value pairs in the
	 * two HashMaps that are passed in.
	 */
	public int commonKeyValuePairs(HashMap<String,String> map1,
				HashMap<String,String> map2) {
		int count = 0;

		// Get iterator over map1
		Iterator<String> it = map1.keySet().iterator();

		while (it.hasNext()) {
			// Get key from map1
			String key = it.next();

			// See if that keys exists in map2
			if (map2.containsKey(key)) {
				// Look up values associated with key in both maps
				String map1Value = map1.get(key);
				String map2Value = map2.get(key);

				// See if values are equal
				if (map2Value.equals(map1Value)) {
					count++;
				}
			}
		}
		return count;
	}

	/* Standard Java entry point */
	/* This method can be eliminated in most Java environments */
	public static void main(String[] args) {
		new CommonKeyValuePairs().start(args);
	}
}
