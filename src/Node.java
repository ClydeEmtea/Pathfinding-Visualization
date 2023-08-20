import java.awt.*;

public class Node {

    // Variables
    private final int x, y; // Coordinates of the node
    private Color color; // Color of the node
    private int gCost, hCost, fCost; // Costs used in pathfinding algorithms
    private Node previousNode; // Previous node in the path
    private boolean isEnd = false; // Whether the node is the end point
    private boolean isStart = false; // Whether the node is the start point

    // Constructor
    public Node(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Draws the node
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x * Constants.NODE_SIZE, y * Constants.NODE_SIZE, Constants.NODE_SIZE, Constants.NODE_SIZE);
    }

    // Getters and setters

    public boolean isWall() {
        return color == Constants.lightGray;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void makeWall() {
        this.color = Constants.lightGray;
    }

    public void makeStart() {
        this.color = Constants.lightBlue;
        this.isStart = true;
    }

    public void makeEnd() {
        this.isEnd = true;
        this.color = Constants.lightBlue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int getFCost() {
        return fCost;
    }

    public void setFCost(int fCost) {
        this.fCost = fCost;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public void makePath() {
        if (!isStart() && !isEnd()) {
            this.color = Constants.green;
        }
    }

    public void close() {
        this.color = Constants.darkBlue;
    }

    public boolean isClosed() {
        return this.color == Constants.darkBlue;
    }

    public void makeRed() {
        this.color = Constants.red;
    }

    public void reset() {
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.previousNode = null;
        this.isStart = false;
        this.isEnd = false;
        this.color = Constants.darkGray;
    }
}
