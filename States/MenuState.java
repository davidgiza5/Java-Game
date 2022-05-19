package PaooGame.States;
import PaooGame.RefLinks;
import java.awt.*;


public class MenuState extends State
{
   private int cnt=0;
   private int choice=0;
   private String[] option=
           {
                   "Start",
                   "Settigs",
                   "Quit"
           };
   private Color titlecolor;
   private Font titlefont;
   private Font font;

    public MenuState(RefLinks refLink)
    {
        super(refLink);
        titlecolor=new Color(17, 255, 0);
        titlefont=new Font("Times New Roman",Font.BOLD,30);
    }

    @Override
    public void Update()
    {
        if (cnt==4)
        {
            getInput();
            cnt=0;

        }
        cnt++;
    }


    @Override
    public void Draw(Graphics g)
    {
        g.setColor(titlecolor);
        g.setFont(titlefont);
        g.drawString("Get the coins",370,120);
        g.setFont(font);
        for (int i=0;i<option.length;i++)
        {
            if (i==choice)
            {
                g.setColor(Color.BLACK);
            }
            else
            {
                g.setColor(Color.BLUE);
            }
            g.drawString(option[i],420,180+i*40);
        }
    }
    private void select(){
        if(choice == 0){
            State.SetState(new PlayState(refLink));
        }
        if(choice == 1){
            State.SetState(refLink.GetGame().settingsState);
        }
        if(choice == 2){
            System.exit(0);
        }
    }
    private void getInput(){
        if(refLink.GetKeyManager().enter)
            select();


        if(refLink.GetKeyManager().up){
            choice--;
            if(choice == -1)
            {
                choice = option.length-1;
            }
        }


        if(refLink.GetKeyManager().down){
            choice++;
            if(choice == option.length)
                choice = 0;
        }
    }

    @Override
    public void resetState() {

    }
}
