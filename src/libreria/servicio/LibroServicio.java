
package libreria.servicio;

import java.util.List;
import java.util.Scanner;
import libreria.entidad.Autor;
import libreria.entidad.Editorial;
import libreria.entidad.Libro;
import libreria.persistencia.LibroDAO;


public class LibroServicio {
    private LibroDAO libroDAO;

    public LibroServicio() {
        libroDAO = new LibroDAO();
    }
    Scanner leer = new Scanner(System.in);
    AutorServicio autorServicio =new AutorServicio();
    
    public Libro crearLibro(Long isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Autor autor, Editorial editorial) throws Exception{
        nombre.toLowerCase();
        try{
            Libro libro = new Libro();
            
            if(ejemplares<ejemplaresPrestados){
                throw new Exception("ERROR: Ingresó más libros prestados de los que existen");
            }
            
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("El nombre del libro es obligatorio");
            }
            
            if(libroDAO.buscarLibrosPorNombre(nombre).isEmpty()==false){ 
                    if(libroDAO.buscarLibroPorNombre(nombre).getAlta()==false){
                        System.out.println("AVISO: El libro se encuentra dado de baja\n¿Desea activarlo nuevamente?\n1.Si\n2.No");
                        System.out.print("Opcion:");
                        String opcion=leer.next();
                        if(opcion.equals("si")){
                           libro=libroDAO.buscarLibroPorNombre(nombre);
                           libro.setAlta(true);
                           libro.setAnio(anio);
                           libro.setEjemplares(ejemplares);
                           libro.setEjemplaresPrestados(ejemplaresPrestados);
                           libro.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
                           libro.setAutor(autor);
                           libro.setEditorial(editorial);
                           libroDAO.modificarLibro(libro);
                           throw new Exception("AVISO: Libro dado de alta nuevamente");
                        }else{
                            throw new Exception("SE VUELVE AL MENÚ");
                        }
                    }    
            }
            
            if (libroDAO.buscarLibrosPorNombre(nombre).isEmpty()== false) {
                throw new Exception("AVISO: El libro ya se encuentra registrado"); 
            }
            
            libro.setTitulo(nombre);
            libro.setIsbn(isbn);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
            libro.setAlta(true);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
          
            libroDAO.guardarLibro(libro);
            System.out.println("Libro guardado exitosamente");
            return libro;
            
        }catch(Exception e){
            throw e;
        }
    }
    
    public void mostrarListaDelibros() throws Exception{
        try{
            List<Libro> libros=libroDAO.listaDeLibros();
            int j=0;
            for(Libro l: libros){
                if(l.getAlta()==false){
                    j++;
                }
            }
            
            if(libros.isEmpty() || j==libros.size()){
                throw new Exception("AVISO: LISTA DE LIBROS VACÍA");
            }

            System.out.println("\n***LISTA DE LIBROS REGISTRADOS***");
            System.out.printf("%-25s%-45s%-15s%-15s%-25s%-25s%-25s%-25s\n","ISBN","TÍTULO","AÑO","EJEMPLARES","EJEMPLARES PRESTADOS", "EJEMPLARES RESTANTES","AUTOR", "EDITORIAL");

            for (Libro l: libros) {
                if(l.getAlta()==true){
                    System.out.printf("%-25s%-45s%-15s%-15s%-25s%-25s%-25s%-25s\n",l.getIsbn(),l.getTitulo(),l.getAnio(),l.getEjemplares(),l.getEjemplaresPrestados(),l.getEjemplaresRestantes(),l.getAutor().getNombre(),l.getEditorial().getNombre());
                    System.out.println("");
                } 
            }
        }catch(Exception e){
            throw e;
        }
    }
    
    
    
    public void eliminarLibro(Long isbn) throws Exception{
        try{
            if(isbn == null){
                throw new Exception("El número de ISBN es obligatorio");
            }
            
            if (libroDAO.buscarLibroPorISBN(isbn)==null) {
                throw new Exception("ERROR: El libro con dicho ISBN no se encuentra en la lista"); 
            }
            
            Libro libro=libroDAO.buscarLibroPorISBN(isbn);
            libro.setAlta(false);
            libroDAO.modificarLibro(libro);
            System.out.println("Libro eliminado exitosamente");
            
        }catch(Exception e){
            throw e;
        }
    }

    public Libro BuscarLibroPorISBN(Long ISBN) throws Exception{
        try{
            if(ISBN == null){
                throw new Exception("El número del ISBN es obligatorio");
            }
            Libro libro=libroDAO.buscarLibroPorISBN(ISBN);
         
         return libro;
            
        }catch(Exception e){
            throw e;
        }
    }
    
    public Libro BuscarLibroPorTitulo(String titulo) throws Exception{
        try{
            Libro libro=libroDAO.buscarLibroPorNombre(titulo);
         return libro;
            
        }catch(Exception e){
            throw e;
        }
    }
    
    
    
    
}
