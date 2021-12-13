
package edu.egg.Biblioteca.repository;

import edu.egg.Biblioteca.entity.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
     @Query("SELECT c FROM Libro c WHERE c.titulo = :titulo")
    public Libro buscarLibroPorNombre(@Param("titulo") String titulo);
    
     @Query("SELECT c FROM Libro c WHERE c.isbn = :isbn")
    public Libro buscarLibroporIsbn (@Param("isbn") String isbn);
    
     @Query("SELECT c FROM Libro c WHERE c.autor.nombre = :autor")
    public Libro buscarLibroporautor (@Param("autor") String autor);
    
     @Query("SELECT c FROM Libro c WHERE c.editorial.nombre = :editorial")
    public Libro buscarLibroporeditorial (@Param("editorial") String editorial);
//  
    
   
}
