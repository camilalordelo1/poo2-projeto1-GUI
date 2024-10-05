import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimationPanel extends JPanel {
    private static final int DIAMETER = 30;
    private List<Point> circles;
    private Timer timer;
    private Random random;
    private int currentSpeed;

    public AnimationPanel() {
        random = new Random();
        circles = new ArrayList<>();
        currentSpeed = 50; // Default initial speed
    }

    public void startAnimation(int speed) {
        if (circles.isEmpty()) {
            for (int i = 0; i < 50; i++) {
                circles.add(new Point(random.nextInt(800), random.nextInt(600)));
            }
        }

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < circles.size(); i++) {
                    Point circle = circles.get(i);
                    circle.y -= 2;

                    if (circle.y < 0) {
                        circle.y = getHeight();
                        circle.x = random.nextInt(800);
                    }
                }
                repaint();
            }
        });
        currentSpeed = speed;
        timer.start();
    }

    public void stopAnimation() {
        if (timer != null) {
            timer.stop();
        }
        circles.clear(); // Clears the circles so they disappear
        repaint();
    }

    public void toggleAnimation() {
        if (timer != null && timer.isRunning()) {
            stopAnimation();
        } else {
            startAnimation(currentSpeed); // Start with the current speed
        }
    }

    public void setSpeed(int speed) {
        if (timer != null && timer.isRunning()) {
            timer.setDelay(speed);  // Adjusts the Timer's interval
        }
        currentSpeed = speed;  // Updates the current speed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!circles.isEmpty()) {
            g.setColor(Color.BLUE);
            for (Point circle : circles) {
                g.fillOval(circle.x, circle.y, DIAMETER, DIAMETER);
            }
        }
    }
}
