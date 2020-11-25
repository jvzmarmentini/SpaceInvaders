import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a simple ball that crosses the screen over and over again
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class Ball extends BasicElement {
    public Ball(int px,int py){
        super(px,py);
    }

    @Override
    public void start(){
        setDirH(1);
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
                // Sorteia o passo de avanço [1,5]
                // setSpeed(Params.getInstance().nextInt(5)+1);
            }
        }
    }

    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
