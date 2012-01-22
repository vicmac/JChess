public class Torre extends Pieza{
    
    public Torre(int x, int y, boolean blanca){
        super(x,y,blanca);
    }
    
    @Override
    public boolean move(int nx, int ny, Casilla tablero[][]){
        
        if (tablero[nx][ny].getPieza() != null && tablero[nx][ny].getPieza()
                .isBlanca() == tablero[x][y].getPieza().isBlanca())
            return false;
        
        
                
        else if (!((Math.abs(nx-x) > 0 && ny-y == 0) || (Math.abs(ny-y) > 0 && nx-x == 0)))
          return false;
        
        else if (Math.abs(nx-x) > 0 && ny-y == 0)
            for (int i = x; i!=nx;){
                if (tablero[i][ny].getPieza() != null && i != x)
                  return false;
                
                if (nx > x)
                    i++;
                else
                    i--;
            }
        
        else if (Math.abs(ny-y) > 0 && nx-x == 0)
            for (int i = y; i!=ny;){
                if (tablero[nx][i].getPieza() !=null && i != y)
                  return false;
                
                if (ny > y)
                    i++;
                else
                    i--;
            }
        
        
        return true;
    }
    
    @Override
    public Pieza getPieza(){
        return new Torre(x,y,blanca);
    }
    
    @Override
    public String ClassName(){
        if (isBlanca())
            return "Tb";
                
        else
            return "Tn";
    }
}