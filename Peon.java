public class Peon extends Pieza{
    
    public Peon(int x, int y, boolean blanca){
        super(x,y,blanca);
    }
    
    
    @Override
    public boolean move(int nx, int ny, Casilla tablero[][]){
        
        if (this.isBlanca() && nx<=x)
            return false;
        
        else if (!(this.isBlanca()) && nx>=x)
            return false;
                        
        else if (tablero[nx][ny].getPieza() != null){
            if (tablero[nx][ny].getPieza().isBlanca() == 
                    tablero[x][y].getPieza().isBlanca())
                return false;
            
            else if (Math.abs(nx-x) != 1 || Math.abs(ny-y) != 1)
                return false;
        }
                
        else if (mov==0){
            if (Math.abs(nx-x) > 2 || ny-y != 0)
                return false;
        }
                
                
        else if (Math.abs(nx-x) != 1 || ny-y != 0)
            return false;
        
        
        return true;
    }
    
    @Override
    public Pieza getPieza(){
        return new Peon(x,y,blanca);
    }
    
    @Override
    public String ClassName(){
        if (isBlanca())
            return "Pb";
                
        else
            return "Pn";
    }
}