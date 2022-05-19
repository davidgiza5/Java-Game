package PaooGame.Items;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;

/*! \class public abstract class Character extends Item
    \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

    Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
    miste/deplaseze in urma calculelor.
 */
public abstract class Character extends Item
{
    public static final int DEFAULT_LIFE            = 1;   /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 3.0f; /*!< Viteza implicita a unui caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 32;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 32;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int life;     /*!< Retine viata caracterului.*/
    protected float speed;  /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;  /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;  /*!< Retine noua pozitie a caracterului pe axa Y.*/
    protected boolean floating=false;

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
        ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
        //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului
     */
    public void Move(boolean xmv)
    {
        ///Modifica pozitia caracterului pe axa X.
        ///Modifica pozitia caracterului pe axa Y.
        if (y>refLink.GetGame().GetHeight())
            State.SetState(refLink.GetGame().menuState);
        if (xmv==true)
            MoveX();
            MoveY();
    }
    public float Xismoving(float d){
        float h;
        for (h=bounds.y ; h<=(bounds.y+bounds.height); h+=bounds.height/2.)
        {
                if (d>0 && refLink.GetMap().getSolid(bounds.x + bounds.width + d, h) || d<0 && refLink.GetMap().getSolid(bounds.x + d, h))
                        return 0;
        }

                        return d;
    }

    public float Yismoving(float d){
        float w;
        for (w=bounds.x ; w<=(bounds.x+bounds.width); w+=bounds.width/2.)
        {
                if (d > 0 && refLink.GetMap().getSolid(w, bounds.y + bounds.height + d))
            {
                    floating = false;
                    return 0;
            }
                if (d < 0 && refLink.GetMap().getSolid(w, bounds.y + d) )
            {
                    return 0;
            }
        }
                    return d;
    }


    /*! \fn public void MoveX()
          \brief Modifica pozitia caracterului pe axa X.
       */

    public void MoveX()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        x += xMove;
        bounds.x += xMove;
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
        ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        y += yMove;
        bounds.y += yMove;
    }

    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife() { return life; }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed() { return speed; }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
     */
    public void SetLife(int life) { this.life = life; }

    /*! \fn public void SetSpeed(float speed)
        \brief
     */
    public void SetSpeed(float speed) { this.speed = speed; }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove() { return xMove; }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */

    public float GetYMove() { return yMove; }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
     */
    public void SetXMove(float xMove) { this.xMove=xMove; }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
     */
    public void SetYMOve(float yMove) { this.yMove=yMove; }
}

