import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.*;

import java.io.*;

/**
 * Handles the game lifecycle and behavior
 * 
 * @author Gabriel Panho, Gabriel Verdi e João Marmentini
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private Level level;
    private boolean gameOver;
    private int pontos;
    private ArrayList<String> highscoreAux;
    // private String playerName;

    private Game() {
        gameOver = false;
        pontos = 0;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPontos() {
        return pontos;
    }

    public void incPontos() {
        pontos++;
    }

    public String getHighScore() {
        String aux = "";
        for (int i = 0; i < 11; i++) {
            aux += highscoreAux.get(i) + "\n";
        }
        return aux;
    }

    public String getFase(){
        return "" + level.getFaseNumber();
    }

    public void salvaPontos() {
        try {
            // Conteudo
            String pontos = String.valueOf(this.getPontos());
            ArrayList<String> pontosArquivo = new ArrayList<String>();

            File file = new File("points.txt");

            if (file.exists()) {
                FileReader ler = new FileReader("points.txt");
                BufferedReader reader = new BufferedReader(ler);

                // Le o arquivo e guarda as pontuações que estão nele na lista.

                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println(linha);
                    pontosArquivo.add(linha);
                }
                reader.close();
            }

            pontosArquivo.add(pontos); // Adiona a pontução atual na lista

            Collections.sort(pontosArquivo); // Ordena a pontuação salva
            Collections.reverse(pontosArquivo); // Inverte para ficar do maior ao menor
            file = new File("points.txt");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // System.out.println(pontosArquivo.toString());

            for (int i = 0; i < pontosArquivo.size(); i++) { // Grava pontuação já ordenada no arquivo novamente
                // System.out.println(pontosArquivo.get(i));
                bw.write(pontosArquivo.get(i) + "\n");
                bw.newLine();
            }

            bw.close();

            highscoreAux = pontosArquivo;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return (game);
    }

    public void addChar(Character c) {
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c) {
        activeChars.remove(c);
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Fases
        level = new Level();

        // Adiciona o canhao
        canhao = new Canhao(400, 550);

        activeChars.add(canhao);
    }

    public void Run() {
        level.nextLevel();
        activeChars.addAll(level.getEnemies());
        for (Character c : activeChars) {
            c.start();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        // System.out.println(activeChars.size());
        if (level.getFaseNumber() == 10) 
            setGameOver();
        else {
            boolean hasEnemies = false;
            for (Character character : activeChars) {
                if (character instanceof Alien) {
                    hasEnemies = true;
                }
            }
            if (!hasEnemies) {
                Run();
            }
        }
        if (gameOver) {
            return;
        }

        for (int i = 0; i < activeChars.size(); i++) {
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for (int j = 0; j < activeChars.size(); j++) {
                Character outro = activeChars.get(j);
                if (este instanceof AlienShot || este instanceof Alien) {
                    if (outro instanceof Canhao) {
                        este.testaColisao(outro);
                        outro.testaColisao(este);
                    }
                }
                if(este instanceof Alien){
                    if(outro instanceof Canhao){
                        este.testaColisao(outro);
                        outro.testaColisao(este);
                        if(outro.jaColidiu())
                            ((Canhao) outro).die();
                    }
                }
                if (este instanceof GunShot) {
                    if (outro instanceof Alien) {
                        este.testaColisao(outro);
                        outro.testaColisao(este);
                    }
                }
                //FIXME: Jogo encerra do nada neste if, não identifiquei o motivo.                
/*                 if(este instanceof Alien){
                    if(este.getY() >= Params.WINDOW_HEIGHT -30){
                        Game.getInstance().setGameOver();
                    }   
                } */
            }
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for (Character c : activeChars) {
            c.Draw(graphicsContext);
        }
    }
}
