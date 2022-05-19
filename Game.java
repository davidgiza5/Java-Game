package PaooGame;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyManager;
import PaooGame.States.*;
import PaooGame.Tiles.Tile;
import java.awt.*;
import java.awt.image.BufferStrategy;
/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

                ------------
                |           |
                |     ------------
    60 times/s  |     |  Update  |  -->{ actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
        =       |     ------------
     16.7 ms    |           |
                |     ------------
                |     |   Draw   |  -->{ deseneaza totul pe ecran
                |     ------------
                |           |
                -------------
    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.

    In mod obisnuit aceasta clasa trebuie sa contina urmatoarele:
        - public Game();            //constructor
        - private void init();      //metoda privata de initializare
        - private void update();    //metoda privata de actualizare a elementelor jocului
        - private void draw();      //metoda privata de desenare a tablei de joc
        - public run();             //metoda publica ce va fi apelata de noul fir de executie
        - public synchronized void start(); //metoda publica de pornire a jocului
        - public synchronized void stop()   //metoda publica de oprire a jocului
 */

public class Game extends Singletone implements Runnable{

    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************

    private Graphics        g;          /*!< Referinta catre un context grafic.*/

    ///Available states
    public State playState;            /*!< Referinta catre joc.*/
    public State menuState;            /*!< Referinta catre menu.*/
    public State settingsState;        /*!< Referinta catre setari.*/
    public State aboutState;           /*!< Referinta catre about.*/
    private KeyManager keyManager;      /*!< Referinta catre obiectul care gestioneaza intrarile din partea utilizatorului.*/
    private RefLinks refLink;            /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/

    private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/
    public int score = 0;
    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */
    public Game (String title,int width,int height) {
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
        ///Construirea obiectului de gestiune a evenimentelor de tastatura
        keyManager = new KeyManager();
    }
    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame()
    {
        wnd.BuildGameWindow();
        wnd.GetCanvas().addKeyListener(keyManager);
        Assets.Init();
        refLink=new RefLinks(this);
        playState=new PlayState(refLink);
        menuState=new MenuState(refLink);
        settingsState= new SettingsState(refLink);
        aboutState= new AboutState(refLink);
        State.SetState(menuState);

    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    @Override
    public void run() {
        InitGame();
        long oldTime=System.nanoTime();
        long currentTime;
        final int framesPerSecond=60;
        final double timeFrame= 1000000000 / framesPerSecond;
        while (runState==true)
        {
            currentTime=System.nanoTime();
            if ((currentTime-oldTime) > timeFrame)
            {
                Update();
                Draw();
                oldTime=currentTime;
            }
        }
    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized  void StartGame()
    {
        if (runState==false)
        {
            runState=true;
            gameThread=new Thread(this );
            gameThread.start();
        }
        else
        {
            return;
        }

    }
     /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */

    public synchronized void StopGame()
    {
        if (runState==true)
        {
            runState=false;
            try
            {

                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }

        }
        else
        {
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */

    private void Update()
    {
        keyManager.Update();
        if (State.GetState()!=null)
        {
            State.GetState().Update();
        }
        System.out.println("Score:    " + score);

    }

    /*! \fn private void Draw()
       \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

       Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
    */
    private void Draw()
    {
        bs=wnd.GetCanvas().getBufferStrategy();
        if (bs==null)
        {
            try
            {
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch(Exception e)
            {
                e.printStackTrace();

            }
        }

        g=bs.getDrawGraphics();
        g.clearRect(0,0,wnd.GetWndWidth(),wnd.GetWndHeight());
        if (State.GetState()!=null)
        {
            State.GetState().Draw(g);
        }
        bs.show();
        g.dispose();
    }

    public static Game getInstace(String title,int width,int height)
    {
        if (instance==null)
            return new Game(title,width,height);
        return (Game)instance;
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei
     */
    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }

    /*! \fn public int GetHeight()
       \brief Returneaza inaltimea ferestrei
    */
    public int GetHeight()
    {
        return wnd.GetWndHeight();
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza obiectul care gestioneaza tastatura.
     */
    public KeyManager GetKeyManager()
    {
        return keyManager;
    }



}




