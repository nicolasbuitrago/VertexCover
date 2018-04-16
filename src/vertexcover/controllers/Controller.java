/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertexcover.controllers;

import vertexcover.models.Arco;
import vertexcover.models.Grafo;
import vertexcover.models.Nodo;
import vertexcover.views.CrearGrafo;
import vertexcover.views.Principal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author nicolasbuitrago
 */
public class Controller {
    
    private Nodo nodoInicial = null, nodoFinal = null;
    private Grafo grafo;
    private Principal p;
    private JPanel panel;

    public Controller() {
//        this.grafo = new Grafo();
//        obtenerGrafo(); 
        this.p = new Principal(this);
//        this.paintGrafo();
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    public static void main(String[] args) {
        Controller c = new Controller();
    }
    
    public void crearGrafo(){
        new CrearGrafo(this.p,true,this);
    }
    
    /**
     * Metodo de obtener el mapa de Grafo.txt y guardarlo en los diferentes ArrayList que se encuntran en la clase Grafo
     * para asi armar el grafo en si
     */
    public void obtenerGrafo(){
        FileReader frg =null;
        try {
            this.grafo = new Grafo();
            String num = JOptionPane.showInputDialog(null, "Digite el numero del grafo: ", "Numero del grafo");
            File Grafo  = new File("Grafo"+num+".txt");
            frg = new FileReader(Grafo);
            BufferedReader gra = new BufferedReader(frg);
            String v = gra.readLine();
            v = gra.readLine();
            while (v != null) {                
                String[] c = v.split(",");//System.out.println("c.length = "+c.length);
                if(c.length==3){
                    grafo.add(new Nodo(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2])));
                }else{
                    Nodo ni = grafo.buscarNodo(toInt(c[0]),toInt(c[1])), nf = grafo.buscarNodo(toInt(c[2]),toInt(c[3]));
                    grafo.add(new Arco(ni,nf,toInt(c[4])));
                }
                v = gra.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error Controller getGrafo =    "+e.getMessage());
            JOptionPane.showMessageDialog(this.p, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                if (frg != null) {
                    frg.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private int toInt(String s){
        return Integer.parseInt(s);
    }

    /**
     * Funcion que se encarga de guardar el grafo en el txt llamado Grafo.txt
     */
    public void setGrafo() {
        FileWriter fw = null;
        try {String num = JOptionPane.showInputDialog(null, "Digite el numero del grafo: ", "Numero del grafo");
            fw = new FileWriter("Grafo"+num+".txt", false);
            PrintWriter pw = new PrintWriter(fw);
            String linea = "Vertice = x,y         Arco = x1,y1,x2,y2,distancia";
            pw.print(linea);
            for (Nodo nodo : grafo.getNodos()) {
                linea = nodo.toString();
                pw.println();
                pw.print(linea);
            }
            for (Arco arco : grafo.getArcos()) {
                linea = arco.toString();
                pw.println();
                pw.print(linea);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al guardar Grafo.txt ");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void paintGrafo() {
        System.out.println("Dibujando grafo");
        Graphics2D g = (Graphics2D) this.panel.getGraphics();
//        Graphics g = this.panel.getGraphics();
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        
        for (Arco arco : grafo.getArcos()) {
            drawArco(g,arco);System.out.println(arco);
        }
        
        for (Nodo nodo : grafo.getNodos()) {
            drawNodo(g,nodo,Integer.toString(nodo.getName()));System.out.println(nodo);
        }
        
//        g.dispose();
//        panel.repaint();
//        Graphics g = panel.getGraphics();
////        panel.paintComponent(g);
//        g.drawImage(img, 0, 0, null);
//        panel.repaint();
    }
    
    /**
     * Se encarga de dibujar un nodo rojo en g con un String de color blanco en el cento
     * @param g es donde se va dibujar el nodo
     * @param nodo es el nodo que se va a dibujar
     * @param s es el String  que se va a dibujar en el centro del nodo
     */
    private void drawNodo(Graphics2D g,Nodo nodo, String s){
        g.setColor(nodo.getColor());
        g.fillOval(nodo.getX(), nodo.getY(), Grafo.TAM_NODOS, Grafo.TAM_NODOS);
        g.setColor(Color.white);
        g.drawString(s, nodo.getX()+Grafo.TAM_NODOS/2.5f, nodo.getY()+Grafo.TAM_NODOS/1.4f);
//        g.drawString(s, nodo.getX(), nodo.getY());
    }
    
    /**
     * Metodo que se encarga de dibujar un arco sobre g
     * @param g es el Graphics2D sobre donde se dibujara el marco
     * @param arco es el arco que se va a dibujar
     */
    private void drawArco(Graphics2D g,Arco arco){
        g.drawLine(arco.getX1(), arco.getY1(), arco.getX2(), arco.getY2());
    }

}
