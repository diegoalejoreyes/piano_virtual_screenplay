package org.example.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class pianoPage {

    public static Target TECLA_prueba = Target.the("tecla_piano").locatedBy("[data-note='3b']");
    public static final Target TECLA = Target.the("la tecla con data-note '{0}'")
            .locatedBy("//span[contains(@class, 'white-key') and @data-note='{0}']");

    public static Target MOSTRARNOTAS = Target.the("mostrar_nombres_notas").located(By.className("hideNotes"));
}
