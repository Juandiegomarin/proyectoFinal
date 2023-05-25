/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programa;

import controllers.GeneracionJpaController;
import controllers.HabilidadJpaController;
import controllers.HabilidadPokemonJpaController;
import controllers.PokemonJpaController;
import entities.Generacion;
import entities.Habilidad;
import entities.Pokemon;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author juandi
 */
public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
    private static final GeneracionJpaController gc = new GeneracionJpaController(emf);
    private static final HabilidadJpaController hc = new HabilidadJpaController(emf);
    private static final HabilidadPokemonJpaController hpc = new HabilidadPokemonJpaController(emf);
    private static final PokemonJpaController pc = new PokemonJpaController(emf);
    
    
    public static void main(String[] args) throws Exception {
        
        
        pc.findPokemonEntities().forEach(System.out::println);
        
        Pokemon aux= new Pokemon();
        aux.setIdPokemon(1);
        aux.setTipo2("Dragon");
        pc.edit(aux);
        
        
        System.out.println("--------------------------------------------------");
        pc.findPokemonEntities().forEach(System.out::println);
        
    }
    
}
