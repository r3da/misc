package org.problems;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class SumGenerator {

	public static void main(String[] args) {
		int n = 5;
		Set<Queue<Integer>> result = computeSums(n);

		System.out.println(result);
	}

	private static Set<Queue<Integer>> computeSums(int n) {
		Set<Queue<Integer>> result = new HashSet<Queue<Integer>>();
		if (n == 1) {
			result.add(createQueueWithOneElement(n));
		} else {
			Set<Queue<Integer>> inductionBase = computeSums(n - 1);
			for (Queue<Integer> elt : inductionBase) {
				result.add(addOneToTail(elt));
				result.addAll(addOneToEachElement(elt));
			}
		}
		return result;
	}

	private static Queue<Integer> createQueueWithOneElement(Integer elt) {
		Queue<Integer> result = new CustomQueue<Integer>();
		result.add(elt);
		return result;
	}

	private static Queue<Integer> addOneToTail(Queue<Integer> elt) {
		PriorityQueue<Integer> sum = new CustomQueue<Integer>(elt);
		sum.offer(1);
		return sum;
	}

	private static Set<Queue<Integer>> addOneToEachElement(Queue<Integer> elt) {
		Set<Queue<Integer>> result = new HashSet<Queue<Integer>>();
		Integer[] sum = elt.toArray(new Integer[0]);
		for (int i = 0; i < sum.length; i++) {
			CustomQueue<Integer> q = new CustomQueue<Integer>();
			for (int j = 0; j < sum.length; j++) {
				if (i == j) {
					q.offer((Integer)sum[j] + 1);
				} else {
					q.offer((Integer)sum[j]);
				}
			}
			result.add(q);
		}
		return result;
	}

	private static class CustomQueue<T> extends PriorityQueue<T>{
		public CustomQueue(Queue<T> elt) {
			super(elt);
		}

		public CustomQueue() {
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + toString().hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			return toString().equals(obj.toString());
		}
	}
}
