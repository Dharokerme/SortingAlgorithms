package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingPanel extends JPanel {
    private int selectedIndex1 = -1;  // Índice de la primera barra seleccionada
    private int selectedIndex2 = -1;  // Índice de la segunda barra seleccionada
    private int numComparisons = 0;  // Número de comparaciones
    private int numSwaps = 0;        // Número de intercambios
    private int numIterations = 0;   // Número de iteraciones del bucle for
    private long startTime;          // Tiempo de inicio del algoritmo
    private long endTime;            // Tiempo de fin del algoritmo

    private int[] array;  // Array a ordenar
    private static final int ARRAY_SIZE = 50;  // Tamaño del array
    private static final int DELAY = 100;  // Retardo para la animación

    private SortingVisualizer visualizer;  // Referencia a la clase visualizadora

    public SortingPanel(SortingVisualizer visualizer) {
        this.visualizer = visualizer;  // Guarda la referencia para actualizar las etiquetas
        array = new int[ARRAY_SIZE];
        Random rand = new Random();

        // Inicializa el array con valores aleatorios
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = rand.nextInt(400) + 50;  // Números entre 50 y 450
        }
    }

    // Método para dibujar los elementos en el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        // Dibujar cada barra representando un número del array
        for (int i = 0; i < array.length; i++) {
            int x = i * (getWidth() / array.length);
            int y = getHeight() - array[i];
            int width = getWidth() / array.length;
            int height = array[i];

            if (i == selectedIndex1 || i == selectedIndex2) {
                g.setColor(Color.RED);  // Color rojo para las barras seleccionadas
            } else {
                g.setColor(Color.BLUE);  // Color azul para las barras no seleccionadas
            }
            g.fillRect(x, y, width, height);
        }
    }

    // Método para iniciar el algoritmo Bubble Sort
    public void startBubbleSort() {
        new Thread(() -> {
            bubbleSort();
        }).start();
    }

    // Algoritmo Bubble Sort con redibujado del panel
    private void bubbleSort() {
        startTime = System.nanoTime();  // Marca el tiempo de inicio

        for (int i = 0; i < array.length - 1; i++) {
            numIterations++;  // Contador de iteraciones del bucle externo
            for (int j = 0; j < array.length - i - 1; j++) {
                numIterations++;  // Contador de iteraciones del bucle interno
                numComparisons++;  // Incrementa el contador de comparaciones

                // Actualizar las estadísticas
                visualizer.updateComparisons(numComparisons);
                visualizer.updateIterations(numIterations);

                // Actualizar los índices comparados
                selectedIndex1 = j;
                selectedIndex2 = j + 1;

                // Redibuja el panel
                repaint();

                // Pausa para la animación
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (array[j] > array[j + 1]) {
                    // Intercambia los elementos
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    numSwaps++;  // Incrementa el contador de intercambios

                    // Actualizar las estadísticas
                    visualizer.updateSwaps(numSwaps);

                    // Redibuja después del intercambio
                    repaint();

                    // Pausa para la animación
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        endTime = System.nanoTime();  // Marca el tiempo de finalización
        selectedIndex1 = -1;
        selectedIndex2 = -1;
        repaint();

        // Actualizar el tiempo total de ejecución
        visualizer.updateTime((endTime - startTime)/ 1_000_000);

        // Imprimir los resultados en consola (opcional)
        System.out.println("Número total de comparaciones: " + numComparisons);
        System.out.println("Número total de intercambios: " + numSwaps);
        System.out.println("Número total de iteraciones: " + numIterations);
        System.out.println("Tiempo total de ejecución (milisegundos): " + (endTime - startTime)/1_000_000);
    }
}
