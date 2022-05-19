package PaooGame.Items;
import java.awt.*;
import java.awt.image.BufferedImage;
import PaooGame.Graphics.Assets;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */

public class Hero extends Character
{
    private BufferedImage[] image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private boolean[] intocoin;
    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public float jmps;
    private int nr=0;

    public Hero(RefLinks refLink, float x, float y)
    {
        ///Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image=new BufferedImage[11];
        image = Assets.hero;
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        bounds.x=(int) x + 10;
        bounds.y=(int) y + 10;
        bounds.width = Character.DEFAULT_CREATURE_WIDTH - 20;
        bounds.height = Character.DEFAULT_CREATURE_HEIGHT - 10;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;

        //intocoin=new boolean [Map.coins.length];
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update() {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        if (x + xMove < refLink.GetGame().GetWidth() / 2)
            Move(true);
        else {
            if (Map.offset + refLink.GetGame().GetWidth() + xMove < refLink.GetMap().getMapWidth()) {
                Map.offset += xMove;
                Move(false);
            } else {
                Move(true);
            }
        }


    }


    private void GetInput() {
        xMove = 0;
        yMove = 0;
        nr++;
        if (nr > 31)
            nr = 0;
        if (refLink.GetKeyManager().left == true) {
            image = Assets.jump;
        } else if (refLink.GetKeyManager().right == true) {
            image = Assets.jump;
        } else {
            image = Assets.jump;
        }

        if (refLink.GetKeyManager().up) {
            yMove = Yismoving(-speed);

        }
        if (refLink.GetKeyManager().down) {
            yMove = Yismoving(speed);

        }
        if (refLink.GetKeyManager().left) {
            xMove = Xismoving(-speed);

        }
        if (refLink.GetKeyManager().right) {
            xMove = Xismoving(speed);
        }

        if (refLink.GetKeyManager().space == true && floating==false) {

            jmps=-15;
            jmps=Yismoving(++jmps);
            yMove=jmps;
            floating=true;
        }
            jmps=Yismoving(++jmps);
            yMove=jmps;
    }

    @Override
    public void Draw(Graphics g) {

        /*if (floating==true) {
            if (jmps < 0)
                if (refLink.GetKeyManager().space== true)
                    g.drawImage(Assets.jump[nr/10],(int) x,(int) y, width, height, null);
                else
                {
                    g.drawImage(Assets.jump[nr/10], (int) x, (int) y, width, height, null);
                }

        }*/

        if(floating == true)
            g.drawImage(Assets.hero[nr/10],(int) x,(int) y, width, height, null);
        else
            g.drawImage(Assets.jump[nr/10],(int) x,(int) y, width, height, null);

    }

}


