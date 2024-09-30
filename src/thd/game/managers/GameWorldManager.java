package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager {

    private final List<GameObject> activatableGameObjects;
    private final List<CollidingGameObject> toBeAddedToCollidingGameObjectsForPathDecision;

    protected GameWorldManager(GameView gameView) {
        super(gameView);
        scoreDisplay = new ScoreDisplay(gameView, this);
        livesDisplay = new LivesDisplay(gameView, this);
        timer = new GameTimer(gameView, this);
        heroShootingBulletDisplay = new HeroShootingBulletDisplay(gameView, this);
        background = new Background(gameView, this);
        heroFeetHitbox = new HeroFeetHitbox(gameView, this);
        heroHeadHitbox = new HeroHeadHitbox(gameView, this);
        hero = new Hero(gameView, this, heroFeetHitbox, heroHeadHitbox);
        overlay = new Overlay(gameView, this);
        activatableGameObjects = new LinkedList<>();
        toBeAddedToCollidingGameObjectsForPathDecision = new LinkedList<>();
    }

    private void spawnGameObjects() {
        spawnGameObject(scoreDisplay);
        spawnGameObject(livesDisplay);
        spawnGameObject(heroShootingBulletDisplay);
        spawnGameObject(background);
        spawnGameObject(overlay);
        spawnGameObject(timer);
    }

    /*
    For the description of characters, check each level.
     */
    private void spawnGameObjectsFromWorldString() {
        String[] lines = level.world.split("\\R");

        for (int indexLine = 0; indexLine < lines.length; indexLine++) {
            for (int indexColumn = 0; indexColumn < lines[indexLine].length(); indexColumn++) {
                double x = (indexColumn - level.worldOffsetColumns) * 50;
                double y = (indexLine - level.worldOffsetLines) * 50;
                char character = lines[indexLine].charAt(indexColumn);
                if (character == 'G') {
                    Ground ground = new Ground(gameView, this);
                    ground.getPosition().updateCoordinates(x, y);
                    spawnGameObject(ground);
                    toBeAddedToCollidingGameObjectsForPathDecision.add(ground);
                } else if (character == 'Y') {
                    EnemyYourei enemyYourei = new EnemyYourei(gameView, this, new Position(x, y));
                    addActivatableGameObject(enemyYourei);
                } else if (character == 'E') {
                    enemyGotokuHeadHitbox = new EnemyGotokuHeadHitbox(gameView, this);
                    EnemyGotoku enemyGotoku = new EnemyGotoku(gameView, this, hero, enemyGotokuHeadHitbox);
                    enemyGotoku.getPosition().updateCoordinates(x, y);
                    enemyGotokuHeadHitbox.getPosition().updateCoordinates(enemyGotoku.getPosition());
                    addActivatableGameObject(enemyGotoku);
                    spawnGameObject(enemyGotokuHeadHitbox);
                } else if (character == 'O') {
                    EnemyOnre enemyOnre = new EnemyOnre(gameView, this, hero);
                    enemyOnre.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(enemyOnre);
                } else if (character == 'I') {
                    BlockIron blockIron = new BlockIron(gameView, this, new Position(x, y));
                    toBeAddedToCollidingGameObjectsForPathDecision.add(blockIron);
                    addActivatableGameObject(blockIron);
                } else if (character == 'W') {
                    BlockWood blockWood = new BlockWood(gameView, this, new Position(x, y));
                    toBeAddedToCollidingGameObjectsForPathDecision.add(blockWood);
                    addActivatableGameObject(blockWood);
                } else if (character == 'D') {
                    BlockDirt blockDirt = new BlockDirt(gameView, this, new Position(x, y));
                    toBeAddedToCollidingGameObjectsForPathDecision.add(blockDirt);
                    addActivatableGameObject(blockDirt);
                } else if (character == 'R') {
                    BlockGrass blockGrass = new BlockGrass(gameView, this, new Position(x, y));
                    toBeAddedToCollidingGameObjectsForPathDecision.add(blockGrass);
                    addActivatableGameObject(blockGrass);
                } else if (character == 'N') {
                    BlockCoin blockCoin = new BlockCoin(gameView, this, new Position(x, y));
                    toBeAddedToCollidingGameObjectsForPathDecision.add(blockCoin);
                    addActivatableGameObject(blockCoin);
                } else if (character == 'C') {
                    CollectableCoin coin = new CollectableCoin(gameView, this);
                    coin.getPosition().updateCoordinates(x + 7, y + 10);  //Adjustment for right placement on block
                    addActivatableGameObject(coin);
                } else if (character == 'H') {
                    CollectableHeart heart = new CollectableHeart(gameView, this);
                    heart.getPosition().updateCoordinates(x, y);
                    addActivatableGameObject(heart);
                } else if (character == 'S') {
                    CollectableHeroShootingBullet shuriken = new CollectableHeroShootingBullet(gameView, this);
                    shuriken.getPosition().updateCoordinates(x + 10, y + 15); //Adjustment for right placement on block
                    addActivatableGameObject(shuriken);
                } else if (character == 'X') {
                    heroFeetHitbox.getPosition().updateCoordinates(x, y);
                    heroHeadHitbox.getPosition().updateCoordinates(x, y);
                    hero.getPosition().updateCoordinates(heroFeetHitbox.getPosition());
                    spawnGameObject(hero);
                    spawnGameObject(heroFeetHitbox);
                    spawnGameObject(heroHeadHitbox);
                } else if (character == '0') {
                    LeftBorder leftBorder = new LeftBorder(gameView, this);
                    leftBorder.getPosition().updateCoordinates(x, 0);
                    spawnGameObject(leftBorder);
                    toBeAddedToCollidingGameObjectsForPathDecision.add(leftBorder);
                } else if (character == '1') {
                    LeftBorderHouse leftBorderHouse = new LeftBorderHouse(gameView, this);
                    leftBorderHouse.getPosition().updateCoordinates(-65, 129);
                    spawnGameObject(leftBorderHouse);
                } else if (character == '9') {
                    RightBorderGoal rightBorderGoal = new RightBorderGoal(gameView, this);
                    rightBorderGoal.getPosition().updateCoordinates(x, 0);
                    spawnGameObject(rightBorderGoal);
                } else if (character == '7') {
                    RightBorderHouse rightBorderHouse = new RightBorderHouse(gameView, this);
                    rightBorderHouse.getPosition().updateCoordinates(x, y);
                    spawnGameObject(rightBorderHouse);
                }
            }
        }
        background.setNumber(level.number);
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        activateGameObjects();
    }
    protected void initializeLevel() {
        activatableGameObjects.clear();
        destroyAllGameObjects();
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
        clearListsForPathDecisionsInGameObjects();
        moveObjectsToCollidingGameObjectsForPathDecision();
    }

    private void clearListsForPathDecisionsInGameObjects() {
        collidingGameObjectsForPathDecision.clear();
    }

    private void moveObjectsToCollidingGameObjectsForPathDecision(){
        collidingGameObjectsForPathDecision.addAll(toBeAddedToCollidingGameObjectsForPathDecision);
        toBeAddedToCollidingGameObjectsForPathDecision.clear();
    }


    private void activateGameObjects() {
        ListIterator<GameObject> iterator = activatableGameObjects.listIterator();

        while (iterator.hasNext()) {

            GameObject gameObject = iterator.next();

            if (gameObject instanceof EnemyOnre enemyOnre) {
                boolean value = enemyOnre.tryToActivate(hero);
                if (value) {
                    spawnGameObject(enemyOnre);
                    iterator.remove();
                }
            } else if (gameObject instanceof EnemyGotoku enemyGotoku) {
                boolean value = enemyGotoku.tryToActivate(null);
                if (value) {
                    spawnGameObject(enemyGotoku);
                    iterator.remove();
                }
            } else if (gameObject instanceof EnemyYourei enemyYourei) {
                boolean value = enemyYourei.tryToActivate(hero);
                if (value) {
                    spawnGameObject(enemyYourei);
                    iterator.remove();
                }
            } else if (gameObject instanceof CollectableHeroShootingBullet collectableHeroShootingBullet) {
                boolean value = collectableHeroShootingBullet.tryToActivate(hero);
                if (value) {
                    spawnGameObject(collectableHeroShootingBullet);
                    iterator.remove();
                }
            } else if (gameObject instanceof CollectableHeart collectableHeart) {
                boolean value = collectableHeart.tryToActivate(hero);
                if (value) {
                    spawnGameObject(collectableHeart);
                    iterator.remove();
                }
            } else if (gameObject instanceof CollectableCoin collectableCoin) {
                boolean value = collectableCoin.tryToActivate(hero);
                if (value) {
                    spawnGameObject(collectableCoin);
                    iterator.remove();
                }
            } else if (gameObject instanceof BlockWood blockWood) {
                boolean value = blockWood.tryToActivate(hero);
                if (value) {
                    spawnGameObject(blockWood);
                    iterator.remove();
                }
            } else if (gameObject instanceof BlockIron blockIron) {
                boolean value = blockIron.tryToActivate(hero);
                if (value) {
                    spawnGameObject(blockIron);
                    iterator.remove();
                }
            } else if (gameObject instanceof BlockDirt blockDirt) {
                boolean value = blockDirt.tryToActivate(hero);
                if (value) {
                    spawnGameObject(blockDirt);
                    iterator.remove();
                }
            } else if (gameObject instanceof BlockGrass blockGrass) {
                boolean value = blockGrass.tryToActivate(hero);
                if (value) {
                    spawnGameObject(blockGrass);
                    iterator.remove();
                }
            } else if (gameObject instanceof BlockCoin blockCoin) {
                boolean value = blockCoin.tryToActivate(hero);
                if (value) {
                    spawnGameObject(blockCoin);
                    iterator.remove();
                }
            }
        }
    }
}



