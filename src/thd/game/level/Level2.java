package thd.game.level;


/**
 * Public class that manages a level 2.
 */
public class Level2 extends Level {

    /**
     * Creates new level 2.
     */
    public Level2() {
        this.name = "   Level 2" +"\n"+"\n"+ " Up in the \nmountains";
        number = 2;
        worldOffsetColumns = 0;
        worldOffsetLines = 0;
        background = "game_background_2.png";
        if (difficulty.equals(Difficulty.STANDARD)){
            world = """
                                                                                   IIIIIIIIIIIIIIIIIIIIIII                                                                                                          D                                                  N                                              \s
                                                  CCC                              IN                    I                                                                                                          D                                 O                                                              \s
                                                  III                              I                     I Y    III                                      S                                                          D  C              ININI                                  7                                                \s
                                                                                   I      O              I      I                                       WWW                                                         D  RRR                           CR                                                                \s
                                                                O                  IH         CCCC    I  II     I                                                                                                   D   DDR                         CRD               WWW                                                     \s
                                           III                                     IIIIIIIIIIIIIIIIIIII II      I                                                                            O                  O   D   DDDR                    Y  CRDDR                                                                      \s
                                                              III                                        I      I                    E                          E                                                   D   DDDDR                      RDDDD                                                                      \s
                                                                                 N                    E  I    Y I                                                                          G                        D  CDDDDDG                        DDR                                                                                            \s
                                  ININI                                                                  I      I            RRRRRRRR          NNN           RRRRRRRRRRRR                  G                        D  RDDDDDG                        DDD     III                                                                                        \s
                                                                                   IIIIIIIIIIIIIIIIIIIIIII     IIN           DDDDDDDDR                       DDDDDDDDDDDD                                           D   DDDDDG                        DDD                                                                 \s
                        10X                                                                                     I            DDDDDDDDDR                              DDDD            W                                  DDDDDG                        DDD                                                                  \s
                                    E                                                                           I            DDDDDDDDDDR                             DDDD     III                                       DDDDDG                        DDD                 9     0                                                 \s
                                                                 WWW       II              SSS                  I            DDDDDDDDDDDR                           HDDDD                          CCCCCC               DDDDDG                        DDDG                        G                        D                                                               \s
                G                        *                                  IIG                        *        IWW    G                        G                        *                 G                        RRRRDDDDDG                        DDDG                        G                        D                                                                                               \s
                                                                                                       IIIIIIIIII                                                                                                   DDDDDDDDDG                        DDDG                        G                        D                                                                                                \s
                                               \s
                                               \s
                                               \s
                                                                                                                                                                                                                                            """;
        } else if (difficulty.equals(Difficulty.EASY)){
            world = """
                                                                                   IIIIIIIIIIIIIIIIIIIIIII                                                                                                          D                                                  N                                              \s
                                                  CCC                              IN                    I                                                                                                          D                                 O                                                              \s
                                                  III                              I                     I Y    III                                      S                                                          D  C              ININI                                  7                                                \s
                                                                                   I      Y              I      I                                       WWW                                                         D  RRR                           CR                                                                \s
                                                                                   IH         CCCC    I  II     I                                                                                                   D   DDR                         CRD               WWW                                                     \s
                                           III                                     IIIIIIIIIIIIIIIIIIII II      I                                                                            O                  H   D   DDDR                    Y  CRDDR                                                                      \s
                                                              III                                        I      I                    E                          E                                                   D   DDDDR                      RDDDD                                                                      \s
                                                                                 N                    E  I      I                                                                          G                        D  CDDDDDG                        DDR                                                                                            \s
                                  ININI                                                                  I      I            RRRRRRRR          NNN           RRRRRRRRRRRR                  G                        D  RDDDDDG                        DDD     III                                                                                        \s
                                                                                   IIIIIIIIIIIIIIIIIIIIIII     IIN           DDDDDDDDR                       DDDDDDDDDDDD                                           D   DDDDDG                        DDD                                                                 \s
                        10X                                                                                     I            DDDDDDDDDR                              DDDD            W                                  DDDDDG                        DDD                                                                  \s
                                    E                                                                           I            DDDDDDDDDDR                             DDDD     III                                       DDDDDG                        DDD                 9     0                                                 \s
                                                                 WWW       II              SSS                  I            DDDDDDDDDDDR                           HDDDD                          CCCCCC               DDDDDG                        DDDG                        G                        D                                                               \s
                G                        *                                  IIG                        *        IWW    G                        G                        *                 G                        RRRRDDDDDG                        DDDG                        G                        D                                                                                               \s
                                                                                                       IIIIIIIIII                                                                                                   DDDDDDDDDG                        DDDG                        G                        D                                                                                                \s
                                               \s
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
