import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

final class SelectSort {

    /** Private constructor to prevent instantiation. */
    private SelectSort() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Returns a sorted version of an array.
     * @return array
     * @param array Array input
     */
    public static int[] sort(final int[] array) {
        // Loop through each pass
        for (int pass = 0; pass < array.length - 1; pass++) {
            // Set min index
            int minIndex = pass;
            // Loop through each proceeding number
            for (int index = pass + 1; index < array.length; index++) {
                // Check if min index is bigger than current num
                if (array[index] < array[minIndex]) {
                    // Update minIndex
                    minIndex = index;
                }
            }
            // Swap min and pass elements
            int temp = array[minIndex];
            array[minIndex] = array[pass];
            array[pass] = temp;
        }

        // Return
        return array;
    }

    /**
     * Writes a sorted version of an array to a file.
     * @param array Array input
     * @param outputFile File name to write to
     */
    public static void writeToFile(final int[] array, final String outputFile) {
        // Write to file, or raise error
        try {
            // Create writer, write each line
            FileWriter writer = new FileWriter(outputFile);
            for (int num : array) {
                writer.write(num + System.lineSeparator());
            }
            // Close writer and inform user
            writer.close();
            System.out.println("Sorted numbers written to " + outputFile);
        } catch (IOException e) {
            // File write error
            System.out.println("Error writing to file: " + outputFile);
        }
    }

    /**
     * Reads a file and converts its contents into an array.
     * @param inputFile Input file
     * @return array
     */
    public static int[] getArray(final String inputFile) {
        try {
            // Create file, scanner and empty array list
            File file = new File(inputFile);
            Scanner fileScanner = new Scanner(file);

            ArrayList<Integer> numbers = new ArrayList<>();

            // Iterate through every line
            while (fileScanner.hasNextLine()) {
                // Read each line
                String line = fileScanner.nextLine();
                try {
                    // Add line to numbers
                    int lineInt = Integer.parseInt(line);
                    numbers.add(lineInt);
                } catch (NumberFormatException error) {
                    // String found, clear numbers
                    numbers.clear();
                    break;
                }
            }

            // Setup numbers array
            int[] numbersAsArray = new int[numbers.size()];
            for (int i = 0; i < numbersAsArray.length; i++) {
                numbersAsArray[i] = numbers.get(i);
            }
            fileScanner.close();
            return numbersAsArray;
        } catch (FileNotFoundException error) {
            // Error msg for file not found
            System.out.println("\nError: The file " + inputFile
                    + " was not found."
                    + " Please ensure it exists in the same directory.");
            return new int[0];
        }
    }

    public static void main(final String[] args) {
        // File names
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // Get array, sort and write to file
        int[] array = getArray(inputFile);
        int[] sorted = sort(array);
        writeToFile(sorted, outputFile);
    }
}
