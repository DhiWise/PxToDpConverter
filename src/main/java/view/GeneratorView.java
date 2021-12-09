package view;

import javax.swing.*;

public class GeneratorView  {
    private JPanel WidthPanel;
    private JTextField viewPortWidth;
    private JPanel actionPanel;
    private JButton generateButton;
    private JPanel HeightPanel;
    private JTextField viewPortHeight;
    private JPanel rootView;
    private JLabel ViewPort;


    public JPanel getRootView() {
        return rootView;
    }

    public JPanel getWidthPanel() {
        return WidthPanel;
    }

    public JTextField getViewPortWidth() {
        return viewPortWidth;
    }

    public JTextField getViewPortHeight() {
        return viewPortHeight;
    }

    public JLabel getViewPort() {
        return ViewPort;
    }

    public JPanel getActionPanel() {
        return actionPanel;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JPanel getHeightPanel() {
        return HeightPanel;
    }


}
