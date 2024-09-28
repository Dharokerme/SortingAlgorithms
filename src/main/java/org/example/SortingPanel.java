package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortingPanel extends JPanel {
    private int selectedIndex1 = -1;
    private int selectedIndex2 = -1;
    private int[] array;
    private static final int ARRAY_SIZE = 100 + 50;

    private SortingVisualizer visualizer;

    // Variable para almacenar la implementación del algoritmo seleccionado
    private SortAlgorithm sortAlgorithm;

    public SortingPanel(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
        array = new int[ARRAY_SIZE];
        // Crear una lista de números secuenciales de 1 a ARRAY_SIZE
        ArrayList<Object> numberList = new ArrayList<>();
        for (int i = 50; i <= ARRAY_SIZE+50; i++) {
            numberList.add(i);
        }

        // Barajar la lista para desorganizarla
        Collections.shuffle(numberList);

        // Copiar los números barajados al array
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) numberList.get(i);
        }


        // Por defecto, seleccionamos Bubble Sort
        sortAlgorithm = new InsertionSort();
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

    // Metodo para cambiar el algoritmo
    public void setAlgorithm(SortAlgorithm algorithm) {
        this.sortAlgorithm = algorithm;
    }

    // Metodo para iniciar el algoritmo de ordenamiento seleccionado
    public void startSort() {
        new Thread(() -> {
            sortAlgorithm.sort(array, visualizer, this);
        }).start();
    }

    // Métodos para gestionar los índices seleccionados
    public void setSelectedIndices(int index1, int index2) {
        this.selectedIndex1 = index1;
        this.selectedIndex2 = index2;
    }

    public void resetSelectedIndices() {
        this.selectedIndex1 = -1;
        this.selectedIndex2 = -1;
    }
}

