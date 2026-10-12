package dev.v1ctorvm.studentregistrymvvm.repository;

// TDD - Repository

// 1 - O que repository precisa oferecer para VM funcionar?
//  -> findAll(), save(), toggle(), update(),
// 2 - Quais comportamentos mínimos devemos provar com os testes
//  -> Ex: Save + findAll deve aumentar a lista, chamar o toggleFlag deve alterar o boolean de Active
//  -> Chamando Update, deve alterar o campo grade no Doc em questão

// Decisões:
// - Testar o repositorio CONCRETO (MONGO REAL)
//  -> Para mostrar ‘input’ e ‘output’ de verdade, sem abstração
//  -> Isolar os testes: Limpar a coleção ANTES de cada teste
//  -> Decidir sobre assincronismo: Se os testes vão ou não se submeter a chamadas que não são imediatas

import dev.v1ctorvm.studentregistrymvvm.config.MongoConfig;
import dev.v1ctorvm.studentregistrymvvm.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentRepositoryTest {

    private StudentRepository repo;

    @BeforeEach
    void setup(){
        // Define que o repository deve ter um construtor que aceite setar em qual DB e qual Collection eu quero manipular
        repo = new StudentRepository(MongoConfig.database(), "students_test");

        repo.deleteAll();
    }

    @Test
    @DisplayName("save + findAll a lista deve crescer")
    void saveAndFindAll_growsList(){
        // Arrange
        Student s1 = new Student("Ana","ana@gmail.com",8.5,true);

        // Act
        repo.save(s1);
        List<Student> all = repo.findAll();

        // Assert
        assertEquals(1,all.size(),"Após salvar 1 aluno, findAll deve retornar 1");
        assertEquals("Ana", all.get(0).getName(), "O nome do aluno salvo deve ser 'ana'");

        // Alternativas:
        // - Validar todos os campos do aluno inserido
        // - Usar uma asserção mais forte (comprar objeto inteiro)
        // - ao invés de dar get no 0, dar um find e verificar pelo ID o aluno
    }

    @Test
    @DisplayName("ToggleFlag deve alternar o atributo 'active'")
    void toggleFlag_changesActive(){

        // Arrange
        Student s = new Student("Ana","ana@gmail.com",8.5,true);
        repo.save(s);
        String id = s.getId();

        // Sanity Check - Garante que esta ativo antes de alternar (evitar falso positivo)
        assertTrue(repo.findAll().get(0).isActive(),"Pré condição deve iniciar como ativo=true");

        // Act
        repo.toggleFlag(id);

        // Assert
        assertFalse(repo.findAll().get(0).isActive(), "Após o toggle, active deve ser false");

    }

    @Test
    @DisplayName("updateField deve atualizar a grade")
    void updateField_updatesGrade(){
        // Arrange
        Student s = new Student("Ana","ana@gmail.com",8.5,true);
        repo.save(s);
        String id = s.getId();

        assertEquals(8.5, repo.findAll().get(0).getGrade(), 0.0001,"Pré requisito: A nota deve ser 5 antes do teste rodar");

        repo.updateField(id,5.0);

        assertEquals(5.0, repo.findAll().get(0).getGrade(), 0.0001,"A nota deve ser sido atualizada para 9");


    }




}
