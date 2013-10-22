package Sorting;

public class RyanSort {

	public static int[] array = {15,6,9,11,12,0,2,5};

	public static void main(String[] args) {

		for (int i : array) {

			System.out.print(i + ", ");
		}

		System.out.println("");

		sort(array);

		for (int i : array) {

			System.out.print(i + ", ");
		}

	}

	public static void sort(int[] a) {

		for (int i = 0; i < a.length; i++) {

			for (int j = i + 1; j < a.length; j++) {

				if (a[i] > a[j]) {// switch a & j
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;

				}

			}

		}

	}
}
