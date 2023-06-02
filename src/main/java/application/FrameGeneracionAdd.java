/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package application;

import controllers.GeneracionJpaController;
import controllers.PokemonJpaController;
import entities.Generacion;
import entities.Pokemon;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Diego
 */
public class FrameGeneracionAdd extends javax.swing.JFrame {

    /**
     * Creates new form FramePokemonAdd
     */
    public FrameGeneracionAdd() {
        initComponents();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
        
        
        listaGen.setEnabled(false);
        

        GeneracionJpaController gc = new GeneracionJpaController(emf);
        List<Generacion> gens = gc.findGeneracionEntities();

        DefaultTableModel m = new DefaultTableModel();

        m.setColumnIdentifiers(new String[]{" Generacion ID "," Numero Generacion "," Numero de Pokemon ", " Nombre Generacion "});
        for (Generacion g : gens) {
            
            Object[] objetos = {g.getIdGeneracion(),g.getNumeroGeneracion(),g.getNumeroPokemon(),g.getNombreRegion()};
            m.addRow(objetos);
        }

        listaGen.setModel(m);
        listaGen.setVisible(true);
        
        
        
        
        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nombreGen = new javax.swing.JLabel();
        nombreGenTexto = new javax.swing.JTextField();
        numeroPokemons = new javax.swing.JLabel();
        numeroPokemonsTexto = new javax.swing.JTextField();
        numeroRegion = new javax.swing.JLabel();
        numeroRegionTexto = new javax.swing.JTextField();
        JScrollPanel1 = new javax.swing.JScrollPane();
        listaGen = new javax.swing.JTable();
        botonInsert = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nombreGen.setText("Nombre");

        numeroPokemons.setText("Numero de pokemon");

        numeroPokemonsTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroPokemonsTextoActionPerformed(evt);
            }
        });

        numeroRegion.setText("Numero Region");

        listaGen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JScrollPanel1.setViewportView(listaGen);

        botonInsert.setText("Insertar");
        botonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertActionPerformed(evt);
            }
        });

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(nombreGen)
                                .addGap(88, 88, 88))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numeroRegion)
                                    .addComponent(numeroPokemons))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nombreGenTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addComponent(numeroRegionTexto))
                            .addComponent(numeroPokemonsTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)))
                .addComponent(JScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonVolver)
                .addGap(375, 375, 375))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreGen)
                            .addComponent(nombreGenTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroRegion)
                            .addComponent(numeroRegionTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroPokemons)
                            .addComponent(numeroPokemonsTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(255, 255, 255)
                        .addComponent(botonInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(JScrollPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(botonVolver)
                .addGap(103, 103, 103))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        FrameGeneracion fm= new FrameGeneracion();
        fm.setVisible(true);
        System.out.println(fm);
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertActionPerformed
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
        
        GeneracionJpaController gc= new GeneracionJpaController(emf);
        
        
        
        String nombre= nombreGenTexto.getText();
        String numPokemons=numeroPokemonsTexto.getText();
        String numeroRegion= numeroRegionTexto.getText();
        
        
        
        
        Generacion aux= new Generacion();
        
        aux.setNombreRegion(nombre);
        try {
          aux.setNumeroGeneracion(Integer.parseInt(numeroRegion));
          aux.setNumeroPokemon(Integer.parseInt(numPokemons));  
        } catch (Exception e) {
        }
        
        if(nombre.equals("")&&numPokemons.equals("")&&numeroRegion.equals("")){
        
        }else{
        
        int id;
        if(gc.findGeneracionEntities().isEmpty()){
        
        id=1;
        }else{
        
        id=gc.findGeneracionEntities().get(gc.findGeneracionEntities().size()-1).getIdGeneracion()+1;
        
        }
        
        aux.setIdGeneracion(id);
        
        
        
        try {
         gc.create(aux);   
        } catch (Exception e) {
        }
        }
        
        List<Generacion> gens = gc.findGeneracionEntities();

        DefaultTableModel m = new DefaultTableModel();

        m.setColumnIdentifiers(new String[]{" Generacion ID "," Numero Generacion "," Numero de Pokemon ", " Nombre Generacion "});
        for (Generacion g : gens) {
            
            Object[] objetos = {g.getIdGeneracion(),g.getNumeroGeneracion(),g.getNumeroPokemon(),g.getNombreRegion()};
            m.addRow(objetos);
        }

        listaGen.setModel(m);
        listaGen.setVisible(true);
        
        
        
    }//GEN-LAST:event_botonInsertActionPerformed

    private void numeroPokemonsTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroPokemonsTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroPokemonsTextoActionPerformed

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
            java.util.logging.Logger.getLogger(FrameGeneracionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameGeneracionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameGeneracionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameGeneracionAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameGeneracionAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanel1;
    private javax.swing.JButton botonInsert;
    private javax.swing.JButton botonVolver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable listaGen;
    private javax.swing.JLabel nombreGen;
    private javax.swing.JTextField nombreGenTexto;
    private javax.swing.JLabel numeroPokemons;
    private javax.swing.JTextField numeroPokemonsTexto;
    private javax.swing.JLabel numeroRegion;
    private javax.swing.JTextField numeroRegionTexto;
    // End of variables declaration//GEN-END:variables
}
