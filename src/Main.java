
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        gui();
    }

    public static void gui() {
        JFrame frame = new JFrame("Statistics");
        Window window = new Window();

        frame.add(window);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocation(0, 0);
        frame.pack();
    }

}
