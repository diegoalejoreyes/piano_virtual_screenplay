# 🎹 Automatización: Piano Virtual - Himno de la Alegría (Serenity BDD + Cucumber y Maven)

Prueba Tecnica QA Diego Reyes
Este proyecto implementa pruebas automatizadas para validar la funcionalidad de pulsación de teclas y reproducción de melodías en el piano virtual de Musicca.com

Aclaraciones: Actualmente al ejecutar las pruebas da un error dado que la question que se implemento vaida si la clase se modifica al darle click a una tecla, esta deberia cambiar a "active", da error en false por la implementacion de la question
al parecer hay un erro en el DOM de la pagina donde la steclas negras se superponen a las teclas blancas y nos las encuentra, sin embargo al quitar la implementacion la question en la Task de "tocarSecuencia" la prueba se ejecuta sin problemas,
pero no hay validadres o ogs para validar si efectivemente se hizo lo que se esperaba

Por cuestiones de tiempo de entrega y dificultades tecnicas, no pude implementar testNG como se solicito, sin embargo, utiilice cucumber y features que son mas manejables para el usuario final
solo se implemento el escenario 1 que es la secuencia base, pero el json tiene las secuencias de todos los escenarios, solo hace falta replicarlos en los Steps

## 1. URL del Repositorio

https://github.com/diegoalejoreyes/piano_virtual_screenplay.git

## 2. Escenarios de Prueba Automatizados

Se han automatizado tres escenarios basados en la canción "Himno de la Alegría" segun las instrucciones dadas. 
La verificación se realiza validando que la clase del elemento de la tecla cambia a "active" al ser presionada, confirmando que la interacción con el DOM es exitosa.

| Escenario | Descripción | Data (Ejemplo de Notas) |
| :--- | :--- | :--- |
| **Escenario 1** | Reproducir la secuencia base del Himno de la Alegría. | si, si, do, re, re, do, si, la, sol, sol, la, si, si, la, la. |
| **Escenario 2** | Repetir la secuencia base dos veces. | (Dos veces la secuencia del Escenario 1). |
| **Escenario 3** | Reproducir la secuencia completa, incluyendo la variación melódica y repetición final. | si, si, do, re, re, do, si, la, sol, sol, la, si, la, sol, sol, la, si, sol, la, si, do, si sol, la, si, do, si, sol, sol, fa, re, (repetir Escenario 1). |

*(Nota: Las notas en el archivo JSON están mapeadas a sus códigos técnicos de `data-note` de la aplicación, como "3b", "4c", etc.)*

## 3. Arquitectura, Herramientas y Buenas Prácticas

### 🛠️ Herramientas y Frameworks

| Herramienta | Versión / Propósito | Requisito Cumplido |
| :--- | :--- | :--- |
| **Serenity BDD** | Framework principal para documentación viva (Cucumber + Screenplay). | Serenity BDD, Patrón de Diseño. |
| **Selenium WebDriver** | Herramienta base para la interacción con el navegador. | Selenium. |
| **Cucumber** | Engine de ejecución para los escenarios Gherkin (`.feature`). | Reemplazo de Junit/TestNG. |
| **Maven** | Gestor de dependencias del proyecto y ciclo de vida. | Gestor de dependencias. |
| **Java** | Lenguaje de programación. | Lenguaje de programación. |
| **Gson** | Librería para parsear y gestionar los datos del archivo JSON. | N/A |

### 🏛️ Arquitectura y Patrones de Diseño (Screenplay)

Se utiliza el patrón **Screenplay (Basado en la Acción)**, que se enfoca en el comportamiento del usuario y promueve la separación de responsabilidades:

| Componente | Responsabilidad | Principio |
| :--- | :--- | :--- |
| **Actor** | El usuario que realiza las acciones (`usuario`). | Patrón Screenplay. |
| **Questions** | Preguntas sobre el estado del sistema (`NotaEstaActiva`). | Separación de Responsabilidades. |
| **Tasks** | Secuencias de acciones de alto nivel (`TocarSecuencia`). |
| **Interactions** | Acciones de bajo nivel (clics, JavaScript) | Encapsula el "cómo" interactuar con el DOM. |
| **POJO & Loader** | Manejo de data de prueba desde JSON  | **Separación de Datos** y Lógica. |

### ✅ Buenas Prácticas Aplicadas

* **Separación de Data:** La data de las melodías se carga desde un archivo JSON, separando los datos de la lógica del código de prueba.
* **Tratamiento de Interceptores:** Se utilizó `JavaScriptClick` para resolver el `ElementClickInterceptedException` causado por la superposición de las teclas negras sobre las teclas blancas en el DOM.
* **Verificación de Estado Físico:** Se implementó una verificación de estado (`WaitUntil.the().attributes("class").value(Matchers.containsString("active"))`) para confirmar que el clic fue funcional y que la tecla reaccionó al evento.
* **Cierre de Browser:** El framework Serenity gestiona el cierre automático del navegador al finalizar cada escenario.

## 4. Instrucciones de Ejecución

Para ejecutar las pruebas y generar el reporte de Serenity BDD, sigue estos pasos:

1.  **Clonar el Repositorio:**
    ```bash
    git clone https://github.com/diegoalejoreyes/piano_virtual_screenplay.git
    ```

2.  **Ejecutar las Pruebas (Maven):**
    Asegurese de tener Java 17+ instalado. El plugin `maven-failsafe-plugin` está configurado para ejecutar el `CucumberRunner`.
    ```bash
    mvn clean verify
    ```

3.  **Generar y Abrir el Reporte:**
    Después de la ejecución, utiliza el plugin Serenity para generar el informe con documentación viva.
    ```bash
    mvn serenity:aggregate
    ```
    El reporte se abrirá automáticamente. También puedes encontrarlo en: `target/site/serenity/index.html`
