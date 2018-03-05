/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clasespropias;

/**
 *
 * @author FABAME
 */
/**
 * Clase para controlar y gestionar excepciones en tiempo de ejecución.
 */
public class Excepcion extends RuntimeException {

    //Atributos de la clase Excepcion.
    private static final long serialVersionUID = 6402188766809335533L;

    //Constructores de la clase Excepcion.
    /**
     * Construye una nueva excepción de tiempo de ejecución con nulo su mensaje
     * de detalle. La causa no se inicializa y, posteriormente, puede
     * inicializarse mediante una llamada a
     * Throwable.initCause(java.lang.Throwable).
     */
    public Excepcion() {
        super();
    }

    /**
     * Construye una nueva excepción de tiempo de ejecución con el mensaje de
     * detalle especificado. La causa no se inicializa y, posteriormente, puede
     * inicializarse mediante una llamada a
     * Throwable.initCause(java.lang.Throwable).
     *
     * @param mensaje el mensaje de detalle. El mensaje de detalle se guarda
     * para su posterior recuperación por el Throwable.getMessage()método.
     */
    public Excepcion(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye una nueva excepción de tiempo de ejecución con el mensaje de
     * detalle y la causa especificados. Tenga en cuenta que el mensaje de
     * detalle asociado con causeestá no incorpora automáticamente en el mensaje
     * de detalle de esta excepción en tiempo de ejecución.
     *
     * @param mensaje el mensaje de detalle (que se guarda para su posterior
     * recuperación por el Throwable.getMessage()método).
     * @param causa la causa (que se guarda para su posterior recuperación por
     * el Throwable.getCause()método). (Se permite un valor nulo e indica que la
     * causa es inexistente o desconocida).
     */
    public Excepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * Construye una nueva excepción de tiempo de ejecución con la causa
     * especificada y un mensaje de detalle de (causa == null? Null:
     * causa.aEstring ()) (que normalmente contiene el mensaje de clase y
     * detalle de causa ). Este constructor es útil para excepciones en tiempo
     * de ejecución que son poco más que envoltorios para otros throwables.
     *
     * @param causa la causa (que se guarda para su posterior recuperación por
     * el Throwable.getCause()método). (Se permite un valor nulo e indica que la
     * causa es inexistente o desconocida).
     */
    public Excepcion(Throwable causa) {
        super(causa);
    }

}
