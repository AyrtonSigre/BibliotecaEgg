/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.Biblioteca.controlador;

import edu.egg.Biblioteca.ExcepcionesServicio.Excepciondeservicio;
import edu.egg.Biblioteca.entity.Autor;
import edu.egg.Biblioteca.service.AutorServicio;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autor")
public class ControladorAutor {

    @Autowired
    public AutorServicio servicioautor;

    @GetMapping("/lista")
    public String lista(ModelMap modelo) throws Excepciondeservicio {
        Collection<Autor> autores = servicioautor.listadoAutores();
        modelo.put("autores", autores);
        return "listaautor.html";
    }
  @PostMapping("/lista")
    public String Listallena() {

        return "listaautor.html";
    }
    @GetMapping("/agregarautor")
    public String AgregarAutor() {
        return "agregarA";
    }

    @PostMapping("/agregarautor")
    public String Agregarautor(ModelMap modelo, @RequestParam String nombre) {
        try {
            servicioautor.AgregarAutor(nombre);
            modelo.put("exito", "Autor cargado Exitosamente!");
            return "redirect:/autor/lista";
        } catch (Exception e) {
            modelo.put("error", "Falla en el cargado de el Autor!");
            return "redirect:/autor/lista";
        }
    }
    
    @GetMapping("/modificar/{id}")
    public String Modificar(ModelMap modelo,@PathVariable String id){
        modelo.put("autor",  servicioautor.Autorid(id));
        return "modificarAutor.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre){
        try {
            servicioautor.modificar(id, nombre);
           return "redirect:/autor/lista";
        } catch (Exception e) {
            return "modificarAutor.html";
        }
    }
    @GetMapping("/baja/{id}")
public String baja(@PathVariable String id) throws Excepciondeservicio{
   servicioautor.deshabilitaroraut(id);
    return "redirect:/autor/lista";
}
    
@GetMapping("/alta/{id}")    
public String alta (@PathVariable String id) throws Excepciondeservicio{
    servicioautor.HabilitarAutor(id);
      return  "redirect:/autor/lista";
}
}
