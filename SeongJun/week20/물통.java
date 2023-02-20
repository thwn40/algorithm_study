package SeongJun.week20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

/**
 A->B
 A->C
 B->A
 B->C
 C->A
 C->B
 */
public class 물통 {
	static Scanner sc = new Scanner(System.in);
	static final int maxA = sc.nextInt();
	static final int maxB = sc.nextInt();
	static final int maxC = sc.nextInt();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		HashMap<Buckets, Boolean> visited = new HashMap<>();

		Queue<Buckets> queue = new LinkedList<>();

		queue.offer(new Buckets(0, 0, maxC));
		visited.put(new Buckets(0, 0, maxC), true);

		while (!queue.isEmpty()) {
			Buckets current = queue.poll();

			//A ->B
			Buckets next = getBuckets(maxB, current);
			int nowBQuantity;
			int nowCQuantity;
			int nowAQuantity;
			if (!visited.getOrDefault(next, false)) {
				visited.put(next, true);
				queue.offer(next);
			}

			//A ->C
			nowAQuantity = current.nowQuantityA;
			nowBQuantity = current.nowQuantityB;
			nowCQuantity = current.nowQuantityC;

			if (current.nowQuantityA + current.nowQuantityC <= maxC) {
				nowAQuantity = 0;
				nowCQuantity = current.nowQuantityA + current.nowQuantityC;
			} else {
				nowAQuantity = current.nowQuantityA - maxC + current.nowQuantityC;
				nowCQuantity = maxC;
			}
			next = new Buckets(nowAQuantity, nowBQuantity, nowCQuantity);
			if (!visited.getOrDefault(next, false)) {
				visited.put(next, true);
				queue.offer(next);
			}

			//B ->A
			nowAQuantity = current.nowQuantityA;
			nowBQuantity = current.nowQuantityB;
			nowCQuantity = current.nowQuantityC;
			if (current.nowQuantityB + current.nowQuantityA <= maxA) {
				nowBQuantity = 0;
				nowAQuantity = current.nowQuantityB + current.nowQuantityA;
			} else {
				nowBQuantity = current.nowQuantityB - maxA + current.nowQuantityA;
				nowAQuantity = maxA;

			}
			next = new Buckets(nowAQuantity, nowBQuantity, nowCQuantity);
			if (!visited.getOrDefault(next, false)) {
				visited.put(next, true);
				queue.offer(next);
			}

			//B ->C
			nowAQuantity = current.nowQuantityA;
			nowBQuantity = current.nowQuantityB;
			nowCQuantity = current.nowQuantityC;

			if (current.nowQuantityB + current.nowQuantityC <= maxC) {
				nowBQuantity = 0;
				nowCQuantity = current.nowQuantityB + current.nowQuantityC;
			} else {

				nowBQuantity = current.nowQuantityB - maxC + current.nowQuantityC;

				nowCQuantity = maxC;

			}
			next = new Buckets(nowAQuantity, nowBQuantity, nowCQuantity);
			if (!visited.getOrDefault(next, false)) {
				visited.put(next, true);
				queue.offer(next);
			}

			//C ->A
			nowAQuantity = current.nowQuantityA;
			nowBQuantity = current.nowQuantityB;
			nowCQuantity = current.nowQuantityC;

			if (current.nowQuantityC + current.nowQuantityA <= maxA) {
				nowCQuantity = 0;
				nowAQuantity = current.nowQuantityC + current.nowQuantityA;
			} else {
				nowCQuantity = current.nowQuantityC - maxA + current.nowQuantityA;
				nowAQuantity = maxA;
			}
			next = new Buckets(nowAQuantity, nowBQuantity, nowCQuantity);
			if (!visited.getOrDefault(next, false)) {
				visited.put(next, true);
				queue.offer(next);
			}

			//C ->B
			nowAQuantity = current.nowQuantityA;
			nowBQuantity = current.nowQuantityB;
			nowCQuantity = current.nowQuantityC;

			if (current.nowQuantityC + current.nowQuantityB <= maxB) {
				nowCQuantity = 0;
				nowBQuantity = current.nowQuantityC + current.nowQuantityB;
			} else {
				nowCQuantity = current.nowQuantityC - maxB + current.nowQuantityB;
				nowBQuantity = maxB;
			}
			next = new Buckets(nowAQuantity, nowBQuantity, nowCQuantity);
			if (!visited.getOrDefault(next, false)) {
				visited.put(next, true);
				queue.offer(next);
			}
		}

		HashSet<Integer> answer = new HashSet<>();
		for (Buckets buckets : visited.keySet()) {
			if (buckets.nowQuantityA == 0) {
				answer.add(buckets.nowQuantityC);
			}

		}

		ArrayList<Integer> integers = new ArrayList<>(answer);
		Collections.sort(integers);
		for (Integer integer : integers) {
			System.out.print(integer + " ");
		}

	}

	private static Buckets getBuckets(int maxB, Buckets current) {
		int nowAQuantity = current.nowQuantityA;
		int nowBQuantity = current.nowQuantityB;
		int nowCQuantity = current.nowQuantityC;

		if (current.nowQuantityA + current.nowQuantityB <= maxB) {
			nowAQuantity = 0;
			nowBQuantity = current.nowQuantityA + current.nowQuantityB;
		} else {
			nowAQuantity = current.nowQuantityA - maxB + current.nowQuantityB;
			nowBQuantity = maxB;
		}
		Buckets next = new Buckets(nowAQuantity, nowBQuantity, nowCQuantity);
		return next;
	}

	public static class Buckets {

		private Integer nowQuantityA;
		private Integer nowQuantityB;
		private Integer nowQuantityC;

		public Buckets(Integer nowQuantityA, Integer nowQuantityB, Integer nowQuantityC) {
			this.nowQuantityA = nowQuantityA;
			this.nowQuantityB = nowQuantityB;
			this.nowQuantityC = nowQuantityC;
		}

		@Override
		public String toString() {
			return "Buckets{" + "A=" + nowQuantityA + ", B=" + nowQuantityB + ", C=" + nowQuantityC + '}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Buckets buckets = (Buckets)o;
			return Objects.equals(nowQuantityA, buckets.nowQuantityA) && Objects.equals(nowQuantityB,
				buckets.nowQuantityB) && Objects.equals(nowQuantityC, buckets.nowQuantityC);
		}

		@Override
		public int hashCode() {
			return Objects.hash(nowQuantityA, nowQuantityB, nowQuantityC);
		}
	}
}
