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
        listaEnlazada.poner("Andrés");
        listaEnlazada.poner("Barrios");
        listaEnlazada.poner("Mena");
        String e1 = listaEnlazada.quitar();
        System.out.println("Se elininó el elemento: " + e1);

        /*for (Empleado empleado : listadoArreglo) {
            System.out.println(empleado.obtenerDatos());
        }*/
        System.out.println(listaEnlazada.imprimir());

    }

    private static class Empleado {

        String identificacion;
        String numeroEmpleado;
        String nombre;
        String apellido;
        int edad;
        double salario;

        public Empleado() {
            this.identificacion = "";
            this.numeroEmpleado = "";
            this.nombre = "";
            this.apellido = "";
            this.edad = 0;
            this.salario = 0.0;
        }

        public Empleado(String identificacion, String numeroEmpleado, String nombre, String apellido, int edad, double salario) {
            this.identificacion = identificacion;
            this.numeroEmpleado = numeroEmpleado;
            this.nombre = nombre;
            this.apellido = apellido;
            this.edad = edad;
            this.salario = salario;
        }

        public String getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public String getNumeroEmpleado() {
            return numeroEmpleado;
        }

        public void setNumeroEmpleado(String numeroEmpleado) {
            this.numeroEmpleado = numeroEmpleado;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public double getSalario() {
            return salario;
        }

        public void setSalario(double salario) {
            this.salario = salario;
        }

        public String obtenerDatos() {
            return "Identifiación: " + identificacion + "\n" + "Numero Empleado:" + numeroEmpleado + "\n" + "Nombre: " + nombre + "\n \"Apellido:" + apellido + "\n Edad: " + edad + "\n Salario: " + salario;
        }
    }
}
