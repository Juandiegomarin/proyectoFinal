/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programa;

import application.FramePrincipal;


/**
 *
 * @author juandi
 */
public class Main {
    
    
//    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemon");
//    private static final EntityManager em= emf.createEntityManager();
//    private static final GeneracionJpaController gc = new GeneracionJpaController(emf);
//    private static final HabilidadJpaController hc = new HabilidadJpaController(emf);
//    private static final HabilidadPokemonJpaController hpc = new HabilidadPokemonJpaController(emf);
//    private static final PokemonJpaController pc = new PokemonJpaController(emf);
    
    
    public static void main(String[] args) throws Exception {
        
        
        FramePrincipal fm = new FramePrincipal();
        fm.setVisible(true);
        System.out.println(fm);
        

//        
//        
//        
//        
//        
//        
//        
//      
//        
//        
//        // obtener una transiccion del entity manager
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        
//        TypedQuery<Pokemon> queryBorrarTodosPokemon= em.createNamedQuery("Pokemon.deleteAll",Pokemon.class);
//        queryBorrarTodosPokemon.executeUpdate();
//        
//        transaction.commit();
//
//        TypedQuery<Clientes> queryBorrarClientes = this.em.createNamedQuery("Clientes.deleteAll", Clientes.class);
//        TypedQuery<Facturas> queryBorrarFacturas = this.em.createNamedQuery("Facturas.deleteAll", Facturas.class);
//        TypedQuery<Productos> queryBorrarProductos = this.em.createNamedQuery("Productos.deleteAll", Productos.class);
//        TypedQuery<Proveedores> queryBorrarProveedores = this.em.createNamedQuery("Proveedores.deleteAll", Proveedores.class);
//        TypedQuery<TarjetasBancarias> queryBorrarTarjetas = this.em.createNamedQuery("TarjetasBancarias.deleteAll", TarjetasBancarias.class);
//
//        queryBorrarClientes.executeUpdate();
//        queryBorrarTarjetas.executeUpdate();
//        queryBorrarProductos.executeUpdate();
//        queryBorrarProveedores.executeUpdate();
//        queryBorrarFacturas.executeUpdate();




        // hacer commit de la transicion
//        transaction.commit();
        
    }
    
}
