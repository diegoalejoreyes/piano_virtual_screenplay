package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;
import org.example.ui.pianoPage;

public class notaEstaActiva implements Question<Boolean> {

    private final String estadoNota;

    public notaEstaActiva(String estadoNota) {
        this.estadoNota = estadoNota;
    }

    public static notaEstaActiva fromNotaEstaActiva(String estadoNota){
        return new notaEstaActiva(estadoNota);
    }

    @Override
    //@Step("{0} verifica si la tecla con data-note '#dataNoteValue' está activa")
    public Boolean answeredBy(Actor actor) {

        Target teclaTarget = pianoPage.TECLA.of(estadoNota);
        //se extrae la clase del target ya creado
        String nombreClase = teclaTarget.resolveFor(actor).getAttribute("class");
        // se comprueba que el nombre de la clase contenga "active"
        return nombreClase != null && nombreClase.contains("active");
    }
}
