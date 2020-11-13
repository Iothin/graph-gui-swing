
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JPanel {
    JTextField numberOfDeath;
    JTextField numberOfSick;
    JLabel deathLabel;
    JLabel sickLabel;
    JButton addDead;
    JButton addSick;
    JLabel totalDeath;
    JLabel totalSick;
    JLabel avgDeath;
    JLabel avgSick;
    Graphics2D g2d;

    ArrayList<Integer> deadList;
    ArrayList<Integer> sickList;

    int scaleUnit;

    {
        deadList = new ArrayList<Integer>();
        sickList = new ArrayList<Integer>();
    }

    public Window() {
        setLayout(null);
        deathField();
        sickField();
        deathLabel();
        sickLabel();
        totalLabelDeath();
        avgLabelDeath();
        deadButton();
        sickButton();
        totalLabelSick();
        avgLabelSick();
        totalDeathNumber();
        totalSickNumber();
        avgDeathNumber();
        avgSickNumber();
        mouseHover();

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.getWidth() );
        int height = (int)(screenSize.getHeight() );

        System.out.println(height + "--------" + width);
        return new Dimension(width, height);
    }

    public void deathField() {
        this.numberOfDeath = new JTextField();
        this.numberOfDeath.setBounds(80, 20, 100, 50);
        add(this.numberOfDeath);
    }

    public void deathLabel() {
        this.deathLabel = new JLabel("Dead");
        this.deathLabel.setBounds(20, 20, 100, 50);
        add(this.deathLabel);
    }

    public void deadButton() {
        this.addDead = new JButton("Add");
        this.addDead.setBounds(190, 30, 60, 30);
        addDeathFunc();
        add(this.addDead);
    }

    public void totalLabelDeath() {
        this.deathLabel = new JLabel("Total: ");
        this.deathLabel.setBounds(20, 100, 100, 50);
        add(this.deathLabel);
    }

    public void avgLabelDeath() {
        this.deathLabel = new JLabel("Avg: ");
        this.deathLabel.setBounds(20, 130, 100, 50);
        add(this.deathLabel);
    }

    public void totalDeathNumber() {
        this.totalDeath = new JLabel();
        this.totalDeath.setBounds(80, 100, 100, 50);
        add(this.totalDeath);
    }

    public void avgDeathNumber() {
        this.avgDeath = new JLabel();
        this.avgDeath.setBounds(80, 130, 100, 50);
        add(this.avgDeath);
    }

    // SICK PART========================================

    public void sickField() {
        this.numberOfSick = new JTextField();
        this.numberOfSick.setBounds(80, 200, 100, 50);
        add(this.numberOfSick);
    }

    public void sickLabel() {
        this.sickLabel = new JLabel("Sick");
        this.sickLabel.setBounds(20, 200, 100, 50);
        add(this.sickLabel);
    }

    public void sickButton() {
        this.addSick = new JButton("Add");
        this.addSick.setBounds(190, 210, 60, 30);
        addSickFunc();
        add(this.addSick);
    }

    public void totalLabelSick() {
        this.deathLabel = new JLabel("Total: ");
        this.deathLabel.setBounds(20, 270, 100, 50);
        add(this.deathLabel);
    }

    public void avgLabelSick() {
        this.sickLabel = new JLabel("Avg: ");
        this.sickLabel.setBounds(20, 300, 100, 50);
        add(this.sickLabel);
    }

    public void totalSickNumber() {
        this.totalSick = new JLabel();
        this.totalSick.setBounds(80, 270, 100, 50);
        add(this.totalSick);
    }

    public void avgSickNumber() {
        this.avgSick = new JLabel();
        this.avgSick.setBounds(80, 300, 100, 50);
        add(this.avgSick);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2d = (Graphics2D) g;
        g2d.drawRect(300, 0, 1700, 800);


        scale();
        countDeathValues();
        countSickValues();
        paintDeathLine();
        paintSickLine();
        repaint();
    }

    public void scale() {
        scaleUnit = 2;
        Integer maxDeath;
        ArrayList<Integer> maxDeathList = new ArrayList<Integer>();
        maxDeathList.addAll(sickList);
        maxDeathList.addAll(deadList);
        if (deadList.size() > 0 || sickList.size() > 0) {
            maxDeath = Collections.max(maxDeathList);
            if (maxDeath > 50) {
                scaleUnit = maxDeath / 100;
            }
        }
    }

    public void paintSickLine() {
        int startX = 300;
        int startY = 100;
        int endX = 10;
        int endY = 800;
        int unitX = (endX - startX) / 10;
        int unitY = (endY - startY) / 100;
        int prevX = startX;
        int prevY = endY;

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        for (int y : deadList) {
            g2d.drawLine(prevX, prevY, prevX -= unitX, prevY = endY - (y * unitY) / scaleUnit);
        }
    }

    public void paintDeathLine() {
        int startXSick = 300;
        int startYSick = 100;
        int endXSick = 10;
        int endYSick = 800;
        int unitXSick = (endXSick - startXSick) / 10;
        int unitYSick = (endYSick - startYSick) / 100;
        int prevXSick = startXSick;
        int prevYSick = endYSick;


        g2d.setColor(Color.BLUE);
        for (int x : sickList) {
            g2d.drawLine(prevXSick, prevYSick, prevXSick -= unitXSick,
                    prevYSick = endYSick - (x * unitYSick) / scaleUnit);
        }
        g2d.setStroke(new BasicStroke(1));
    }

    public void addDeathFunc() {
        this.addDead.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deadList.add(Integer.parseInt(numberOfDeath.getText()));
                System.out.println(deadList);
                numberOfDeath.setText("");
            }
        });
    }

    public void addSickFunc() {
        this.addSick.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sickList.add(Integer.parseInt(numberOfSick.getText()));
                System.out.println(sickList);
                numberOfSick.setText("");
            }
        });
    }

    public int sumAllElements(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }

    public int avgValue(ArrayList<Integer> list) {
        int avg = sumAllElements(list) / list.size();
        return avg;
    }

    public void countDeathValues() {
        if (deadList.size() > 0) {
            totalDeath.setText(Integer.toString(sumAllElements(deadList)));
            avgDeath.setText(Integer.toString(avgValue(deadList)));
        }
    }

    public void countSickValues() {
        if (sickList.size() > 0) {
            totalSick.setText(Integer.toString(sumAllElements(sickList)));
            avgSick.setText(Integer.toString(avgValue(sickList)));
        }
    }

    //TODO -> when mouse is on the line show value

    public void mouseHover() {
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 300 && x <= 1700 && y >= 0 && y <= 800) {
                    System.out.println(x + " x Enter y " + y);
                }

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

}
