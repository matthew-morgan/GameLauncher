import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Horloge extends JFrame {
    protected JLabel label;
    protected JLabel scoreLabel;
    protected int tps;
    protected Timer timer;
    protected int max = 5 ;
    protected int score = 0;
    private JButton joyButton;
    private JButton bouton;

    // commence � 0, intervalle 1 sec
    public Horloge () {
        super ("Demo chrono");
        setPreferredSize(new Dimension(800, 600));
        bouton = new JButton ("start/stop");
        joyButton = new JButton("CLICK START");
        joyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(timer.isRunning()){
                    score = score + 1;
                    scoreLabel.setText("Score = "+score);
                }
            }
        }) ;

        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()){
                    timer.stop() ;
                    joyButton.setText("CLICK START");
                }
                else {
                    tps = 0 ; label.setText("Il reste "+max) ;
                    timer.start() ;
                    joyButton.setText("CLICK AS FAST AS YOU CAN");
                }
            }}) ;

        tps = 0 ;
        label = new JLabel ("Il reste "+max);
        scoreLabel = new JLabel("Score = 0");
        timer = new Timer (1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tps++ ;
                if (tps==max) {
                    JOptionPane.showMessageDialog(Horloge.this,"Score = "+score) ;
                    dispose();
                }else
                    label.setText("Il reste "+(max-tps)) ;
            }});

        add (bouton, BorderLayout.SOUTH) ;
        add (label, BorderLayout.NORTH);
        add (joyButton, BorderLayout.CENTER);
        add (scoreLabel, BorderLayout.EAST);

        setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        setLocation (250, 250);
        pack ();
        setVisible (true);
    };


    public static void main (String argv []) {
        new Horloge ();
    }
}
