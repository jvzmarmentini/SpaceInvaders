import javafx.scene.canvas.GraphicsContext;

public abstract class Enemy extends BasicElement{

    public Enemy(int px,int py){
        super(px,py);
   }

   @Override
    public void start(){
        setDirH(1);
    }

   @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
