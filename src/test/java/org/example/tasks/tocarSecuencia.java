package org.example.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.example.iteractions.tocarNota;
import org.example.questions.notaEstaActiva;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class tocarSecuencia implements Task {

    private final List<String> notas;

    public tocarSecuencia(List<String> notas) {
        this.notas = notas;
    }

    public static tocarSecuencia fromTocarSecuencia(List<String> notas){
        return instrumented(tocarSecuencia.class, notas);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        //se crea un ciclo for para reutilizar la Interaccion para cada nota
        for (String nota : notas){
            actor.attemptsTo(
                    tocarNota.fromTocarNota(nota)
            );
            //se  valida que la nota presionada este activa
            actor.attemptsTo(
                    Ensure.that(notaEstaActiva.fromNotaEstaActiva(nota)).isTrue()
            );
            try{
                Thread.sleep(900);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

    }
}
