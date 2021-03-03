/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clasespropias;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import sun.misc.SharedSecrets;

/**
 * Clase ColaArreglo para guardar y manipular elementos en una cola de arreglo.
 */
/**
 * Implementación de arreglo redimensionable de la interfaz {@link ColaDeque}.
 * Formación los deques no tienen restricciones de capacidad; crecen según sea
 * necesario para apoyar uso. No son seguros para subprocesos; en ausencia de
 * externos sincronización, no admiten el acceso simultáneo de varios
 * subprocesos. Se prohíben los elementos nulos. Es probable que esta clase sea
 * más rápida que {@link Stack} cuando se usa como una pila y más rápido que
 * {@link LinkedList} cuando se usa como cola.
 *
 * <p>
 * La mayoría de las operaciones de {@code ArrayDeque} se ejecutan en un tiempo
 * constante amortizado. Las excepciones incluyen {@link #remover(Object) remover}, {@link
 * #removerPrimeraOcurrencia removerPrimeraOccurrenc}, {@link #removerUltimaOccurrencia
 * removerUltimaOccurrencia}, {@link #contiene contiene}, {@link #iterator
 * iterator.remove ()} y las operaciones masivas, todas las cuales se ejecutan
 * en línea hora.
 *
 * <p>
 * Los iteradores devueltos por el método {@code iterator} de esta clase son
 * <i> fail-fast </i>: si el deque se modifica en cualquier momento después del
 * iterador se crea, de cualquier forma, excepto a través del propio
 * {@code remove} del iterador método, el iterador generalmente arrojará un {@link
 * ConcurrentModificationException}. Así, frente a la concurrencia modificación,
 * el iterador falla rápida y limpiamente, en lugar de arriesgarse
 * comportamiento arbitrario, no determinista en un momento indeterminado en el
 * futuro.
 *
 * <p>
 * Tenga en cuenta que no se puede garantizar el comportamiento a prueba de
 * fallos de un iterador ya que, en términos generales, es imposible ofrecer
 * garantías concretas en el presencia de modificación concurrente no
 * sincronizada. Iteradores de fallas rápidas lanzar
 * {@code ConcurrentModificationException} sobre la base del mejor esfuerzo. Por
 * lo tanto, sería incorrecto escribir un programa que dependiera de este
 * excepción por su corrección: <i> el comportamiento rápido de fallas de los
 * iteradores debe usarse solo para detectar errores. </i>
 *
 * <p>
 * Esta clase y su iterador implementan todos los Métodos <em> opcionales </em>
 * de {@link Coleccion} y {@link
 * Interfaces * Iterator}.
 *
 * <p>
 * Esta clase es miembro de la
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Marco de colecciones de Java </a>.
 *
 *
 * @author FABAME
 * @since 1.6
 * @param <E> el tipo de elementos contenidos en esta colección
 */
public class ColaArreglo<E> extends ColeccionAbstracta<E> implements Cola<E>, Cloneable, Serializable {

    //Atributos de la clase ColaArreglo.
    /**
     * El arreglo en la que se almacenan los elementos de la deque. La capacidad
     * de la deque es la longitud de este arreglo, que es siempre una potencia
     * de dos. Nunca se permite que el arreglo se convierta completo, excepto
     * transitoriamente dentro de un método addX donde es redimensionado (ver
     * duplicarCapacidad) inmediatamente después de estar lleno, evitando así
     * que la cabeza y la cola se enrollen para igualar cada otro. También
     * garantizamos que todas las celdas del arreglo no se mantienen los
     * elementos deque son siempre nulos.
     */
    transient Object[] listadoElementos; // No privado para simplificar el acceso a clases anidadas (

    /**
     * El índice del elemento a la cabeza de la cola (que es el elemento que
     * sería eliminado por remover() o quitar()); o un número arbitrario igual a
     * cola si la cola está vacía.
     */
    transient int cabeza;

    /**
     * El índice en el que se agregaría el siguiente elemento a la cola. de la
     * cola (a través de agregarUltimo(E), agregar(E) o poner(E)).
     */
    transient int cola;

    /**
     * La capacidad mínima que usaremos para un deque recién creado. Debe ser
     * una potencia de 2.
     */
    private static final int CAPACIDAD_INICIAL_MINIMA = 8;

    /**
     * Número serial version UID generado de la clase ColaArreglo.
     */
    private static final long serialVersionUID = 2340985798034038923L;

    //Constructores de la clase ColaArreglo.
    /**
     * Construye una cola arreglo vacía con una capacidad inicial suficiente
     * para contener 16 elementos.
     */
    public ColaArreglo() {
        this.listadoElementos = new Object[16];
    }

    /**
     * Construye una cola arreglo vacía con una capacidad inicial suficiente
     * para contener el número especificado de elementos.
     *
     * @param numeroElementos límite inferior de la capacidad inicial de la cola
     */
    public ColaArreglo(int numeroElementos) {
        this.asignarElementos(numeroElementos);
    }

    /**
     * Construye una cola que contiene los elementos del especificado colección,
     * en el orden en que son devueltos por la colección iterador. (El primer
     * elemento devuelto por la colección iterador se convierte en el primer
     * elemento, o <i> frente </i> del deque.)
     *
     * @param coleccion la colección cuyos elementos se colocarán en el deque
     * @throws NullPointerException si la colección especificada es nula
     */
    public ColaArreglo(Coleccion<? extends E> coleccion) {
        this.asignarElementos(coleccion.tamanio());
        this.agregarTodo(coleccion);
    }

    //Métodos de la clase ColaArreglo.
    // Los principales métodos de inserción y extracción son agregarPrimero,
    // agregarUltimo, encuestarPrimero, encuestarUltimo. Los otros métodos se definen en
    // términos de estos.
    /**
     * Asigna una matriz vacía para contener el número dado de elementos.
     *
     * @param numeroElementos el número de elementos a contener
     */
    private void asignarElementos(int numeroElementos) {
        this.listadoElementos = new Object[calcularTamanio(numeroElementos)];
    }

    /**
     * Duplica la capacidad de esta cola. Llame solo cuando esté lleno, es
     * decir, cuando la cabeza y la cola se han enrollado para igualarse.
     */
    private void duplicarCapacidad() {
        int p = this.cabeza;
        int n = this.listadoElementos.length;
        int r = n - p; //número de elementos a la derecha de p
        int nuevaCapacidad = n << 1;
        if (nuevaCapacidad < 0) {
            throw new IllegalStateException("Lo siento, cola demasiado grande");
        }
        Object[] a = new Object[nuevaCapacidad];
        System.arraycopy(this.listadoElementos, p, a, 0, r);
        System.arraycopy(this.listadoElementos, 0, a, r, p);
        this.listadoElementos = a;
        this.cabeza = 0;
        this.cola = n;
    }

    /**
     * Copia los elementos de nuestro arreglo de elementos en el arreglo
     * especificado, en orden (del primero al último elemento de la cola). Es
     * asumido que el arreglo es lo suficientemente grande para contener todos
     * los elementos de la cola.
     *
     * @return su argumento
     */
    private <T> T[] copiarElementos(T[] arreglo) {
        if (this.cabeza < this.cola) {
            System.arraycopy(this.listadoElementos, this.cabeza, arreglo, 0, this.tamanio());
        } else if (this.cabeza > this.cola) {
            int longitudPosicionCabeza = this.listadoElementos.length - this.cabeza;
            System.arraycopy(this.listadoElementos, this.cabeza, arreglo, 0, longitudPosicionCabeza);
            System.arraycopy(this.listadoElementos, 0, arreglo, longitudPosicionCabeza, this.cola);
        }
        return arreglo;
    }

    /**
     * Inserta el elemento especificado al frente de esta cola.
     *
     * @param elemento el elemento a agregar
     * @throws NullPointerException si el elemento especificado es nulo
     */
    public void agregarPrimero(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }
        this.listadoElementos[this.cabeza = (this.cabeza - 1) & (this.listadoElementos.length - 1)] = elemento;
        if (this.cabeza == this.cola) {
            this.duplicarCapacidad();
        }
    }

    /**
     * Inserta el elemento especificado al final de esta cola.
     *
     * <p>
     * Este método es equivalente a {@link #agregar}.
     *
     * @param elemento el elemento a agregar
     * @throws NullPointerException si el elemento especificado es nulo
     */
    public void agregarUltimo(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }
        this.listadoElementos[this.cola] = elemento;
        if ((this.cola = (this.cola + 1) & (this.listadoElementos.length - 1)) == this.cabeza) {
            this.duplicarCapacidad();
        }
    }

    /**
     * Inserta el elemento especificado al frente de esta cola.
     *
     * @param elemento el elemento a agregar
     * @return {@code true} (según lo especificado por
     * {@link ColaDeque # ofrecerPrimero})
     * @throws NullPointerException si el elemento especificado es nulo
     */
    public boolean ofrecerPrimero(E elemento) {
        this.agregarPrimero(elemento);
        return true;
    }

    /**
     * Inserta el elemento especificado al final de esta cola.
     *
     * @param elemento el elemento a agregar
     * @return {@code true} (según lo especificado por
     * {@link ColaDeque#ofrecerUltimo})
     * @throws NullPointerException si el elemento especificado es nulo
     */
    public boolean ofrecerUltimo(E elemento) {
        this.agregarUltimo(elemento);
        return true;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removerPrimero() {
        E elemento = encuestarPrimero();
        if (elemento == null) {
            throw new NoSuchElementException();
        }
        return elemento;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removerUltimo() {
        E elemento = encuestarUltimo();
        if (elemento == null) {
            throw new NoSuchElementException();
        }
        return elemento;
    }

    public E encuestarPrimero() {
        int ca = this.cabeza;
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[ca];
        //El elemento es nulo si la cola está vacía
        if (resultado == null) {
            return null;
        }
        this.listadoElementos[ca] = null;     //Debe anular la ranura
        this.cabeza = (ca + 1) & (this.listadoElementos.length - 1);
        return resultado;
    }

    public E encuestarUltimo() {
        int co = (this.cola - 1) & (this.listadoElementos.length - 1);
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[co];
        if (resultado == null) {
            return null;
        }
        this.listadoElementos[co] = null;
        this.cola = co;
        return resultado;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E obtenerPrimero() {
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[this.cabeza];
        if (resultado == null) {
            throw new NoSuchElementException();
        }
        return resultado;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E obtenerUltimo() {
        @SuppressWarnings("unchecked")
        E resultado = (E) this.listadoElementos[(this.cola - 1) & (this.listadoElementos.length - 1)];
        if (resultado == null) {
            throw new NoSuchElementException();
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    public E ojearPrimero() {
        // listadoElementos[cabeza] es nulo si la col está vacía
        return (E) this.listadoElementos[this.cabeza];
    }

    @SuppressWarnings("unchecked")
    public E ojearUltimo() {
        return (E) this.listadoElementos[(this.cola - 1) & (this.listadoElementos.length - 1)];
    }

    /**
     * Elimina la primera aparición del elemento especificado en esta cola (al
     * atravesarla cola de la cabeza a la cola). Si la cola no contiene el
     * elemento, no se modifica. Más formalmente, elimina el primer elemento
     * {@code elemento} de modo que {@code objeto.equals (elemento)} (si tal
     * elemento existe). Devuelve {@code true} si este deque contenía el
     * elemento especificado (o de manera equivalente, si esta cola cambió como
     * resultado de la llamada).
     *
     * @param objeto elemento que se eliminará de esta cola, si está presente
     * @return {@code true} si el deque contiene el elemento especificado
     */
    public boolean removerPrimeraOccurrencia(Object objeto) {
        if (objeto == null) {
            return false;
        }
        int mascara = this.listadoElementos.length - 1;
        int i = this.cabeza;
        Object x;
        while ((x = this.listadoElementos[i]) != null) {
            if (objeto.equals(x)) {
                this.eliminar(i);
                return true;
            }
            i = (i + 1) & mascara;
        }
        return false;
    }

    /**
     * Elimina la última aparición del elemento especificado en esta cola (al
     * atravesar la cola de la cabeza a la cola). Si la deque no contiene el
     * elemento, no se modifica. Más formalmente, elimina el último elemento
     * {@code elemento} de modo que {@code objeto.equals (elemento)} (si tal
     * elemento existe). Devuelve {@code true} si esta cola contenía el elemento
     * especificado (o de manera equivalente, si esta cola cambió como resultado
     * de la llamada).
     *
     * @param objeto elemento que se eliminará de esta cola, si está presente
     * @return {@code true} si la cola contiene el elemento especificado
     */
    public boolean removerUltimaOccurrencia(Object objeto) {
        if (objeto == null) {
            return false;
        }
        int mascara = this.listadoElementos.length - 1;
        int i = (this.cola - 1) & mascara;
        Object x;
        while ((x = this.listadoElementos[i]) != null) {
            if (objeto.equals(x)) {
                this.eliminar(i);
                return true;
            }
            i = (i - 1) & mascara;
        }
        return false;
    }

    // *** Métodos de la colección ***
    /**
     * Devuelve el número de elementos de esta deque.
     *
     * @return el número de elementos en este deque
     */
    @Override
    public int tamanio() {
        return (this.cola - this.cabeza) & (this.listadoElementos.length - 1);
    }

    /**
     * Devuelve {@code true} si esta cola no contiene elementos.
     *
     * @return {@code true} si este cola no contiene elementos
     */
    @Override
    public boolean estaVacia() {
        return this.cabeza == this.cola;
    }

    /**
     * Devuelve un iterador sobre los elementos de esta cola. Los elementos se
     * ordenará de la primera (cabeza) a la última (cola). Esto es lo mismo
     * ordenar que los elementos se eliminen de la cola (a través de llamadas
     * sucesivas a {@link #remover} o popped (mediante llamadas sucesivas a
     * {@link #quitar}).
     *
     * @return un iterador sobre los elementos en este deque
     */
    @Override
    public Iterator<E> iterator() {
        return new ColaDequeIterator();
    }

    public Iterator<E> descendingIterator() {
        return new DescendienteIterator();
    }

    /**
     * Inserta el elemento especificado al final de esta cola.
     *
     * <p>
     * Este método es equivalente a {@link #agregarUltimo}.
     *
     * @param elemento el elemento a agregar
     * @return {@code true} (según lo especificado por
     * {@link Coleccion#agregar})
     * @throws NullPointerException si el elemento especificado es nulo
     */
    @Override
    public boolean agregar(E elemento) {
        this.agregarUltimo(elemento);
        return true;
    }

    /**
     * Inserta el elemento especificado al final de esta cola.
     *
     * <p>
     * Este método es equivalente a {@link #ofrecerUltimo}.
     *
     * @param eelemento el elemento a agregar
     * @return {@code true} (según lo especificado por {@link Cola#ofrecer})
     * @throws NullPointerException si el elemento especificado es nulo
     */
    @Override
    public boolean ofrecer(E elemento) {
        this.ofrecerUltimo(elemento);
        return true;
    }

    /**
     * Recupera y elimina la cabecera de la cola representada por esta cola.
     *
     * Este método se diferencia de {@link #encuestar encuestar} solo en que
     * genera una excepción si esta cola está vacía.
     *
     * <p>
     * Este método es equivalente a {@link #removerPrimero}.
     *
     * @return el encabezado de la cola representada por est cola
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public E remover() {
        return this.removerPrimero();
    }

    /**
     * Recupera y elimina la cabecera de la cola representada por este cola (en
     * otras palabras, el primer elemento de esta cola), o devuelve {@code null}
     * si esta cola está vacía.
     *
     * <p>
     * Este método es equivalente a {@link #encuestarPrimero}.
     *
     * @return el encabezado de la cola representada por esta cola, o
     * {@code null} si esta cola está vacía
     */
    @Override
    public E encuestar() {
        return this.encuestarPrimero();
    }

    /**
     * Recupera, pero no elimina, el encabezado de la cola representado por esta
     * cola. Este método difiere de {@link #ojear ojear} solo en que lanza una
     * excepción si esta cola está vacía.
     *
     * <p>
     * Este método es equivalente a {@link #obtenerPrimero}.
     *
     * @return el encabezado de la cola representada por esta cola
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Override
    public E elemento() {
        return this.obtenerPrimero();
    }

    /**
     * Recupera, pero no elimina, el encabezado de la cola representado por esta
     * cola, o devuelve {@code null} si esta cola está vacía.
     *
     * <p>
     * Este método es equivalente a {@link #ojearPrimero}.
     *
     * @return el encabezado de la cola representada por esta cola, o
     * {@code null} si esta cola está vacía
     */
    @Override
    public E ojear() {
        return this.ojearPrimero();
    }

    // *** Métodos de Pila ***
    /**
     * Inserta un elemento en la pila representado por esta cola. En otra
     * palabras, inserta el elemento al principio de esta cola.
     *
     * <p>
     * Este método es equivalente a {@link #agregarPimero}.
     *
     * @param elemento el elemento a poner
     * @throws NullPointerException si el elemento especificado es nulo
     */
    public void poner(E elemento) {
        this.agregarPrimero(elemento);
    }

    /**
     * Saca un elemento de la pila representado por este cola. En otra palabras,
     * elimina y devuelve el primer elemento de esta cola.
     *
     * <p>
     * Este método es equivalente a {@link #removerPrimero()}.
     *
     * @return el elemento al frente de esta cola (que es la parte superior de
     * la pila representada por esta cola)
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E quitar() {
        return this.removerPrimero();
    }

    private void verificarInvariantes() {
        assert this.listadoElementos[this.cola] == null;
        assert this.cabeza == this.cola ? this.listadoElementos[cabeza] == null
                : (this.listadoElementos[this.cabeza] != null
                && this.listadoElementos[(this.cola - 1) & (this.listadoElementos.length - 1)] != null);
        assert this.listadoElementos[(this.cabeza - 1) & (this.listadoElementos.length - 1)] == null;
    }

    /**
     * Elimina el elemento en la posición especificada en el arreglo de
     * elementos, ajustando la cabeza y la cola según sea necesario. Esto puede
     * resultar en movimiento de elementos hacia atrás o hacia adelante en el
     * arreglo.
     *
     * <p>
     * Este método se llama eliminar en lugar de eliminar para enfatizar que su
     * semántica difiere de la de {@link Lista#remover(int)}.
     *
     * @return true si los elementos se movieron hacia atrás
     */
    private boolean eliminar(int i) {
        this.verificarInvariantes();
        final Object[] listadoElementos = this.listadoElementos;
        final int mascara = listadoElementos.length - 1;
        final int ca = this.cabeza;
        final int co = this.cola;
        final int frente = (i - ca) & mascara;
        final int back = (co - i) & mascara;

        //Invariante: cabeza <= i < cola mod circularidad
        if (frente >= ((co - ca) & mascara)) {
            throw new ConcurrentModificationException();
        }

        //Optimizar para el menor movimiento de elementos
        if (frente < back) {
            if (ca <= i) {
                System.arraycopy(listadoElementos, ca, listadoElementos, ca + 1, frente);
            } else { //Envolver alrededor
                System.arraycopy(listadoElementos, 0, listadoElementos, 1, i);
                listadoElementos[0] = listadoElementos[mascara];
                System.arraycopy(listadoElementos, ca, listadoElementos, ca + 1, mascara - ca);
            }
            listadoElementos[ca] = null;
            this.cabeza = (ca + 1) & mascara;
            return false;
        } else {
            if (i < co) { // Copie la cola nula también
                System.arraycopy(listadoElementos, i + 1, listadoElementos, i, back);
                this.cola = co - 1;
            } else { // Wrap around
                System.arraycopy(listadoElementos, i + 1, listadoElementos, i, mascara - i);
                listadoElementos[mascara] = listadoElementos[0];
                System.arraycopy(listadoElementos, 1, listadoElementos, 0, co);
                this.cola = (co - 1) & mascara;
            }
            return true;
        }
    }

    @Override
    public boolean conservarTodo(Coleccion<?> coleccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contieneTodo(Coleccion<?> coleccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Devuelve {@code true} si esta cola contiene el elemento especificado. Más
     * formalmente, devuelve {@code true} si y solo si esta cola contiene al
     * menos un elemento {@code e} tal que {@code objeto.equals(e)}.
     *
     * @param objeto objeto a comprobar para la contención en este deque
     * @return {@code true} si esta deque contiene el elemento especificado
     */
    @Override
    public boolean contiene(Object objeto) {
        if (objeto == null) {
            return false;
        }
        int mascara = this.listadoElementos.length - 1;
        int i = this.cabeza;
        Object x;
        while ((x = this.listadoElementos[i]) != null) {
            if (objeto.equals(x)) {
                return true;
            }
            i = (i + 1) & mascara;
        }
        return false;
    }

    /**
     * Elimina una sola instancia del elemento especificado de esta cola. Si la
     * cola no contiene el elemento, no se modifica. Más formalmente, elimina el
     * primer elemento {@code elemento} de modo que
     * {@code objeto.equals(elemento)} (si tal elemento existe). Devuelve
     * {@code true} si este deque contenía el elemento especificado (o de manera
     * equivalente, si esta cola cambió como resultado de la llamada).
     *
     * <p>
     * Este método es equivalente a {@link #removerPrimeraOccurrencia (Object)}.
     *
     * @param objeto elemento que se eliminará de esta cola, si está presente
     * @return {@code true} si este deque contiene el elemento especificado
     */
    @Override
    public boolean remover(Object objeto) {
        return this.removerPrimeraOccurrencia(objeto);
    }

    /**
     * Elimina todos los elementos de este cola. La cola estará vacía después de
     * que regrese esta llamada.
     */
    @Override
    public void limpiar() {
        int ca = this.cabeza;
        int co = this.cola;
        if (ca != co) { //borrar todas las celdas
            this.cabeza = this.cola = 0;
            int i = ca;
            int mascara = this.listadoElementos.length - 1;
            do {
                this.listadoElementos[i] = null;
                i = (i + 1) & mascara;
            } while (i != co);
        }
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta cola en la
     * secuencia adecuada (del primero al último elemento).
     *
     * <p>
     * El arreglo devuelto será "seguro" en el sentido de que no hay referencias
     * a él mantenido por esta cola. (En otras palabras, este método debe
     * asignar un nuevo arreglo). Por tanto, la persona que llama es libre de
     * modificar la matriz devuelta.
     *
     * <p>
     * Este método actúa como puente entre el basado en arreglos y el basado en
     * colecciones API.
     *
     * @return un arreglo que contiene todos los elementos en esta cola
     */
    @Override
    public Object[] paraFormar() {
        return this.copiarElementos(this.listadoElementos);
    }

    /**
     * Devuelve un arreglo que contiene todos los elementos de esta cola en
     * secuencia adecuada (del primero al último elemento); el tipo de tiempo de
     * ejecución del El arreglo devuelto es la del arreglo especificado. Si el
     * deque encaja el arreglo especificado, se devuelve allí. De lo contrario,
     * un nuevo arreglo se asigna con el tipo de tiempo de ejecución del arreglo
     * especificado y el tamaño de esta cola.
     *
     * <p>
     * Si este deque encaja en el arreglo especificado con espacio de sobra (es
     * decir, el arreglo tiene más elementos que esta cola), el elemento en el
     * arreglo que sigue inmediatamente al final de la deque se establece en
     * {@code null}.
     *
     * <p>
     * Al igual que el método {@link #paraFormar()}, este método actúa como
     * puente entre API basadas en arreglos y colecciones. Además, este método
     * permite control preciso sobre el tipo de tiempo de ejecución del arreglo
     * de salida, y puede, en determinadas circunstancias, se utilizará para
     * ahorrar costes de asignación.
     *
     * <p>
     * Supongamos que {@code x} es una cola conocido por contener solo cadenas.
     * El siguiente código se puede utilizar para volcar la cola en un nuevo
     * arreglo asignado de {@code String}:
     *
     * <pre> {@code String [] y = x.paraFormar (new String [0]);} </pre>
     *
     * Tenga en cuenta que {@code toArray (new Object [0])} es idéntico en
     * función a {@code toArray ()}.
     *
     * @param arreglo el arreglo en el que los elementos de la cola deben
     * almacenarse, si es lo suficientemente grande; de lo contrario, un nuevo
     * arreglo de se asigna el mismo tipo de tiempo de ejecución para este
     * propósito
     * @return un arreglo que contiene todos los elementos en este deque
     * @throws ArrayStoreException si el tipo de tiempo de ejecución del arreglo
     * especificado no es un supertipo del tipo de tiempo de ejecución de cada
     * elemento en este deque
     * @throws NullPointerException si el arreglo especificado es nulo
     */
    @Override
    public <T> T[] paraFormar(T[] arreglo) {
        int tamanio = this.tamanio();
        if (arreglo.length < tamanio) {
            arreglo = (T[]) java.lang.reflect.Array.newInstance(
                    arreglo.getClass().getComponentType(), tamanio);
        }
        this.copiarElementos(arreglo);
        if (arreglo.length > tamanio) {
            arreglo[tamanio] = null;
        }
        return arreglo;
    }

    // *** Métodos de Objeto ***
    /**
     * Devuelve una copia de este deque.
     *
     * @return una copia de este deque
     */
    @Override
    public ColaArreglo<E> clone() {
        try {
            @SuppressWarnings("unchecked")
            ColaArreglo<E> resultado = (ColaArreglo<E>) super.clone();
            resultado.listadoElementos = Arrays.copyOf(this.listadoElementos, this.listadoElementos.length);
            return resultado;
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    /**
     * Guarda esta cola en una secuencia (es decir, la serializa).
     *
     * @serialData El tamaño actual ({@code int}) de la cola, seguido de todos
     * sus elementos (cada uno una referencia de objeto) en pedido del primero
     * al último.
     */
    private void escribirObjeto(ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();

        //Escriba el tamaño.
        s.writeInt(this.tamanio());

        //Escribe los elementos en orden.
        int mascara = this.listadoElementos.length - 1;
        for (int i = this.cabeza; i != this.cola; i = (i + 1) & mascara) {
            s.writeObject(this.listadoElementos[i]);
        }
    }

    /**
     * Reconstituye esta cola de una secuencia (es decir, la deserializa).
     */
    private void leerObjeto(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        //Leer en tamaño y asignar arreglo
        int tamanio = s.readInt();
        int capacidad = calcularTamanio(tamanio);
        SharedSecrets.getJavaOISAccess().checkArray(s, Object[].class, capacidad);
        this.asignarElementos(tamanio);
        this.cabeza = 0;
        this.cola = tamanio;

        // Leer todos los elementos en el orden correcto.
        for (int i = 0; i < tamanio; i++) {
            this.listadoElementos[i] = s.readObject();
        }
    }

    /**
     * Crea un <em> <a href="Spliterator.html#binding"> enlace-tardío </a> </em>
     * y <em> fallo-rapido </em> {@link Spliterator} sobre los elementos de este
     * cola.
     *
     * <p>
     * El {@code Spliterator} informa {@link Spliterator # SIZED},
     * {@link Spliterator # SUBSIZED}, {@link Spliterator # ORDERED} y
     * {@link Spliterator # NONNULL}. Las implementaciones primordiales deben
     * documentar la notificación de valores característicos adicionales.
     *
     * @return un {@code Spliterator} sobre los elementos en esta cola
     * @desde 1.8
     */
    @Override
    public Spliterator<E> spliterator() {
        return new ColaDequeSpliterator<E>(this, -1, -1);
    }

    @Override
    public boolean removerTodo(Coleccion<?> coleccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // ****** Utilidades de asignación y cambio de tamaño de arreglos ******
    private static int calcularTamanio(int numeroElementos) {
        int capacidadInicial = CAPACIDAD_INICIAL_MINIMA;
        //Encuentra la mejor potencia de dos para sostener elementos.
        // Prueba "<=" porque los arreglos no se mantienen llenas.
        if (numeroElementos >= capacidadInicial) {
            capacidadInicial = numeroElementos;
            capacidadInicial |= (capacidadInicial >>> 1);
            capacidadInicial |= (capacidadInicial >>> 2);
            capacidadInicial |= (capacidadInicial >>> 4);
            capacidadInicial |= (capacidadInicial >>> 8);
            capacidadInicial |= (capacidadInicial >>> 16);
            capacidadInicial++;

            if (capacidadInicial < 0) // Demasiados elementos, debe retroceder
            {
                capacidadInicial >>>= 1;// Buena suerte asignando 2 ^ 30 elementos
            }
        }
        return capacidadInicial;
    }

    //Clase interna ColaDequeIterator.
    private class ColaDequeIterator implements Iterator<E> {
        //Atributos de la clase interna ColaDequeIterator.

        /**
         * Índice del elemento que se devolverá mediante la siguiente llamada a
         * siguiente.
         */
        private int cursor = cabeza;

        /**
         * Cola grabada en la construcción (también en remover), para detener
         * iterador y también para comprobar la comodificación.
         */
        private int valla = cola;

        /**
         * Índice del elemento devuelto por la llamada más reciente al
         * siguiente. Restablecer a -1 si el elemento es eliminado por una
         * llamada a eliminar.
         */
        private int ultimoRet = -1;

        //Constructor de la clase interna ColaDequeIterator.
        public ColaDequeIterator() {

        }

        //Métodos de la clase interna ColaDequeIterator.
        @Override
        public boolean hasNext() {
            return this.cursor != this.valla;
        }

        @Override
        public E next() {
            if (this.cursor == this.valla) {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            E resultado = (E) listadoElementos[this.cursor];
            // Esta comprobación no recoge todas las posibles comodificaciones,
            // pero atrapa a los que corrompen el recorrido
            if (cola != this.valla || resultado == null) {
                throw new ConcurrentModificationException();
            }
            this.ultimoRet = this.cursor;
            this.cursor = (this.cursor + 1) & (listadoElementos.length - 1);
            return resultado;
        }

        @Override
        public void remove() {
            if (this.ultimoRet < 0) {
                throw new IllegalStateException();
            }
            if (eliminar(this.ultimoRet)) { // if left-shifted, undo increment in next()
                this.cursor = (this.cursor - 1) & (listadoElementos.length - 1);
                this.valla = cola;
            }
            this.ultimoRet = -1;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            Object[] a = listadoElementos;
            int m = a.length - 1, f = this.valla, i = this.cursor;
            this.cursor = f;
            while (i != f) {
                @SuppressWarnings("unchecked")
                E e = (E) a[i];
                i = (i + 1) & m;
                if (e == null) {
                    throw new ConcurrentModificationException();
                }
                action.accept(e);
            }
        }
    }

    private class DescendienteIterator implements Iterator<E> {

        /*
         * Esta clase es casi una imagen reflejada de DeqIterator , usando
         * cola en lugar de cabeza para el cursor inicial y cabeza en lugar de
         * cola para valla.
         */
        //Atributos de la clase interna DescendienteIterator
        private int cursor = cola;
        private int valla = cabeza;
        private int ultimoRet = -1;

        //Constructor de la clase interna DescendienteIterator
        public DescendienteIterator() {

        }

        @Override
        public boolean hasNext() {
            return this.cursor != this.valla;
        }

        @Override
        public E next() {
            if (this.cursor == this.valla) {
                throw new NoSuchElementException();
            }
            this.cursor = (this.cursor - 1) & (listadoElementos.length - 1);
            @SuppressWarnings("unchecked")
            E resultado = (E) listadoElementos[this.cursor];
            if (cabeza != valla || resultado == null) {
                throw new ConcurrentModificationException();
            }
            this.ultimoRet = this.cursor;
            return resultado;
        }

        @Override
        public void remove() {
            if (this.ultimoRet < 0) {
                throw new IllegalStateException();
            }
            if (!eliminar(this.ultimoRet)) {
                this.cursor = (this.cursor + 1) & (listadoElementos.length - 1);
                this.valla = cabeza;
            }
            this.ultimoRet = -1;
        }
    }

    //Clase interna ColaDequeSpliterator.
    static final class ColaDequeSpliterator<E> implements Spliterator<E> {
        //Atributos de la clase interna ColaDequeSpliterator.

        private final ColaArreglo<E> colaArreglo;
        private int valla;  //-1 hasta el primer uso
        private int indice;  // índice actual, modificado en poligonal/split

        //Constructores de la clase interna ColaDequeSpliterator.
        ColaDequeSpliterator() {
            this.colaArreglo = null;
        }

        /**
         * Crea un nuevo spliterator que cubre el arreglo y el rango dados
         */
        ColaDequeSpliterator(ColaArreglo<E> colaArreglo, int origen, int valla) {
            this.colaArreglo = colaArreglo;
            this.indice = origen;
            this.valla = valla;
        }

        private int getValla() { //forzar la inicialización
            int t;
            if ((t = this.valla) < 0) {
                t = this.valla = this.colaArreglo.cola;
                this.indice = this.colaArreglo.cabeza;
            }
            return t;
        }

        @Override
        public ColaDequeSpliterator<E> trySplit() {
            int t = getValla(), h = indice, n = this.colaArreglo.listadoElementos.length;
            if (h != t && ((h + 1) & (n - 1)) != t) {
                if (h > t) {
                    t += n;
                }
                int m = ((h + t) >>> 1) & (n - 1);
                return new ColaDequeSpliterator<>(this.colaArreglo, h, this.indice = m);
            }
            return null;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> consumer) {
            if (consumer == null) {
                throw new NullPointerException();
            }
            Object[] arreglo = this.colaArreglo.listadoElementos;
            int m = arreglo.length - 1, f = getValla(), i = this.indice;
            this.indice = f;
            while (i != f) {
                @SuppressWarnings("unchecked")
                E e = (E) arreglo[i];
                i = (i + 1) & m;
                if (e == null) {
                    throw new ConcurrentModificationException();
                }
                consumer.accept(e);
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> consumer) {
            if (consumer == null) {
                throw new NullPointerException();
            }
            Object[] arreglo = this.colaArreglo.listadoElementos;
            int m = arreglo.length - 1, f = getValla(), i = this.indice;
            if (i != this.valla) {
                @SuppressWarnings("unchecked")
                E e = (E) arreglo[i];
                indice = (i + 1) & m;
                if (e == null) {
                    throw new ConcurrentModificationException();
                }
                consumer.accept(e);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            int n = getValla() - this.indice;
            if (n < 0) {
                n += this.colaArreglo.listadoElementos.length;
            }
            return (long) n;
        }

        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED
                    | Spliterator.NONNULL | Spliterator.SUBSIZED;
        }
    }
}
