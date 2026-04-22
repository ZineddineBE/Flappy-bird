package flappy;

import flappy.models.*;
import flappy.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Principal extends Canvas implements KeyListener {

    public static final int LARGEUR_FENETRE = 800;
    public static final int HAUTEUR_FENETRE = 600;

    private JFrame fenetre = new JFrame();
    private Oiseau oiseau = new Oiseau();
    private Tuyau tuyau = new Tuyau();
    private Nuage[] nuages = new Nuage[10];
    private ArrayList<Bonus> listeBonus = new ArrayList<>();

    private boolean pause = false;

    private int score = 0;

    private int iteration = 0;

    public Principal() throws InterruptedException, IOException {

        fenetre.setTitle("Flappy Bird");
        fenetre.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);

        this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
        this.setBounds(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        fenetre.addKeyListener(this);

        JPanel panel = new JPanel();
        panel.add(this);
        fenetre.setContentPane(panel);

        fenetre.requestFocus();
        this.setFocusable(false);

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fenetre.setIgnoreRepaint(true);
        fenetre.setResizable(false);

        fenetre.pack();
        fenetre.setVisible(true);

        this.createBufferStrategy(2);

        for( int i=0 ; i < nuages.length; i++) {
            nuages[i] = new Nuage();
        }

        start();

    }

    public void start() throws InterruptedException, IOException {
        reset();

        while(true) {
            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();

            if (!pause) {
                score++;
                iteration++;

                for(Nuage nuage : nuages) nuage.deplacement();

                if(iteration % 120 == 0) listeBonus.add(new Bonus());

                ArrayList<Bonus> bonusTouche = new ArrayList<>();
                for(Bonus bonus : listeBonus) {
                    bonus.deplacement();
                    if(oiseau.testCollision(bonus)) {
                        score += 100;
                        bonusTouche.add(bonus);
                    }
                }
                listeBonus.removeAll(bonusTouche);

                oiseau.deplacement();
                tuyau.deplacement();

                if(tuyau.testCollision(oiseau) || oiseau.getY() > HAUTEUR_FENETRE - 50 || oiseau.getY() < -50) {
                    pause = true;
                }
            }

            // Fond
            dessin.setColor(new Color(100, 50, 150));
            dessin.fillRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

            for(Nuage nuage : nuages) nuage.dessiner(dessin);
            for(Bonus bonus : listeBonus) bonus.dessiner(dessin);

            tuyau.dessiner(dessin);
            oiseau.dessiner(dessin);

            // UI Score
            dessin.setColor(Color.YELLOW);
            dessin.setFont(new Font("Arial", Font.BOLD, 16));
            dessin.drawString("Score: " + score, 50, 50);

            if (pause) {
                ecranFin(dessin);
            }

            dessin.dispose();
            this.getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }
    }

    public void reset() {
        pause = false;
        score = 0;
        iteration = 0;
        listeBonus.clear();

        oiseau = new Oiseau();
        oiseau.setX(200);
        oiseau.setY(200);

        tuyau = new Tuyau();
        tuyau.setX(LARGEUR_FENETRE);
        tuyau.setY(300);

        for(Nuage nuage : nuages) {
            nuage.setX(Utils.aleatoire(0, LARGEUR_FENETRE));
            nuage.setY(Utils.aleatoire(0, HAUTEUR_FENETRE - 200));
            nuage.setLargeur(Utils.aleatoire(30, 200));
        }

        // 4. On débloque la pause en DERNIER
        pause = false;
    }

    public void ecranFin(Graphics2D dessin) {
        // 1. Overlay semi-transparent
        dessin.setColor(new Color(0, 0, 0, 150));
        dessin.fillRect(0, 0, LARGEUR_FENETRE, HAUTEUR_FENETRE);

        // 2. Texte "GAME OVER"
        dessin.setColor(Color.RED);
        dessin.setFont(new Font("Arial", Font.BOLD, 60));
        String gameOverText = "GAME OVER";
        FontMetrics fm = dessin.getFontMetrics();
        int xGameOver = (LARGEUR_FENETRE - fm.stringWidth(gameOverText)) / 2;
        dessin.drawString(gameOverText, xGameOver, HAUTEUR_FENETRE / 2 - 40);

        // 3. Score et Rejouer
        dessin.setColor(Color.WHITE);
        dessin.setFont(new Font("Arial", Font.BOLD, 20));
        String scoreText = "Score final : " + score;
        String replayText = "Appuyez sur R pour rejouer";
        FontMetrics fmInfo = dessin.getFontMetrics();
        int xScore = (LARGEUR_FENETRE - fmInfo.stringWidth(scoreText)) / 2;
        int xReplay = (LARGEUR_FENETRE - fmInfo.stringWidth(replayText)) / 2;

        dessin.drawString(scoreText, xScore, HAUTEUR_FENETRE / 2 + 10);
        dessin.drawString(replayText, xReplay, HAUTEUR_FENETRE / 2 + 50);

    }

    public static void main(String[] args) throws InterruptedException, IOException {

        new Principal();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            oiseau.sauter();
        }

        if(e.getKeyCode() == KeyEvent.VK_R) {
            if(pause){
                reset();
            }
        }


    }
}
