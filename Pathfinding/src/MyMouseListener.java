import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private final App app;

    public MyMouseListener(App app) {
        this.app = app;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            app.setNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            app.deleteNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
        }
        app.addMouseMotionListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        app.removeMouseMotionListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        app.setNode(e.getX() / Constants.NODE_SIZE, e.getY() / Constants.NODE_SIZE);
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
