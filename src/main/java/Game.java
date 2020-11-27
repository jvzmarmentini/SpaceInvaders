import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private boolean gameOver;
    private int pontos;

    private Game(){
        gameOver = false;
        pontos = 0;
    }

    public void setGameOver(){
        gameOver = true;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public int getPontos(){
        return pontos;
    }

    public void incPontos(){
        pontos++;
    }

    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }

    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c){
        activeChars.remove(c);
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao
        canhao = new Canhao(400,550);
        activeChars.add(canhao);

        // Adiciona bolas
        // for(int i=0; i<5; i++){
        //     activeChars.add(new AngryAlien(100+(i*60),60));
        // }
        // activeChars.add(new DrunkAlien(100, 60));

        activeChars.add(new InvokerAlien(100, 60));


        for(Character c:activeChars){
            c.start();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        if(gameOver){
            return;
        }

        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                //FIXME: make these ifs prettier
                if (este instanceof AlienShot) {
                    if (outro instanceof Canhao) {
                        este.testaColisao(outro);
                        outro.testaColisao(este);
                    }
                }
                if (este instanceof GunShot) {
                    if (outro instanceof Alien) {
                        este.testaColisao(outro);
                        outro.testaColisao(este);
                    }
                }
            }
        }
    }


    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }
}
