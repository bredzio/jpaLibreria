
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidad.Libro;


public class LibroDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    public void guardarLibro(Libro libro) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error al guardar Libro");
        }
    }
    
    public void modificarLibro(Libro libro) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
            
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar libro");
        }
    }
    
    public Libro buscarLibroPorISBN(Long isbn) throws Exception{
         try{
             Libro libro = em.createQuery("SELECT l FROM Libro l WHERE l.isbn=:isbn",Libro.class).setParameter("isbn", isbn).getSingleResult();
             return libro;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            throw new Exception("ERROR AL BUSCAR LIBRO");
        }
    }
    
    public List<Libro> buscarLibrosPorNombre(String nombre) throws Exception{
         try{
             List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :nombre",Libro.class).setParameter("nombre", nombre).getResultList();
             return libros;
         }catch (Exception e){
             throw new Exception ("Error al buscar libro");
         }
     }
    
    public Libro buscarLibroPorNombre (String nombre) throws Exception{
        try{
            Libro libro = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :nombre",Libro.class).setParameter("nombre", nombre).getSingleResult();
            return libro;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            throw new Exception("ERROR AL BUSCAR LIBRO");
        }
    }
    
    
    
    public List<Libro> listaDeLibros() throws Exception{
        try{
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
            return libros;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    
}
