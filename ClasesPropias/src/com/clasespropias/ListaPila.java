/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clasespropias;

import java.util.EmptyStackException;

/**
 * Clase ListaPila para guardar y manipular elementos en una pila.
 */
/**
 * La clase <code> ListaPila </code> representa un último en entrar, primero en
 * salir (LIFO) pila de objetos. Extiende la clase <tt> ListaVector </tt> con
 * cinco operaciones que permiten tratar un vector como una pila. Lo normal Se
 * proporcionan operaciones <tt> poner </tt> y <tt> quitar </tt>, así como
 * método para <tt> ojear </tt> al elemento superior de la pila, un método para
 * probar para saber si la pila está <tt> vacía </tt> y un método para <tt>
 * buscar </tt>
 * la pila de un artículo y descubre qué tan lejos está de la parte superior.
 * <p>
 * Cuando se crea una pila por primera vez, no contiene elementos.
 *
 * <p>
 * Un conjunto más completo y consistente de operaciones de pila LIFO es
 * proporcionada por la interfaz {@link Cola} y sus implementaciones, que debe
 * usarse con preferencia a esta clase. Por ejemplo:
 * <pre> {@code
 * Cola <Integer> cola = new ColaArreglo<Integer>();} </pre>
 *
 * @author FABAME
 * @since JDK 1.0
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public class ListaPila<E> extends ListaVector<E> implements Pila<E> {

    //Atributos de la clase ListaPila.
    /**
     * Número serial version UID generado de la clase ListaPila. Usa
     * serialVersionUID de JDK 1.0.2 para interoperabilidad
     */
    private static final long serialVersionUID = 1224463164541339165L;

//Constructores de la clase ListaPila.
    /**
     * Crea una Pila vacía.
     */
    public ListaPila() {

    }

    //Métodos de la clase ListaPila.
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
    @Override
    public E poner(E elemento) {
        this.agregarElemento(elemento);
        return elemento;
    }

    /**
     * Elimina el objeto en la parte superior de esta pila y devuelve ese objeto
     * como el valor de esta función.
     *
     * @return El objeto en la parte superior de esta pila (el último elemento
     * del objeto <tt> Vector </tt>).
     * @throws EmptyStackException si esta pila está vacía.
     */
    @Override
    public synchronized E quitar() {
        E objeto;
        int tamanio = this.tamanio();

        objeto = this.ojear();
        this.removerElementoEn(tamanio - 1);

        return objeto;
    }

    /**
     * Mira el objeto en la parte superior de esta pila sin quitarlo de la pila.
     *
     * @return el objeto en la parte superior de esta pila (el último elemento
     * del objeto <tt> Vector </tt>).
     * @throws EmptyStackException si esta pila está vacía.
     */
    @Override
    public synchronized E ojear() {
        int tamanio = this.tamanio();
        if (tamanio == 0) {
            throw new EmptyStackException();
        }
        return this.elementoEn(tamanio);
    }

    /**
     * Prueba si esta pila está vacía.
     *
     * @return <code> true </code> si y solo si esta pila contiene No hay
     * artículos; <code> false </code> de lo contrario.
     */
    @Override
    public boolean estaVacia() {
        return this.tamanio() == 0;
    }

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
    @Override
    public synchronized int buscar(Object objeto) {
        int i = this.ultimoIndiceDe(objeto);
        if (i >= 0) {
            return this.tamanio() - i;
        }
        return -1;
    }

    /**
     * <p>
     * Muestra todos los elementos agregados en la pila.
     *
     * @return los elementos agregados en la pila.
     */
    @Override
    public String imprimir() {
        String s = "";
        ListaPila<E> listaPilaAuxiliar = new ListaPila<>();
        while (!this.estaVacia()) {
            E elemento = this.quitar();
            s += elemento + "\n";
            listaPilaAuxiliar.poner(elemento);
        }
        while (!listaPilaAuxiliar.estaVacia()) {
            this.poner(listaPilaAuxiliar.quitar());
        }
        return s;
    }

}
