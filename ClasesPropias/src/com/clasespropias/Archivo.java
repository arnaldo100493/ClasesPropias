/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clasespropias;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author FABAME
 */
/**
 * Clase para la lectura y escritura de archivos.
 */
public final class Archivo {

    //Atributos de la clase Archivo.
    private RandomAccessFile raf = null;

    /**
     * Flag para hacer que la clase imprima o no los errores a pantalla.
     */
    private boolean debug;

    /**
     * Nombre del archivo actual
     */
    private String nombreArchivo;

    //Constructores de la clase Archivo.
    /**
     * Constructor de la clase Archivo. Crea un archivo vacío.
     *
     */
    public Archivo() {
        this.debug = false;
        this.nombreArchivo = "";

    }

    /**
     * Constructor de la clase Archivo. Crea un archivo vacío con debug.
     *
     * @param debug a leer/escribir
     */
    public Archivo(boolean debug) {
        this.debug = debug;
        this.nombreArchivo = "";
    }

    /**
     * Constructor de la clase Archivo. Crea un archivo con su nombre.
     *
     * @param nombreArchivo a leer/escribir
     */
    public Archivo(String nombreArchivo) {
        this.debug = false;
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Constructor de la clase Archivo. Crea un archivo con su nombre y con
     * debug.
     *
     * @param nombreArchivo a leer/escribir
     * @param debug flag para mostrar o no los errores en caso de haberlos
     */
    public Archivo(boolean debug, String nombreArchivo) {
        this.debug = debug;
        this.nombreArchivo = nombreArchivo;
    }

    //Métodos de la clase Archivo.
    /**
     * Método que abre un archivo para su lectura/escritura.
     *
     * @return true/false en caso de poder o no abrir el archivo para lectura
     */
    public final boolean reiniciar() {
        if (this.nombreArchivo == null) {
            return false;
        }
        File file = new File(this.nombreArchivo);
        if (!file.exists()) {
            if (this.debug) {
                System.out.println("ERROR: Archivo <" + this.nombreArchivo + "> no existe!");
            }
            return false;
        }
        if (!file.canRead()) {
            if (this.debug) {
                System.out.println("ERROR: Archivo <" + this.nombreArchivo + "> no se puede leer!");
            }
            return false;
        }
        try {
            this.raf = new RandomAccessFile(file, "rwd");
        } catch (FileNotFoundException ex) {
            if (this.debug) {
                System.out.println("ERROR: Archivo <" + this.nombreArchivo + "> no puede ser abierto para lectura. " + ex.getMessage());
            }
        }
        return true;
    }

    /**
     * Método para preparar el archivo para escritura.
     *
     * @return true/false en caso de poder o no reescribir el archivo para
     * lectura
     */
    public final boolean reescribir() {
        if (this.nombreArchivo == null) {
            return false;
        }

        File file = new File(this.nombreArchivo);

        // Si el archivo existe, lo borra
        if (file.exists()) {
            file.delete();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                if (this.debug) {
                    System.out.println("ERROR: Archivo <" + this.nombreArchivo + "> no puede ser creado! " + ex.getMessage());
                }
                return false;
            }
        }

        if (!file.canWrite()) {
            if (this.debug) {
                System.out.println("ERROR: Archivo <" + this.nombreArchivo + "> no se puede escribir!");
            }
            return false;
        }

        try {
            this.raf = new RandomAccessFile(file, "rwd");
        } catch (FileNotFoundException ex) {
            if (this.debug) {
                System.out.println("ERROR: Archivo <" + this.nombreArchivo + "> no puede ser abierto para lectura. " + ex.getMessage());
            }
        }
        return true;

    }

    /**
     * Método que lee una linea desde el archivo, requiere que previamente se
     * llame al metodo reset() para habilitar el acceso al archivo.
     *
     * @return cada vez que es llamado retorna el archivo linea a linea, en caso
     * de llegar al final del archivo retorna null
     */
    public final String leerLinea() {
        if (this.raf == null) {
            if (this.debug) {
                System.out.println("ERROR: Archivo no ha sido inicializado!");
            }
            return null;
        }
        try {
            return this.raf.readLine();
        } catch (IOException ex) {
            if (this.debug) {
                System.out.println("ERROR:" + ex.getMessage());
            }
            return null;
        }
    }

    /**
     * Método que escribe una linea al archivo.
     *
     * @param linea a escribir en el archivo
     * @return false si se produjo algun error
     */
    public final boolean escribirLinea(String linea) {

        if (this.raf == null) {
            if (this.debug) {
                System.out.println("ERROR: Archivo no ha sido inicializado!");
            }
            return false;
        }

        try {
            this.raf.writeBytes(linea + "\n");
        } catch (IOException ex) {
            if (this.debug) {
                System.out.println("ERROR: " + ex.getMessage());
            }
            return false;
        }

        return true;
    }

    /**
     * Método que retorna el tamanio del archivo (en caracteres).
     *
     * @return long cantidad de caracteres
     */
    public final long obtenerTamanio() {
        try {
            return this.raf.length();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return -1;
        }
    }

    /**
     * Método que cierra el archivo de lectura y libera la memoria asignadas.
     */
    public final void cerrar() {
        if (this.raf == null) {
            if (this.debug) {
                System.out.println("ERROR: Archivo no ha sido inicializado.");
            }
        }
        try {
            this.raf.close();
            this.raf = null;
        } catch (IOException ex) {
            if (this.debug) {
                System.out.println("ERROR: Archivo no se puede cerrar," + ex.getMessage());
            }
        }
    }
}
