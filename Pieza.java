import java.io.*;

public abstract class Pieza implements Serializable{
    protected boolean blanca;
    protected int x,y;
    protected int mov;
    
    public Pieza(int x, int y, boolean blanca){
        setX(x);
        setY(y);
        this.blanca = blanca;
        mov = 0;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
    	out.writeObject(blanca);
    	out.writeObject(x);
    	out.writeObject(y);
    	out.writeObject(mov);
    }
    
		private void readObject(ObjectInputStream in)throws IOException, ClassNotFoundException{
			Object var;
			
			var = in.readObject();
			if (var instanceof Boolean)
				blanca = (Boolean) var;
				
			var = in.readObject();
			if (var instanceof Integer)
				x = (Integer) var;
			
			var = in.readObject();
			if (var instanceof Integer)
				y = (Integer) var;
				
			var = in.readObject();
			if (var instanceof Integer)
				mov = (Integer) var;
				
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
