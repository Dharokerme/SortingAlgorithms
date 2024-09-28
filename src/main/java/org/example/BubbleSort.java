package org.example;

public class BubbleSort implements SortAlgorithm {

    private static final int DELAY = 10;  // Retardo para la animación

    @Override
    public void sort(int[] array, SortingVisualizer visualizer, SortingPanel sortingPanel) {
        long startTime = System.nanoTime();
        int numComparisons = 0;
        int numSwaps = 0;
        int numIterations = 0;

        for (int i = 0; i < array.length - 1; i++) {
            numIterations++;
            for (int j = 0; j < array.length - i - 1; j++) {
                numIterations++;
                numComparisons++;
                visualizer.updateComparisons(numComparisons);
                visualizer.updateIterations(numIterations);

                sortingPanel.setSelectedIndices(j, j + 1);
                sortingPanel.repaint();

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
                    sortingPanel.repaint();

                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        sortingPanel.resetSelectedIndices();
        sortingPanel.repaint();
        long endTime = System.nanoTime();
        visualizer.updateTime((endTime - startTime) / 1_000_000);
    }
}
