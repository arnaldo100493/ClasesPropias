/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clasespropias;

import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author FABAME
 */
public abstract class Imprimir {

    public Imprimir() {

    }

    public static void imprimirArregloDeCaracteresChar(char[] arregloNumerosChar) {
        System.out.print(arregloNumerosChar);
    }

    public static void imprimirArregloDeCaracteresCharConSaltoDeLinea(char[] arregloNumerosChar) {
        System.out.println(arregloNumerosChar);
    }

    public static void imprimirCaracterChar(char numeroChar) {
        System.out.print(numeroChar);
    }

    public static void imprimirCaracterCharConSaltoDeLinea(char caracter) {
        System.out.println(caracter);
    }

    public static void imprimirDecimalDouble(double numeroDouble) {
        System.out.print(numeroDouble);
    }

    public static void imprimirDecimalDoubleConSaltoDeLinea(double numeroDouble) {
        System.out.println(numeroDouble);
    }

    public static void imprimirDecimalFloat(float numeroFloat) {
        System.out.print(numeroFloat);
    }

    public static void imprimirDecimalFloatConSaltoDeLinea(float numeroFloat) {
        System.out.println(numeroFloat);
    }

    public static void imprimirEnteroInt(int numeroInt) {
        System.out.print(numeroInt);
    }

    public static void imprimirEnteroIntConSaltoDeLinea(int numeroInt) {
        System.out.println(numeroInt);
    }

    public static void imprimirEnteroLong(long numeroLong) {
        System.out.print(numeroLong);
    }

    public static void imprimirEnteroLongConSaltoDeLinea(long numeroLong) {
        System.out.println(numeroLong);
    }

    public static void imprimirLogicoBoolean(boolean estado) {
        System.out.print(estado);
    }

    public static void imprimirLogicoBooleanConSaltoDeLinea(boolean estado) {
        System.out.println(estado);
    }

    public static void imprimirObjetoObject(Object objeto) {
        System.out.print(objeto);
    }

    public static void imprimirObjetoConJOptionPane(Object mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void imprimirObjetoConJOptionPane(Object mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoMensaje);
    }

    public static void imprimirObjetoConJOptionPane(Object mensaje, String titulo, int tipoMensaje, Icon icono) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, tipoMensaje, icono);
    }

    public static void imprimirObjetoObjectConSaltoDeLinea(Object objeto) {
        System.out.println(objeto);
    }

    public static void imprimirSaltoDeLinea() {
        System.out.println();
    }

    public static void imprimirTextoString(String texto) {
        System.out.print(texto);
    }

    public static void imprimirTextoStringConSaltoDeLinea(String texto) {
        System.out.println(texto);
    }

}
