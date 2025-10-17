package org.example.iteractions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.example.ui.pianoPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;


public class tocarNota implements Interaction {

    private final String nota;

    public tocarNota(String nota) {
        this.nota = nota;
    }

    public static tocarNota fromTocarNota(String nota){
        return instrumented(tocarNota.class, nota);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(pianoPage.TECLA.of(nota), isVisible()).forNoMoreThan(5).seconds(),
                JavaScriptClick.on(pianoPage.TECLA.of(nota))
        );

        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
