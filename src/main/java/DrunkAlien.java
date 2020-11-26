/**
 * Represents a drunk alien that his direction cant be predicted
 * 
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class DrunkAlien extends Alien {
    private final int REST_TIME = 1000000000;
    private int stamina = 0;

    public DrunkAlien(int px, int py) {
        super(px, py, "alien.png");
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().incPontos();
            deactivate();
        } else {
            setPosX(getX() + getDirH() * getSpeed());
            if (stamina > 0)
                stamina -= deltaTime / 2;
            else {
                if (Params.getInstance().nextInt(3) == 0)
                    setDirH(getDirH() * -1);
                    setSpeed(Params.getInstance().nextInt(3) + 1);
                stamina = REST_TIME;
            }
            if (getX() >= getLMaxH() || getX() < getLMinH()) {
                setDirH(getDirH() * -1);
                setPosY(getY() + 35);
            }
        }
    }
}
