import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel implements MouseMotionListener {
      Grid grid = new Grid();
      LinkedList<Point> mouseTrail = new LinkedList<>();
      long lastUpdateTime = 0;
      int delay = 10;

      public Canvas() {
        setPreferredSize(new Dimension(720, 720));
        addMouseMotionListener(this);
      }

      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point mousePos = getMousePosition();

        grid.paint(g, mousePos);
        for (Point p : mouseTrail) {
          int alpha = 102;
          g.setColor(new Color(64, 64, 64, alpha));
          g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
      }

      @Override
      public void mouseMoved(MouseEvent e) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= delay) {
          mouseTrail.addFirst(e.getPoint());
          if (mouseTrail.size() > 100) {
            mouseTrail.removeLast();
          }
          lastUpdateTime = currentTime;
          repaint();
        }
      }

      @Override
      public void mouseDragged(MouseEvent e) {

      }
    }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    public void run() {
      while (true) {
        try {
          Thread.sleep(8);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
}
