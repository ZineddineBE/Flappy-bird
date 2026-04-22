package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bonus extends Sprite {

    private Image imageBonus;

    public Bonus() {
        x = Principal.LARGEUR_FENETRE;
        y = Utils.aleatoire(25, Principal.HAUTEUR_FENETRE-25);
        largeur = 25;
        try {
            imageBonus = ImageIO.read(new File("src/img/KC.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dessiner(Graphics2D dessin) throws IOException {
        dessin.drawImage(imageBonus, x, y, largeur, largeur, null);
    }

    public void deplacement() {
        x -= 6;
    }
}