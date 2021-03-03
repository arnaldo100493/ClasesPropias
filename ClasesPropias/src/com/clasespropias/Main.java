package com.clasespropias;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FABAME
 */
public class Main {

    public Main() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ListaEnlazada<String> listaEnlazada = new ListaEnlazada<>();

        listaEnlazada.poner("Arnaldo");
        listaEnlazada.poner("Andres");
        String e1 = listaEnlazada.quitar();
        System.out.println("Se elininó el elemento: " + e1);

        /*for (Empleado empleado : listadoArreglo) {
            System.out.println(empleado.obtenerDatos());
        }*/
        System.out.println(listaEnlazada.imprimir());

    }

    private static class Empleado {

        String nombre;
        String apellido;
        int edad;
        double salario;

        public Empleado() {
            this.nombre = "";
            this.apellido = "";
            this.edad = 0;
            this.salario = 0.0;
        }

        public Empleado(String nombre, String apellido, int edad, double salario) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.edad = edad;
            this.salario = salario;
        }

        public String obtenerDatos() {
            return "Nombre: " + nombre + "\n \"Apellido:" + apellido + "\n Edad: " + edad + "\n Salario: " + salario;
        }
    }
}
