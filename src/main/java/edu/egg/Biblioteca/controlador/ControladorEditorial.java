//
//package edu.egg.Biblioteca.controlador;
//
//import edu.egg.Biblioteca.entity.Editorial;
//import edu.egg.Biblioteca.service.EditorialServicio;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//@Controller
//@RequestMapping("/editorial")
//public class ControladorEditorial {
////    @Autowired
////    public EditorialServicio ServicioEdi;
//    
//    @GetMapping("/lista")
//    public String ListaDeEditorial(ModelMap modelo){
//        List <Editorial> editorial= ServicioEdi.ListadoEditoriales();
//        modelo.put("Editoriales", editorial);
//        return "listaeditorial.html";
//    }
//    @GetMapping("/agregareditorial")
//    public String AgregarAutor() {
//        return "agregarE";
//    }
//
//    @PostMapping("/agregareditorial")
//    public String Agregarautor(ModelMap modelo, @RequestParam String nombre) {
//        try {
//            ServicioEdi.AgregarEditorial(nombre);
//            modelo.put("exito", "Autor cargado Exitosamente!");
//            return "redirect:/editorial/lista";
//        } catch (Exception e) {
//            modelo.put("error", "Falla en el cargado de el Autor!");
//            return "redirect:/editorial/lista";
//        }
//    }
//}
