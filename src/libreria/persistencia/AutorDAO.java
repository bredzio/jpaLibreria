
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidad.Autor;


public class AutorDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    public void guardarAutor(Autor autor) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar autor");
        }
    }
    
    public void modificarAutor(Autor autor) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al eliminar autor");
        }
    }
    
    public List<Autor> listaDeAutores() throws Exception{
        try{
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a ", Autor.class).getResultList();
            return autores;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    
    
    public Autor buscarAutorPorCodigo(Integer id) throws Exception{
        try{
            Autor autor = em.find(Autor.class, id);
            return autor;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<Autor> buscarAutoresPorNombre (String nombre) throws Exception{
        try{
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre",Autor.class).setParameter("nombre", nombre).getResultList();
            return autores;
        }catch(Exception e){
            throw e;
        }
    }
    
    public Autor buscarAutorPorNombre (String nombre) throws Exception{
        try{
            Autor autor = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre",Autor.class).setParameter("nombre", nombre).getSingleResult();
            return autor;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            throw new Exception("ERROR AL BUSCAR AUTOR");
        }
    }
    
    public List<Autor> obtenerAutores () throws Exception{
        try{
            List<Autor> autores = em.createQuery("SELECT a FROM Autor a ",Autor.class).getResultList();
            return autores;
        }catch(Exception e){
            throw e;
        }
    }
    
}
