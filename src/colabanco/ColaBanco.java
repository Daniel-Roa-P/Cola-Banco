
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
    
    JLabel texto1 = new JLabel("Ingrese el nombre del cliente: ");
    JLabel texto2 = new JLabel("Ingrese las tareas a realizar: ");
    
    JScrollPane scrollPane = new JScrollPane();
    JScrollPane scrollPane1 = new JScrollPane();
    
    JTextField campoNombre = new JTextField();
    JTextField campoTareas = new JTextField();
    
    ListaCircular cola = new ListaCircular();
    
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
        
        c.add(texto1);
        c.add(texto2);
        
        c.add(botonIngreso);
        c.add(botonAtender);
        
        c.add(scrollPane1);
     
        texto1.setBounds(60, 0, 250, 30);
        texto2.setBounds(60, 25, 300, 30);
        
        campoNombre.setBounds(240, 5, 100, 20);
        campoTareas.setBounds(240, 30, 100, 20);
        
        botonIngreso.addActionListener(this);
        botonIngreso.setBounds(60, 60, 280, 30);
        botonIngreso.setBackground(Color.green);
        
        botonAtender.addActionListener(this);
        botonAtender.setBounds(60, 621, 280, 30);
        botonAtender.setBackground(Color.ORANGE);
     
        scrollPane.setBounds(0, 0, 470, 3000);
        scrollPane.setPreferredSize(new Dimension(470, 3000));  
        scrollPane.setBackground(Color.LIGHT_GRAY);
        
        scrollPane1.setBounds(5, 100, 370, 510);
        scrollPane1.setPreferredSize(new Dimension(465, 500));
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

        if(e.getSource() == botonIngreso){
            
            String nombre = campoNombre.getText();
            int tareas = Integer.parseInt(campoTareas.getText());
            
            cola.insertar(nombre, tareas);
            
            System.out.println(cola.imprimir());
            
            llenarCola();
            
        } else if(e.getSource() == botonAtender && cola.getTamaño()>0) {
            
            Nodo clienteActual = cola.getCabeza();
            
            if(clienteActual.getTareas()-3 <= 0){
            
                cola.eliminar(clienteActual);
                
            } else {
                
                cola.getCabeza().setTareas(clienteActual.getTareas()-3);
                cola.intercambiar(clienteActual);
                
            }
            
            if(cola.getTamaño()>0){
            
                System.out.println(cola.imprimir());

            }
            
            llenarCola();
            
        }
        
    }
    
}
