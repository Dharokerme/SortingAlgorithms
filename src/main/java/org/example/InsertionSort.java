package org.example;

public class InsertionSort implements SortAlgorithm {

    // Definir el delay directamente dentro de la clase
    private static final int DELAY = 1;  // Por ejemplo, 100 ms

    @Override
    public void sort(int[] array, SortingVisualizer visualizer, SortingPanel sortingPanel) {
        long startTime = System.nanoTime();
        int numComparisons = 0;
        int numSwaps = 0;
        int numIterations = 0;

        // Algoritmo Insertion Sort
        for (int i = 1; i < array.length; i++) {
            numIterations++;
            int key = array[i];
            int j = i - 1;

            // Mueve los elementos del array que son mayores que el "key" una posición adelante
            while (j >= 0 && array[j] > key) {
                numComparisons++;
                visualizer.updateComparisons(numComparisons);
                visualizer.updateIterations(numIterations);

                // Actualizar los índices seleccionados
                sortingPanel.setSelectedIndices(j, j + 1);
                sortingPanel.repaint();

                // Pausar la animación
                try {
                    Thread.sleep(DELAY);  // Usar el delay definido en esta clase
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                array[j + 1] = array[j];
                j--;

                sortingPanel.repaint();

                // Pausar nuevamente
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Inserta el "key" en su posición correcta
            array[j + 1] = key;
            numSwaps++;
            visualizer.updateSwaps(numSwaps);
            sortingPanel.repaint();

            // Pausar la animación después del intercambio
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Resetear los índices seleccionados
        sortingPanel.resetSelectedIndices();
        sortingPanel.repaint();

        // Calcular y mostrar el tiempo total
        long endTime = System.nanoTime();
        visualizer.updateTime((endTime - startTime) / 1_000_000);
    }
}
