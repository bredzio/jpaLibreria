
package libreria.servicio;

import java.util.List;
import java.util.Scanner;
import libreria.entidad.Editorial;
import libreria.persistencia.EditorialDAO;


public class EditorialServicio {
    private EditorialDAO editorialDAO;

    public EditorialServicio() {
        editorialDAO = new EditorialDAO();
    }
    Scanner leer = new Scanner(System.in);
    
    public Editorial crearEditorial(String nombre) throws Exception{
       nombre.toLowerCase();
        try{
            Editorial editorial = new Editorial();
            
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("ERROR: El nombre de la editorial es obligatoria");
            }
            
            if(editorialDAO.buscarEditorialesPorNombre(nombre).isEmpty()==false){
                if(editorialDAO.buscarEditorialPorNombre(nombre).getAlta()==false){
                    System.out.println("AVISO: La editorial se encuentra dada de baja\n¿Desea activarla nuevamente?\n1.Si\n2.No");
                    System.out.print("Opcion:");
                    String opcion=leer.next();
                    if(opcion.equals("si")){
                        editorial=editorialDAO.buscarEditorialPorNombre(nombre);
                        editorial.setAlta(true);
                        editorialDAO.modificarEditorial(editorial);
                        throw new Exception("AVISO: Editorial dada de alta nuevamente");
                    }else{
                        throw new Exception("SE VUELVE AL MENÚ");
                    }
                }
            }
            
            if (editorialDAO.buscarEditorialesPorNombre(nombre).isEmpty()== false) {
                throw new Exception("AVISO: La editorial ya se encuentra registrado"); 
            }

            
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            editorialDAO.guardarEditorial(editorial);
            System.out.println("Editorial guardada exitosamente");
            return editorial;  
            
       }catch(Exception e) {
           throw e;
       }
    }
    
    public Editorial crearEditorialParaLibro(String nombre) throws Exception{
       nombre.toLowerCase();
        try{
            Editorial editorial = new Editorial();
            
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("ERROR: El nombre de la editorial es obligatoria");
            }
            
            if(editorialDAO.buscarEditorialesPorNombre(nombre).isEmpty()==false){
                if(editorialDAO.buscarEditorialPorNombre(nombre).getAlta()==false){
                    System.out.println("AVISO: La editorial se encuentra dada de baja\n¿Desea activarla nuevamente?\n1.Si\n2.No");
                    System.out.print("Opcion:");
                    String opcion=leer.next();
                    if(opcion.equals("si")){
                        editorial=editorialDAO.buscarEditorialPorNombre(nombre);
                        editorial.setAlta(true);
                        editorialDAO.modificarEditorial(editorial);
                        return editorial;
                    }
                }else{
                    editorial=editorialDAO.buscarEditorialPorNombre(nombre);
                    return editorial;
                }
            }else{
                editorial.setNombre(nombre);
                editorial.setAlta(true);
                editorialDAO.guardarEditorial(editorial);
            }
            return editorial;
            
       }catch(Exception e) {
           throw e;
       }
    }
    
    public void mostrarListaDeEditoriales() throws Exception{
        try{
            List<Editorial> editoriales=editorialDAO.listaDeEditoriales();
            if(editoriales.isEmpty()){
                throw new Exception("AVISO: LISTA DE EDITORIALES VACÍA");
            }
            int j=0;
            for (Editorial e: editoriales) {
                if(e.getAlta()==false){
                    j++;
                }
            }
            
            if(j==editoriales.size()){
                throw new Exception("AVISO: LISTA DE EDITORIALES VACÍA");
            }
            
            System.out.println("***Lista de editoriales registradas***");
            int i=1;
            for (Editorial e: editoriales) {
                if(e.getAlta()==true){
                    System.out.println(i+"."+e.getNombre());
                    i++;
                }
            }
        }catch(Exception e){
            throw e;
        }
    }
    
    public Editorial BuscarEditorialPorNombre(String nombre) throws Exception{
        try{
            Editorial editorial=editorialDAO.buscarEditorialPorNombre(nombre);
            return editorial;
        }catch(Exception e){
            throw e;
        }
    }
    
    public List<Editorial> BuscarEditorialesPorNombre(String nombre) throws Exception{
        try{
            List<Editorial> editoriales=editorialDAO.buscarEditorialesPorNombre(nombre);
            return editoriales;
        }catch(Exception e){
            throw e;
        }
    }
    
    
    public void eliminarEditorial(String nombre) throws Exception{
        nombre.toLowerCase();
        try{
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("El nombre de la editorial es obligatorio");
            }
            
            if (editorialDAO.buscarEditorialesPorNombre(nombre).isEmpty() || editorialDAO.buscarEditorialPorNombre(nombre).getAlta()== false) {
                throw new Exception("ERROR: El nombre de la editorial no se encuentra registrado"); 
            }
            
            Editorial editorial=editorialDAO.buscarEditorialPorNombre(nombre);
            editorial.setAlta(false);
            editorialDAO.modificarEditorial(editorial);
            System.out.println("Editorial eliminada exitosamente");
            
        }catch(Exception e){
            throw e;
        }
    }
    
}
