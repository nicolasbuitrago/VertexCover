/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vertexcover.views;

import vertexcover.controllers.Controller;
import vertexcover.models.Arco;
import vertexcover.models.Grafo;
import vertexcover.models.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author nicolasbuitrago
 */
public class CrearGrafo extends javax.swing.JDialog {

    
    Controller c;
    Grafo grafo;
//    ArrayList<Nodo> nodos;
//    ArrayList<Arco> arcos;
//    int cantNodos = 0;
    Nodo nodoInicial = null, nodoFinal = null;
//    int tamNodos = 30;
    int matriz[][];
    
    /**
     * Creates new form CrearGrafo
     */
    public CrearGrafo(java.awt.Frame parent, boolean modal,Controller c) {
        super(parent, modal);
        initComponents();
        this.c = c;
//        this.grafo = c.getGrafo();
        this.grafo = new Grafo();
        initComponents();
        insertar(jPanel1.getGraphics());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    
    public void insertar (Graphics g){
        jPanel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("CLICK");
                if (rbNodo.isSelected()) {//System.out.println("NODOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    g.setColor(Color.BLACK);
                    g.fillOval(e.getX() - grafo.getTamNodos() / 2, e.getY() - grafo.getTamNodos() / 2, grafo.getTamNodos(), grafo.getTamNodos());
                    grafo.add(new Nodo(grafo.getCantNodos(), e.getX() - grafo.getTamNodos() / 2, e.getY() - grafo.getTamNodos() / 2, Color.red));
                    g.setColor(Color.white);
                    g.drawString(Integer.toString(grafo.getCantNodos()), e.getX(), e.getY());
                } else if (nodoInicial == null) {
                    nodoInicial = grafo.buscarNodo(e.getX(), e.getY());
                    if (nodoInicial != null) {
                        seleccionarNodo(nodoInicial, g, Color.YELLOW);
                    }
                } else {
                    nodoFinal = grafo.buscarNodo(e.getX(), e.getY());
                    if (nodoFinal != null) {
                        seleccionarNodo(nodoFinal, g, Color.YELLOW);
                        if (nodoFinal.getName() != nodoInicial.getName()) {
                            g.setColor(Color.BLACK);
                            g.drawLine(nodoInicial.x + grafo.getTamNodos() / 2, nodoInicial.y + grafo.getTamNodos() / 2, nodoFinal.x + grafo.getTamNodos() / 2, nodoFinal.y + grafo.getTamNodos() / 2);
                            int dist = grafo.distancia(nodoInicial.x + grafo.getTamNodos() / 2, nodoInicial.y + grafo.getTamNodos() / 2, nodoFinal.x + grafo.getTamNodos() / 2, nodoFinal.y + grafo.getTamNodos() / 2);
//                            grafo.add(new Arco(nodoInicial.getName(), nodoFinal.name, nodoInicial.x + grafo.getTamNodos() / 2, nodoInicial.y + grafo.getTamNodos() / 2, nodoFinal.x + grafo.getTamNodos() / 2, nodoFinal.y + grafo.getTamNodos() / 2,
                            grafo.add(new Arco(nodoInicial, nodoFinal, dist));
//                                 g.drawString(Integer.toString(distancia(nodoInicial.x+grafo.getTamNodos()/2, nodoInicial.y+grafo.getTamNodos()/2, nodoFinal.x+grafo.getTamNodos()/2, nodoFinal.y+grafo.getTamNodos()/2)),nodoInicial.x+dist/2,nodoInicial.y+5);
                        } else {
                            seleccionarNodo(nodoInicial, g, Color.BLACK);
                        }
                        seleccionarNodo(nodoInicial, g, Color.BLACK);
                        seleccionarNodo(nodoFinal, g, Color.BLACK);
                        nodoInicial = null;
                    } else {
                        seleccionarNodo(nodoInicial, g, Color.BLACK);
                        nodoInicial = null;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    
    private void seleccionarNodo(Nodo nodo, Graphics g, Color color){
        g.setColor(color);
        g.drawOval(nodo.x, nodo.y, grafo.getTamNodos()-1, grafo.getTamNodos() -1);
    }
    
     private void calcularMatriz(){
        matriz = new int [grafo.getNodos().size()][grafo.getNodos().size()];
        for (Arco arco : grafo.getArcos()) {
//            matriz[arco.nodoInicial][arco.nodoFinal] = arco.dist;
//            matriz[arco.nodoFinal][arco.nodoInicial] = arco.dist;
        }
        prim();
    }
     
     private void prim(){
         boolean vector[] = new boolean[grafo.getNodos().size()];
         vector[0]=true;
         while (todosSeleccionados(vector)) {             
             int min = menor(matriz,vector);
             vector[min] = true;
         }
     }
     
     private int menor(int[][] matriz, boolean[] vector){
         int menor = Integer.MAX_VALUE;
         int fila = -1, col = -1;
         for (int i = 0; i < matriz.length; i++) {
             if (vector[i]) {
                 for (int j = 0; j < matriz.length; j++) {
                     if (matriz[j][i]!=0 && vector[j]==false && matriz[j][i]<=menor) {
                         menor = matriz[j][i];
                         fila = j;
                         col = i;
                     }
                 }
             }
         }
         System.out.println(""+fila+" - "+col);
         return fila;
     }
     
     private boolean todosSeleccionados(boolean[] vector) {
         for (int i = 0; i < vector.length; i++) {
             if(!vector[i]){
                 return true;
             }
         }
         return false;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        rbNodo = new javax.swing.JRadioButton();
        rbArco = new javax.swing.JRadioButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jButton1.setText("Matriz adyacencia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNodo);
        rbNodo.setText("Nodo");
        rbNodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbNodoMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbArco);
        rbArco.setText("Arco");
        rbArco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbArcoMouseClicked(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(rbNodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbArco, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(rbNodo)
                        .addGap(18, 18, 18)
                        .addComponent(rbArco)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(49, 49, 49)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        calcularMatriz();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rbNodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbNodoMouseClicked
        rbArco.setSelected(rbNodo.isSelected());
        rbNodo.setSelected(!rbNodo.isSelected());
    }//GEN-LAST:event_rbNodoMouseClicked

    private void rbArcoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbArcoMouseClicked
        rbNodo.setSelected(rbArco.isSelected());
        rbArco.setSelected(!rbArco.isSelected());
    }//GEN-LAST:event_rbArcoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.c.setGrafo(this.grafo);
//        this.c.setGrafo();
        this.c.paintGrafo();
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearGrafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                CrearGrafo dialog = new CrearGrafo(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbArco;
    private javax.swing.JRadioButton rbNodo;
    // End of variables declaration//GEN-END:variables
}
