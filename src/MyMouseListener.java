import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// Listens for mouse events and mouse motion
public class MyMouseListener implements MouseListener, MouseMotionListener {

    private final App app;
    private boolean isLeftMouseButtonPressed = false; // Track left mouse button state
    private boolean isRightMouseButtonPressed = false; // Track right mouse button state

    // Constructor
    public MyMouseListener(App app) {
        this.app = app;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        // Check the pressed mouse button and perform corresponding actions
        if (e.getButton() == MouseEvent.BUTTON1) {
            isLeftMouseButtonPressed = true;
            NodeController.setNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            isRightMouseButtonPressed = true;
            NodeController.deleteNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
        }
        app.addMouseMotionListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Reset mouse button states and remove mouse motion listener
        isLeftMouseButtonPressed = false;
        isRightMouseButtonPressed = false;
        app.removeMouseMotionListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        // Check which mouse button is pressed and perform corresponding actions
        if (isLeftMouseButtonPressed) {
            NodeController.setNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
        } else if (isRightMouseButtonPressed) {
            NodeController.deleteNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
