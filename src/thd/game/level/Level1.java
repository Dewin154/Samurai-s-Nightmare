package thd.game.level;

/**
 * Public class that manages a level 1.
 */

public class Level1 extends Level{

    /**
     * Creates a new level 1.
     */
    public Level1(){
        this.name = "      Level 1" +"\n"+"\n"+ "A weird morning";
        number = 1;
        worldOffsetColumns = 0;
        worldOffsetLines = 0;
        background = "game_background_1.png";
        if (difficulty.equals(Difficulty.STANDARD)){
            world = """
                                                                                                                                  CHC                              I                                       S                                                                                                \s
                                                                                                                                  III                              I                                       I                                                                                                \s
                               S                                                          NNN            II                                                        I                                                                                                                                        \s
                               I     W     W     W     C                                                  I                                                        I                                                                                                                7                 \s
                                                       I     II    CC                                     I                               III                      I          I   I                                                                                                                         \s
                                                                   II                                    OI                                                        I          I   II                            III                  I   I                                                                  \s
                                                                                                          I                                                    II  I          I   III                                                I   I                                                                  \s
                                           O                              II                             II                      III                           NI  I     Y    I  EIIII                                               I   I                    O                                              \s
                                                                                        RRRRRRR           I                                            WWW      I  I  SCCCCC  I   IIIII                        IIIIIINIIIIIIINIIIIIIII   I        R                     R                                     \s
                           R               R                                            DDDDDDD      Y    I                                      E              I  IIIIIIIIIIII  II   HI                       I                        II        DR         II        RD     NNN                                  \s
                10X       RD               DR                                           DDDDDDD           I                                                     I                 I                            I                         I        DDR                 RDD                                         \s
                         RDD     Y         DDR                   E               G                        I             G                        *              I                 IO                           I  Y O         Y          I        DDDR       Y       RDDD                                         \s
                        RDDD               DDDR            CCCCC                 G                        *             G                                       I                 I                       CCCC I H                       I  SSS   DDDDR   CCCCCCC   RDDDD           9  0                                \s
        G                        G                G                        *     G                        *     WWW     G                        G                        G                     G                        G                       G                       G                        *                                                           \s
                                                                                                                                                                                                                                                                                          \s
                                                                                                                                                                \s
                                                                                                                                                                \s
                                                                                                                                                                \s
                                                                                                                                                               \s""";
        } else if (difficulty.equals(Difficulty.EASY)){
            world = """
                                                                                                                                  CHC                              I                                       S                                                                                                \s
                                                                                                                                  III                              I                                       I                                                                                                \s
                               S                                                          NNN            II                                                        I                                                                                                                                        \s
                               I     W     W     W     C                                                  I                                                        I                                                                                                                7                 \s
                                                       I     II    CC                                     I                               III                      I          I   I                                                                                                                         \s
                                                                   II                                    OI                                                        I          I   II                            III                  I   I                                                                  \s
                                                                                                          I                                                    II  I          I   III                                                I   I                                                                  \s
                                                                          II                             II                      III                           NI  I     Y    I  EIIII                                               I   I                    O                                              \s
                                                                                        RRRRRRR           I                                            III      I  I  SCCCCC  I   IIIII                        IIIIIINIIIIIIINIIIIIIII   I        R                     R                                     \s
                    NNNN   R               R                                            DDDDDDD           I                                      E              I  IIIIIIIIIIII  II   HI                       I                        II        DR         II        RD     NNN                                  \s
                10X       RD               DR                                           DDDDDDD           I                                                     I                 I                            I                         I        DDR                 RDD                                         \s
                         RDD     Y         DDR                   E               G                        I             G                        *              I                 IO                           I  Y O                    I        DDDR       Y       RDDD                                         \s
                        RDDD               DDDR            CCCCC                 G                        *             G                                       I                 I                       CCCC I H                       I  SSS   DDDDR   CCCCCCC   RDDDD           9  0                                \s
        G                        G                G                        *     G                        *     WWW     G                        G                        G                     G                        G                       G                       G                        *                                                           \s
                                                                                                                                                                                                                                                                                          \s
                                                                                                                                                                \s
                                                                                                                                                                \s
                                                                                                                                                                \s
                                                                                                                                                               \s""";
        }

    }

     /*
     Ratio 32x18 with scaling 50
     Self-destructive Objects: Blocks, Collectables after -(2 * GameView.WIDTH) and 10 seconds countdown;
        X = Hero, 3 Lines from Ground
        G = Ground, Ground is Long 24 chars, * indicates end of ground, 6 spaces apart for gap
        Y = EnemyYourei, 1 Lines from Ground where Ground = 0
        O = EnemyOnre, 1 Lines from Ground
        E = EnemyGotoku, 1 Lines from Ground
        C = Collectable coin,  Lines from Ground
        H = Collectable heart,  Lines from Ground
        S = Collectable shuriken,  Lines from Ground
        I = Block of Iron, 5 Blocks from other block for jump
        W = Block of Wood
        D = Block of Dirt
        R = Block of Grass
        N = Block of Coin
        0 = Left Border, y-coordinate is fixed!
        1 = Left Border House, the position is fixed!
        9 = Right Border Goal, y-coordinate is fixed!

        7 = Right Border House, 10 lines from ground
         */
}
