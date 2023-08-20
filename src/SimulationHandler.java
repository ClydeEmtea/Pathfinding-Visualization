// Handles simulation logic and pathfinding algorithm
public class SimulationHandler {

    // Starts the simulation
    public static void startSim() {
        App.openList.clear();
        App.closedList.clear();
        for (Node[] nodes1 : App.nodes) {
            for (Node node : nodes1) {
                if (node.isStart() || node.isEnd() || node.isWall()) {
                    continue;
                }
                node.reset();
            }
        }
        App.openList.add(App.startNode);
        App.simulating = true;
        App.showInstructions = false;
        App.showResults = false;
    }

    // Stops the simulation
    public static void stopSim() {
        App.simulating = false;
        App.success = false;
    }

    // Steps through the simulation
    public static void stepSim() {
        if (App.openList.isEmpty()) {
            App.simulating = false;
            NodeController.deadEnd();
            return;
        }
        Node currentNode = App.openList.get(0);
        for (Node node : App.openList) {
            if (node.getFCost() < currentNode.getFCost()) {
                currentNode = node;
            }
        }
        if (!currentNode.isStart() && !currentNode.isEnd()) currentNode.close();
        App.openList.remove(currentNode);
        App.closedList.add(currentNode);

        if (currentNode.isEnd()) {
            NodeController.makePath(currentNode);
            App.simulating = false;
        }

        Node[] neighbours = NodeController.getNeighbours(currentNode);
        for (Node neighbour : neighbours) {
            if (neighbour == null) continue;
            if (App.closedList.contains(neighbour)) continue;
            if (neighbour.isWall()) continue;
            if (!App.openList.contains(neighbour)) {
                neighbour.setPreviousNode(currentNode);
                calculateGCost(neighbour, currentNode.getGCost(), currentNode);
                calculateHCost(neighbour);
                calculateFCost(neighbour);
                App.openList.add(neighbour);
            } else {
                if (neighbour.getGCost() > currentNode.getGCost()) {
                    neighbour.setPreviousNode(currentNode);
                    calculateGCost(neighbour, currentNode.getGCost(), currentNode);
                    calculateFCost(neighbour);
                }
            }
        }
    }

    // Calculates the G cost of a node
    public static void calculateGCost(Node node, int lastGCost, Node lastNode) {
        int movementCost = Math.abs(node.getX() - lastNode.getX()) + Math.abs(node.getY() - lastNode.getY());
        node.setGCost(lastGCost + (movementCost == 2 ? 14 : 10));
    }

    // Calculates the H cost of a node
    public static void calculateHCost(Node node) {
        int diffX = Math.abs(node.getX() - App.endNode.getX());
        int diffY = Math.abs(node.getY() - App.endNode.getY());
        int result = (diffX + diffY) * 10 + Math.min(diffX, diffY) * 4;
        node.setHCost(result);
    }

    // Calculates the F cost of a node
    public static void calculateFCost(Node node) {
        node.setFCost(node.getGCost() + node.getHCost());
    }
}
