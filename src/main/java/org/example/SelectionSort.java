package org.example;

public class SelectionSort implements SortAlgorithm {

    private static final int DELAY = 10;  // Retardo para la animación

    @Override
    public void sort(int[] array, SortingVisualizer visualizer, SortingPanel sortingPanel) {
        long startTime = System.nanoTime();
        int numComparisons = 0;
        int numSwaps = 0;
        int numIterations = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            numIterations++;
            for (int j = i + 1; j < array.length; j++) {
                numComparisons++;
                visualizer.updateComparisons(numComparisons);
                visualizer.updateIterations(numIterations);

                sortingPanel.setSelectedIndices(i, j);
                sortingPanel.repaint();

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
                sortingPanel.repaint();

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        sortingPanel.resetSelectedIndices();
        sortingPanel.repaint();
        long endTime = System.nanoTime();
        visualizer.updateTime((endTime - startTime) / 1_000_000);
    }
}
