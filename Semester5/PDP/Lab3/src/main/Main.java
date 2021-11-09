package main;

import entity.Matrix;
import operation.*;
import strategy.ThreadStrategy;
import strategy.Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int ROW_A = 100;
    private static final int COLUMN_A = 100;
    private static final int ROW_B = 100;
    private static final int COLUMN_B = 100;
    private static final int THREADS = 4;
    private static final ThreadStrategy GENERATION_STRATEGY = ThreadStrategy.CLASSIC;
    private static final Threads GENERATION_THREAD = Threads.ROW;

    public static void main(String[] args) {
        Matrix matrixA = new Matrix(ROW_A, COLUMN_A);
        Matrix matrixB = new Matrix(ROW_B, COLUMN_B);

        matrixA.populate();
        System.out.println(matrixA);
        matrixB.populate();
        System.out.println(matrixB);

//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < matrixA.getRow(); i++) {
//            for (int j = 0; j < matrixB.getColumn(); j++) {
//                stringBuilder.append(ComputeSingleElement.computeElement(matrixA, matrixB, i, j)).append(" ");
//            }
//            stringBuilder.append("\n");
//        }
//        System.out.println(stringBuilder);

        if (matrixA.getRow() == matrixB.getColumn()) {
            Matrix matrixC = new Matrix(matrixA.getRow(), matrixB.getColumn());
            long startTime = System.nanoTime();
            createGenerationStrategy(matrixA, matrixB, matrixC);
            long stopTime = System.nanoTime();
            double totalTime = ((double) stopTime - (double) startTime) / 1_000_000_000.0;
            System.out.println("Running time: " + totalTime + "s");
        } else {
            System.err.println("Invalid matrices to multiply!");
        }
    }

    private static MatrixThread createRowThread(Matrix matrixA, Matrix matrixB, Matrix matrixC, int index) {
        List<Integer> indexes = getStartingIndexesAndNoOfElement(matrixC, index);
        return new RowThread(indexes.get(0), indexes.get(1), indexes.get(2), matrixA, matrixB, matrixC);
    }

    private static MatrixThread createColumnThread(Matrix matrixA, Matrix matrixB, Matrix matrixC, int index) {
        List<Integer> indexes = getStartingIndexesAndNoOfElement(matrixC, index);
        return new ColumnThread(indexes.get(0), indexes.get(1), indexes.get(2), matrixA, matrixB, matrixC);
    }

    private static MatrixThread createKthThread(Matrix matrixA, Matrix matrixB, Matrix matrixC, int index) {
        int numberOfElementsInMatrix = matrixC.getRow() * matrixC.getColumn();
        int setSize = numberOfElementsInMatrix / THREADS;
        if (index < numberOfElementsInMatrix % THREADS) {
            setSize++;
        }
        int startingLine = index / matrixC.getColumn();
        int startingColumn = index % matrixC.getColumn();
        return new KThread(startingLine, startingColumn, setSize, matrixA, matrixB, matrixC, THREADS);
    }

    private static List<Integer> getStartingIndexesAndNoOfElement(Matrix matrixC, int index) {
        List<Integer> result = new ArrayList<>();
        int noOfElements = matrixC.getRow() * matrixC.getColumn();
        int setSize = noOfElements / THREADS;

        result.add(setSize * index / matrixC.getRow());
        result.add(setSize * index % matrixC.getRow());

        if (index == THREADS - 1) {
            setSize += noOfElements % THREADS;
        }
        result.add(setSize);
        return result;
    }

    private static void createGenerationStrategy(Matrix matrixA, Matrix matrixB, Matrix matrixC) {
        switch (GENERATION_STRATEGY) {
            case THREAD_POOL:
                createThreadPool(matrixA, matrixB, matrixC);
                break;
            case CLASSIC:
                createEachThreadForEachTask(matrixA, matrixB, matrixC);
                break;
            default:
                System.err.println("Bad generation strategy!");
                break;
        }
    }

    private static void createEachThreadForEachTask(Matrix matrixA, Matrix matrixB, Matrix matrixC) {
        List<Thread> threads = new ArrayList<>();
        switch (GENERATION_THREAD) {
            case ROW:
                for (int i = 0; i < THREADS; i++) {
                    threads.add(createRowThread(matrixA, matrixB, matrixC, i));
                }
                break;
            case COLUMN:
                for (int i = 0; i < THREADS; i++) {
                    threads.add(createColumnThread(matrixA, matrixB, matrixC, i));
                }
                break;
            case K:
                for (int i = 0; i < THREADS; i++) {
                    threads.add(createKthThread(matrixA, matrixB, matrixC, i));
                }
                break;
            default: {
                System.err.println("Bad thread for this generation!");
                break;
            }
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Matrix Result:");
        System.out.println(matrixC);
    }

    private static void createThreadPool(Matrix matrixA, Matrix matrixB, Matrix matrixC) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        switch (GENERATION_THREAD) {
            case ROW:
                for (int i = 0; i < THREADS; i++) {
                    executorService.submit((createRowThread(matrixA, matrixB, matrixC, i)));
                }
                break;
            case COLUMN:
                for (int i = 0; i < THREADS; i++) {
                    executorService.submit((createColumnThread(matrixA, matrixB, matrixC, i)));
                }
                break;
            case K:
                for (int i = 0; i < THREADS; i++) {
                    executorService.submit((createKthThread(matrixA, matrixB, matrixC, i)));
                }
                break;
            default: {
                System.err.println("Bad generation strategy!");
                break;
            }
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(300, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
            System.out.println("Matrix Result:");
            System.out.println(matrixC.toString());
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
