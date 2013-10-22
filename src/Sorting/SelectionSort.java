package Sorting;

public class SelectionSort {

	public static void sort(int[] a) {
		int index, temp;

		for (int i = 0; i < a.length - 1; i++) {
			index = i;
			for (int j = i; j < a.length - 1; j++) {
				if (a[j] < a[index]) {
					index = j;
				}
			}

			temp = a[index];
			a[index] = a[i];
			a[i] = temp;

		}

	}
}
