package PaooGame.States;
import PaooGame.RefLinks;
import java.awt.*;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    private String[] commands=
            {
                    "Left--->A   ",
                    "Right--->D   ",
                    "Jump--->SPACE",
                    "Exit--->ESC"
            };

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public SettingsState(RefLinks refLink)
    {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        getInput();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.setFont(new Font("Times New Roman", Font.BOLD,30));
        g.drawString("Settings", 380, 80);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 15));
        for(int i = 0; i < commands.length; i++) {
            g.drawString(commands[i], 380, 150 + i * 40);
        }
    }
    private void getInput(){
        if(refLink.GetKeyManager().escape == true){
            State.SetState(refLink.GetGame().menuState);
        }
    }

    @Override
    public void resetState() {

    }
}
