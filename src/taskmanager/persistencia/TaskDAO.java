/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import taskmanager.entidades.Task;

/**
 *
 * @author brunopc
 */
public class TaskDAO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TASK");
    EntityManager em = emf.createEntityManager();

    //CREAR TAREA
    public void crearTarea(Task tarea) {
        try {
            em.getTransaction().begin();
            em.persist(tarea);
            em.getTransaction().commit();

        } catch (Exception e) {
        }
    }

    //CONSULTAR 
    public List<Task> obtenerTareas() {
        try {

            return em.createQuery("SELECT t FROM Task t").getResultList();
        } catch (Exception e) {
            throw e;
        }
    }

    //MODIFICAR
    
    
    public void modificarItem(Task task){
        try {
            em.getTransaction().begin();
            em.merge(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
                 e.printStackTrace();
            }
        }
    }
    public void eliminarItem(Task task){
        try {
            em.getTransaction().begin();
            em.remove(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
               em.getTransaction().rollback();
                 e.printStackTrace();
            }
        }
    }
}
