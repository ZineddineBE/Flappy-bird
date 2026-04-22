package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import java.awt.*;

public class Tuyau extends Sprite {

    protected int ecartement = 150;
    protected int marge = 50;
    protected int vitesse = 5;

    public Tuyau() {
        largeur = 100;
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(Color.green);
        dessin.fillRect(x, y, largeur, Principal.HAUTEUR_FENETRE);
        dessin.fillRect(x, y - ecartement - Principal.HAUTEUR_FENETRE, largeur, Principal.HAUTEUR_FENETRE);

        dessin.setColor(Color.black);
        dessin.setStroke(new BasicStroke(1.0f));

        dessin.drawRect(x, y, largeur, Principal.HAUTEUR_FENETRE);
        dessin.drawRect(x, y - ecartement - Principal.HAUTEUR_FENETRE, largeur, Principal.HAUTEUR_FENETRE);
    }

    public void deplacement() {
        x -= vitesse;

        if (x < - largeur) {
            x = 800;
            y = Utils.aleatoire(marge + ecartement, Principal.HAUTEUR_FENETRE-marge);
        }
    }

    @Override
    public Zone[] getZones() {

        Zone zoneTuyauBas = new Zone(
                new Point(x, y),
                new Point(x + largeur, y),
                new Point(x, y + Principal.HAUTEUR_FENETRE),
                new Point(x + largeur, y + Principal.HAUTEUR_FENETRE));

        Zone zoneTuyauHaut = new Zone(
                new Point(x, y - Principal.HAUTEUR_FENETRE),
                new Point(x + largeur, y - Principal.HAUTEUR_FENETRE),
                new Point(x, y - ecartement),
                new Point(x + largeur, y - ecartement));

        return new Zone[]{zoneTuyauBas, zoneTuyauHaut};
    }
}
