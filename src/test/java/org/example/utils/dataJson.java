package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.models.melodiaModel;
import org.openqa.selenium.json.TypeToken;


import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class dataJson {
    /**
     * Carga el archivo notes_sequences.json desde classpath y lo devuelve
     * como Map<String, List<String>>. Lanza RuntimeException en caso de error.
     */

    private static Map<String, List<melodiaModel>> data;
    private static final String DATA_FILE = "/data/secuencia_himno.json";

    static {
        try (InputStreamReader reader =
                new InputStreamReader(dataJson.class.getResourceAsStream(DATA_FILE))) {

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<melodiaModel>>>(){}.getType();
            data = gson.fromJson(reader, type);
        } catch (Exception e){
            System.out.println("Error al cargar la data "  + DATA_FILE);
            e.printStackTrace();
            data = Map.of();
        }
    }
    /**
     * Obtiene el objeto Melody completo buscando por el id del escenario a probar parametrizado en el Json
     * @return El objeto Melody correspondiente.
     */
    public static melodiaModel getMelodiaId(String id){
        if (data == null || !data.containsKey("melodias")){
            throw new IllegalStateException("Data no cargada correctamente");
        }

        return data.get("melodias").stream()
                .filter(melodiaModel -> id.equals(melodiaModel.getId())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el arreglo para la melodia"  + id));
    }



}
