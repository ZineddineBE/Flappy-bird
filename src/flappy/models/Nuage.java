package flappy.models;

import flappy.Principal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Nuage extends Sprite{

    protected int largeur;
    protected int vitesse = 2;

    protected Image imageNuage;

    public Nuage() {
        largeur = 50;
        try {
            // On ne charge l'image qu'UNE SEULE FOIS ici
            imageNuage = ImageIO.read(new File("src/img/Nuage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dessiner(Graphics2D dessin) throws IOException {
        dessin.drawImage(imageNuage, x, y, largeur / 2, largeur / 4, null);
    }

    @Override
    public void deplacement() {
        x -= vitesse;

        if(x < -largeur) {
            x = Principal.LARGEUR_FENETRE;
        }

    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
