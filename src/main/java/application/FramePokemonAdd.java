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
public class FramePokemonAdd extends javax.swing.JFrame {

    /**
     * Creates new form FramePokemonAdd
     */
    public FramePokemonAdd() {
        initComponents();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");

        listaPokemon.setEnabled(false);

        PokemonJpaController pc = new PokemonJpaController(emf);
        List<Pokemon> pokemons = pc.findPokemonEntities();

        DefaultTableModel m = new DefaultTableModel();

        m.setColumnIdentifiers(new String[]{" Pokemon ID ", " Generacion ", " Numero Pokedex ", " Nombre ", " Tipo 1 ", " Tipo 2 "});
        for (Pokemon p : pokemons) {

            Object[] objetos = {p.getIdPokemon(), p.getGeneracion(), p.getNumeroPokedex(), p.getNombre(), p.getTipo1(), p.getTipo2()};
            m.addRow(objetos);
        }

        listaPokemon.setModel(m);
        listaPokemon.setVisible(true);

        GeneracionJpaController gc = new GeneracionJpaController(emf);

        List<Generacion> l = gc.findGeneracionEntities().stream().sorted((o1, o2) -> o1.getNumeroGeneracion().compareTo(o2.getNumeroGeneracion())).toList();

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        for (Generacion g : l) {

            modelo.addElement("Numero: " + g.getNumeroGeneracion() + " Nombre: " + g.getNombreRegion());
        }

        this.generaciones.setModel(modelo);

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
        nombrePokemon = new javax.swing.JLabel();
        nombrePokemonTexto = new javax.swing.JTextField();
        numeroPokedex = new javax.swing.JLabel();
        numeroPokedexTexto = new javax.swing.JTextField();
        tipo1 = new javax.swing.JLabel();
        tipo1Texto = new javax.swing.JTextField();
        tipo2 = new javax.swing.JLabel();
        tipo2Texto = new javax.swing.JTextField();
        generacion = new javax.swing.JLabel();
        generaciones = new javax.swing.JComboBox<>();
        JScrollPanel1 = new javax.swing.JScrollPane();
        listaPokemon = new javax.swing.JTable();
        botonInsert = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nombrePokemon.setText("Nombre");

        numeroPokedex.setText("Numero Pokedex");

        tipo1.setText("Tipo 1");

        tipo2.setText("Tipo 2");

        generacion.setText("Generacion");

        generaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generacionesActionPerformed(evt);
            }
        });

        listaPokemon.setModel(new javax.swing.table.DefaultTableModel(
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
        JScrollPanel1.setViewportView(listaPokemon);

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
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroPokedex)
                            .addComponent(nombrePokemon)
                            .addComponent(tipo1)
                            .addComponent(tipo2)
                            .addComponent(generacion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nombrePokemonTexto)
                            .addComponent(numeroPokedexTexto)
                            .addComponent(tipo1Texto)
                            .addComponent(tipo2Texto)
                            .addComponent(generaciones, 0, 240, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(221, Short.MAX_VALUE)
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
                            .addComponent(nombrePokemon)
                            .addComponent(nombrePokemonTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numeroPokedex)
                            .addComponent(numeroPokedexTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipo1)
                            .addComponent(tipo1Texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipo2)
                            .addComponent(tipo2Texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generacion)
                            .addComponent(generaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(134, 134, 134)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generacionesActionPerformed


    }//GEN-LAST:event_generacionesActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        FramePokemon fm = new FramePokemon();
        fm.setVisible(true);
        System.out.println(fm);
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertActionPerformed

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
        PokemonJpaController pc = new PokemonJpaController(emf);
        GeneracionJpaController gc = new GeneracionJpaController(emf);

        String nombre = nombrePokemonTexto.getText();
        String numPkdx = numeroPokedexTexto.getText();
        String tipo1 = tipo1Texto.getText();
        String tipo2 = tipo2Texto.getText();

        int index = generaciones.getSelectedIndex();
        int g = gc.findGeneracionEntities().get(index).getIdGeneracion();

        Pokemon p = new Pokemon();

        Generacion aux = gc.findGeneracion(g);

        p.setGeneracion(aux);
        p.setNombre(nombre);
        try {
            p.setNumeroPokedex(Integer.parseInt(numPkdx));
        } catch (NumberFormatException nfe) {
        }

        p.setTipo1(tipo1);
        p.setTipo2(tipo2);

        if (nombre.equals("") && numPkdx.equals("") && tipo1.equals("") && tipo2.equals("") && generaciones.getSelectedIndex() == -1) {

        } else {

            int id;
            if (pc.findPokemonEntities().isEmpty()) {

                id = 1;
            } else {

                id = pc.findPokemonEntities().get(pc.findPokemonEntities().size() - 1).getIdPokemon() + 1;

            }

            p.setIdPokemon(id);
            try {
                pc.create(p);
            } catch (Exception e) {
            }
        }

        List<Pokemon> pokemons = pc.findPokemonEntities();

        DefaultTableModel m = new DefaultTableModel();

        m.setColumnIdentifiers(new String[]{" Pokemon ID ", " Generacion ", " Numero Pokedex ", " Nombre ", " Tipo 1 ", " Tipo 2 "});
        for (Pokemon pok : pokemons) {

            Object[] objetos = {pok.getIdPokemon(), pok.getGeneracion(), pok.getNumeroPokedex(), pok.getNombre(), pok.getTipo1(), pok.getTipo2()};
            m.addRow(objetos);
        }

        listaPokemon.setModel(m);
        listaPokemon.setVisible(true);


    }//GEN-LAST:event_botonInsertActionPerformed

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
            java.util.logging.Logger.getLogger(FramePokemonAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePokemonAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePokemonAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePokemonAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePokemonAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPanel1;
    private javax.swing.JButton botonInsert;
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel generacion;
    private javax.swing.JComboBox<String> generaciones;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable listaPokemon;
    private javax.swing.JLabel nombrePokemon;
    private javax.swing.JTextField nombrePokemonTexto;
    private javax.swing.JLabel numeroPokedex;
    private javax.swing.JTextField numeroPokedexTexto;
    private javax.swing.JLabel tipo1;
    private javax.swing.JTextField tipo1Texto;
    private javax.swing.JLabel tipo2;
    private javax.swing.JTextField tipo2Texto;
    // End of variables declaration//GEN-END:variables
}
