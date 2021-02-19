package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Student;
import service.EtudiantService;

public class InfoStudentController extends MonController implements Initializable {
	

	@FXML Button modify = new Button("Modifier");

	@FXML Button update = new Button("Enregistrer");

	@FXML
	private AnchorPane pane;

	@FXML
	private DatePicker dateDeNaissance;
	
	@FXML
	private TextField nomTextField;

	@FXML
	private TextField prenomTextField;

	@FXML
	private TextField phototextfield;	
	
	@FXML
	private ImageView photoStudentPicture = new ImageView();

	@FXML
	private Button photobutton = new Button("Parcourir...");
	
	@FXML
	private Label photolabel;
	
	EtudiantService service = new EtudiantService();
	
	private Student s;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		s = MonController.getStudent();
		//Student student = this.table.getSelectionModel().getSelectedItem();
		//System.out.println(s);
		//nomTextField.setText(this.table.getSelectionModel().getSelectedItem().getNom());
		nomTextField.setEditable(false);
		prenomTextField.setEditable(false);
		dateDeNaissance.getEditor().setEditable(false);
		dateDeNaissance.setEditable(false);
		photobutton.setVisible(false);
		phototextfield.setVisible(false);
		photolabel.setVisible(false);
		
		File f = new File(s.getPhoto());
		Image i = new Image(new File(s.getPhoto()).toURI().toString());
		photoStudentPicture.setImage(i);
		nomTextField.setText(s.getNom());
		prenomTextField.setText(s.getPrenom());
		phototextfield.setText(s.getPhoto());
		Date d = s.getDateDeNaissance();
		LocalDate datepicker = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		dateDeNaissance.setValue(datepicker);
		
		update.setVisible(false);
		
	}
	
	@FXML
	public void modifyStudent(ActionEvent e) {
		modify.setText("Annuler");
		update.setVisible(true);
		dateDeNaissance.getEditor().setEditable(true);
		photobutton.setVisible(true);
		phototextfield.setVisible(true);
		photolabel.setVisible(true);
		nomTextField.setEditable(true);
		prenomTextField.setEditable(true);
		
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
	
	@FXML
	private void updateStudent(ActionEvent e) {
		Date d = Date.from(dateDeNaissance.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		s.setNom(nomTextField.getText());
		s.setPrenom(prenomTextField.getText());
		s.setPhoto(phototextfield.getText());
		s.setDateDeNaissance(d);
		service.modifierEtudiant(s);
		
		 Stage stage = (Stage) update.getScene().getWindow();
		    stage.hide();
		
		service.listEtudiant();
	}

}
