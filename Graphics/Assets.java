package PaooGame.Graphics;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */


public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.

    public static BufferedImage concrete;
    public static BufferedImage door1;
    public static BufferedImage door2;
    public static BufferedImage earth;
    public static BufferedImage goldencoin;
    public static BufferedImage grass;
    public static BufferedImage roundgrass;
    public static BufferedImage silvercoin;
    public static BufferedImage stones;
    public static BufferedImage wood;
    public static BufferedImage background;
    public static BufferedImage[] coins;
    public static BufferedImage[] hero;
    public static BufferedImage[] jump;
    public static BufferedImage[] idle;

/*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */


    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Complete Sunday Job Tileset.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage("/textures/coins/coin_gold.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/3 Dude_Monster/Dude_Monster_Jump_8.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/3 Dude_Monster/Dude_Monster_Idle_4.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        concrete = sheet.crop(7, 1, 1);
        door1 = sheet.crop(10, 7, 1);
        door2 = sheet.crop(10, 8, 1);
        earth = sheet.crop(2, 2, 1);
        goldencoin = sheet.crop(2, 5, 1);
        grass = sheet.crop(2, 1, 1);
        roundgrass = sheet.crop(5, 1, 1);
        silvercoin = sheet.crop(2, 6, 1);
        stones = sheet.crop(2, 3, 1);
        wood = sheet.crop(8, 1, 1);

        background = ImageLoader.LoadImage("/textures/beach.png");

        coins = new BufferedImage[8];
        for (int i = 0; i < coins.length; ++i) {
            coins[i] = sheet1.crop(i, 0, 1);
        }

        hero = new BufferedImage[8];
        for (int i = 0; i < hero.length; ++i) {
            hero[i] = sheet2.crop(i, 0, 1);
        }

            jump = new BufferedImage[4];
            for (int i = 0; i < jump.length; ++i) {
                jump[i] = sheet3.crop(i, 0, 1);



            }
        }
    }
