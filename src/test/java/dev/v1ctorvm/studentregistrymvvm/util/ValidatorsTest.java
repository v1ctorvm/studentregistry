package dev.v1ctorvm.studentregistrymvvm.util;

// Como pensar esta classe
// 1 - O que a VM vai precisar decidir
//  -> Texto nao pode ser vazio, email valido, nota entre 0 e 10

// Como testar funções abertas com diversas possibilidades?
// -> Para cada função, devemos listar vários exemplos de entradas e saídas esperadas. Ao invés de escrever
// 10 testes iguais, mudando os valores do arrange, utilizamos um teste PARAMETRIZED, um teste que roda X vezes, mas cada
// 1 com conjunto de dados diferentes

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorsTest {

    @ParameterizedTest
    @DisplayName("notBlank deve detectar Strings validas/invalidas")
    @CsvSource(
            value = {
                    // FORMATO DE LINHA: "ENTRADA", RESULTADOESPERADO
                    // CASOS VALIDOS (espera = true)
                    "'abc', true",
                    "' x ', true",

                    // CASOS INVALIDSO (espera = false)
                    "'  ', false",
                    "'', false",
                    "null,false"
            },
            nullValues = {"null"}
    )
    void notBlank_tests(String input, boolean expected){
        boolean result = Validators.notBlank(input);

        assertEquals(expected,result, () -> "notBlank('" + input + "') deveria ser " + expected);
    }

    @ParameterizedTest
    @DisplayName("Email simples deve validar formato basico")
    @CsvSource(
            value = {
                    // Validos
                    "ana@exemplo.com, true",
                    "joao.silva@bol.org.br, true",

                    // Falso
                    "carlos.com, false",
                    "vitor@feio, false",
                    "@gmail.com, false",
                    "ghisi@gmail.com., false",
                    "' ', false",
                    "null, false"
            },
            nullValues = {"null"}
    )
    void email_tests(String input,boolean expected){
        boolean result = Validators.email(input);

        assertEquals(expected,result, () -> "email('" + input + "') deveria ser " + expected);
    }

    // Teste para o metodo inRange(double notaAtual, int min, int max)
    // Criar exemplos de teste parametrizado qe sao validos e invalidos
    @ParameterizedTest
    @DisplayName("Nota deve ser validada")
    @CsvSource(
            value = {
                //validos
                    "0, true",
                    "10, true",
                    "5.5,true",
                    "-0.1,false",
                    "-1,false",
                    "10.1,false",

                //invalidos
                    "-1,false",
                    "10.1,false"
            },
            nullValues = {"null"}
    )
    void grade_tests(double value, boolean expected){

        boolean result = Validators.inRange(value, 0, 10);

        assertEquals(expected,result, () -> "grade('" + value + ", 0, 10') deveria ser " + expected);

    }
}
