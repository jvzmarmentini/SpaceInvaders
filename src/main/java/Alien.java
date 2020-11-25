import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a simple alien that crosses the screen over and go back
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public abstract class Alien extends BasicElement {
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
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH() || getX() < getLMinH()){
                // Reposiciona no lado esquerdo e ...
                setDirH(getDirH()*-1);
                setPosY(getY()+35);
                // Sorteia o passo de avanço [1,5]
                // setSpeed(Params.getInstance().nextInt(5)+1);
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(), getY());
    }
}
