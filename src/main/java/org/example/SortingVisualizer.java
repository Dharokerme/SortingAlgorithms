package org.example;

import javax.swing.*;
import java.awt.*;

public class SortingVisualizer extends JFrame {

    private SortingPanel panel;
    private JLabel comparisonsLabel;
    private JLabel swapsLabel;
    private JLabel iterationsLabel;
    private JLabel timeLabel;

    public SortingVisualizer() {
        setTitle("Bubble Sort Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de visualización del array
        panel = new SortingPanel(this);  // Pasa la referencia del frame al panel
        add(panel, BorderLayout.CENTER);

        // Panel de estadísticas
        JPanel statsPanel = new JPanel(new GridLayout(4, 1));
        comparisonsLabel = new JLabel("Comparaciones: 0");
        swapsLabel = new JLabel("Intercambios: 0");
        iterationsLabel = new JLabel("Iteraciones: 0");
        timeLabel = new JLabel("Tiempo de ejecución: 0 ns");

        statsPanel.add(comparisonsLabel);
        statsPanel.add(swapsLabel);
        statsPanel.add(iterationsLabel);
        statsPanel.add(timeLabel);

        add(statsPanel, BorderLayout.SOUTH);

        // Comienza la visualización del algoritmo
        panel.startBubbleSort();
    }

    // Métodos para actualizar las etiquetas
    public void updateComparisons(int comparisons) {
        comparisonsLabel.setText("Comparaciones: " + comparisons);
    }

    public void updateSwaps(int swaps) {
        swapsLabel.setText("Intercambios: " + swaps);
    }

    public void updateIterations(int iterations) {
        iterationsLabel.setText("Iteraciones: " + iterations);
    }

    public void updateTime(long time) {
        timeLabel.setText("Tiempo de ejecución: " + time + " ns");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingVisualizer visualizer = new SortingVisualizer();
            visualizer.setVisible(true);
        });
    }
}
