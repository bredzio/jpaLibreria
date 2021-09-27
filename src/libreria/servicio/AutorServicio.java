
package libreria.servicio;

import java.util.List;
import java.util.Scanner;
import libreria.entidad.Autor;
import libreria.persistencia.AutorDAO;


public class AutorServicio {
    private AutorDAO autorDAO;
    
    public AutorServicio(){
        autorDAO = new AutorDAO();
    }
    Scanner leer = new Scanner(System.in);
    public Autor crearAutor(String nombre) throws Exception{
        nombre.toLowerCase();
        try{
            Autor autor = new Autor();
            
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("ERROR: El nombre del autor es obligatorio");
            }
            
            if(autorDAO.buscarAutoresPorNombre(nombre).isEmpty()==false){
                if(autorDAO.buscarAutorPorNombre(nombre).getAlta()==false){
                    System.out.println("AVISO: El autor se encuentra dado de baja\n¿Desea activarlo nuevamente?\n1.Si\n2.No");
                    System.out.print("Opcion:");
                    String opcion=leer.next();
                    if(opcion.equals("si")){
                        autor=autorDAO.buscarAutorPorNombre(nombre);
                        autor.setAlta(true);
                        autorDAO.modificarAutor(autor);
                        throw new Exception("AVISO: Autor dado de alta nuevamente");
                    }else{
                        throw new Exception("SE VUELVE AL MENÚ");
                    }
                }
            }
            
            if (autorDAO.buscarAutoresPorNombre(nombre).isEmpty()== false) {
                throw new Exception("AVISO: El autor ya se encuentra registrado"); 
            }

            
            autor.setNombre(nombre);
            autor.setAlta(true);
            autorDAO.guardarAutor(autor);
            System.out.println("Autor guardada exitosamente");
            return autor;  
            
        }catch(Exception e){
            throw e;
        }
    }
    
    public Autor crearAutorParaLibro(String nombre) throws Exception{
       nombre.toLowerCase();
        try{
            Autor autor = new Autor();
            
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("ERROR: El nombre del autor es obligatorio");
            }
            
            if(autorDAO.buscarAutoresPorNombre(nombre).isEmpty()==false){
                if(autorDAO.buscarAutorPorNombre(nombre).getAlta()==false){
                    System.out.println("AVISO: El autor se encuentra dada de baja\n¿Desea activarlo nuevamente?\n1.Si\n2.No");
                    System.out.print("Opcion:");
                    String opcion=leer.next();
                    if(opcion.equals("si")){
                        autor=autorDAO.buscarAutorPorNombre(nombre);
                        autor.setAlta(true);
                        autorDAO.modificarAutor(autor);
                        return autor;
                    }
                }else{
                    autor=autorDAO.buscarAutorPorNombre(nombre);
                    return autor;
                }
            }else{
                autor.setNombre(nombre);
                autor.setAlta(true);
                autorDAO.guardarAutor(autor);
            }
            return autor;
            
       }catch(Exception e) {
           throw e;
       }
    }
    
    
    public void eliminarAutor(String nombre) throws Exception{
        nombre.toLowerCase();
        try{
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("ERROR: Debe ingresar el nombre del autor");
            }
            
            if (autorDAO.buscarAutoresPorNombre(nombre).isEmpty() || autorDAO.buscarAutorPorNombre(nombre).getAlta()==false) {
                throw new Exception("ERROR: El nombre del autor no se encuentra registrado"); 
            }
            
            Autor autor=autorDAO.buscarAutorPorNombre(nombre);
            autor.setAlta(false);
            autorDAO.modificarAutor(autor);
            System.out.println("Autor eliminado exitosamente");
            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void mostrarListaDeAutores() throws Exception{
        try{
            List<Autor> autores=autorDAO.listaDeAutores();
            if(autores.isEmpty()){
                throw new Exception("AVISO: LISTA DE AUTORES VACÍA");
            }
            int j=0;
            for (Autor a: autores) {
                if(a.getAlta()==false){
                j++;}
                
            }
            
            if(j==autores.size()){
                throw new Exception("AVISO: LISTA DE AUTORES VACÍA");
            }
            
            
            System.out.println("\n***Lista de autores registrados***");
            int i=1;
            for (Autor a: autores) {
                if(a.getAlta()==true){
                System.out.println(i+"."+a.getNombre());
                i++;}
                
            }
                        
        }catch(Exception e){
            throw e;
        }
    }
    
    
    public Autor BuscarAutorPorNombre(String nombre) throws Exception{
        try{
            Autor autor=autorDAO.buscarAutorPorNombre(nombre);
            return autor;
        }catch(Exception e){
            throw e;
        }
    }
    
    public List<Autor> BuscarAutoresPorNombre(String nombre) throws Exception{
        try{
            List<Autor> autores=autorDAO.buscarAutoresPorNombre(nombre);
            return autores;
        }catch(Exception e){
            throw e;
        }
    }
    
    public List<Autor> obtenerAutores() throws Exception{
        try{
            List<Autor> autores=autorDAO.obtenerAutores();
            return autores;
        }catch(Exception e){
            throw e;
        }
    }
    
    
}
