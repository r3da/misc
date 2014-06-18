package org.problems;

import java.util.ArrayList;
import java.util.List;

public class MatchingBraces {

	public static void main(String[] args) {
		int n = 4;
		List<String> matchingBraces = computeMatchingBraces(n);
		System.out.println("The result is: " + matchingBraces); 		
	}

	public static List<String> computeMatchingBraces(int n) {
		List<String> result = new ArrayList<String>();
		if (n < 0) return null;
		else if (n == 1) {
			result.add("()");
		} else {
			for (String elt : computeMatchingBraces(n - 1)) {
				result.add("(" + elt + ")");
				result.add("()" + elt);		
			}
		}
		return result;
	}
}
