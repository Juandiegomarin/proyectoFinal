/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programa;

import controllers.GeneracionJpaController;
import entities.Generacion;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan Diego
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
        controllers.GeneracionJpaController gc = new GeneracionJpaController(emf);
        
        List<Generacion> l = gc.findGeneracionEntities();
        String[] gens=new String[l.size()];
        
        for (int i = 0; i < l.size(); i++) {
            gens[i]=l.get(i).getNombreRegion();
        }
        
        for (int i = 0; i < gens.length; i++) {
            System.out.println(gens[i]);
        }
    }
    
}
