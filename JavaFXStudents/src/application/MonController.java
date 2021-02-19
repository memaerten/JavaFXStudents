package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;
import service.EtudiantService;

public class MonController implements Initializable {
	@FXML AnchorPane pane;

	@FXML MenuBar menubar;

	@FXML Menu menu1;

	@FXML Menu menu2;

	@FXML Menu menu3;

	@FXML TableView<Student> table = new TableView<Student>();

	@FXML TableColumn<Student, Integer> idStudentColumn = new TableColumn<Student, Integer>("ID");
	@FXML TableColumn<Student, String> nomColumn = new TableColumn<Student, String>("Nom");
	@FXML TableColumn<Student, String> prenomColumn = new TableColumn<Student, String>("Prénom");
	@FXML TableColumn<Student, Date> dateColumn = new TableColumn<Student, Date>("Date de naissance");

	@FXML Button photoButton = new Button("Parcourir...");

	EtudiantService service = new EtudiantService();
	
	private static Student s;

	private ObservableList<Student> liste(){
		ObservableList<Student> characters = FXCollections.observableArrayList();
		for (int i = 0 ; i < service.listEtudiant().size(); i++) {
			characters.add(service.listEtudiant().get(i));
		}
		System.out.println(characters);
		return characters;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		idStudentColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("idStudent"));
		nomColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("nom"));
		prenomColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("prenom"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Student, Date>("dateDeNaissance"));

		if(service.listEtudiant().size() != 0) {
			table.setItems(liste());
			Student.setListStudents(service.listEtudiant().size());
		}
	}

	@FXML
	private void menuclose(ActionEvent e) {
		Platform.exit();
	}

	@FXML
	private void addStudent(ActionEvent e) throws IOException {
		AnchorPane addStudentPane = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
		pane.getChildren().setAll(addStudentPane);
	}

	@FXML
	private void listStudents(ActionEvent e) throws IOException {
		AnchorPane addStudentPane = FXMLLoader.load(getClass().getResource("Scene.fxml"));
		pane.getChildren().setAll(addStudentPane);

	}

	@FXML
	private void about(ActionEvent e) {
		System.out.println("About");
	}

	@FXML
	private void infoStudent(MouseEvent e) {
			Parent root;
			try {
				MonController.setEtudiant(table.getSelectionModel().getSelectedItem());
				System.out.println(s);
				root = FXMLLoader.load(getClass().getClassLoader().getResource("application/InfoStudent.fxml"));
				Stage stage = new Stage();
				stage.setTitle("My New Stage Title");
				stage.setScene(new Scene(root));
				stage.show();
				table.getSelectionModel().getSelectedItem();
				
			}
			catch (IOException error) {
				error.printStackTrace();
			}
	}

	public static void setEtudiant(Student s) {
		MonController.s = s;
	}

	public static Student getStudent() {
		return s;
	} 




}
//CDA-59013-S07-MAERTEN-MELISSA

