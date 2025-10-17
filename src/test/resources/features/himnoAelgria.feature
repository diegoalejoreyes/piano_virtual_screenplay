Feature: Reproducir secuencias definidas por un archivo .json en el piano virtual

  @himno_alegria
  Scenario: Escenario 1 - Himno de la alegría secuencia base
    Given que el usuario requiere ingresar al piano virtual y tocar una melodia
    When se carga la secuencia "escenario_1" desde el archivo json
    Then se reprocude la melodia correctamente
    Then la secuencia se completa sin errores y se valida que las notas queden activas

  Scenario: Escenario 2 - Repetir la secuencia base dos veces
    Given que el usuario requiere ingresar al piano virtual y tocar una melodia
    When se carga la secuencia "escenario_2" desde el archivo json
    Then la secuencia se completa sin errores

  Scenario: Escenario 3 - Secuencia extendida y repetir escenario1
    Given que el usuario requiere ingresar al piano virtual y tocar una melodia
    When se carga la secuencia "escenario_3" desde el archivo json
    Then se reprocude la melodia correctamente y la última tecla presionada queda activa


