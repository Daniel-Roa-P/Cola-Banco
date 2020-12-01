
package colabanco;

public class Nodo {
    
    private String llave;
    private int tareas;
    private Nodo siguiente;
    
    Nodo(String llave, int tareas){
    
        this.llave = llave;
        this.tareas = tareas;
        siguiente = null;
        
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public int getTareas() {
        return tareas;
    }

    public void setTareas(int tareas) {
        this.tareas = tareas;
    }
    
}
