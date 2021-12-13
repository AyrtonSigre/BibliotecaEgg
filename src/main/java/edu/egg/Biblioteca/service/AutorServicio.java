package edu.egg.Biblioteca.service;

import edu.egg.Biblioteca.ExcepcionesServicio.Excepciondeservicio;
import edu.egg.Biblioteca.entity.Autor;
import edu.egg.Biblioteca.repository.AutorRepositorio;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio repositorioautor;

    @Transactional
    public void AgregarAutor(String nombre) throws Excepciondeservicio {
        validacion(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        repositorioautor.save(autor);//save es un metodo de la clase que estiende JPArepository y sirve para guardarlo en BD 
        //El repositorio es el que se va a encargar de almacenar ese objeto en una o mas tablas

    }

    public void modificar(String id, String nombre) throws Excepciondeservicio {
        if (id != null) {

            Autor autor = repositorioautor.getOne(id);

            if (autor != null) {
                autor.setNombre(nombre);
                repositorioautor.save(autor);
            }

        } else {
            throw new Excepciondeservicio("No se encuentra el autor");
        }
    }

    public void validacion(String nombre) throws Excepciondeservicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new Excepciondeservicio("El nombre del autor no puede ser nulo");
        }

    }

    @Transactional
    public Autor Autorid(String id) {
        return repositorioautor.getOne(id);
    }

    @Transactional
    public List<Autor> listadoAutores() {

        List<Autor> libros = repositorioautor.findAll();
        return libros;

    }

//    @Transactional
//    public Collection ListarAutores() throws Excepciondeservicio {
//
//        Collection<Autor> autores = repositorioautor.findAll();
//
//        if (autores != null) {
//            return autores;
//        } else {
//            throw new Excepciondeservicio("No puede estar vacia");
//        }
//
//    }

    @Transactional
    public void deshabilitaroraut(String id) throws Excepciondeservicio {
        Optional<Autor> respuesta = repositorioautor.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            repositorioautor.save(autor);
        } else {
            throw new Excepciondeservicio("no se encontro el id solicitado");

        }
    }

    @Transactional
    public void HabilitarAutor(String id) throws Excepciondeservicio {
        Optional<Autor> respuesta = repositorioautor.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);
            repositorioautor.save(autor);
        } else {
            throw new Excepciondeservicio("no se encontro el id solicitado");

        }
    }
}
