package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {
    private static final int NO_TILES = 128;
    public static Tile[] tiles = new Tile[NO_TILES];

    public static Tile earth = new earth(27);
    public static Tile goldencoin = new goldencoin(63);
    public static Tile concrete = new concrete(20);
    public static Tile grass = new grass(15);
    public static Tile wood = new wood(21);
    public static Tile silvercoin = new silvercoin(75);
    public static Tile stones = new stones(39);
    public static Tile roundgrass = new roundgrass(18);
    public static Tile door1 = new door1(95);
    public static Tile door2 = new door1(107);

    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;

    protected BufferedImage img;
    protected final int id;

    public Tile(BufferedImage image, int idd) {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    public void Update() {


    }

    public void Draw(Graphics g, int x, int y) {
        //GetTile();
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);

    }

    public boolean IsSolid() {
        return false;

    }

    public int GetId() {

        return id;
    }


}


