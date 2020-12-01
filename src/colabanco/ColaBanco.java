
package colabanco;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ColaBanco extends JFrame implements ActionListener{

    JButton botonIngreso = new JButton("Ingresar");
    JButton botonAtender = new JButton("Atender");
    JButton botonAleatorio = new JButton("llenar aleatoriamente (10 clientes)");
    JButton botonVaciar = new JButton("Vaciar cola");
    
    JLabel texto1 = new JLabel("Ingrese el nombre del cliente: ");
    JLabel texto2 = new JLabel("Ingrese las tareas a realizar: ");
    JLabel texto3 = new JLabel("Informacion: ");
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    JTextField campoNombre = new JTextField();
    JTextField campoTareas = new JTextField();
    JTextField campoInfo = new JTextField();
    
    ListaCircular cola = new ListaCircular();
    
    String [] nombres = {"Daniela", "Ana", "Mario", "Carlos", "Juan", "Karen", 
                        "Brayan", "David", "Diego", "Cristian", "Carolina", 
                        "Nury", "Alejandro", "Sebastian", "Sergio", "Salome", 
                        "Sofia", "Jhon", "Andres", "Esteban"};
    
    public static void main(String[] args) {

        ColaBanco cb = new ColaBanco();
        cb.setBounds(500, 0, 400, 700);
        cb.setTitle("Banco");
        cb.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cb.setVisible(true); 
        
    }

    ColaBanco(){
        
        Container c = getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        
        c.add(campoNombre);
        c.add(campoTareas);
        c.add(campoInfo);
        
        c.add(texto1);
        c.add(texto2);
        c.add(texto3);
        
        c.add(botonIngreso);
        c.add(botonAtender);
        c.add(botonAleatorio);
        c.add(botonVaciar);
        
        c.add(scrollPane1);
     
        texto1.setBounds(60, 0, 250, 30);
        texto2.setBounds(60, 25, 300, 30);
        texto3.setBounds(5, 115, 300, 30);
        
        campoNombre.setBounds(240, 5, 100, 20);
        campoTareas.setBounds(240, 30, 100, 20);
        campoInfo.setBounds(80, 120, 300, 20);
        
        botonIngreso.addActionListener(this);
        botonIngreso.setBounds(60, 60, 280, 20);
        botonIngreso.setBackground(Color.green);
        
        botonAleatorio.addActionListener(this);
        botonAleatorio.setBounds(60, 90, 280, 20);
        botonAleatorio.setBackground(Color.CYAN);
        
        botonAtender.addActionListener(this);
        botonAtender.setBounds(5, 621, 170, 30);
        botonAtender.setBackground(Color.ORANGE);
     
        botonVaciar.addActionListener(this);
        botonVaciar.setBounds(205, 621, 170, 30);
        botonVaciar.setBackground(Color.RED);
        
        scrollPane.setBounds(0, 0, 470, 3000);
        scrollPane.setPreferredSize(new Dimension(470, 3000));  
        scrollPane.setBackground(Color.LIGHT_GRAY);
        
        scrollPane1.setBounds(5, 150, 370, 460);
        scrollPane1.setPreferredSize(new Dimension(370, 460));
        scrollPane1.setBackground(Color.BLUE);
        
    }
    
    public void llenarCola(){
        
        scrollPane.removeAll();
            
        JLabel img1 = new JLabel();

        ImageIcon imgIcon = new ImageIcon(getClass().getResource("cajero.jpg"));

        Image imgEscalada = imgIcon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        img1.setBounds(130 , 0, 100, 100);
        img1.setIcon(iconoEscalado);
        
        scrollPane.add(img1);
        
        Nodo q = cola.getCabeza();
        
        int coorY = 0;
        
        while(true && cola.getUltimo()!= null){            
            
            JLabel tempTexto = new JLabel(q.getLlave() + ", tareas: " + q.getTareas());
            tempTexto.setBounds(130, 120 + (30*coorY), 150, 20);
            tempTexto.setBackground(Color.red);
            
            scrollPane.add(tempTexto);
            
            coorY++;
            
            q = q.getSiguiente();
            
            if(q.equals(cola.getUltimo())){
                
                if(!q.equals(cola.getCabeza())){
                
                    JLabel tempTexto2 = new JLabel(q.getLlave() + ", tareas: " + q.getTareas());
                    tempTexto2.setBounds(130, 120 + (30*coorY), 150, 20);
                    tempTexto2.setBackground(Color.red);
                    
                    scrollPane.add(tempTexto2);
                    
                }

                break;
                
            }
            
        }  
        
        scrollPane.repaint();
        scrollPane1.setViewportView(scrollPane);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == botonIngreso && campoNombre.getText().length()!=0 && campoTareas.getText().length()!=0){
            
            String nombre = campoNombre.getText();
            int tareas = Integer.parseInt(campoTareas.getText());
            
            cola.insertar(nombre, tareas);
            
            System.out.println(cola.imprimir());
            
            campoInfo.setText(nombre + " fue agregado con " + tareas + " tareas.");
            
            llenarCola();
            
        } else if(e.getSource() == botonAtender && cola.getTamaño()>0) {
            
            Nodo clienteActual = cola.getCabeza();
            
            if(clienteActual.getTareas()-3 <= 0){
            
                campoInfo.setText(cola.getCabeza().getLlave() + " se fue de la cola.");
                cola.eliminar(clienteActual);
                
            } else {
                
                cola.getCabeza().setTareas(clienteActual.getTareas()-3);
                campoInfo.setText(cola.getCabeza().getLlave() + " fue al inicio de la cola con " + cola.getCabeza().getTareas() + " tareas.");
                cola.intercambiar(clienteActual);
                
            }
            
            if(cola.getTamaño()>0){
            
                System.out.println(cola.imprimir());

            }
            
            llenarCola();
            
        } else if (e.getSource() == botonAleatorio){
        
            for(int i = 0; i<10; i++){
                
                cola.insertar(nombres[(int) (Math.random() * 20)], (int) (Math.random() * 15) + 1);
                
            }
            
            System.out.println(cola.imprimir());
            
            llenarCola();
            
        } else if (e.getSource() == botonVaciar){
        
            cola = new ListaCircular();
        
            llenarCola();
            
        }
        
    }
    
}
