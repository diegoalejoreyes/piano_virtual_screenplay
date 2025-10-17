package org.example.tasks;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class openPage implements Task {

    private EnvironmentVariables environmentVariables;

    public static Performable fromOpenPage(){
        return instrumented(openPage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String URL = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webdriver.base.url");

        actor.attemptsTo(
                Open.url(URL)
        );
    }
}
