/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.Biblioteca.ExcepcionesServicio;

/**
 *
 * @author Usuario
 */
public class Excepciondeservicio extends Exception {

    /**
     * Creates a new instance of <code>Excepciondeservicio</code> without detail
     * message.
     */
    public Excepciondeservicio() {
    }

    /**
     * Constructs an instance of <code>Excepciondeservicio</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public Excepciondeservicio(String msg) {
        super(msg);
    }
}
