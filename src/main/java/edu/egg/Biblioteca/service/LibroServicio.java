/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.Biblioteca.service;

import edu.egg.Biblioteca.ExcepcionesServicio.Excepciondeservicio;
import edu.egg.Biblioteca.entity.Autor;
import edu.egg.Biblioteca.entity.Libro;
import edu.egg.Biblioteca.repository.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio repositoriolibro;

    @Transactional
    public Libro AgregarLibro(String isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresp, Autor autor) throws Excepciondeservicio {
        vallidacion(nombre, isbn, anio, ejemplares);

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(nombre);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresP(ejemplaresp);
        libro.setEjemplaresR(ejemplares-ejemplaresp);

        libro.setAutor(autor);
        libro.setEditorial(null);
        libro.setAlta(true);

        return repositoriolibro.save(libro);//save es un metodo de la clase que estiende JPArepository y sirve para guardarlo en BD 
        //El repositorio es el que se va a encargar de almacenar ese objeto en una o mas tablas

    }
//
//    public int calculor(int ejemplares, int ejemplaresp) {
//        int ejemplaresr;
//        if (ejemplaresp == 0) {
//            return 0;
//        } else {
//            if (ejemplaresp != 0) {
//                ejemplaresr = ejemplares - ejemplaresp;
//                return ejemplaresr;
//            }
//            if (ejemplares == 0) {
//                return 0;
//            } else {
//                ejemplaresr = ejemplares - ejemplaresp;
//                return ejemplaresr;
//            }
//
//        }
//    }

    @Transactional
    public Libro validarmodificacion(String isbn, String titulo) throws Excepciondeservicio {
        try {
            Libro libro = repositoriolibro.buscarLibroporIsbn(isbn);
            return libro;
        } catch (Exception e) {
            throw new Excepciondeservicio("No se encuentra el libro buscado");
        }
    }

    @Transactional
    public Libro buscarporid(String id) {
        return repositoriolibro.getOne(id);
    }

    @Transactional
    public void modificarlibro(String id, String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresp,Autor autor) throws Excepciondeservicio {

//        validarmodificacion(isbn, titulo);
        Optional<Libro> respuesta = repositoriolibro.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresP(ejemplaresp);
            libro.setEjemplaresR(ejemplares-ejemplaresp);
            libro.setAutor(autor);
            libro.setEditorial(null);
            repositoriolibro.save(libro);

        } else {
            throw new Excepciondeservicio("El libro no se encuentra en la db");
        }

    }

    @Transactional
    public Libro librosPorNombre(String nombre) throws Excepciondeservicio {
        Libro libro = repositoriolibro.buscarLibroPorNombre(nombre);
        if (libro != null) {
            return libro;
        } else {
            throw new Excepciondeservicio("No se encuentra el libro buscado");
        }
    }

    @Transactional
    public void deshabilitarLibro(String id) throws Excepciondeservicio {
        Optional<Libro> respuesta = repositoriolibro.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);
            repositoriolibro.save(libro);
        } else {
            throw new Excepciondeservicio("no se encontro el id solicitado");

        }
    }

    @Transactional
    public void HabilitarLibro(String id) throws Excepciondeservicio {
        Optional<Libro> respuesta = repositoriolibro.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(true);

            repositoriolibro.save(libro);
        } else {
            throw new Excepciondeservicio("no se encontro el id solicitado");
        }
    }

    @Transactional
    public List<Libro> listadoLibros() {

        List<Libro> libros = repositoriolibro.findAll();
        return libros;

    }

    public void vallidacion(String nombre, String isbn, Integer anio, Integer ejemplares) throws Excepciondeservicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new Excepciondeservicio("El nombre del libro no puede ser nulo");
        }
        if (isbn == null) {
            throw new Excepciondeservicio("El isbn no puede ser nulo");
        }
        if (anio == null) {
            throw new Excepciondeservicio("El anio no puede ser nulo");
        }
        if (ejemplares == null) {
            throw new Excepciondeservicio("La cantidad de ejemplares no puede ser nula");
        }
//        if (autor == null) {
//            throw new Excepciondeservicio("el/la autora no puede ser nula/o");
//        }
//        if (editorial == null) {
//            throw new Excepciondeservicio("La Editorial no puede ser nula");
//        }
    }
}
