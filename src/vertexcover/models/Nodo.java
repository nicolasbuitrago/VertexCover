/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertexcover.models;

import java.awt.Color;
import java.util.Comparator;

/**
 *
 * @author nicolasbuitrago
 */
public class Nodo implements Comparable<Nodo> {
    public int name, x,y, grado;
    public Color color;
    
    public Nodo(int name,int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
        this.grado = 0;
    }

    public Nodo(int name, int x, int y, Color color) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.color = color;
        this.grado = 0;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getGrado() {
        return grado;
    }

    public void addGrado() {
        this.grado++;
    }

    @Override
    public String toString() {
        return name + "," + x + "," + y;
    }

    @Override
    public int compareTo(Nodo t) {
        return this.grado - t.grado;
    }
  
    public static Comparator<Nodo> CompNodos() {
        Comparator<Nodo> comp = new Comparator<Nodo>() {
            @Override
            public int compare(Nodo s1, Nodo s2) {
                return s2.compareTo(s1);
            }
        };
        return comp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.name;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
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
        final Nodo other = (Nodo) obj;
        if (this.name != other.name) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    
}
