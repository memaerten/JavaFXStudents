package application;

import java.io.File;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Student;

public class AddController extends MonController implements Initializable {

	@FXML
	private AnchorPane pane;

	@FXML
	private DatePicker dateDeNaissance;

	@FXML
	private TextField prenomTextField;

	@FXML
	private TextField nomTextField;

	@FXML
	private TextField phototextfield;

	@FXML
	private Button createStudent;
	
	@FXML
	private ImageView photoStudentPicture = new ImageView();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void add(ActionEvent e) {
		Date d = Date.from(dateDeNaissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

		service.ajouterEtudiant(new Student(nomTextField.getText(), prenomTextField.getText(),"", d, phototextfield.getText()));
		
	}

	@FXML
	private void fileChoose(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		phototextfield.clear();
		Stage stage = new Stage();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			phototextfield.setText(file.getAbsolutePath());
			Image i = new Image(file.toURI().toString());
			photoStudentPicture.setImage(i);
		}
	}

}
