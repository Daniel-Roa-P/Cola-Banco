
package colabanco;

public class ListaCircular {
    
    private Nodo cabeza;
    private Nodo ultimo;
    private int tamaño;
    private String cadenaLista, eliminado, pivote;
    
    ListaCircular(){
        
       this.cabeza = null; 
       this.ultimo = null; 
       this.tamaño = 0;
       
    }
    
    void insertar(String s, int n){
        
        Nodo p = new Nodo(s, n);
        Nodo sig = null;
        
        if(cabeza == null){
            
            cabeza = p;
            ultimo = p;
            cabeza.setSiguiente(ultimo);
            
        } else {
            
            ultimo.setSiguiente(p);

            p.setSiguiente(cabeza);

            ultimo = p;
            
        }
        
        tamaño ++;
        
    }
    
    public String imprimir(){
    
        cadenaLista = "";
        
        Nodo q = cabeza;
        
        while(true){
        
            cadenaLista = cadenaLista + q.getLlave() + " tareas: " + q.getTareas() + ",";
            
            q = q.getSiguiente();
            
            if(q == ultimo){
                
                if(!q.equals(cabeza)){
                
                    cadenaLista = cadenaLista + q.getLlave() + " tareas: " + q.getTareas() + ",";
                    
                }

                break;
                
            }
            
        }  
        
        return cadenaLista;
        
    }
    
    public void eliminar(Nodo nodoAtendido){
        
        Nodo anterior = null;
        Nodo actual = nodoAtendido;
                
        if(actual.equals(ultimo)){

            ultimo = anterior;
            ultimo.setSiguiente(actual.getSiguiente());
            actual = null;
            tamaño--;

        }else if(actual.equals(cabeza)) {

            cabeza = actual.getSiguiente();
            ultimo.setSiguiente(cabeza);
            actual = null;
            tamaño--;

        } else {

            anterior.setSiguiente(actual.getSiguiente());
            actual = null;
            tamaño--;

        }
        
    }

    public void intercambiar(Nodo nodoAtendido){
    
        cabeza = nodoAtendido.getSiguiente();
        ultimo.setSiguiente(nodoAtendido);
        
        ultimo = nodoAtendido;
        ultimo.setSiguiente(cabeza);
    
    }
    
    public int getTamaño() {
        return tamaño;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public String getEliminado() {
        return eliminado;
    }

    public String getPivote() {
        return pivote;
    }
    
}
