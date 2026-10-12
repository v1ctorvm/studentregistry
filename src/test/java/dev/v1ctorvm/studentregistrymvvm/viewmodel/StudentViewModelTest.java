package dev.v1ctorvm.studentregistrymvvm.viewmodel;

// ViewModel mantem o ESTADO da tela (itens, loading, erro)
// e expoe metodos de aca (loadAll, addNew, toggleFlag e upDateField)

// Contratods a VM deve cumprir
// 1- loadAll
//  Ligar o loading durante a operacoa e desligar no final
//  preencher a lista de itens com o que veio do repository
// 2- addNew:
//  Pegar e validar os campos, se invalido nao salva e escreve no lbl error
// se validos, salva no repo e atualiza a lista
// 3- toggleFlag
// solicitar ao repo que altered o active do estudante x e atualizar a lista
// 4- updateField:
//  valida a nota, se invalida nao atualiza e escreve no lblError, se valida, altera a nota

import com.mongodb.client.MongoDatabase;
import dev.v1ctorvm.studentregistrymvvm.config.MongoConfig;
import dev.v1ctorvm.studentregistrymvvm.model.Student;
import dev.v1ctorvm.studentregistrymvvm.repository.StudentRepository;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentViewModelTest {

    private StudentRepository repo;
    private StudentListViewModel vm;

    @BeforeEach
    void setup(){
        repo = new StudentRepository(MongoConfig.database(),"studentes_vm_test");
        repo.deleteAll();
        vm = new StudentListViewModel(repo);
    }

    @Test
    @DisplayName("loadAll precenche itens e desliga o loading no fim")
    void loadAll_changesLoadingAndItems(){

        // Arrange
        repo.save(new Student("ana","ana@x.com",7.5,true));
        repo.save(new Student("joao","joao@x.com",8.8,true));

        // Act
        vm.loadAll();

        // Assert
        assertFalse(vm.loadingProperty().get(), "Loading deve ser falso no final");
        assertEquals(2,vm.getItems().size(),"Items deve conter exatamente 2 alunos");

    }

}
