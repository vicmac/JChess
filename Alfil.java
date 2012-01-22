public class Alfil extends Pieza{
    
    public Alfil(int x, int y, boolean blanca){
        super(x,y,blanca);
    }
    
    @Override
    public boolean move(int nx, int ny, Casilla tablero[][]){
        
        if (Math.abs(nx-x) != Math.abs(ny-y))
            return false;
        
        else if (tablero[nx][ny].getPieza() != null && tablero[nx][ny].
                getPieza().isBlanca()== tablero[x][y].getPieza().isBlanca())
            return false;
        
        for (int i=x; i!=nx;)
            for (int j=y; j!=ny;){
                if (tablero[i][j].getPieza() != null && i != x && j!=y)
                    return false;
                
                if (nx>x)
                    i++;
                else
                    i--;
                
                if (ny>y)
                    j++;
                else
                    j--;
                
            }
                       
        return true;
    }
    
    @Override
    public Pieza getPieza(){
        return new Alfil(x,y,blanca);
    }
    
    @Override
    public String ClassName(){
        if (isBlanca())
            return "Ab";
                
        else
            return "An";
    }
    
}