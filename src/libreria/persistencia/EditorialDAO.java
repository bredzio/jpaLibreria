
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import libreria.entidad.Editorial;


public class EditorialDAO {
    
    private EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    public void guardarEditorial(Editorial editorial) throws Exception{
        try{
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
            
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al guardar editorial");
        }
    }
        
    public void modificarEditorial(Editorial editorial) throws Exception{
        try{
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
            
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new Exception("Error al modificar editorial");
        }
    }
   
    public List<Editorial> listaDeEditorialesPorNombre(String nombre) throws Exception{
        try{
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre", Editorial.class).setParameter("nombre", nombre).getResultList();
            return editoriales;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<Editorial> listaDeEditoriales() throws Exception{
        try{
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e", Editorial.class).getResultList();
            return editoriales;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
        
     public Editorial buscarEditorialPorCodigo(Integer codigo) throws Exception{
         try{
             Editorial editorial = em.find(Editorial.class, codigo);
             return editorial;
         }catch(Exception e){
             e.printStackTrace();
             throw new Exception("Error al buscar editorial por codigo");
         }
     } 
     
     public List<Editorial> buscarEditoriales() throws Exception{
         try{
             List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e",Editorial.class).getResultList();
             return editoriales;
         }catch (Exception e){
             e.printStackTrace();
             throw new Exception ("Error al buscar editorialees");
         }
     }
     
    public List<Editorial> buscarEditorialesPorNombre(String nombre) throws Exception{
         try{
             List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre",Editorial.class).setParameter("nombre", nombre).getResultList();
             return editoriales;
         }catch (Exception e){
             throw new Exception ("Error al buscar editoriales");
         }
     }
    
    public Editorial buscarEditorialPorNombre (String nombre) throws Exception{
        try{
            Editorial editorial = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre",Editorial.class).setParameter("nombre", nombre).getSingleResult();
            return editorial;
        }catch(NoResultException e){
            return null;
        }catch(Exception e){
            throw new Exception("ERROR AL BUSCAR EDITORIAL");
        }
    }
    
    
}
