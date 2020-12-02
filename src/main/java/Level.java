import java.util.LinkedList;

public class Level {
    private int level;
    private LinkedList<Alien> enemies;

    public Level() {
        level = 0;
        enemies = new LinkedList<>();
    }

    public int getFaseNumber() {
        return level;
    }    

    public LinkedList getEnemies(){
        return enemies;
    }

    public void nextLevel(){
        level++;
        switch (level) {
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
                enemies.add(new AngryAlien(10, 10));
            case 1:
                enemies.add(new HardAlien(10, 10));
                // enemies.add(new DrunkAlien(110, 10));
                // enemies.add(new InvokerAlien(210, 10));
                // enemies.add(new AngryAlien(310, 10));
        }
    }
}
