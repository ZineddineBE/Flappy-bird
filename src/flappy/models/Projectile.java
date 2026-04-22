package flappy.models;

import flappy.Principal;
import flappy.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Projectile extends Sprite {

    protected Image imageProjectile;
    protected int delaiTir;

    public Projectile(int oiseauX, int oiseauY) {
        x = oiseauX;
        y = oiseauY;
        largeur = 15;
        vitesse = 25;
        delaiTir = 500;

        try {
            imageProjectile = ImageIO.read(new File("src/img/Projectile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dessiner(Graphics2D dessin) throws IOException {
        dessin.drawImage(imageProjectile, x, y, largeur, largeur, null);
    }

    @Override
    public void deplacement() {
        x += vitesse;
    }

    public int getDelaiTir() {
        return delaiTir;
    }

    public void setDelaiTir(int delaiTir) {
        this.delaiTir = delaiTir;
    }

    public Image getImageProjectile() {
        return imageProjectile;
    }

    public void setImageProjectile(Image imageProjectile) {
        this.imageProjectile = imageProjectile;
    }
}
