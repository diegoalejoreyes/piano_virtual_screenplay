package org.example.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.example.models.melodiaModel;
import org.example.tasks.openPage;
import org.example.tasks.tocarSecuencia;
import org.example.ui.pianoPage;
import org.example.utils.dataJson;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class pianoStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().clearDriverCache().setup();
        setTheStage(new OnlineCast());
    }

    @After
    public void setAfter(){
        driver.quit();
    }

    @Given("que el usuario requiere ingresar al piano virtual y tocar una melodia")
    public void que_el_usuario_requiere_ingresar_al_piano_virtual_y_tocar_una_melodia() {
        theActorCalled("user").wasAbleTo(openPage.fromOpenPage());
    }

    @When("se carga la secuencia {string} desde el archivo json")
    public void se_carga_la_secuencia_desde_el_archivo_json(String idEscenario) {
        //Carga de Data: Usa el Loader para buscar por id de escenario
        melodiaModel melodia = dataJson.getMelodiaId(idEscenario);
        List<String> notas = melodia.getNotas();

        // Si la Task TocarSecuencia falla en la verificación (active=false), fallara la prueba
        OnStage.theActorInTheSpotlight().attemptsTo(tocarSecuencia.fromTocarSecuencia(notas));

//        OnStage.theActorInTheSpotlight().wasAbleTo(JavaScriptClick.on(pianoPage.TECLA_prueba));
//        System.out.println("✅ ÉXITO: Se hizo clic en la tecla con data-note: ");
    }

    @Then("se reprocude la melodia correctamente")
    public void se_reprocude_la_melodia_correctamente() {
        System.out.println("🏁 Verificación completada. La prueba simple ha finalizado.");
    }

    @Then("la secuencia se completa sin errores y se valida que las notas queden activas")
    public void la_secuencia_se_completa_sin_errores_y_se_valida_que_las_notas_queden_activas() {
    }

}
