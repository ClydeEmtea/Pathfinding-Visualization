import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Application main panel class
public class App extends JPanel implements ActionListener, Constants {

    // Constructor
    public App() {
        this.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT)); // Set window size
        this.setBackground(Color.BLACK); // Set background color
        this.setFocusable(true); // Allows the window to be focused
        this.addKeyListener(new MyKeyListener()); // Adds the MyKeyListener.java class to the JPanel
        this.addMouseListener(new MyMouseListener(this));
        start(); // Calls the start() method
    }

    // Variables
    Timer timer;
    static Node[][] nodes = new Node[NODES_X][NODES_Y];
    static int[][] map;
    static Node startNode;
    static Node endNode;
    static List<Node> openList = new ArrayList<>();
    static List<Node> closedList = new ArrayList<>();
    static boolean isStart = false;
    static boolean isEnd = false;
    static boolean simulating = false;
    static boolean showInstructions = true;
    static boolean showResults = false;
    static boolean success = false;

    // Initializes the game and variables to their default values and states
    public void start() {
        timer = new Timer(DELAY, this);
        timer.start();
        initializeNodes();
    }

    // Initializes the node grid
    private void initializeNodes() {
        for (int x = 0; x < NODES_X; x++) {
            for (int y = 0; y < NODES_Y; y++) {
                nodes[x][y] = new Node(x, y, Constants.darkGray);
            }
        }
    }

    // Resets important variables for a new pathfinding simulation
    public static void reset() {
        isStart = false;
        startNode = null;
        isEnd = false;
        endNode = null;
        showResults = false;
        success = false;
    }

    // Overrides the paintComponent method to draw the panel contents
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        DrawingHandler.drawNodes(g);
        if (showInstructions) DrawingHandler.drawInstructions(g);
        if (showResults) DrawingHandler.drawResults(g);
    }

    // Overrides the actionPerformed method to handle simulation steps and repaint
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < STEPS_PER_FRAME; i++) {
            if (simulating) {
                SimulationHandler.stepSim();
            }
        }
        repaint();
    }
}
