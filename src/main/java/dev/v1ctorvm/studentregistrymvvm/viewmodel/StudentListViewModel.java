package dev.v1ctorvm.studentregistrymvvm.viewmodel;

import dev.v1ctorvm.studentregistrymvvm.model.Student;
import dev.v1ctorvm.studentregistrymvvm.repository.StudentRepository;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableBooleanProperty;

public class StudentListViewModel {

    // Propriedades observáveis -> Estado da tela
    private final ObservableList<Student> items = FXCollections.observableArrayList();

    // Indicador de visibilidade do loading
    // a view binda o progress.visibleProprety().bind()vm.loadingProperty());
    private final BooleanProperty loading = new SimpleBooleanProperty(false);

    private final StringProperty lblError = SimpleStringProperty();

    // Dependencia de dados
    private final StudentRepository repository;

    public StudentListViewModel(StudentRepository repository){
        this.repository = repository;
    }

    public void loadAll(){
        items = repository.findAll();
        loading.set(true);
        var lbl
    }

    public ObservableList<Student> getItems() {
        return items;
    }

    public BooleanProperty loadingProperty() {
        return loading;
    }


}
