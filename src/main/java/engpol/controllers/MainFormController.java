package engpol.controllers;

import engpol.db.dao.EngpolManager;
import engpol.db.model.Engpol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFormController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @FXML
    public Button addWordBtn;
    @FXML
    private Label engWordLbl;
    @FXML
    private Label polWordLbl;
    @FXML
    private Label engSentenceLbl;
    @FXML
    private Label polSentenceLbl;
    @FXML
    private TableView engpolTableView;
    @FXML
    TextField engWordTF;
    @FXML
    TextField polWordTF;
    @FXML
    TextField engSentenceTF;
    @FXML
    TextField polSentenceTF;

    public void initialize() {
        logger.info("Initialize MainFormController");
        writeTable();
    }

    @FXML
    public void addWord(ActionEvent actionEvent) {
        String engWord = engWordTF.getText();
        logger.info("engWord: " + engWord);
        String polWord = polWordTF.getText();
        logger.info("polWord: " + polWord);
        String engSentence = engSentenceTF.getText();
        logger.info("engSentence: " + engSentence);
        String polSentence = polSentenceTF.getText();
        logger.info("polSentence: " + polSentence);
        Engpol engpol = null;
        try {
            engpol = new Engpol.EngpolBuilder()
                    .engWord(engWord)
                    .polWord(polWord)
                    .engSentence(engSentence)
                    .polSentence(polSentence)
                    .build();
            logger.log(Level.WARNING, engpol.toString());
            EngpolManager em = new EngpolManager();
            em.insert(engpol);
            clearTF();
            writeTable();
        } catch (IllegalArgumentException e) {
            logger.severe(e.getMessage());
        }
    }

    @FXML
    public void deleteItem(ActionEvent actionEvent) {
        Engpol engpol = (Engpol) engpolTableView.getSelectionModel().getSelectedItem();
        logger.info("Deleting: " + engpol.toString());
        EngpolManager em = new EngpolManager();
        em.delete(engpol);
        writeTable();
    }

    private void clearTF() {
        engWordTF.clear();
        polWordTF.clear();
        engSentenceTF.clear();
        polSentenceTF.clear();
    }

    private void writeTable() {
        EngpolManager em = new EngpolManager();
        sample(em.getAllEngpols());
    }

    private void sample(List<Engpol> engpols) {
        engpolTableView.setEditable(true);
        ObservableList<Engpol> engpolObservableList = FXCollections.observableList(engpols);
        TableColumn engWordCol = new TableColumn("Angielskie słowo");
        engWordCol.setMinWidth(150);
        engWordCol.setCellValueFactory(new PropertyValueFactory<Engpol, String>("engWord"));

        TableColumn polWordCol = new TableColumn("Polskie słowo");
        polWordCol.setMinWidth(150);
        polWordCol.setCellValueFactory(new PropertyValueFactory<Engpol, String>("polWord"));

        TableColumn engSentenceCol = new TableColumn("Angielska sentencja");
        engSentenceCol.setMinWidth(300);
        engSentenceCol.setCellValueFactory(new PropertyValueFactory<Engpol, String>("engSentence"));

        TableColumn polSentenceCol = new TableColumn("Polska sentencja");
        polSentenceCol.setMinWidth(300);
        polSentenceCol.setCellValueFactory(new PropertyValueFactory<Engpol, String>("polSentence"));

        engpolTableView.setItems(engpolObservableList);

        engpolTableView.getColumns().setAll(engWordCol, polWordCol, engSentenceCol, polSentenceCol);
        engpolTableView.refresh();
    }

    private void writeLabels() {
        Engpol engpol = (Engpol) engpolTableView.getSelectionModel().getSelectedItem();
        engWordLbl.setText(engpol.getEngWord());
        polWordLbl.setText(engpol.getPolWord());
        engSentenceLbl.setText(engpol.getEngSentence());
        polSentenceLbl.setText(engpol.getPolSentence());
    }
    @FXML
    public void writeTF() {
        engWordLbl.setText(engWordTF.getText());
        polWordLbl.setText(polWordTF.getText());
        engSentenceLbl.setText(engSentenceTF.getText());
        polSentenceLbl.setText(polSentenceTF.getText());
    }

    public void tblViewOnMouseClicked(MouseEvent mouseEvent) {
        writeLabels();
    }

    public void tblViewOnKeyPress(KeyEvent keyEvent) {
        writeLabels();
    }

    public void updateItem(ActionEvent actionEvent) {
        Engpol engpol = (Engpol) engpolTableView.getSelectionModel().getSelectedItem();
        engWordTF.setText(engpol.getEngWord());
        polWordTF.setText(engpol.getPolWord());
        engSentenceTF.setText(engpol.getEngSentence());
        polSentenceTF.setText(engpol.getPolSentence());
//        addWordBtn.setText("Update");
    }

    public void engWordLblKeyReleased() {
        writeTF();
        EngpolManager em = new EngpolManager();
        sample(em.getEngpolsByEngWord(engWordTF.getText()));
    }
}
