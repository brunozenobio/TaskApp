/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.services;

import java.util.Date;
import java.util.List;
import taskmanager.entidades.Task;
import taskmanager.persistencia.TaskDAO;

/**
 *
 * @author brunopc
 */
public class TaskService {

    TaskDAO tDAO = new TaskDAO();

    public String capitalizarPrimeraLetra(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }

        return Character.toUpperCase(texto.charAt(0)) + texto.substring(1).toLowerCase();
    }

    public void crearTarea(String nombre, String descripcion) throws Exception {
        nombre = capitalizarPrimeraLetra(nombre);
        descripcion = capitalizarPrimeraLetra(descripcion);
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar un nombre");
            }
            if (descripcion == null || descripcion.trim().isEmpty()) {
                throw new Exception("Debe indicar una descripcion");
            }
        } catch (Exception e) {
        }

        Task tarea = new Task();
        tarea.setNombreTarea(nombre);
        tarea.setDescTarea(descripcion);
        tarea.setFechaDeCreacion(new Date());

        tDAO.crearTarea(tarea);

    }

    public List<Task> obtenerTareas() {
        return tDAO.obtenerTareas();
    }

    public void modificarItem(String nombre, String desc, Task task) throws Exception {

        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("debe ingresar un nombre");

            }
            if (desc == null || desc.trim().isEmpty()) {
                throw new Exception("debe ingresar una descripcion");

            }
        } catch (Exception e) {
        }
        task.setNombreTarea(nombre);
        task.setDescTarea(desc);
        tDAO.modificarItem(task);
    }

    public void eliminarItem(Task task) {
        try {
            tDAO.eliminarItem(task);
        } catch (Exception e) {
        }
    }

}
