package dev.v1ctorvm.studentregistrymvvm.model;


// Passo a passo para desenvolver um teste:
//
// 1 - Pensar no CONTRATO
//     O que a entidade deve garantir?
//     Exemplo: ao nascer, Student deve ter um id gerado,
//     Strings vazias, nota = 0.0 e ativo = true.
//
// 2 - Escrever o teste primeiro (RED)
//     Expressar esse contrato no teste:
//     "defaultConstructor_initializeFields"
//     O teste irá falhar porque a classe ainda não foi implementada.
//
// 3 - Implementar o mínimo necessário para passar no teste (GREEN)
//     Implementar Student apenas com o construtor,
//     getters e setters necessários.
//
// 4 - Refatorar (sem mudar o comportamento)
//     Refinar e otimizar o código, melhorar nomes,
//     limpar a implementação e pensar em desempenho.
//     Sempre mantendo os testes passando.

// Testar model primeiro, pois é mais estável, sem dependências
// Contratos fechados em classes de domínios, sustentam os testes das camadas superiores


// Padrao AAA para testes:
// Arrange (preparar) - cria dados e contexto para a acao
// Act (agir) - Chamamos um comportamento especifico do objeto testado

// Importante deixar apenas uma ação por tete, pois assim você tem uma informação clara de que quebrou


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.SynchronousQueue;

import static org.junit.jupiter.api.Assertions.*;
public class StudentTest {

    @Test
    @DisplayName("Construtor padrao inicia id, strings vazia, nota 0.0 e ativo=true")
    void defaultConstructor_initializeFields(){
        // Arrange - nao tem
        // Act
        Student s = new Student();

        // Assert
        assertNotNull(s.getId(), "ID deve ser gerado, nunca NULL");
        assertEquals("", s.getName(), "Nome deve iniciar como string vazia");
        assertEquals("", s.getEmail(), "Email deve iniciar como string vazia");
        assertEquals(0.0, s.getGrade(), 0.0001, "Nota deve iniciar exatamente com 0.0");
        assertTrue(s.isActive(), "Aluno novo deve vir como ativo = true");
    }

    @Test
    @DisplayName("COntrutor conpleto preenche todos os campos (Exeto ID)")
    void fullConstructor_setsFields(){
        // Arrange
        String name = "Ana";
        String email = "ana@gmail.com";
        double grade = 8.5;
        boolean active = false;

        // Act
        Student s = new Student(name,email,grade,active);

        // Assert
        assertNotNull(s.getId(), "ID deve ser gerado");
        assertEquals(name, s.getName(), "Nome deve refletir o argument 'name'");
        assertEquals(email, s.getEmail(), "Email deve  refletir o argument 'email");
        // 'Delta' de 0,0001, faz com que números com ponto fluent sofrem imprecisão binária.
        // Em alguns casos, uma operação que deve dar 0,3, vira 0.30000004
        // Então, usa o delta para definir uma tolerância para Double, onde delta é o erro maximo esperado
        assertEquals(grade, s.getGrade(), 0.0001, "Nota deve iniciar exatamente com 0.0");
        assertFalse(active, "Active deve refletir o argumento 'active', que no teste é falso");


    }




}
