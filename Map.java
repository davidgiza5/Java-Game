package PaooGame.Maps;
import PaooGame.Items.Coins;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import java.awt.*;


public class Map {
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private static int[][] tiles; /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    public static int offset = 0;
    public static Coins[] coins;


    public void putCoins(){
        coins=new Coins[8];
        for(int i=0;i<8;++i)
            coins[i]=new Coins(refLink,10,120-i*20);

        for(int i=0;i<8;++i)
            coins[i]=new Coins(refLink,440+i*8, 250-i*12);
    }

    public Map(RefLinks refLink) {
        this.refLink = refLink;
        LoadWorld();
        putCoins();
    }

    public void Update() {
        for (int i = 0; i < coins.length; i++)
            coins[i].Update(); }

    public void Draw(Graphics g) {
        g.drawImage(Assets.background, 0, 0, null);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Lifes", 100, 100);

        if (offset < 0)
            offset = 0;

        for (int y = 0; y < refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT; y++) {
            for (int x = 0 + (offset / Tile.TILE_WIDTH); x < (refLink.GetGame().GetWidth() + offset) / Tile.TILE_WIDTH + 1; x++) {

                if (GetTile(x, y) != null)
                    GetTile(x, y).Draw(g, (int) x * Tile.TILE_HEIGHT - offset, (int) y * Tile.TILE_WIDTH);

            }
        }
        for (int i = 0; i < coins.length; i++)
            coins[i].Draw(g);


    }

    public static Tile GetTile(int x, int y) {
        if (tiles[x][y] == 0)
            return null;
        Tile t = Tile.tiles[tiles[x][y]];
        return t;

    }

    private void LoadWorld() {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 55;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 15;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = MiddleEastMap(y, x);
            }
        }
    }

    private int MiddleEastMap(int x, int y) {

        final int map[][] = {
                {20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20},
                {20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20},
                {20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20},
                {20,0,0,0,0,0,0,0,0,21,21,21,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20},
                {20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,0,20 },
                {20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,27,15,20},
                {20,0,0,0,0,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,27,27,27,20},
                {20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,15,27,27,27,27,20},
                {20,0,0,0,0,0,0,0,21,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,27,0,0,0,0,0,0,20},
                {20,0,0,0,0,0,21,0,0,0,0,21,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,27,27,0,0,0,0,0,0,20},
                {20,15,15,0,21,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,21,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,27,27,27,0,0,0,0,0,0,20},
                {27,27,27,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,21,0,21,0,18,0,18,0,18,0,18,0,18,0,18,0,18,0,39,39,39,39,39,39,39,0,39,0,39,0,20,},
                {27,27,27,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,95},
                {27,27,27,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,75,0,107},
                {27,27,27,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15}};

        return map[x][y];
    }

    public int getMapWidth()
    {
        return width*Tile.TILE_WIDTH;
    }

    public boolean getSolid(float w, float h) {
        try {
            return tiles[(int)((w + offset) / Tile.TILE_HEIGHT)][(int)(h / Tile.TILE_WIDTH)] != 0;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }



}
