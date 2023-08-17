import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class App extends JPanel implements ActionListener, Constants {
    public App() { // Constructor
        this.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT)); // Set window size
        this.setBackground(Color.BLACK); // Set background color
        this.setFocusable(true); // Allows the window to be focused
        this.addKeyListener(new MyKeyListener(this)); // Adds the MyKeyListener.java class to the JPanel
        this.addMouseListener(new MyMouseListener(this));
        start(); // Calls the start() method
    }

    // Variables
    Timer timer;
    Node[][] nodes = new Node[NODES_X][NODES_Y];
    boolean isStart = false;
    boolean isEnd = false;
    Node startNode;
    Node endNode;

    List<Node> openList = new ArrayList<>();
    List<Node> closedList = new ArrayList<>();
    boolean simulating = false;
    boolean showInstructions = true;

    int[][] map;

    // Starts the game and initializes variables and objects to their default values and states
    public void start() {
        timer = new Timer(DELAY, this);
        timer.start();
        for (int x = 0; x < NODES_X; x++) {
            for (int y = 0; y < NODES_Y; y++) {
                nodes[x][y] = new Node(x, y, Constants.darkGray);
            }
        }
    }

    // Draws nodes
    public void setNode(int x, int y) {
        if (x < 0 || x >= NODES_X || y < 0 || y > NODES_Y) return;
        if (nodes[x][y].isStart() || nodes[x][y].isEnd() || nodes[x][y].isWall()) return;
        if (!isStart) {
            nodes[x][y].makeStart();
            isStart = true;
            startNode = nodes[x][y];

        } else if (!isEnd) {
            nodes[x][y].makeEnd();
            isEnd = true;
            endNode = nodes[x][y];

        } else {
            nodes[x][y].makeWall();

        }
    }

    // Deletes nodes
    public void deleteNode(int x, int y) {
        if (x < 0 || x >= NODES_X || y < 0 || y > NODES_Y) return;
        if (nodes[x][y].isStart()) {
            isStart = false;
            startNode = null;

        } else if (nodes[x][y].isEnd()) {
            isEnd = false;
            endNode = null;

        }
        nodes[x][y].makeOpen();
        nodes[x][y].setPreviousNode(null);
    }

    // Deletes all nodes
    public void deleteAllNodes() {
        for (Node[] nodes1 : nodes) {
            for (Node node : nodes1) {
                node.setColor(Constants.darkGray);
                node.makeOpen();
                node.setPreviousNode(null);
            }
        }
        isStart = false;
        startNode = null;
        isEnd = false;
        endNode = null;
    }


    // Starts the simulation
    public void startSim() {
        openList.clear();
        closedList.clear();
        for (Node[] nodes1 : nodes) {
            for (Node node : nodes1) {
                if (node.isStart() || node.isEnd() || node.isWall()) {
                    continue;
                }

                node.setPreviousNode(null);
                node.setGCost(0);
                node.setHCost(0);
                node.setFCost(0);
                node.makeOpen();
            }
        }
        openList.add(startNode);
        simulating = true;
        showInstructions = false;
    }

    // Stops the simulation
    public void stopSim() {
        simulating = false;
    }

    // Steps through the simulation
    public void stepSim () {
        if (openList.isEmpty()) {
            simulating = false;
            deadEnd();
            return;

        }
        Node currentNode = openList.get(0);
        for (Node node : openList) {
            if (node.getFCost() < currentNode.getFCost()) {
                currentNode = node;

            }
        }
        if (!currentNode.isStart() && !currentNode.isEnd()) currentNode.close();
        openList.remove(currentNode);
        closedList.add(currentNode);

        if (currentNode.isEnd()) {
            makePath(currentNode);
            simulating = false;

        }

        Node[] neighbours = getNeighbours(currentNode);
        for (Node neighbour : neighbours) {
            if (neighbour == null) continue;
            if (closedList.contains(neighbour)) continue;
            if (neighbour.isWall()) continue;
            if (!openList.contains(neighbour)) {
                neighbour.setPreviousNode(currentNode);
                calculateGCost(neighbour, currentNode.getGCost(), currentNode);
                calculateHCost(neighbour);
                calculateFCost(neighbour);
                openList.add(neighbour);

            } else {
                if (neighbour.getGCost() > currentNode.getGCost()) {
                    neighbour.setPreviousNode(currentNode);
                    calculateGCost(neighbour, currentNode.getGCost(), currentNode);
                    calculateFCost(neighbour);

                }
            }
        }
    }

    // Makes the path
    public void makePath(Node node) {
        while (node.getPreviousNode() != null) {
            node.makePath();
            node = node.getPreviousNode();
        }

    }

    // Makes all nodes that are closed red
    public void deadEnd() {
        for (Node[] nodes1 : nodes) {
            for (Node node : nodes1) {
                if (node.isClosed()) {
                    node.makeRed();
                }
            }
        }
    }

    // Calculates the G cost of a node
    public void calculateGCost(Node node, int lastGCost, Node lastNode) {
        if (Math.abs(node.getX() - lastNode.getX()) + Math.abs(node.getY() - lastNode.getY()) == 2) {
            node.setGCost(lastGCost + 14);

        } else if (Math.abs(node.getX() - lastNode.getX()) + Math.abs(node.getY() - lastNode.getY()) == 1) {
            node.setGCost(lastGCost + 10);

        }
    }

    // Calculates the H cost of a node
    public void calculateHCost(Node node) {
        int result = 0;
        int diffX = Math.abs(node.getX() - endNode.getX());
        int diffY = Math.abs(node.getY() - endNode.getY());
        while (diffX > 0 && diffY > 0) {
            result += 14;
            diffX--;
            diffY--;

        }
        while (diffX > 0) {
            result += 10;
            diffX--;

        }
        while (diffY > 0) {
            result += 10;
            diffY--;

        }
        node.setHCost(result);
    }

    // Calculates the F cost of a node
    public void calculateFCost(Node node) {
        node.setFCost(node.getGCost() + node.getHCost());
    }

    // Gets the neighbours of a node
    public Node[] getNeighbours(Node node) {
        Node[] neighbours = new Node[8];
        int index = 0;
        for (int x = node.getX() - 1; x <= node.getX() + 1; x++) {
            for (int y = node.getY() - 1; y <= node.getY() + 1; y++) {
                if (x < 0 || x >= NODES_X || y < 0 || y >= NODES_Y) continue;

                if (nodes[x][y].isWall()) continue;

                if (x == node.getX() && y == node.getY()) continue;

                neighbours[index] = nodes[x][y];
                index++;
            }
        }
        return neighbours;
    }


    // Creates a maze
    public void createMap() {
        for (Node[] nodes1 : nodes) {
            for (Node node : nodes1) {
                node.makeOpen();
                node.setPreviousNode(null);
            }
        }

        isStart = false;
        isEnd = false;
        startNode = null;
        endNode = null;
        map = Constants.createMap(NODES_Y, NODES_X);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    nodes[j][i].makeWall();

                } else {
                    nodes[j][i].makeOpen();
                    nodes[j][i].setPreviousNode(null);
                }
            }
        }
    }


    // Draws nodes
    public void drawNodes(Graphics g){
        for (Node[] nodes1 : nodes) {
            for (Node node : nodes1) {
                node.draw(g);
            }
        }
    }

    // Draws lines
    public void drawLines(Graphics g){
        g.setColor(Constants.gray);
        for (int i = 0; i < NODES_X; i++) {
            g.drawLine(i * NODE_SIZE, 0, i * NODE_SIZE, Constants.HEIGHT);
        }
        for (int i = 0; i < NODES_Y; i++) {
            g.drawLine(0, i * NODE_SIZE, Constants.WIDTH, i * NODE_SIZE);
        }
    }

    // Draws instructions
    public void drawInstructions(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Press R to generate a maze", 10, 20);
        g.drawString("Press C to clear the map", 10, 40);
        g.drawString("Press SPACE to start/stop the simulation", 10, 60);
        g.drawString("Press and hold the left mouse button to draw walls", 10, 80);
        g.drawString("Press and hold the right mouse button to delete walls", 10, 100);
    }

    // Paints the JPanel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNodes(g);
        drawLines(g);
        if (showInstructions) drawInstructions(g);
    }


    // Runs every frame
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < STEPS_PER_FRAME; i++) {
            if (simulating) {
                stepSim();
            }
        }
        repaint();
    }
}
