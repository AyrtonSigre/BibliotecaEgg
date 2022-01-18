//package edu.egg.Biblioteca.service;
//
//import edu.egg.Biblioteca.ExcepcionesServicio.Excepciondeservicio;
//import edu.egg.Biblioteca.entity.Editorial;
//import edu.egg.Biblioteca.repository.EditorialRepositorio;
//import java.util.List;
//import java.util.Optional;
//import javax.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class EditorialServicio {
//
//    @Autowired
//    private EditorialRepositorio repositorioeditorial;
//
//    @Transactional
//    public void AgregarEditorial(String nombre) throws Excepciondeservicio {
//        validacion(nombre);
//        Editorial editorial = new Editorial();
//        editorial.setNombre(nombre);
//        editorial.setAlta(true);
//        repositorioeditorial.save(editorial);
//        //save es un metodo de la clase que estiende JPArepository y sirve para guardarlo en BD 
//        //El repositorio es el que se va a encargar de almacenar ese objeto en una o mas tablas
//
//    }
//
//    @Transactional
//    public void modificarEditorial(String nombre, String id) throws Excepciondeservicio {
//        if (id != null) {
//            Editorial editorialM = BuscarEditorialPoid(id);
//            if (editorialM != null) {
//                editorialM.setNombre(nombre);
//                repositorioeditorial.save(editorialM);
//            }
//        } else {
//            throw new Excepciondeservicio("La editorial buscada no puede ser nula");
//        }
//
//    }
//
//    @Transactional
//
//    public Editorial BuscarEditorialPoid(String id) throws Excepciondeservicio {
//        Editorial edi = repositorioeditorial.getOne(id);
//        if (edi != null) {
//            return edi;
//
//        } else {
//            throw new Excepciondeservicio("La editorial buscada no puede ser nula");
//        }
//    }
//
//    @Transactional
//
//    public List ListadoEditoriales() {
//        List<Editorial> ListEdi = repositorioeditorial.findAll();
//        return ListEdi;
//
//    }
//
//    public void validacion(String nombre) throws Excepciondeservicio {
//        if (nombre == null || nombre.isEmpty()) {
//            throw new Excepciondeservicio("El nombre del editorial no puede ser nulo");
//        }
//
//    }
//
//    @Transactional
//    public void deshabilitareditorial(String id) throws Excepciondeservicio {
//        Optional<Editorial> respuesta = repositorioeditorial.findById(id);
//        if (respuesta.isPresent()) {
//            Editorial editorial = respuesta.get();
//            editorial.setAlta(false);
//            repositorioeditorial.save(editorial);
//        } else {
//            throw new Excepciondeservicio("no se encontro el id solicitado");
//        }
//    }
//
//    @Transactional
//    public void Habilitareditorial(String id) throws Excepciondeservicio {
//        Optional<Editorial> respuesta = repositorioeditorial.findById(id);
//        if (respuesta.isPresent()) {
//            Editorial editorial = respuesta.get();
//            editorial.setAlta(true);
//            repositorioeditorial.save(editorial);
//        } else {
//            throw new Excepciondeservicio("no se encontro el id solicitado");
//
//        }
//    }
//}
