/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import taskmanager.entidades.Task;
import taskmanager.services.TaskService;

/**
 * FXML Controller class
 *
 * @author brunopc
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField newname;
    @FXML
    private TextField newdesc;
    @FXML
    private Pane viewTask;

    @FXML
    private Label label;
    @FXML
    private Pane agregarTask;
    @FXML
    private TableView<Task> tableView;
    @FXML
    private TableColumn<Task, Long> id;
    @FXML
    private TableColumn<Task, String> nombre;

    @FXML
    private TableColumn<Task, String> descp;
    @FXML
    private TableColumn<Task, Date> fecha;
    private ObservableList<Task> taskObservableList;
    private List<Task> taskList;
    @FXML
    private Pane panelMod;
    @FXML
    private Pane paneDelete;
    @FXML
    private TextField nombreMod;
    @FXML
    private TextField descMod;

    TaskService listaTareas = new TaskService();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        agregarTask.setVisible(true);
    }

    @FXML
    public void handleaddTask(ActionEvent event) throws Exception {
        String nombree = newname.getText();
        String desc = newdesc.getText();

        listaTareas.crearTarea(nombree, desc);

        agregarTask.setVisible(false);
        //AGREGAR NUEVA
        actualizarTabla();

    }

    public void actualizarTabla() {
        taskList = listaTareas.obtenerTareas();
        taskObservableList = FXCollections.observableArrayList(taskList);
        tableView.setItems(taskObservableList);
    }

    public void handleEliminar(ActionEvent event) {
        Task tareaDelete = tableView.getSelectionModel().getSelectedItem();
        if (tareaDelete != null) {
            paneDelete.setVisible(true);
        }
    }

    public void confirmDelete(ActionEvent event) {
        Task tareaDelete = tableView.getSelectionModel().getSelectedItem();
        listaTareas.eliminarItem(tareaDelete);
        paneDelete.setVisible(false);
        actualizarTabla();
    }

    public void cancelDelete(ActionEvent event) {
        paneDelete.setVisible(false);
    }

    public void handleModificar(ActionEvent event) {
        Task tareaMod = tableView.getSelectionModel().getSelectedItem();
        if (tareaMod != null) {
            panelMod.setVisible(true);
        }
    }

    public void confirmarMod(ActionEvent event) throws Exception {
        Task tareaMod = tableView.getSelectionModel().getSelectedItem();
        String nombre = nombreMod.getText();
        String descripcion = descMod.getText();
        listaTareas.modificarItem(nombre, descripcion, tareaMod);
        actualizarTabla();
        panelMod.setVisible(false);

    }

    public void cancelMod(ActionEvent event) {
        panelMod.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializar la lista de tareas
        taskList = listaTareas.obtenerTareas();
        tableView.setItems(FXCollections.observableArrayList(taskList));

        // Asignar la lista de tareas a la tabla
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        descp.setCellValueFactory(new PropertyValueFactory<>("descTarea"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechaDeCreacion"));
    }

}
