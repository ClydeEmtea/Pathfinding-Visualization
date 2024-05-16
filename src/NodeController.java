// Controls node-related operations
public class NodeController implements Constants {

    // Sets a node based on mouse click
    public static void setNode(int x, int y) {
        if (App.simulating) return;
        if (x < 0 || x >= NODES_X || y < 0 || y >= NODES_Y) return;

        if (App.nodes[x][y].isStart() || App.nodes[x][y].isEnd() || App.nodes[x][y].isWall()) return;

        if (!App.isStart) {
            App.nodes[x][y].makeStart();
            App.isStart = true;
            App.startNode = App.nodes[x][y];
        } else if (!App.isEnd) {
            App.nodes[x][y].makeEnd();
            App.isEnd = true;
            App.endNode = App.nodes[x][y];
        } else {
            App.nodes[x][y].makeWall();
        }
    }

    // Deletes a node
    public static void deleteNode(int x, int y) {
        if (App.simulating) return;
        App.showResults = false;

        if (x < 0 || x >= NODES_X || y < 0 || y > NODES_Y) return;

        if (App.nodes[x][y].isStart()) {
            App.isStart = false;
            App.startNode = null;
        } else if (App.nodes[x][y].isEnd()) {
            App.isEnd = false;
            App.endNode = null;
        }

        App.nodes[x][y].reset();
    }

    // Deletes all nodes
    public static void deleteAllNodes() {
        for (Node[] nodes1 : App.nodes) {
            for (Node node : nodes1) {
                node.reset();
            }
        }

        App.reset();
    }

    // Clears nodes except for start, end, and walls
    public static void clear() {
        for (Node[] nodes1 : App.nodes) {
            for (Node node : nodes1) {
                if (node.isStart() || node.isEnd() || node.isWall()) {
                    continue;
                }
                node.reset();
            }
        }

        App.showResults = false;
        App.success = false;
    }

    // Creates a path from a given node
    public static void makePath(Node node) {
        while (node.getPreviousNode() != null) {
            node.makePath();
            node = node.getPreviousNode();
        }

        App.success = true;
        App.showResults = true;
    }

    // Marks closed nodes as red
    public static void deadEnd() {
        for (Node[] nodes1 : App.nodes) {
            for (Node node : nodes1) {
                if (node.isClosed()) {
                    node.makeRed();
                }
            }
        }

        App.success = false;
        App.showResults = true;
    }

    // Gets neighboring nodes
    public static Node[] getNeighbours(Node node) {
        Node[] neighbours = new Node[8];
        int index = 0;
        for (int x = node.getX() - 1; x <= node.getX() + 1; x++) {
            for (int y = node.getY() - 1; y <= node.getY() + 1; y++) {
                if (x < 0 || x >= NODES_X || y < 0 || y >= NODES_Y) continue;

                if (App.nodes[x][y].isWall()) continue;

                if (x == node.getX() && y == node.getY()) continue;

                neighbours[index] = App.nodes[x][y];
                index++;
            }
        }
        return neighbours;
    }

    public static Node[] getNonDiagonalNeighbours(Node node) {
        Node[] neighbours = new Node[4];
        int index = 0;
        for (int x = node.getX() - 1; x <= node.getX() + 1; x++) {
            for (int y = node.getY() - 1; y <= node.getY() + 1; y++) {
                if (x < 0 || x >= NODES_X || y < 0 || y >= NODES_Y) continue;

                if (App.nodes[x][y].isWall()) continue;

                if (x == node.getX() && y == node.getY()) continue;

                if (x != node.getX() && y != node.getY()) continue;

                neighbours[index] = App.nodes[x][y];
                index++;
            }
        }
        return neighbours;
    }

    // Creates a map of walls
    public static void createMap() {
        for (Node[] nodes1 : App.nodes) {
            for (Node node : nodes1) {
                node.reset();
            }
        }

        App.reset();
        App.map = Map.createMap();

        for (int i = 0; i < App.map.length; i++) {
            for (int j = 0; j < App.map[i].length; j++) {
                if (App.map[i][j] == 1) {
                    App.nodes[j][i].makeWall();
                } else {
                    App.nodes[j][i].reset();
                }
            }
        }
    }
}
