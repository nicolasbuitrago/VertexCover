/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertexcover.models;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author nicolasbuitrago
 */
public class Grafo {
    
    private ArrayList<Nodo> nodos;
    private ArrayList<Arco> arcos;
//  private   int cantNodos;
//    Nodo nodoInicial = null, nodoFinal = null;
    public static final int TAM_NODOS = 30;
    public int adyacencia[][];
//    public int grados[];
    public ArrayList<Nodo> grados;
    private int color;
    
    private ArrayList<Nodo> vertexCover;
    
    public static final Color[] colors ={Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW,Color.PINK,Color.MAGENTA,Color.ORANGE,Color.GRAY,Color.DARK_GRAY};

    public Grafo() {
        this.nodos = new ArrayList();
        this.arcos = new ArrayList();
//        this.cantNodos = 0; TAM_NODOS = 30;
        this.color=0;
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public ArrayList<Arco> getArcos() {
        return arcos;
    }
    
    public Nodo getNodoInicial() {
        return nodos.get(0);
    }
    
    public Nodo getNodoFinal() {
        return nodos.get(this.nodos.size()-1);
    }

    public int getTamNodos() {
        return TAM_NODOS;
    }
    
    public int getCantNodos() {
        return this.nodos.size();
    }
    
    public Nodo buscarNodo(int x, int y){
        Nodo nodoR = null;
        for (Nodo nodo : this.nodos) {
            if (x>=nodo.x && y>= nodo.y && x<=nodo.x+TAM_NODOS && y <=nodo.y+TAM_NODOS) {
                nodoR= nodo;
                break;
            }
        }
        return nodoR;
    }
    
    public static int distancia(int x1, int y1, int x2, int y2){
        return (int) Math.sqrt(Math.pow(x2-x1, 2.0)+Math.pow(y2-y1, 2.0));
    }
    
    public void add(Nodo nodo){
        this.nodos.add(nodo);
    }
    
    public void add(Arco arco){
        this.arcos.add(arco);
    }
    
    /**
     * Metodo para hayar la matriz de adyacencia
     */
    public void adyacencia(){
        if (this.adyacencia == null) {
            adyacencia = new int[nodos.size()][nodos.size()];System.out.println("HACIENDO");
            grados = new ArrayList();
            for (Nodo nodo : nodos) {
                for (Arco arco : arcos) {
                    if (arco.getNodoInicial().equals(nodo)) {
//                    adya.add(arco.getNodoFinal());
                        adyacencia[nodo.getName()][arco.getNodoFinal().getName()] = 1;
                        nodo.addGrado();
//                        grados[nodo.getName()]++;
                    }
                    if (arco.getNodoFinal().equals(nodo)) {
//                    adya.add(arco.getNodoInicial());
                        adyacencia[nodo.getName()][arco.getNodoInicial().getName()] = 1;
                        nodo.addGrado();
//                        grados[nodo.getName()]++;
                    }
                }
                grados.add(nodo);
            }
        }
        this.printAdyacencia();
    }
    
    public void printAdyacencia(){
        System.out.println("");
        for (int i = 0; i < adyacencia.length; i++) {
            for (int j = 0; j < adyacencia.length; j++) {
                System.out.print(adyacencia[i][j]+"\t");
            }
            System.out.println("");
        }
        System.out.println("");
//        for (int i = 0; i < grados.length; i++) {
//            System.out.print(grados[i]+"\t");
//        }
        
        for (Nodo nodo : grados) {
            System.out.print(nodo.grado+"\t");
        }
    }
    
    /**
     * Metodo utilizado para poder colorear el grafo. Autoria por mi :D
     */
    public void colorear(){
        if (this.color==0) {
            grados.sort(Nodo.CompNodos());
            grados.get(0).setColor(colors[color++]);
            /*for1:*/ for (int i = 1; i < grados.size(); i++) {
//                grados.get(i).setColor(colors[color]);
                boolean sw = true;
                for2: for (int j = 0; j < i; j++) {
                    if(!isAdyacente(grados.get(i), grados.get(j))){
                        for (int k = 0; k < nodos.size(); k++) {
                        //if(adyacencia[grados.get(j).getName()][k]==1 && isAdyacente(grados.get(i), nodos.get(k))){
                            if(adyacencia[grados.get(i).getName()][k]==1 && nodos.get(k).getColor().equals(grados.get(j).getColor()) ){
                                continue for2;
                            }
                        }
                        grados.get(i).setColor(grados.get(j).getColor()); sw = false;
                        break /*for1*/;
                    }else{}
                }
                try {
                    /*if(!grados.get(i).getColor().equals(Color.BLACK))*/ if(sw)grados.get(i).setColor(colors[color++]);
                    else System.out.println("Diferenteeeee");
                } catch (Exception e) {
                    grados.get(i).setColor(Color.BLACK); System.out.println("ERORR");
                }
                //grados.get(i).setColor(colors[color++]);
            }
        }
        
    }
    
    public void vertexCover(){
        if (vertexCover == null) {
            vertexCover = new ArrayList();
            grados.sort(Nodo.CompNodos());
            ArrayList<Arco> a1 = new ArrayList();
            ArrayList<Arco> a2 = new ArrayList(arcos);
            for (int i = 0; i < 10; i++) {
                ArrayList<Arco> e = new ArrayList();
                if(!a2.isEmpty()){
                    vertexCover.add(grados.get(i));
                    int name = grados.get(i).name;
                    for (int j = 0; j < adyacencia[name].length; j++) {
                        if(adyacencia[name][j] == 1){
                            e.add(new Arco(grados.get(i),nodos.get(j)));
                        }
                    }
                    for (Arco arco : a2) {
                        if()
                    }
                }else{
                    return;
                }
            }
        }
       
        
        
    }
    
    private boolean isAdyacente(Nodo n1,Nodo n2){
        return adyacencia[n1.getName()][n2.getName()]==1;
    }
    
//    private int gradoMasAlto(){
//        int max = grados[0], pos = 0;
//        for (int i = 1; i < grados.length; i++) {
//            if (max > pos) {
//                max = grados[i];
//                pos = i;
//            }
//        }
//        return pos;
//    }
    
}
