import javax.swing.*;

public class Casilla extends JButton{
    private Pieza pieza;
    private final int x,y;
    
    public Casilla(int x, int y){
        setPieza(null);
        this.x = x;
        this.y = y;
    }
    
    public final void setPieza(Pieza pieza){
        this.pieza = pieza;   
        
        if (pieza != null)
            setIcon(new ImageIcon(pieza.ClassName() + ".gif"));
                
        else
            setIcon(null);
        
    }
    
    public final Pieza getPieza(){
        return pieza;
    } 
    
    public final int getx(){
        return x;
    }
    
    public final int gety(){
        return y;
    }
    
}