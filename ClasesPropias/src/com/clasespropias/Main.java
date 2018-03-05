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
        ListaArreglo<Empleado> listadoArreglo = new ListaArreglo<>();
        
        listadoArreglo.agregar(new Empleado("Arnaldo", 25, 14900));
        listadoArreglo.agregar(new Empleado("Andres", 25, 14900));
        listadoArreglo.agregar(new Empleado("Barrios", 25, 14900));
        listadoArreglo.agregar(new Empleado("Mena", 25, 14900));
        
        for (Empleado empleado : listadoArreglo) {
            System.out.println(empleado.obtenerDatos());
        }
        
    }

    private static class Empleado {

        String nombre;
        int edad;
        double sueldo;

        public Empleado(String nombre, int edad, double sueldo) {
            this.nombre = nombre;
            this.edad = edad;
            this.sueldo = sueldo;
        }

        public String obtenerDatos() {
            return "Nombre: " + nombre + "\n Edad: " + edad + "\n Sueldo: " + sueldo;
        }
    }

}
