package PaooGame.Items;
import PaooGame.Graphics.Assets;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import java.awt.*;

public class Coins extends Character {

    public boolean isthere=true;
    public boolean grabbed=false;
    private int anm=0;
    private int nr=0;

    public Coins(RefLinks refLink,float x,float y)
    {
        super(refLink,x,y,Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void Update(){
        nr++;
        if(nr>70)
            nr=0;
        if(grabbed=true)
        {
            anm++;
            if(anm>35)
            {
                grabbed=false;
                anm=0;
            }
        }
    }
    @Override
    public void Draw(Graphics g){
        if(isthere==true)
            g.drawImage(Assets.coins[nr/10],(int) x - Map.offset, (int)y, width, height, null);
        if(grabbed==true)
            g.drawImage(Assets.coins[anm/10], (int) x - Map.offset, (int) y, width, height, null);
    }

    public Rectangle getCoinRectangle(){return new Rectangle((int)x+5- Map.offset,(int)y+5,width-5,height-5);}

    }

