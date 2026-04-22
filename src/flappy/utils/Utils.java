package flappy.utils;

public abstract class Utils {

    public static int aleatoire (int min, int max) {
        return (int)(Math.random() * (max -min) + min);
    }
}
