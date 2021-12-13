/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.Biblioteca.repository;

import edu.egg.Biblioteca.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
     @Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
    public Editorial buscarEditorialporNOMBRE(@Param("nombre") String nombre);
}
