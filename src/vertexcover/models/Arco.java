/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertexcover.models;

import java.util.Objects;

/**
 *
 * @author nicolasbuitrago
 */
public class Arco {
    int  x1,y1,x2,y2, dist;
    Nodo nodoInicial, nodoFinal;

    public Arco(Nodo nodoInicial, Nodo nodoFinal, int distancia) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.x1 = nodoInicial.getX() + Grafo.TAM_NODOS / 2;
        this.y1 = nodoInicial.getY() + Grafo.TAM_NODOS / 2;
        this.x2 = nodoFinal.getX() + Grafo.TAM_NODOS / 2;
        this.y2 = nodoFinal.getY() + Grafo.TAM_NODOS / 2;
        this.dist = distancia;
    }
    
    public Arco(Nodo nodoInicial, Nodo nodoFinal) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.x1 = nodoInicial.getX() + Grafo.TAM_NODOS / 2;
        this.y1 = nodoInicial.getY() + Grafo.TAM_NODOS / 2;
        this.x2 = nodoFinal.getX() + Grafo.TAM_NODOS / 2;
        this.y2 = nodoFinal.getY() + Grafo.TAM_NODOS / 2;
        this.dist = Grafo.distancia(nodoInicial.x,nodoInicial.y,nodoFinal.x,nodoFinal.y);
    }

    public Nodo getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Nodo nodoInicial) {
        this.nodoInicial = nodoInicial;
    }

    public Nodo getNodoFinal() {
        return nodoFinal;
    }

    public void setNodoFinal(Nodo nodoFinal) {
        this.nodoFinal = nodoFinal;
    }

    public int getDist() {
        return dist;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
//        return  + "," + y1 + "," + x2 + "," + y2 + "," + dist;
        return x1 + "," + y1 + "," + x2 + "," + y2 + "," + dist;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.x1;
        hash = 97 * hash + this.y1;
        hash = 97 * hash + this.x2;
        hash = 97 * hash + this.y2;
        hash = 97 * hash + this.dist;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arco other = (Arco) obj;
//        if (this.x1 != other.x1) {
//            return false;
//        }
//        if (this.y1 != other.y1) {
//            return false;
//        }
//        if (this.x2 != other.x2) {
//            return false;
//        }
//        if (this.y2 != other.y2) {
//            return false;
//        }
        if (this.dist != other.dist) {
            return false;
        }
        if (!Objects.equals(this.nodoInicial, other.nodoInicial)&&!Objects.equals(this.nodoInicial, other.nodoFinal)) {
            return false;
        }
        if (!Objects.equals(this.nodoFinal, other.nodoFinal)&&!Objects.equals(this.nodoInicial, other.nodoFinal)) {
            return false;
        }
        return true;
    }
    
}
