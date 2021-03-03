package com.clasespropias;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FABAME
 * @since 1.0
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public interface Pila<E> extends Coleccion<E> {

    /**
     * Coloca un elemento en la parte superior de esta pila. Esto tiene
     * exactamente el mismo efecto que:
     * <blockquote>
     * <pre>
     * addElement (elemento) </pre> </blockquote>
     *
     * @param elemento el elemento que se va a colocar en esta pila.
     * @return el argumento <code> item </code>.
     * @ver java.util.Vector # addElement
     */
    public E poner(E elemento);

    /**
     * Elimina el objeto en la parte superior de esta pila y devuelve ese objeto
     * como el valor de esta función.
     *
     * @return El objeto en la parte superior de esta pila (el último elemento
     * del objeto <tt> Vector </tt>).
     */
    public E quitar();

    /**
     * Mira el objeto en la parte superior de esta pila sin quitarlo de la pila.
     *
     * @return el objeto en la parte superior de esta pila (el último elemento
     * del objeto <tt> Vector </tt>).
     */
    public E ojear();

    /**
     * Prueba si esta pila está vacía.
     *
     * @return <code> true </code> si y solo si esta pila contiene No hay
     * artículos; <code> false </code> de lo contrario.
     */
    @Override
    public boolean estaVacia();

    /**
     * Devuelve la posición basada en 1 donde un objeto está en esta pila. Si el
     * objeto <tt> o </tt> aparece como un elemento en esta pila, esto El método
     * devuelve la distancia desde la parte superior de la pila del ocurrencia
     * más cercana a la parte superior de la pila; el elemento más alto en el Se
     * considera que la pila está a una distancia <tt> 1 </tt>. <tt> es igual a
     * </tt>
     * El método se utiliza para comparar <tt> o </tt> con el elementos de esta
     * pila.
     *
     * @param objeto el objeto deseado.
     * @return la posición basada en 1 desde la parte superior de la pila donde
     * el objeto se encuentra; el valor de retorno <code> -1 </code> indica que
     * el objeto no está en la pila.
     */
    public int buscar(Object objeto);

    /**
     * <p>
     * Imprime todos los elementos agregados en la pila.
     *
     * @return los elementos agregados en la pila.
     */
    public String imprimir();

}
