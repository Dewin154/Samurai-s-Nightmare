package thd.game.level;

/**
 * Public class that manages level 3.
 */

public class Level3 extends Level {

    /**
     * Creates a new level 3.
     */
    public Level3() {
        this.name = "      \n\n\n\n\n\n\n\nLevel 3" +"\n"+"\n"+ "Skyfall";
        number = 3;
        worldOffsetColumns = 0;
        worldOffsetLines = 0;
        background = "game_background_3.png";
        if (difficulty.equals(Difficulty.STANDARD)){
            world = """
                                             N                                                   NNN                                                                                                                                                             IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII                                         \s
                                                                                 E                                           H                                              O                                                                                    I                            NNN       I                                \s
                    X                                                                                                       WWW                                                                                                                                  I                                      I                                         \s
                                                                           IWWWWWI     IIII                                          C                                      I                                                                         III        I    S                                OI                  7                    \s
                                     O      III                                                  III                                WWW                                                                                                         C                I   II                                 I  I                                        \s
                0   IIII                                                                                                                                                                                                                        W                I   I                O       III      II  I                                             \s
                                    III                                                                                                                                     O                                                                                    I   I               E                  I  I                                         \s
                                                                      CCC                                                   CCC               E R     RC                                                  III                             C                      I   I                IIII              I  I                                            \s
                            III                                       III                                    Y              WWW                RD     DRC                   I    H                                                        W                      I  II      NNN      II  II             II I                                            \s
                                                                                                          I      I                            RDD     DDRC                     IIIII                               NNN                                           I   I              II    II            I  I                                            \s
                                                                                                          IIIIIIII                           RDDD     DDDRC                                       III                             IIII                               I             II      II              I                                             \s
                                                          S                                       W                                         RDDDD     DDDDRC                O                                                                                        I    Y       II        II        Y    I                                               \s
                                                    WWWWWWWWWWWWWW             IIIII      W                                                RDDDDD     DDDDDR                                                                                                         I     S     II          II     H      I            9     0                                 \s
                                                                                                                       G                        D     G             G                       *                     IIIII     I                                   IIIIIIIIIIIIIIIIII            IIIIIIIIIIIIII     G                       *                  \s
                                                                                                                                                D                                                                                                                                                                                                       \s
                                                         O                                      O                    O                          D  O                                                                                                                                                                                                         \s
                                               \s
                                               \s
                                                                                                                                                                                                                                            """;
        } else if (difficulty.equals(Difficulty.EASY)){
            world = """
                                             N                                                   NNN                                                                                                                                                             IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII                                         \s
                                                                                 E                                           H                                              O                                                                                    I                            NNN       I                                \s
                     X                                                                                                      WWW                                                                                                                                  I                                      I                                         \s
                                                                           IWWWWWI     IIII                                          C                                      I                                                                         III        I    S                                CI                  7                    \s
                                            III                                                  III                                WWW                                                                                                         C                I   II                                 I  I                                        \s
                0   IIII                                                                                                                                                                                                                        W                I   I                O       III      II  I                                             \s
                                    III                                                                                                                                                                                                                          I   I               E                  I  I                                         \s
                                                                      CCC                                                   CCC               E R     RC                                                  III                             C                      I   I                IIII              I  I                                            \s
                            III                                       III                                    Y              WWW                RD     DRC                        H                                                        W                      I  II      NNN      II  II             II I                                            \s
                                                                                                          I      I                            RDD     DDRC                     IIIII                               NNN                                           I   I              II    II            I  I                                            \s
                                                                                                          IIIIIIII                           RDDD     DDDRC                                       III                             IIII                               I             II      II              I                                             \s
                                                          S                                       W                                         RDDDD     DDDDRC                O                                                                                        I    Y       II        II        Y    I                                               \s
                                                    WWWWWWWWWWWWWW             IIIII      W                                                RDDDDD     DDDDDR                                                                                                         I     S     II          II     H      I            9     0                                 \s
                                                                                                                       G                        D     G             G                       *                     IIIII     I                                   IIIIIIIIIIIIIIIIII            IIIIIIIIIIIIII     G                       *                  \s
                                                                                                                                                D                                                                                                                                                                                                       \s
                                                                                                                                                D  O                                                                                                                                                                                                         \s
                                               \s
                                               \s
                                                                                                                                                                                                                                            """;
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
