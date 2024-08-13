import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel {
      Grid grid = new Grid();
      LinkedList<Point> mouseTrail = new LinkedList<>();

      public Canvas() {
        setPreferredSize(new Dimension(720, 720));
      }

      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Point mousePos = getMousePosition();
        if (mousePos != null) {
          mouseTrail.addFirst(mousePos);
          if (mouseTrail.size() > 100) {
            mouseTrail.removeLast();
          }
        }
        grid.paint(g, mousePos);
        for (int i = 0; i < mouseTrail.size(); i++) {
          Point p = mouseTrail.get(i);
          int alpha = 102;
          g.setColor(new Color(64, 64, 64, alpha));
          g.fillOval(p.x - 5, p.y - 5, 10, 10);
        }
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
        repaint();
        try {
          Thread.sleep(8);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
}
