/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.Biblioteca.service;

import edu.egg.Biblioteca.ExcepcionesServicio.Excepciondeservicio;
import edu.egg.Biblioteca.entity.Autor;
import edu.egg.Biblioteca.entity.Libro;
import edu.egg.Biblioteca.repository.AutorRepositorio;
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
    @Autowired
    private AutorRepositorio repositorioautor;

    @Transactional
    public Libro AgregarLibro(String isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresp, String autorid) throws Excepciondeservicio {
        vallidacion(nombre, isbn, anio, ejemplares);
        Autor autor= repositorioautor.findById(autorid).get();
    
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

        return repositoriolibro.save(libro);

    }


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
    public void modificarlibro(String id, String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresp,String idautor) throws Excepciondeservicio {
        
        Autor autor = repositorioautor.findById(idautor).get();

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

    }
}
