/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package application;

import controllers.GeneracionJpaController;
import controllers.HabilidadJpaController;
import controllers.PokemonJpaController;
import controllers.exceptions.NonexistentEntityException;
import entities.Generacion;
import entities.Habilidad;
import entities.Pokemon;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan Diego
 */
public class FramePokemonUpdate extends javax.swing.JFrame {

    /**
     * Creates new form FrameModificar
     */
    public FramePokemonUpdate() {
        initComponents();
        listaPokemon.setEnabled(false);

        this.textoError.setVisible(false);
        this.textoVacio.setVisible(false);

        this.nombre.setVisible(false);
        this.nombreTexto.setVisible(false);

        this.numeroPkdx.setVisible(false);
        this.numeroPkdxTexto.setVisible(false);

        this.tipo1.setVisible(false);
        this.tipo1Texto.setVisible(false);

        this.tipo2.setVisible(false);
        this.tipo2Texto.setVisible(false);

        this.generacion.setVisible(false);
        this.generacionText.setVisible(false);

        this.habilidadPokemon.setVisible(false);
        this.habilidadPokemonTexto.setVisible(false);

        this.botonModificar.setVisible(false);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
        GeneracionJpaController gc = new GeneracionJpaController(emf);

        List<Generacion> l = gc.findGeneracionEntities();

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        for (Generacion g : l) {

            modelo.addElement("Numero: " + g.getNumeroGeneracion() + " Nombre: " + g.getNombreRegion());
        }

        this.generacionText.setModel(modelo);

        PokemonJpaController pc = new PokemonJpaController(emf);

        List<Pokemon> pokemons = pc.findPokemonEntities();

        DefaultTableModel m = new DefaultTableModel();

        m.setColumnIdentifiers(new String[]{" Pokemon ID ", " Generacion ", " Numero Pokedex ", " Nombre ", " Tipo 1 ", " Tipo 2 ", " Habilidad "});
        for (Pokemon p : pokemons) {

            Object[] objetos = {p.getIdPokemon(),p.getGeneracion().getNumeroGeneracion(), p.getNumeroPokedex(), p.getNombre(), p.getTipo1(), p.getTipo2(), p.getHabilidad().getNombreHabilidad()};
            m.addRow(objetos);
        }

        listaPokemon.setModel(m);
        listaPokemon.setVisible(true);

        HabilidadJpaController hc = new HabilidadJpaController(emf);

        List<Habilidad> habs = hc.findHabilidadEntities().stream().sorted((o1, o2) -> o1.getIdHabilidad().compareTo(o2.getIdHabilidad())).toList();

        DefaultComboBoxModel modeloHab = new DefaultComboBoxModel();

        for (Habilidad hab : habs) {
            modeloHab.addElement(" Habilidad: " + hab.getNombreHabilidad());

        }
        this.habilidadPokemonTexto.setModel(modeloHab);
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
        textoCodigo = new javax.swing.JLabel();
        Codigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaPokemon = new javax.swing.JTable();
        Atras = new javax.swing.JButton();
        OkeyBotton = new javax.swing.JButton();
        nombre = new javax.swing.JLabel();
        nombreTexto = new javax.swing.JTextField();
        numeroPkdx = new javax.swing.JLabel();
        tipo1 = new javax.swing.JLabel();
        numeroPkdxTexto = new javax.swing.JTextField();
        tipo1Texto = new javax.swing.JTextField();
        botonModificar = new javax.swing.JButton();
        textoError = new javax.swing.JLabel();
        tipo2 = new javax.swing.JLabel();
        tipo2Texto = new javax.swing.JTextField();
        generacion = new javax.swing.JLabel();
        generacionText = new javax.swing.JComboBox<>();
        textoVacio = new javax.swing.JLabel();
        habilidadPokemon = new javax.swing.JLabel();
        habilidadPokemonTexto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textoCodigo.setText("Introduce el id del pokemon que quieras modificar");

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
        jScrollPane1.setViewportView(listaPokemon);

        Atras.setText("Volver");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        OkeyBotton.setText("Ok");
        OkeyBotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkeyBottonActionPerformed(evt);
            }
        });

        nombre.setText("Nombre");

        numeroPkdx.setText("Numero Pokedex");

        tipo1.setText("Tipo 1");

        numeroPkdxTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroPkdxTextoActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        textoError.setText("Ese pokemon no esxiste , vuelva a introducir el id");

        tipo2.setText("Tipo 2");

        generacion.setText("Generacion");

        textoVacio.setText("Inserta al menos un dato para modificar el pokemon");

        habilidadPokemon.setText("Habilidad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroPkdx)
                            .addComponent(tipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipo2)
                            .addComponent(generacion)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textoVacio)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(textoCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textoError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(OkeyBotton, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(Codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))))
                            .addComponent(habilidadPokemon)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botonModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(numeroPkdxTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(tipo1Texto, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(nombreTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(tipo2Texto)
                            .addComponent(generacionText, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(habilidadPokemonTexto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCodigo)
                    .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OkeyBotton)
                    .addComponent(textoError))
                .addGap(18, 18, 18)
                .addComponent(textoVacio)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre)
                    .addComponent(nombreTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroPkdx)
                    .addComponent(numeroPkdxTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo1)
                    .addComponent(tipo1Texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo2)
                    .addComponent(tipo2Texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generacion)
                    .addComponent(generacionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(habilidadPokemon)
                    .addComponent(habilidadPokemonTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Atras)
                    .addComponent(botonModificar))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
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

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        // TODO add your handling code here:

        FramePokemon f = new FramePokemon();

        f.setVisible(true);

        System.out.println(f);
        this.dispose();
    }//GEN-LAST:event_AtrasActionPerformed

    private void OkeyBottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkeyBottonActionPerformed
        // TODO add your handling code here:

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");

        PokemonJpaController pc = new PokemonJpaController(emf);

        String pk = Codigo.getText();
        int key = 0;
        try {
            key = Integer.parseInt(pk);
            this.textoError.setVisible(false);
        } catch (NumberFormatException nfe) {
            this.textoError.setVisible(true);
        }

        boolean seguir = false;
        List<Pokemon> pokemons = pc.findPokemonEntities();

        for (Pokemon p : pokemons) {

            if (p.getIdPokemon() == key) {
                seguir = true;
            }

        }

        if (seguir) {
            nombre.setVisible(true);
            nombreTexto.setVisible(true);

            this.numeroPkdx.setVisible(true);
            this.numeroPkdxTexto.setVisible(true);

            this.tipo1.setVisible(true);
            this.tipo1Texto.setVisible(true);

            this.tipo2.setVisible(true);
            this.tipo2Texto.setVisible(true);

            this.generacion.setVisible(true);
            this.generacionText.setVisible(true);
            this.generacionText.setSelectedIndex(-1);

            this.habilidadPokemon.setVisible(true);
            this.habilidadPokemonTexto.setVisible(true);
            this.habilidadPokemonTexto.setSelectedIndex(-1);

            this.botonModificar.setVisible(true);
        } else {
            this.textoError.setVisible(true);

            nombre.setVisible(false);
            nombreTexto.setVisible(false);

            this.numeroPkdx.setVisible(false);
            this.numeroPkdxTexto.setVisible(false);

            this.tipo1.setVisible(false);
            this.tipo1Texto.setVisible(false);

            this.tipo2.setVisible(false);
            this.tipo2Texto.setVisible(false);

            this.generacion.setVisible(false);
            this.generacionText.setVisible(false);

            this.habilidadPokemon.setVisible(false);
            this.habilidadPokemonTexto.setVisible(false);

            this.botonModificar.setVisible(false);
        }
    }//GEN-LAST:event_OkeyBottonActionPerformed

    private void numeroPkdxTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroPkdxTextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroPkdxTextoActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");

        PokemonJpaController pc = new PokemonJpaController(emf);

        int pk = Integer.parseInt(this.Codigo.getText());

        Pokemon aux = pc.findPokemon(pk);

        if (nombreTexto.getText().equals("")
                && numeroPkdxTexto.getText().equals("")
                && tipo1Texto.getText().equals("")
                && tipo2Texto.getText().equals("")
                && generacionText.getSelectedIndex() == -1
                && habilidadPokemonTexto.getSelectedIndex()==-1) {

            this.textoVacio.setVisible(true);

        } else {

            if (nombreTexto.getText().equals("")) {

                aux.setNombre(aux.getNombre());

            } else {

                aux.setNombre(nombreTexto.getText());
                nombreTexto.setText("");

            }

            if (numeroPkdxTexto.getText().equals("")) {

                aux.setNumeroPokedex(aux.getNumeroPokedex());

            } else {

                try {
                    aux.setNumeroPokedex(Integer.parseInt(numeroPkdxTexto.getText()));
                } catch (NumberFormatException e) {
                }
                numeroPkdxTexto.setText("");
            }

            if (tipo1Texto.getText().equals("")) {

                aux.setTipo1(aux.getTipo1());

            } else {

                aux.setTipo1(tipo1Texto.getText());
                tipo1Texto.setText("");

            }

            if (tipo2Texto.getText().equals("")) {

                aux.setTipo2(aux.getTipo2());

            } else {

                aux.setTipo2(tipo2Texto.getText());
                tipo2Texto.setText("");
            }

            if (generacionText.getSelectedIndex() == -1) {

                aux.setGeneracion(aux.getGeneracion());

            } else {
                GeneracionJpaController gc = new GeneracionJpaController(emf);
                int gen = generacionText.getSelectedIndex();
                Generacion g = gc.findGeneracionEntities().get(gen);

                aux.setGeneracion(g);
                generacionText.setSelectedIndex(-1);

            }

            if (habilidadPokemonTexto.getSelectedIndex() == -1) {

                aux.setHabilidad(aux.getHabilidad());

            } else {
                HabilidadJpaController hc = new HabilidadJpaController(emf);
                int hab = habilidadPokemonTexto.getSelectedIndex();
                Habilidad h = hc.findHabilidadEntities().get(hab);

                aux.setHabilidad(h);
                habilidadPokemonTexto.setSelectedIndex(-1);

            }

            try {
                pc.edit(aux);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FramePokemonUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FramePokemonUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

         List<Pokemon> pokemons = pc.findPokemonEntities();

        DefaultTableModel m = new DefaultTableModel();

        m.setColumnIdentifiers(new String[]{" Pokemon ID ", " Generacion ", " Numero Pokedex ", " Nombre ", " Tipo 1 ", " Tipo 2 ", " Habilidad "});
        for (Pokemon p : pokemons) {

            Object[] objetos = {p.getIdPokemon(), p.getGeneracion().getNumeroGeneracion(), p.getNumeroPokedex(), p.getNombre(), p.getTipo1(), p.getTipo2(),p.getHabilidad().getNombreHabilidad()};
            m.addRow(objetos);
        }

        listaPokemon.setModel(m);
        listaPokemon.setVisible(true);

    }//GEN-LAST:event_botonModificarActionPerformed

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
            java.util.logging.Logger.getLogger(FramePokemonUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePokemonUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePokemonUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePokemonUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePokemonUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atras;
    private javax.swing.JTextField Codigo;
    private javax.swing.JButton OkeyBotton;
    private javax.swing.JButton botonModificar;
    private javax.swing.JLabel generacion;
    private javax.swing.JComboBox<String> generacionText;
    private javax.swing.JLabel habilidadPokemon;
    private javax.swing.JComboBox<String> habilidadPokemonTexto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listaPokemon;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField nombreTexto;
    private javax.swing.JLabel numeroPkdx;
    private javax.swing.JTextField numeroPkdxTexto;
    private javax.swing.JLabel textoCodigo;
    private javax.swing.JLabel textoError;
    private javax.swing.JLabel textoVacio;
    private javax.swing.JLabel tipo1;
    private javax.swing.JTextField tipo1Texto;
    private javax.swing.JLabel tipo2;
    private javax.swing.JTextField tipo2Texto;
    // End of variables declaration//GEN-END:variables
}
