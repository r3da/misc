package org.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SumGenerator {

	public static void main(String[] args) {
		int n = 6;
		Set<List<Integer>> result = computeSums(n);

		System.out.println(result);
	}

	private static Set<List<Integer>> computeSums(int n) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		if (n == 1) {
			result.add(createListWithOneElement(n));
		} else {
			Set<List<Integer>> inductionBase = computeSums(n - 1);
			for (List<Integer> elt : inductionBase) {
				result.add(addOneToTail(elt));
				result.addAll(addOneToEachElement(elt));
			}
		}
		result = removeDuplicates(result);
		return result;
	}

	private static Set<List<Integer>> removeDuplicates(Set<List<Integer>> set) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			List<Integer> list = (List<Integer>) iterator.next();
			List<Integer> sortedList = new ArrayList<Integer>(list);
			Collections.sort(sortedList);
			result.add(sortedList);
		}
		return result;
	}

	private static List<Integer> createListWithOneElement(Integer elt) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(elt);
		return result;
	}

	private static List<Integer> addOneToTail(List<Integer> elt) {
		ArrayList<Integer> sum = new ArrayList<Integer>(elt);
		sum.add(1);
		return sum;
	}

	private static Set<List<Integer>> addOneToEachElement(List<Integer> elt) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		Integer[] sum = elt.toArray(new Integer[0]);
		Arrays.sort(sum);
		for (int i = 0; i < sum.length; i++) {
			ArrayList<Integer> q = new ArrayList<Integer>();
			for (int j = 0; j < sum.length; j++) {
				if (i == j) {
					q.add((Integer)sum[j] + 1);
				} else {
					q.add((Integer)sum[j]);
				}
			}
			result.add(q);
		}
		return result;
	}

}
