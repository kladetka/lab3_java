import java.io.*;
import java.util.Random;

class MatrixWriter implements AutoCloseable {
    private final BufferedWriter writer;

    public MatrixWriter(String fileName) throws IOException {
        writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void write(int[][] matrix) throws IOException {
        for (int[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                writer.write(Integer.toString(row[i]));
                if (i < row.length - 1) writer.write(" ");
            }
            writer.newLine();
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}

class MatrixReader implements AutoCloseable {
    private final BufferedReader reader;

    public MatrixReader(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public int[][] read(int rows, int cols) throws IOException {
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("Недостатньо рядків у файлі.");
            }

            String[] values = line.trim().split("\\s+");
            if (values.length != cols) {
                throw new IOException("Неправильна кількість елементів у рядку " + (i + 1));
            }

            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(values[j]);
            }
        }

        return matrix;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

public class Task1Matrix {
    public static void main(String[] args) {
        final int wagons = 10;
        final int seats = 20;
        final String fileName = "train.txt";

        int[][] train = new int[wagons][seats];
        Random random = new Random();

        // Генерація даних: 0 — вільне місце, 1 — проданий квиток.
        for (int i = 0; i < wagons; i++) {
            for (int j = 0; j < seats; j++) {
                train[i][j] = random.nextInt(2);
            }
        }

        try (MatrixWriter writer = new MatrixWriter(fileName)) {
            writer.write(train);
            System.out.println("Матрицю записано у файл " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
            return;
        }

        try (MatrixReader reader = new MatrixReader(fileName)) {
            int[][] restoredTrain = reader.read(wagons, seats);

            System.out.println("\nМатриця місць у потязі:");
            printMatrix(restoredTrain);

            System.out.println("\nПовністю зайняті вагони:");
            boolean found = false;

            for (int i = 0; i < wagons; i++) {
                boolean full = true;

                for (int j = 0; j < seats; j++) {
                    if (restoredTrain[i][j] == 0) {
                        full = false;
                        break;
                    }
                }

                if (full) {
                    System.out.println("Вагон №" + (i + 1));
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Повністю зайнятих вагонів немає.");
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
