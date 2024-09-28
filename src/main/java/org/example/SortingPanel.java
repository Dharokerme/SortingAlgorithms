package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingPanel extends JPanel {
    private int selectedIndex1 = -1;
    private int selectedIndex2 = -1;
    private int numComparisons = 0;
    private int numSwaps = 0;
    private int numIterations = 0;
    private long startTime;
    private long endTime;

    private int[] array;
    private static final int ARRAY_SIZE = 50;
    private static final int DELAY = 1;

    private SortingVisualizer visualizer;

    // Enum para los tipos de algoritmo de ordenamiento
    public enum SortAlgorithm {
        BUBBLE_SORT,
        SELECTION_SORT
    }

    // Variable para almacenar el algoritmo seleccionado
    private SortAlgorithm selectedAlgorithm;

    public SortingPanel(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
        array = new int[ARRAY_SIZE];
        Random rand = new Random();

        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(400) + 50;
        }

        // Por defecto, seleccionamos el algoritmo Bubble Sort
        selectedAlgorithm = SortAlgorithm.SELECTION_SORT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        for (int i = 0; i < array.length; i++) {
            int x = i * (getWidth() / array.length);
            int y = getHeight() - array[i];
            int width = getWidth() / array.length;
            int height = array[i];

            if (i == selectedIndex1 || i == selectedIndex2) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillRect(x, y, width, height);
        }
    }

    // Metodo para cambiar el algoritmo seleccionado
    public void setAlgorithm(SortAlgorithm algorithm) {
        this.selectedAlgorithm = algorithm;
    }

    // Metodo para iniciar el algoritmo de ordenamiento seleccionado
    public void startSort() {
        new Thread(() -> {
            if (selectedAlgorithm == SortAlgorithm.BUBBLE_SORT) {
                bubbleSort();
            } else if (selectedAlgorithm == SortAlgorithm.SELECTION_SORT) {
                selectionSort();
            }
        }).start();
    }

    private void bubbleSort() {
        startTime = System.nanoTime();

        for (int i = 0; i < array.length - 1; i++) {
            numIterations++;
            for (int j = 0; j < array.length - i - 1; j++) {
                numIterations++;
                numComparisons++;
                visualizer.updateComparisons(numComparisons);
                visualizer.updateIterations(numIterations);

                selectedIndex1 = j;
                selectedIndex2 = j + 1;
                repaint();

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    numSwaps++;
                    visualizer.updateSwaps(numSwaps);
                    repaint();

                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        endTime = System.nanoTime();
        selectedIndex1 = -1;
        selectedIndex2 = -1;
        repaint();
        visualizer.updateTime((endTime - startTime) / 1_000_000);

        System.out.println("Número total de comparaciones: " + numComparisons);
        System.out.println("Número total de intercambios: " + numSwaps);
        System.out.println("Número total de iteraciones: " + numIterations);
        System.out.println("Tiempo total de ejecución (milisegundos): " + (endTime - startTime) / 1_000_000);
    }

    private void selectionSort() {
        startTime = System.nanoTime();

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            numIterations++;
            for (int j = i + 1; j < array.length; j++) {
                numComparisons++;
                visualizer.updateComparisons(numComparisons);
                visualizer.updateIterations(numIterations);

                selectedIndex1 = i;
                selectedIndex2 = j;
                repaint();

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                numSwaps++;
                visualizer.updateSwaps(numSwaps);
                repaint();

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        endTime = System.nanoTime();
        selectedIndex1 = -1;
        selectedIndex2 = -1;
        repaint();
        visualizer.updateTime((endTime - startTime) / 1_000_000);

        System.out.println("Número total de comparaciones: " + numComparisons);
        System.out.println("Número total de intercambios: " + numSwaps);
        System.out.println("Número total de iteraciones: " + numIterations);
        System.out.println("Tiempo total de ejecución (milisegundos): " + (endTime - startTime) / 1_000_000);
    }
}
