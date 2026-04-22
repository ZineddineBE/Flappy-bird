package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Oiseau extends Sprite {

    protected float gravite = 1.0f;
    protected Image imageOiseau;

    public Oiseau() {
        largeur = 50;
        vitesse = 25;

        try {
            // On ne charge l'image qu'UNE SEULE FOIS ici
            imageOiseau = ImageIO.read(new File("src/img/Flappy-Bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dessiner(Graphics2D dessin) throws IOException {
        dessin.drawImage(imageOiseau, x, y, largeur, largeur, null);
    }

    public void deplacement() {
        y += gravite;
        gravite += 0.2f;
    }

    public void deplacementHorizontal(int vitesse) {
        x += vitesse;
    }

    public void sauter() {
        gravite = -5f;
    }


}
