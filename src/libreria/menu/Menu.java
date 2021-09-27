
package libreria.menu;

import java.util.Scanner;
import java.util.UUID;
import libreria.entidad.Autor;
import libreria.entidad.Editorial;
import libreria.entidad.Libro;
import libreria.servicio.AutorServicio;
import libreria.servicio.EditorialServicio;
import libreria.servicio.LibroServicio;


public class Menu {
    private Menu menu;
    AutorServicio autorServicio = new AutorServicio();
    EditorialServicio editorialServicio = new EditorialServicio();
    LibroServicio libroServicio = new LibroServicio();
    Scanner leer = new Scanner (System.in).useDelimiter("\n");
    
    public void ejecutarMenu(){
        Integer opcion1 = 0;
        Integer opcion2 = 0;
        Integer opcion3 = 0;
        String autor;
     
        try{ 
            do {
                System.out.println("\n***MENU***"); 
                System.out.println("1.AUTOR\n2.EDITORIAL\n3.LIBRO\n4.BÚSQUEDAS\n5.SALIR");
                System.out.print("Opción:");
                opcion1 = leer.nextInt();
                switch (opcion1) {
                    case 1:
                        System.out.println("\n***AUTOR***\n1.ALTA\n2.BAJA\n3.MOSTRAR LISTA DE REGISTROS\n4.VOLVER AL MENÚ ANTERIOR");
                        System.out.print("Opción:");
                        opcion2 = leer.nextInt();
                        switch (opcion2) {
                            case 1:
                                    try{
                                        altaDeAutor();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }   
                            case 2:
                                    try{
                                        bajaDeAutor();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }
                            case 3:
                                    try{
                                        autorServicio.mostrarListaDeAutores();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }
                            case 4:break;        
                            default:
                                System.out.println("Ingresó una opción inválida");
                                break;
                            } 
                            break;
                    case 2:
                        System.out.println("\n***EDITORIAL***\n1.ALTA\n2.BAJA\n3.MOSTRAR LISTA DE REGISTROS\n4.VOLVER AL MENÚ ANTERIOR");
                        System.out.print("Opción:");
                        opcion2 = leer.nextInt();
                        switch (opcion2) {
                            case 1:
                                    try{
                                        altaDeEditorial();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }   
                            case 2:
                                    try{
                                        bajaDeEditorial();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }   
                            case 3:
                                    try{
                                        editorialServicio.mostrarListaDeEditoriales();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }
                            case 4:
                                break;
                            default:
                                System.out.println("Ingresó una opción inválida");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("\n***LIBRO***\n1.ALTA\n2.BAJA\n3.MOSTRAR LISTA DE REGISTROS\n4.VOLVER AL MENÚ ANTERIOR");
                        System.out.print("Opción:");
                        opcion2 = leer.nextInt();
                        switch (opcion2) {
                            case 1:
                                    try{
                                        altaDeLibro();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    }   
                            case 2:
                                    try{
                                        bajaDeLibro();
                                    }catch(Exception e){
                                        System.out.println(e.getMessage());
                                        throw e;
                                    }finally{
                                        break;
                                    } 
                            case 3:
                                try{
                                   libroServicio.mostrarListaDelibros();
                                }catch(Exception e){
                                    System.out.println(e.getLocalizedMessage());
                                    throw e;
                                }finally{
                                    break;
                                }
                            default:
                                System.out.println("Ingresó una opción inválida");
                                break;
                        }
                        break;
                    case 4:
                         System.out.println("\n***BUSQUEDAS***");
                         System.out.println("1.Búsqueda de un Autor por nombre\n2.Búsqueda de un libro por ISBN\n3.Búsqueda de un libro por Título\n4.Búsqueda de un libro/s por nombre de Autor\n5.Búsqueda de un libro/s por nombre de Editorial\n6.Salir");
                         System.out.print("Opcion:");
                         opcion3=leer.nextInt();
                         switch (opcion3) {
                            case 1:
                                try{
                                    System.out.println("\n***BUSQUEDA DE AUTOR***");
                                    System.out.print("Ingrese el nombre del autor:");
                                    autor=leer.next();
                                    busquedaAutorPorNombre(autor); 
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                    throw e;
                                }finally{
                                        break;        
                                }
                            case 2:
                                try{
                                    System.out.println("\n***BUSQUEDA DE LIBRO POR ISBN***");
                                    System.out.print("Ingrese ISBN:");
                                    Long isbn=leer.nextLong();
                                    busquedaLibroPorISBN(isbn);
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                    throw e;
                                }finally{
                                    break;
                                }
  
                            case 3:
                               try{
                                   System.out.println("\n***BUSQUEDA DE LIBRO POR TITULO***");
                                   System.out.print("Ingrese titulo del libro:");
                                   String titulo=leer.next();
                                   busquedaLibroPorTitulo(titulo);
                               }catch(Exception e){
                                   System.out.println(e.getMessage());   
                                   throw e;
                               }finally{
                                   break;
                               }
                            case 4:
                                try{
                                    System.out.println("\n***BÚSQUEDA DE UN LIBRO/S POR NOMBRE DE AUTOR***");
                                    System.out.print("Ingrese el nombre del autor:");
                                    autor=leer.next();
                                    busquedaDeAutorPorNombre(autor);
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                    throw e;
                                }finally{
                                    break;
                                }
                            case 5:
                                try{
                                    System.out.println("\n***BÚSQUEDA DE UN LIBRO/S POR NOMBRE DE EDITORIAL***");
                                    System.out.print("Ingrese el nombre de la editorial:");
                                    String editorial=leer.next();
                                    busquedaDeLibrosPorNombreDeEditorial(editorial);
                                }catch(Exception e){
                                    System.out.println(e.getMessage());
                                    throw e;
                                }finally{
                                    break;
                                }
                            case 6:
                                System.out.println("***PROGRAMA FINALIZADO***");
                                break;
                            default:
                                System.out.println("Ingresó una opción invalida");
                                break;
                            }
                         break;
                    case 5:
                        System.out.println("***PROGRAMA FINALIZADO***");
                        break;
                    default:
                        throw new Exception("Ingresó una opción inválida");
                }
            } while (opcion1!=5); 
        }catch(Exception e){
            leer.nextLine();
            System.out.println(e.getMessage());
        }
       }

    
    public void altaDeAutor() throws Exception{ 
        System.out.println("\n***ALTA AUTOR***");
        System.out.print("Ingrese nombre:");
        String autor=leer.next();
           try{
               autorServicio.crearAutor(autor);
           }catch(Exception e){
            throw e;
           } 
    }
    
    public void bajaDeAutor() throws Exception{ 
        System.out.println("\n***BAJA AUTOR***");
        autorServicio.mostrarListaDeAutores();
        try{
          System.out.print("Seleccione el nombre del autor a eliminar:");
            String autor=leer.next();
            autorServicio.eliminarAutor(autor);  
        }catch(Exception e){
            throw e;
        }    
    }
    
    public void altaDeEditorial() throws Exception{ 
        System.out.println("\n***ALTA EDITORIAL***");
        System.out.print("Ingrese nombre:");
        String editorial=leer.next();
           try{
               editorialServicio.crearEditorial(editorial);
           }catch(Exception e){
            throw e;
           } 
    }
    
    public void bajaDeEditorial() throws Exception{ 
        System.out.println("\n***BAJA EDITORIAL***");
        editorialServicio.mostrarListaDeEditoriales();
        try{
          System.out.print("Seleccione el nombre de la editorial a eliminar:");
            String editorial=leer.next();
            editorialServicio.eliminarEditorial(editorial);
        }catch(Exception e){
            throw e;
        }    
    }   
    
    public void altaDeLibro() throws Exception{ 
        try{
            Libro libro;
            System.out.println("\n***ALTA LIBRO***");
            System.out.print("INGRESE TÍTULO:");
            String nombreLibro=leer.next();
            Long isbn=Long.parseLong(UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0,13));
            System.out.print("INGRESE AÑO:");
            Integer anio=leer.nextInt();
            
            Integer ejemplares = (int)(Math.random()*4+3);
            Integer ejemplaresPrestados = (int)(Math.random()*3+1);
            
            editorialServicio.mostrarListaDeEditoriales();
            Editorial editorial;
            System.out.print("INGRESE NOMBRE DE EDITORIAL:");
            String nombre=leer.next();    
            editorial=editorialServicio.crearEditorialParaLibro(nombre);
         
            autorServicio.mostrarListaDeAutores();
            Autor autor;
            System.out.print("INGRESE NOMBRE DE AUTOR:");
            String nombreAutor=leer.next();
            autor=autorServicio.crearAutorParaLibro(nombreAutor);
            
            libroServicio.crearLibro(isbn, nombreLibro, anio, ejemplares, ejemplaresPrestados, autor, editorial);
        
        }catch(Exception e){
            throw e;
           } 
    }
    
    public void bajaDeLibro() throws Exception{ 
        System.out.println("\n***BAJA DE LIBRO***");
        libroServicio.mostrarListaDelibros();
        try{
          System.out.print("INGRESE EL NÚMERO DE ISBN DEL LIBRO A ELIMINAR:");
          Long isbn=leer.nextLong();
          libroServicio.eliminarLibro(isbn);
        }catch(Exception e){
            throw e;
        }    
    } 
    
    public void busquedaAutorPorNombre(String nombre) throws Exception{
        try{
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("El nombre del autor es obligatorio");
            } 
            
            if(autorServicio.BuscarAutoresPorNombre(nombre).isEmpty()){
                throw new Exception("No existe autor con ese nombre");
            }
           
            Autor autor=autorServicio.BuscarAutorPorNombre(nombre);
            
            System.out.printf("\n%-25s%-25s%-45s%-15s%-15s%-25s%-25s%-25s\n","AUTOR","ISBN","TÍTULO","AÑO","EJEMPLARES","EJEMPLARES PRESTADOS", "EJEMPLARES RESTANTES","EDITORIAL");
            for (Libro l: autor.getLibros()) {
                if(l.getAlta()==true){
                    System.out.printf("%-25s%-25s%-45s%-15s%-15s%-25s%-25s%-25s\n",l.getAutor().getNombre(),l.getIsbn(),l.getTitulo(),l.getAnio(),l.getEjemplares(),l.getEjemplaresPrestados(),l.getEjemplaresRestantes(),l.getEditorial().getNombre());
                }
            }
        
        }catch(Exception e){
            throw e;
        }
    }
    
        public void busquedaDeAutorPorNombre(String nombre) throws Exception{
        try{
            if(nombre == null || nombre.trim().isEmpty()){
                throw new Exception("El nombre del autor es obligatorio");
            } 
            
            if(autorServicio.BuscarAutoresPorNombre(nombre).isEmpty()){
                throw new Exception("No existe autor con ese nombre");
            }
           
            Autor autor=autorServicio.BuscarAutorPorNombre(nombre);
            
            int i=1;
            System.out.printf("\n%-25s%-25s%-45s%-15s%-15s%-25s%-25s%-25s\n","AUTOR","ISBN","TÍTULO","AÑO","EJEMPLARES","EJEMPLARES PRESTADOS", "EJEMPLARES RESTANTES","EDITORIAL");
           

            for (Libro l: autor.getLibros()) {
                if((l.getAlta()==true)){
                  System.out.printf("\n%-25s%-25s%-45s%-15s%-15s%-25s%-25s%-25s\n",l.getAutor().getNombre(),l.getIsbn(),l.getTitulo(),l.getAnio(),l.getEjemplares(),l.getEjemplaresPrestados(),l.getEjemplaresRestantes(),l.getEditorial().getNombre());
                }
            }
        
        }catch(Exception e){
            throw e;
        }
    }
    
    
    public void busquedaLibroPorISBN(Long isbn) throws Exception{
        try{
            if(isbn == null){
                throw new Exception("El número de ISBN es obligatorio");
            } 
           
           Libro libro=libroServicio.BuscarLibroPorISBN(isbn);            
           if(libro==null || libro.getAlta()==false){
                throw new Exception("No hay libros asociados a dicho ISBN");
            }
           
           System.out.printf("\n%-25s%-45s%-15s%-15s%-25s%-25s%-25s%-25s\n","ISBN","TÍTULO","AÑO","EJEMPLARES","EJEMPLARES PRESTADOS", "EJEMPLARES RESTANTES","AUTOR","EDITORIAL");
           System.out.printf("\n%-25s%-45s%-15s%-15s%-25s%-25s%-25s%-25s\n",libro.getIsbn(),libro.getTitulo(),libro.getAnio(),libro.getEjemplares(),libro.getEjemplaresPrestados(),libro.getEjemplaresRestantes(),libro.getAutor().getNombre(),libro.getEditorial().getNombre());

        }catch(Exception e){
            throw e;
        }
    }
    
    public void busquedaLibroPorTitulo(String titulo) throws Exception{
        try{
            if(titulo == null){
                throw new Exception("El nombre del titulo es obligatorio");
            } 
           Libro libro=libroServicio.BuscarLibroPorTitulo(titulo);
           
           if(libro==null || libro.getAlta()==false){
                throw new Exception("No hay libros asociados a dicho titulo");
            }
           
           System.out.printf("\n%-45s%-25s%-15s%-15s%-25s%-25s%-25s%-25s\n","TÍTULO","ISBN","AÑO","EJEMPLARES","EJEMPLARES PRESTADOS", "EJEMPLARES RESTANTES","AUTOR","EDITORIAL");
           System.out.printf("\n%-45s%-25s%-15s%-15s%-25s%-25s%-25s%-25s\n",libro.getTitulo(),libro.getIsbn(),libro.getAnio(),libro.getEjemplares(),libro.getEjemplaresPrestados(),libro.getEjemplaresRestantes(),libro.getAutor().getNombre(),libro.getEditorial().getNombre());
        
        }catch(Exception e){
            throw e;
        }
    }
    
    
        public void busquedaDeLibrosPorNombreDeEditorial(String nombre) throws Exception{
        try{
            if(nombre == null){
                throw new Exception("El nombre de la editorial es obligatorio");
            } 
            Editorial editorial =editorialServicio.BuscarEditorialPorNombre(nombre);
            if(editorial==null){
                throw new Exception("No hay libros asociados a dicho autor");
            }
            
            System.out.printf("\n%-25s%-25s%-45s%-25s%-25s%-25s%-25s%-25s\n","EDITORIAL","ISBN","TITULO","AÑO","EJEMPLARES","EJEMPLARES PRESTADOS", "EJEMPLARES RESTANTES","AUTOR");
            for (Libro l: editorial.getLibros()) {
                if(l.getAlta()==true){
                    System.out.printf("\n%-25s%-25s%-45s%-25s%-25s%-25s%-25s%-25s\n",l.getEditorial().getNombre(),l.getIsbn(),l.getTitulo(),l.getAnio(),l.getEjemplares(),l.getEjemplaresPrestados(),l.getEjemplaresRestantes(),l.getAutor().getNombre());
                }
            }
        }catch(Exception e){
            throw e;
        }
    }
    
}
