import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a simple alien that crosses the screen over and go back
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class Alien extends BasicElement {
    private Image image;
    
    public Alien(int px,int py,String imagePath){
        super(px,py);

        try {
            image = new Image(imagePath, 0, 30, true, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setDirH(-1);
    }


    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            Game.getInstance().incPontos();
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            if (getX() >= getLMaxH() || getX() < getLMinH()){
                setDirH(getDirH()*-1);
                setPosY(getY()+35);
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(), getY());
    }
}
