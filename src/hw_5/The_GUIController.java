/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw_5;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.persistence.*;

/**
 * FXML Controller class
 *
 * @author AboodHassKov
 */
public class The_GUIController implements Initializable {

    @FXML
    private Pane ThePane;
    @FXML
    private TableView<Student> TableView_Student;
    @FXML
    private TableColumn<Student, Integer> ID_Column_STD;
    @FXML
    private TableColumn<Student, String> Name_Column_STD;
    @FXML
    private TableColumn<Student, String> Major_Column_STD;
    @FXML
    private TableColumn<Student, Double> Grade_Column_STD;
    @FXML
    private Label S_Label;
    @FXML
    private Button Add_Button;
    @FXML
    private Button Delete_Button;
    @FXML
    private Button Update_Button;
    @FXML
    private Button Add_REG_Button;
    @FXML
    private Button Delete_REG_Button;
    @FXML
    private Button Update_REG_Button;
    @FXML
    private Label ID_Label;
    @FXML
    private Label Name_Label;
    @FXML
    private Label MAjor_Label;
    @FXML
    private Label Grade_Label;
    @FXML
    private TextField ID_Field;
    @FXML
    private TextField NAme_Field;
    @FXML
    private TextField Major_Field;
    @FXML
    private TextField Grade_Field;
    @FXML
    private Label R_Label;
    @FXML
    private TableView<Registration> TableView_Registration;
    @FXML
    private TableColumn<Registration, Integer> sID_Column_REG;
    @FXML
    private TableColumn<Registration, Integer> cID_Column_REG;
    @FXML
    private TableColumn<Registration, String> Semester_Column_REG;
    @FXML
    private Button SE_Student_Button;
    @FXML
    private Button Excellent_Button;
    @FXML
    private Button Pass_Button;
    @FXML
    private Button CS_Button;
    @FXML
    private Label Semester_Label;
    @FXML
    private TextField Semester_Field;
    @FXML
    private Label course_id_Label;
    @FXML
    private TextField course_id_Field;
    
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("PU");
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ID_Column_STD   .setCellValueFactory(new PropertyValueFactory("id"));
        Name_Column_STD .setCellValueFactory(new PropertyValueFactory("name"));
        Major_Column_STD.setCellValueFactory(new PropertyValueFactory("major"));
        Grade_Column_STD.setCellValueFactory(new PropertyValueFactory("grade"));
        
        sID_Column_REG     .setCellValueFactory(new PropertyValueFactory("studentid"));
        cID_Column_REG     .setCellValueFactory(new PropertyValueFactory("courseid"));
        Semester_Column_REG.setCellValueFactory(new PropertyValueFactory("semester"));
        
        show();
        
        TableView_Student.
                    getSelectionModel().
                    selectedItemProperty().
                    addListener(event-> ShowSTDSelected());
        
        TableView_Registration.
                    getSelectionModel().
                    selectedItemProperty().
                    addListener(event-> ShowREGSelected());
    }    

    @FXML
    private void Button_Add_REG(ActionEvent event) {
        
EntityManager eml = emf.createEntityManager();
        Registration REG = new Registration();
        REG.setStudentid(Integer.parseInt(ID_Field.getText()));
        REG.setSemester(Semester_Field.getText());
        REG.setCourseid(Integer.parseInt(course_id_Field.getText()));
        eml.getTransaction().begin();
        eml.persist(REG);
        eml.getTransaction().commit();
        eml.close();
        show();
        
    }
    @FXML
    private void Button_Add(ActionEvent event) {
    
        

    EntityManager em = emf.createEntityManager();
        Student student = new Student();
        student.setId(Integer.parseInt(ID_Field.getText()));
        student.setName(NAme_Field.getText());
        student.setMajor(Major_Field.getText());
        student.setGrade(Double.parseDouble(Grade_Field.getText()));
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        show();
        
    

        
    }
    

    @FXML
    private void Button_Delete(ActionEvent event) {
        
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, Integer.parseInt(ID_Field.getText()));
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
        em.close();
        show();
    }
    
    @FXML
    private void Button_Delete_REG(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        Registration REG = em.find(Registration.class, Integer.parseInt(ID_Field.getText()));
        em.getTransaction().begin();
        em.remove(REG);
        em.getTransaction().commit();
        em.close();
        show(); 
    }

    @FXML
    private void Button_Update(ActionEvent event) {
        
//        Query Q = em.createQuery("Update student s set s.name = '" + NAme_Field.getText() +"', s.major = '"+Major_Field.getText()+"', s.grade = "+Double.parseDouble(Grade_Field.getText())+" where s.id = "+Integer.parseInt(ID_Field.getText()));
        EntityManager em = emf.createEntityManager();
        Student student = new Student();
        student.setId(Integer.parseInt(ID_Field.getText()));
        student.setName(NAme_Field.getText());
        student.setMajor(Major_Field.getText());
        student.setGrade(Double.parseDouble(Grade_Field.getText()));
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
        show();   
    }
    
    @FXML
    private void Button_Update_REG(ActionEvent event) {
        
        EntityManager eml = emf.createEntityManager();
        Registration REG = new Registration();
        REG.setStudentid(Integer.parseInt(ID_Field.getText()));
        REG.setSemester(Semester_Field.getText());
        REG.setCourseid(Integer.parseInt(course_id_Field.getText()));
        eml.getTransaction().begin();
        eml.merge(REG);
        eml.getTransaction().commit();
        eml.close();
        show();  
    }

    @FXML
    private void Button_Show(ActionEvent event) {
        show();
    }
    
    
    void show(){
        
    EntityManager em = emf.createEntityManager();
    
        List<Student> STD = em.createNamedQuery("Student.findAll").getResultList();
        
        TableView_Student.getItems().setAll(STD);
        
          
    EntityManager emR = emf.createEntityManager();
    
        
        List<Registration> Reg = emR.createNamedQuery("Registration.findAll").getResultList();
        TableView_Registration.getItems().setAll(Reg);
        
        
    }
    private void ShowSTDSelected() {
        
        Student selected_STD =  TableView_Student.getSelectionModel().getSelectedItem();
        
        if(selected_STD != null){
        ID_Field.setText(selected_STD.getId()+"");
        NAme_Field.setText(selected_STD.getName());
        Major_Field.setText(selected_STD.getMajor());
        Grade_Field.setText(selected_STD.getGrade()+"");
        }
        
        
    }
    private void ShowREGSelected() {
        Registration selected_REG = TableView_Registration.getSelectionModel().getSelectedItem();
        
        if(selected_REG != null){
        ID_Field.setText(selected_REG.getStudentid()+"");
        Semester_Field.setText(selected_REG.getSemester()+"");
        course_id_Field.setText(selected_REG.getCourseid()+"");
        }
        
    }
    @FXML
    private void Button_SE(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
    
        List<Student> SE = em.createNamedQuery("Student.findByMajor")
                .setParameter("major", "Software Engineering")
                .getResultList();
        
        TableView_Student.getItems().setAll(SE);
    }

    @FXML
    private void Button_excellent(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
    
        List<Student> Exllent = em.createNamedQuery("Student.find_+85_STDs")
                .getResultList();
        
        TableView_Student.getItems().setAll(Exllent);
    }

    @FXML
    private void Button_pass(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
    
        List<Student> pass = em.createNamedQuery("Student.findPass")
                .getResultList();
        
        TableView_Student.getItems().setAll(pass);
    }

    @FXML
    private void Button_CS(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
    
        List<Student> CS = em.createNamedQuery("Student.findByCS")
                .getResultList();
        System.out.println(CS);
        em.getTransaction().begin();
        for(Student STD : CS){
            STD.setGrade(STD.getGrade() + 3.0);
            em.merge(STD);
        }
        em.getTransaction().commit();
        em.close();
        
        show();
    }

}
