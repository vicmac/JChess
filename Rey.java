public class Rey extends Pieza{
    
    
    public Rey(int x, int y, boolean blanca){
        super(x,y,blanca);
    }
    
    @Override
    public boolean move(int nx, int ny, Casilla tablero[][]){
        
        if (tablero[nx][ny].getPieza() !=null && tablero[nx][ny].getPieza()
                .isBlanca() == tablero[x][y].getPieza().isBlanca())
            return false;
        
        else if (Math.abs(nx-x)>1 || Math.abs(ny-y)>1)
            return false;
        
        return true;
    }
    
    @Override
    public Pieza getPieza(){
        return new Rey(x,y,blanca);
    }
    
    @Override
    public String ClassName(){
        if (isBlanca())
            return "Ryb";
                
        else
            return "Ryn";
    }
    
}