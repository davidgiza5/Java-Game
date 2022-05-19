package PaooGame.Items;
import PaooGame.RefLinks;
import java.awt.*;


public abstract class Item {

    ///asa cum s-a mai precizat, coordonatele x si y sunt de tip float pentru a se elimina erorile de rotunjire
    ///ce pot sa apara in urma calculelor, urmand a se converti la intreg doar in momentul desenarii.
    protected float x;                  /*!< Pozitia pe axa X a "tablei" de joc a imaginii entitatii.*/
    protected float y;                  /*!< Pozitia pe axa Y a "tablei" de joc a imaginii entitatii.*/
    protected int width;                /*!< Latimea imaginii entitatii.*/
    protected int height;               /*!< Inaltimea imaginii entitatii.*/
    protected Rectangle bounds;         /*!< Dreptunghiul curent de coliziune.*/
    protected Rectangle normalBounds;   /*!< Dreptunghiul de coliziune aferent starii obisnuite(spatiul ocupat de entitate in mod normal), poate fi mai mic sau mai mare decat dimensiunea imaginii sale.*/
    protected Rectangle attackBounds;   /*!< Dreptunghiul de coliziune aferent starii de atac.*/
    protected RefLinks refLink;         /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/


    public Item(RefLinks refLink,float x,float y,int width, int height)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.refLink=refLink;

        normalBounds=new Rectangle(0,0,width,height);
        attackBounds=new Rectangle(0,0,width,height);
        bounds=normalBounds;
    }

    public abstract void Update();
    public abstract void Draw(Graphics g);

    public float GetX()
    {
        return x;
    }

    public float GetY()
    {
        return y;
    }

    public int GetWidth()
    {
        return width;
    }

    public int GetHeight() { return height; }

    public void SetX(float x)
    {
        this.x=x;
    }

    public void SetY(float y)
    {
        this.y=y;
    }

    public void SetWidth(int width) { this.width=width; }


    public void SetHeight(int height)
    {
        this.height=height;
    }

    public void SetNormalMode()
    {
        bounds=normalBounds;
    }

    public void SetAttackMode()
    {
        bounds=attackBounds;
    }


}
