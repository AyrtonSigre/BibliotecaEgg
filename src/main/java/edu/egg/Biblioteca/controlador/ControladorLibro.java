/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.Biblioteca.controlador;

import edu.egg.Biblioteca.ExcepcionesServicio.Excepciondeservicio;
import edu.egg.Biblioteca.entity.Autor;
import edu.egg.Biblioteca.entity.Libro;
import edu.egg.Biblioteca.service.AutorServicio;
import edu.egg.Biblioteca.service.LibroServicio;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class ControladorLibro {

    @Autowired
    private LibroServicio serviciolibro;
    @Autowired
    private AutorServicio servicioautor;

    @GetMapping("/editarlibro")
    public String editarlibro(ModelMap modelo) {
        List<Autor> autores = servicioautor.listadoAutores();
        modelo.put("autores", autores);
        return "editarlibros.html";
    }

    @PostMapping("/editarlibro")
    public String guardarlibro(ModelMap modelo, @RequestParam String isbn, @RequestParam String titulo, Integer anio, Integer ejemplares, Integer ejemplaresp,Autor autor) {
//            @RequestParam Autor autor,@RequestParam Editorial editorial

        try {
            serviciolibro.AgregarLibro(isbn, titulo, anio, ejemplares, ejemplaresp,autor);
            modelo.put("exito", "libro cargado exitosamente!");
            return "editarlibros.html";
        } catch (Excepciondeservicio e) {
            modelo.put("error", "Faltan datos!");
            return "editarlibros.html";
        }
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        Collection<Libro> libros = serviciolibro.listadoLibros();
        modelo.put("libros", libros);
        return "listado.html";
    }

    @PostMapping("/lista")
    public String Listallena() {

        return "listado.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
       List<Autor> autores = servicioautor.listadoAutores();
       modelo.put("autores", autores);
       modelo.put("libro", serviciolibro.buscarporid(id));
        
        return "modificarl.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String isbn, @RequestParam String titulo, Integer anio, Integer ejemplares,Integer ejemplaresp,Autor autor) {
        try {

            if (serviciolibro.buscarporid(id) != null) {
                serviciolibro.modificarlibro(id, isbn, titulo, anio, ejemplares, ejemplaresp,autor);
            } else {
                throw new Excepciondeservicio("No puede haber variable vacia");
            }

            return  "redirect:/libro/lista";
        } catch (Exception e) {

            return "modificarl.html";
        }
    }
@GetMapping("/baja/{id}")
public String baja(@PathVariable String id) throws Excepciondeservicio{
   serviciolibro.deshabilitarLibro(id);
    return "redirect:/libro/lista";
}
    
@GetMapping("/alta/{id}")    
public String alta (@PathVariable String id) throws Excepciondeservicio{
    serviciolibro.HabilitarLibro(id);
      return  "redirect:/libro/lista";
}
    
}
