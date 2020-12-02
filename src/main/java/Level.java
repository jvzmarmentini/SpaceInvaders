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

    public LinkedList getEnemies() {
        return enemies;
    }

    public void nextLevel() {
        level++;
        switch (level) {
            case 9:
                enemies.add(new AngryAlien(10, 100));
                enemies.add(new HardAlien(160, 100));
                enemies.add(new DrunkAlien(310, 100));
                enemies.add(new InvokerAlien(450, 100));
            case 8:
                enemies.add(new AngryAlien(10, 80));
                enemies.add(new HardAlien(160, 80));
                enemies.add(new DrunkAlien(310, 80));
                enemies.add(new InvokerAlien(450, 80));
            case 7:
                enemies.add(new AngryAlien(10, 60));
                enemies.add(new HardAlien(160, 60));
                enemies.add(new DrunkAlien(310, 60));
                enemies.add(new InvokerAlien(450, 60));
            case 6:
                enemies.add(new AngryAlien(10, 40));
                enemies.add(new HardAlien(160, 40));
                enemies.add(new DrunkAlien(310, 40));
                enemies.add(new InvokerAlien(450, 40));
            case 5:
                enemies.add(new AngryAlien(10, 20));
                enemies.add(new HardAlien(160, 20));
                enemies.add(new DrunkAlien(310, 20));
                enemies.add(new InvokerAlien(450, 20));
                break;
            case 4:
                enemies.add(new InvokerAlien(10, 20));
                break;
            case 3:
                enemies.add(new DrunkAlien(10, 20));
                break;
            case 2:
                enemies.add(new HardAlien(10, 20));
                break;
            case 1:
                enemies.add(new AngryAlien(10, 20));
        }
    }
}
