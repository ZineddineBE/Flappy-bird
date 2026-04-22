package flappy.models;

import java.awt.*;
import java.io.IOException;

public abstract class Sprite {

    protected int x;
    protected int y;
    protected int largeur = 100;

    public abstract void dessiner(Graphics2D dessin) throws IOException;
    public abstract void deplacement();

    public boolean testCollision(Sprite cible){

        Zone[] zones = getZones();
        Zone[] zonesCible = cible.getZones();

        //pour toutes les zones de l'objet qui test
        for (Zone zone : zones) {
            //pour toutes les zones de l'objet qui est testé
            for (Zone zoneCible : zonesCible) {
                Point[] pointsCible = {
                        zoneCible.topLeft(),
                        zoneCible.topRight(),
                        zoneCible.bottomLeft(),
                        zoneCible.bottomRight()};

                //on vérifie si l'un des points de la zone cible se trouve
                // dans la zone de l'objet qui test
                for (Point pointCible : pointsCible) {
                    //si le point est dans la zone
                    if(pointCible.x() > zone.topLeft().x()
                            && pointCible.x() < zone.topRight().x()
                            && pointCible.y() > zone.topLeft().y()
                            && pointCible.y() < zone.bottomLeft().y()){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Zone[] getZones() {
        return new Zone[]{
                new Zone(
                        new Point(x, y),
                        new Point(x + largeur, y),
                        new Point(x, y + largeur),
                        new Point(x + largeur, y + largeur)
                )
        };
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
