package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import java.awt.*;

public class Tuyau extends Sprite {

    protected int ecartement = 150;
    protected int marge = 50;
    protected int vitesse = 5;
    protected int pointDeVie = 3;
    protected Color couleurStroke = Color.black;


    public Tuyau() {
        largeur = 100;
        couleur = new Color(43, 176, 25);
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, Principal.HAUTEUR_FENETRE);
        dessin.fillRect(x, y - ecartement - Principal.HAUTEUR_FENETRE, largeur, Principal.HAUTEUR_FENETRE);

        dessin.setColor(couleurStroke);
        dessin.setStroke(new BasicStroke(3.0f));

        dessin.drawRect(x, y, largeur, Principal.HAUTEUR_FENETRE);
        dessin.drawRect(x, y - ecartement - Principal.HAUTEUR_FENETRE, largeur, Principal.HAUTEUR_FENETRE);
    }

    public void deplacement() {
        x -= vitesse;

        if (x < - largeur) {
            resetTuyau();
        }
    }

    public void toucheParProjectile() {
        pointDeVie --;

        if (pointDeVie == 2) {
            couleur = Color.ORANGE;
        } else if (pointDeVie == 1) {
            couleur = Color.RED;
        } else if (pointDeVie == 0) {
            resetTuyau();
        }
    }

    public void resetTuyau(){
        x = Principal.LARGEUR_FENETRE;
        y = Utils.aleatoire(marge + ecartement, Principal.HAUTEUR_FENETRE-marge);
        pointDeVie = 3;
        couleur = new Color(43, 176, 25);
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
