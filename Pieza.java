public abstract class Pieza {
    protected final boolean blanca;
    protected int x,y;
    protected int mov;
    
    public Pieza(int x, int y, boolean blanca){
        setX(x);
        setY(y);
        this.blanca = blanca;
        mov = 0;
    }
    
    public abstract boolean move(int nx, int ny, Casilla tablero[][]);
    
    public abstract String ClassName();
    
    public abstract Pieza getPieza();
    
    
    public final void setX(int x){
        this.x = x;
    }
    
    public final void setY(int y){
        this.y = y;
    }
    
    public final int getX(){
        return x;
    }
    
    public final int getY(){
        return y;
    }
    
    public final boolean isBlanca(){
        return blanca;
    }
    
    public final void move(){
        mov++;
    }
    
    public final void deMove(){
        mov--;
    }
    
    public final int getMov(){
        return mov;
    }
    
    public final void setMov(int m){
        mov = m;
    }
}