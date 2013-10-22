package AutoRepair;

import java.util.Arrays;
import java.util.Scanner;

public class AutoRepair {
	public static void main(String args[]) {
		// Create array of AutoRepairObjects

		AutoRepairObject[] repairs = new AutoRepairObject[7];

		repairs[0] = new AutoRepairObject("Oil Change", 29.99, 15);
		repairs[1] = new AutoRepairObject("Tire Rotation", 34.79, 25);
		repairs[2] = new AutoRepairObject("Brake Pads", 125.99, 45);
		repairs[3] = new AutoRepairObject("Oxygen Sensor", 238.71, 55);
		repairs[4] = new AutoRepairObject("Catalytic Converter", 1001.74, 60);
		repairs[5] = new AutoRepairObject("Ignition Coils", 213.16, 150);
		repairs[6] = new AutoRepairObject("Air Flow Sensor", 376.69, 105);

		// User selects sorting method
		Scanner scan = new Scanner(System.in);
		System.out.print("What do you want to sort by?\n1 by Des");
		System.out.print("cription\n2 by Price\n3 by Time\n");

		int menu = scan.nextInt();

		switch (menu) {
		case 1:
			bubbleSortDesc(repairs);
			break;
		case 2:
			insertionSortCost(repairs);
			break;
		case 3:
			selectionSortTime(repairs);
			break;
		default:
			System.out.print("Invalid Selection");

		}// end switch

		// Arrays.sort(repairs);

		// Output each object to the console

		System.out.printf("\n%-22s%10s%8s", "Description", "Cost", "Time");
		System.out.printf("\n%-22s%10s%8s\n", "-----------------", "--------",
				"------");

		for (int i = 0; i < repairs.length; i++)
			repairs[i].outputString();
	}// end main method

	public static void insertionSortCost(AutoRepairObject[] array) {

		int j;
		AutoRepairObject key;
		int i;

		for (j = 1; j < array.length; j++) {
			key = array[j];
			for (i = j - 1; (i >= 0) && (array[i].getCost() > key.getCost()); i--)

			{
				array[i + 1] = array[i];
			}
			array[i + 1] = key;
		}

	}// end insertionSortCost method

	public static void selectionSortTime(AutoRepairObject[] array) {

		int index;
		AutoRepairObject temp;

		for (int i = 0; i < array.length - 1; i++) {
			index = i;
			for (int j = i; j < array.length; j++) {
				if (array[j].getTime() < array[index].getTime()) {
					index = j;
				}
			}

			temp = array[index];
			array[index] = array[i];
			array[i] = temp;

		}

	}

	// end selectionSortTime method

	public static void bubbleSortDesc(AutoRepairObject[] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {

				if (array[i].getDesc().compareToIgnoreCase(array[j].getDesc()) > 0) {
					AutoRepairObject temp = array[i];
					array[i] = array[j];
					array[j] = temp;

				}

			}

		}

	}// end bubbleSortDesc method

}// end class