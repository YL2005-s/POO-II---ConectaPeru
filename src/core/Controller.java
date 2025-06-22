package core;

import javax.swing.*;
import java.awt.*;

public abstract class Controller {
    protected static final JFrame mainFrame = new JFrame("ConectaPerú");
    private static final JPanel viewsLogin = new JPanel(new CardLayout());
    private static final JPanel viewsViewer = new JPanel(new BorderLayout());
    private static final JPanel viewerContent = new JPanel(new CardLayout());
    static {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 700);
        mainFrame.setResizable(true);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.add(viewsLogin);

        viewsViewer.add(viewerContent, BorderLayout.CENTER);
    }

    public abstract void run();

    public static void addView(String viewName, Component view) {
        viewerContent.add(view, viewName);
    }

    public static void addLoginView(String viewName, Component view) {
        viewsLogin.add(view, viewName);
    }

    public static void exitLoginView() {
        mainFrame.remove(viewsLogin);
        mainFrame.add(viewsViewer);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void loadView(String viewName) {
        CardLayout cl = (CardLayout) viewerContent.getLayout();
        cl.show(viewerContent, viewName);
    }

    public static void loadLoginView(String viewName) {
        CardLayout cl = (CardLayout) viewsLogin.getLayout();
        cl.show(viewsLogin, viewName);
    }

    public static void setViewerSidebar(JPanel panel) {
        viewsViewer.add(panel, BorderLayout.WEST);
        viewsViewer.revalidate();
        viewsViewer.repaint();
    }
}
