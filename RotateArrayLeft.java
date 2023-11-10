// This class provides two simple functions to rotate an integer array to give different options to the caller.
// The first rotateLeft() is more CPU efficient.
// The second rotateInplace() is more memory efficient.

public class RotateArrayLeft {

	/**
	 * This function rotates the given array of integers to the left by the
	 * specified number of positions. If the number of positions to rotate is
	 * greater than the array size, only to rotate the modulo of the array size. If
	 * the number of positions is negative, throws an IllegalArgumentException.
	 * 
	 * @param intAry   integer array to be rotated, array is not modified
	 * @param rotateBy number of positions to rotate left.
	 * @return array of integers containing the rotated elements.
	 */
	// This function provides a solution that is more CPU efficient.
	public static int[] rotateLeft(int[] intAry, int rotateBy) {
		if (rotateBy < 0) {
			throw new IllegalArgumentException(
					"Expect a non-negative integer for roateBy argument, Received: " + rotateBy);
		}

		int aryLen = intAry.length;
		int rotateAmt = rotateBy % aryLen;
		// allocate a new array for storing the rotated array.
		int[] rotatedAry = new int[aryLen];
		int idx = 0; // index to keep track of where we are

		// copy integer value starting from the position rotateAmt into first slot, loop
		// till the end of array
		for (int i = rotateAmt; i < aryLen; ++i) {
			rotatedAry[idx] = intAry[i];
			++idx;
		}

		// continue copying from the beginning of the array, loop till rotateAmt.
		for (int i = 0; i < rotateAmt; ++i) {
			rotatedAry[idx] = intAry[i];
			++idx;
		}

		return rotatedAry;
	}

	/**
	 * This function rotates the given array of integers to the left by the
	 * specified number of positions. If the number of positions to rotate is
	 * greater than the array size, only to rotate the modulo of the array size. If
	 * the number of positions is negative, throws an IllegalArgumentException.
	 * 
	 * @param intAry   integer array to be rotated. It is overridden with the
	 *                 rotated values.
	 * @param rotateBy number of positions to rotate left.
	 */
	// This function provides a solution that is more memory efficient.
	public static void rotateInplace(int[] intAry, int rotateBy) {
		if (rotateBy < 0) {
			throw new IllegalArgumentException(
					"Expect a non-negative integer for roateBy argument, Received: " + rotateBy);
		}

		int aryLen = intAry.length;
		int rotateAmt = rotateBy % aryLen;
		int shiftCnt = 0;

		// loop for number of position to rotate
		while (shiftCnt < rotateAmt) {
			int tmp = intAry[0]; // save the first element for the end.

			for (int i = 0; i < aryLen - 1; ++i) {
				intAry[i] = intAry[i + 1]; // shift each element by one.
			}
			intAry[aryLen - 1] = tmp; // taking care of the wrapping
			++shiftCnt;
		}
	}

	// print array content in a single line, skip comma for the last element and end
	// with new line.
	public static void prettyPrintAry(int[] intAry) {
		for (int i = 0; i < intAry.length; ++i) {
			if (i < intAry.length - 1) {
				System.out.print(intAry[i] + ",");
			} else {
				System.out.println(intAry[i]);
			}
		}
	}

	// Test Driver that can take an input array to provide more testing flexibility.
	//
	// Usage: No argument: run the test cases described in the problem statement.
	// One argument (must be integer): rotate the problem statement array by the
	// specified amount.
	// More than one argument: convert the first (n - 1) argument into an array, use
	// the last argument as rotating amount.

	// output without input parameters
	//	Original:   1,2,3,4,5,6,7
	//	Rotated(2): 3,4,5,6,7,1,2
	//	Rotated(8): 2,3,4,5,6,7,1
	//	==Calling more memory efficient function, the original array is overridden when done.
	//	Original:   1,2,3,4,5,6,7
	//	Rotated(2): 3,4,5,6,7,1,2
	
	public static void main(String[] args) {

		// Populate default array based on problem statement
		int[] testAry = { 1, 2, 3, 4, 5, 6, 7 };
		int rotateBy = 0;
		int argCount = args.length;

		if (argCount > 0) {
			// handle user input for more flexible testing.
			// Take the last argument as the rotating amount,
			String lastArgument = args[argCount - 1];
			try {
				rotateBy = Integer.parseInt(lastArgument); // note: only handle up to the size of int32. Number bigger
															// than that is consider invalid.
				if (rotateBy < 0) {
					System.err.println("Invalid input in the last argument. Expect non-negative integer, Received: "
							+ lastArgument);
					System.exit(-1);
				}
			} catch (Exception e) {
				System.err.println("Invalid input in the last argument. Expect integer, Received: " + lastArgument);
				System.exit(-1);
			}

			// array elements are specified, convert and store them in the test array
			if (argCount > 1) {
				// Convert the first (n - 1) arguments to the test array.
				testAry = new int[argCount - 1];
				for (int i = 0; i < argCount - 1; ++i) {
					try {
						testAry[i] = Integer.parseInt(args[i]);
					} catch (Exception e) {
						System.err.println(
								"Invalid input. Expect integer for argument " + (i + 1) + ", Received: " + args[i]);
						System.exit(-1);
					}
				}
			}
		}

		// Now the test array is populated, ready to print original and the rotated
		// array.
		System.out.print("Original:   ");
		prettyPrintAry(testAry);

		if (argCount == 0) { // No argument specified, do the test in the problem statement

			rotateBy = 2;
			System.out.print("Rotated(" + rotateBy + "): ");
			prettyPrintAry(rotateLeft(testAry, rotateBy));

			rotateBy = 8;
			System.out.print("Rotated(" + rotateBy + "): ");
			prettyPrintAry(rotateLeft(testAry, rotateBy));

		} else { // handle user inputs (plus the test array if specified)
			System.out.print("Rotated(" + rotateBy + "): ");
			prettyPrintAry(rotateLeft(testAry, rotateBy));
		}

		System.out.println("==Calling more memory efficient function, the original array is overridden when done.");
		System.out.print("Original:   ");
		prettyPrintAry(testAry);
		rotateBy = 2;

		rotateInplace(testAry, rotateBy);
		System.out.print("Rotated(" + rotateBy + "): ");
		prettyPrintAry(testAry);
	}
}
