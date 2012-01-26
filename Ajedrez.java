import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Ajedrez extends JFrame{
	private static final long serialVersionUID = 5;
	
  private int rbx, rby, rnx, rny; //Tracking de la posicion del Rey
  private boolean activo, enroque, turn;  // Pieza seleccionada y si se esta en enrroque
  
  private JPanel panel, info; // Panel = Tablero   Info = Opciones
  private JTextField turno; // Caja de texto que contiene el color de la pieza activa
  private JButton nuevo, guarda, carga, salir, otro;  // Botones Otro = Locura mia
  private Casilla casillas[][], tmp1, tmp2;
  
  /**
    casillas es la representacion interna del tablero, tmp1 y tmp2 son temporales para
    alojar las posiciones de las piezas mientras se realiza un movimiento
  */
    
    
    
	public Ajedrez(){
    super("Ajedrez");
    
    casillas = new Casilla[8][8];
    makePanel();
    
    enroque = activo = false;
    turn = 1;
	}
	
	/* makePanel()
	  Coloca la piezas pertinentes en su sitio adecuado para el inico de una nueva partida
	  Se debe llamar solo al inicio del programa, o al reiniciar una partida (nueva)
	*/
	
	protected void makePanel(){
		for (int i = 0; i<8; i++)
		  for (int j = 0; j<8; j++)
		      casillas[i][j] = new Casilla(i,j);
		  
		  for (int i = 0; i<8; i++){
		    casillas[1][i].setPieza(new Peon(1,i,true));
		    casillas[6][i].setPieza(new Peon(6,i,false));
		  }
		  
		  casillas[0][0].setPieza(new Torre(0,0,true));
		  casillas[0][7].setPieza(new Torre(0,7,true));
		  casillas[7][0].setPieza(new Torre(7,0,false));
		  casillas[7][7].setPieza(new Torre(7,7,false));
		  
		  casillas[0][1].setPieza(new Caballo(0,1,true));
		  casillas[0][6].setPieza(new Caballo(0,6,true));
		  casillas[7][1].setPieza(new Caballo(7,1,false));
		  casillas[7][6].setPieza(new Caballo(7,6,false));
		  
		  casillas[0][2].setPieza(new Alfil(0,2,true));
		  casillas[0][5].setPieza(new Alfil(0,5,true));
		  casillas[7][2].setPieza(new Alfil(7,2,false));
		  casillas[7][5].setPieza(new Alfil(7,5,false));
		  
		  
		  setRey(0,3, true);
		  casillas[0][3].setPieza(new Rey(0,3,true));
		  casillas[0][4].setPieza(new Reina(0,4,true));
		  
		  setRey(7,3, false);
		  casillas[7][3].setPieza(new Rey(7,3,false));
		  casillas[7][4].setPieza(new Reina(7,4,false));
	}
	
	
	/* addListeners()
	  Agrega los listeners necesarios para la correcta ejecucion del programa, se modulariza
	  para soportar posteriormente la opcion de cargar una partida desde archivo...
	*/
	
	protected void addListeners(){
		nuevo.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
		      dispose();
		      new Ajedrez().showGUI();
		    }
		  });
		  
		carga.addMouseListener(new MouseAdapter(){
  		public void mouseClicked(MouseEvent e){
  			Ajedrez nvo = new Ajedrez();
  			if (nvo.cargaPartida()){
  				dispose();
  				nvo.showGUI();
  			}
  			
  			else
  				nvo = null;
  			
  		}
  	});
  	
  	guarda.addMouseListener(new MouseAdapter(){
  		public void mouseClicked(MouseEvent e){
  			guardaPartida();
  		}
  	});
  	
		  
		  salir.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
		      int res = JOptionPane.showConfirmDialog(null, "Desea guardar la partida actual???");
					
					if (res == JOptionPane.OK_OPTION)
						guardaPartida();
					
					else if (res == JOptionPane.NO_OPTION)
						JOptionPane.showMessageDialog(null, "Chido pues!!!\nEmpiezele de cero a la siguiente...");
						
					if (res != JOptionPane.CANCEL_OPTION)
						System.exit(0);
		    }
		  });
		  
		  for (int i = 0, j; i<8; i++)
		    for (j = 0; j<8; j++){
		    	casillas[i][j].addMouseListener(new MouseAdapter(){

						/* TODO: Reestructurar toda la funcion del movimiento */
						
		        @Override
		        public void mouseClicked(MouseEvent E){
		        
		          if (E.getButton() != MouseEvent.MOUSE_BUTTON_3 && !activo){
		          	tmp1 = (Casilla) E.getComponent();
		          	
		          	if (tmp1.getPieza() != null && ){
		          		activo = true;
		          	}
		          	
		          }
		          
		          
		        } // Funcion Sobreescrita mouseClicked()
		        
		      }); //Clase Anonima (Listener)
		      
		    } // for de asignacion de Listener
		    
	}  //Metodo de clase
	
	
	protected void showGUI(){
    setLayout(new FlowLayout());
    
    info = new JPanel();
    turno = new JTextField(5);
    nuevo = new JButton("Nuevo");
    otro = new JButton();
    salir = new JButton("Salir");
    guarda = new JButton("Guardar");
    carga = new JButton("Cargar");
    
    turno.setEditable(false);
    
    if (turn == 1)
	    turno.setText("Blancas");
	   else 
	   	turno.setText("Negras");
    
    info.setLayout(new GridLayout(6,1));
    
    
    //otro es para un icono extra, puede ser removido sin problemas
    otro.setIcon(new ImageIcon("lerolero.gif"));
    otro.setEnabled(false);
    otro.setDisabledIcon(new ImageIcon("lerolero.gif"));
    
    info.add(turno);
    info.add(nuevo);
    info.add(salir);
    info.add(carga);
    info.add(guarda);
    info.add(otro);
    
    panel = new JPanel();
    
    panel.setLayout(new GridLayout(8,8));
    
    addListeners();
    
    for (int i = 0; i<8; i++)
    	for (int j = 0; j<8; j++)
    		panel.add(casillas[i][j]);

    
    add(panel);
    add(info);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    pack();
    setVisible(true);
	}   
	
	protected boolean cargaPartida(){
		int res;
		boolean status = true;
  	
  	JFileChooser archivo = new JFileChooser("Cargar partida guardada");
  	res = archivo.showOpenDialog(this);
  	
  	if (res == JFileChooser.APPROVE_OPTION){
  		File file = archivo.getSelectedFile();
  		
			try{
				ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()));
				Object var;
				
				var = entrada.readObject();
				
				if (var instanceof Casilla[][])
					casillas = (Casilla[][])var;
				else{
					JOptionPane.showMessageDialog(null, "Archivo no correspondiente al programa.\nIntenta de nuevo...\nCasillas");
					status = false;
				}
				
				var = entrada.readObject();	
				if (var instanceof Integer)
					turn = (Integer)var;
				else{
					JOptionPane.showMessageDialog(null, "Archivo no correspondiente al programa.\nIntenta de nuevo...\nturn");
					status = false;
				}
				
				entrada.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo");
			}
			
			finally{
				return res == JFileChooser.APPROVE_OPTION && status;
			}
			
  	}
  	
  	return false;
  }

	
	private boolean guardaPartida(){
  	int res;
  	boolean retval = true;
  	
  	JFileChooser archivo = new JFileChooser("Guardar partida actual");
  	res = archivo.showSaveDialog(this);
  	
  	if (res == JFileChooser.APPROVE_OPTION){
  		File file = archivo.getSelectedFile();
  		
			try{
				ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()));
				salida.writeObject(casillas);
				salida.writeObject(new Integer(turn));
				salida.flush();
				salida.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo");
				e.printStackTrace();
				retval = false;
			}
			
			finally{
				return res != JFileChooser.CANCEL_OPTION && retval;
			}
			
  	}
  	
  	return false;
  	
  }
    
  protected void alerta(int opc){
      String msg = "";
      
      switch (opc){
          case 1:
              msg = "No es tu pieza, selecciona otra";
              break;
              
          case 2:
              msg = "Movimiento invalido. \nVuelve a intentarlo";
              break;
              
          case 3:
              msg = "Casilla vacia, selecciona una pieza";
              break;
              
          case 4:
              msg = "Estas en jaque!!!\nMueve otra pieza";
              
      }
      
      JOptionPane.showMessageDialog(null, msg);
  }
  
  protected final void setRey(int x, int y, boolean c){
      
      if (c){
          rbx = x;
          rby = y;
      }
              
      else{
          rnx = x;
          rny = y;
      }
      
  }
  
  protected int jaque(){
      int st=0, i, j;
      Pieza temp;
      
      for (i = 0; i<8; i++){
          for (j = 0; j<8; j++){
              if (casillas[i][j].getPieza() == null)
                  continue;
              
              temp = casillas[i][j].getPieza();
              
              if (temp.isBlanca() && temp.move(rnx, rny, casillas))
                  st = 1;

              
              else if (!temp.isBlanca() && temp.move(rbx, rby, casillas))
                  st = 2;

          }
          
          if (st != turn && st != 0){
              alerta(4);
              break;
          }
          
      }
      
     return st; 
  }
    
  protected boolean enroq(){
      
    if (tmp2.getPieza() != null){
        return false;
    }
    
    else if (tmp1.getPieza().getMov() != 0){
        return false;
    }
    
    else if (tmp1.getx() - tmp2.getx() != 0){
        return false;
    }
    
    else if (Math.abs(tmp1.gety() - tmp2.gety()) != 2){
        return false;
    }
    
    for (int j = tmp1.gety(), i = tmp1.getx(), k = tmp2.gety();
            !(casillas[i][j].getPieza() instanceof Torre); ){
        if (casillas[i][j].getPieza() != null && j != tmp1.gety()){
            return false;
        }
        
        if (k>tmp1.gety())
            j++;
        else
            j--;
        
        if (j>7 || j<0){
            return false;
        }
    }
    
    return true;
  }
  
  protected void cTurno(){
      if (turn == 1){
          turn = 2;
          turno.setText("Negras");
      }
      
      else{
          turn = 1;
          turno.setText("Blancas");
      }
      
  }
  
  /*
  public void Revive(){
      final JFrame win = new JFrame("Revivir Pieza");
      final JComboBox sel = new JComboBox();
      JButton OK = new JButton("OK");
      boolean v = true;

      sel.addItem("Gatito");
      sel.addItem("Perrito");

      OK.addMouseListener(new MouseAdapter(){

          @Override
          public void mouseClicked(MouseEvent E){
              String p = "";

              p = (String) sel.getSelectedItem();
              System.out.println(p);

              if (p.equals("Gatito"))
                  tmp = new Reina(tmp2.getx(), tmp2.gety(), 
                          tmp2.getPieza().isBlanca());
              else
                  tmp = new Alfil(tmp2.getx(), tmp2.gety(), 
                          tmp2.getPieza().isBlanca());

              win.dispose();
          }
          
      });

      win.setBounds(400, 300, 250, 80);
      win.setLayout(new FlowLayout());
      win.add(sel);
      win.add(OK);

      win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      win.setVisible(true);


  }*/
  
  public static void main(String args[]){
      new Ajedrez().showGUI(); 
      
  }
    
}
