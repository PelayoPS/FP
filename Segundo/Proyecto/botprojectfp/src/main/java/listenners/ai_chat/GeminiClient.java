package listenners.ai_chat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class GeminiClient {

    // URL de la API de Gemini
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    // Clave de la API de Gemini
    private String apiKey;

    /**
     * Constructor que recibe la clave de la API
     * @param String apiKey clave de la API
     */
    public GeminiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Método que genera contenido a partir de un prompt
     * 
     * @param String prompt con el texto de entrada
     * @return String con el texto generado
     * @throws Exception si hay un error en la petición
     */
    public String generateContent(String prompt) throws Exception {
        // Crear la URI con la URL de la API y la clave
        URI uri = new URI(API_URL + apiKey);

        // Crear un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear el JSON de entrada
        String jsonInputString = "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + prompt + "\" }] }] }";

        // Crear la petición HTTP
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString, StandardCharsets.UTF_8)).build();

        // Enviar la petición y obtener la respuesta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificar si la respuesta es correcta
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }

        // Crear un objeto JSON a partir de la respuesta
        JSONObject jsonObject = new JSONObject(response.body());

        // Ajustar el código para navegar correctamente por la estructura del JSON
        if (jsonObject.has("candidates")) {

            // Obtener el contenido del primer candidato
            JSONArray candidates = jsonObject.getJSONArray("candidates");

            // Verificar si hay contenido
            if (candidates.length() > 0) {

                // Obtener el primer candidato
                JSONObject firstCandidate = candidates.getJSONObject(0);

                // Verificar si hay contenido
                if (firstCandidate.has("content")) {

                    // Obtener el contenido
                    JSONObject content = firstCandidate.getJSONObject("content");

                    // Verificar si hay partes
                    if (content.has("parts")) {

                        // Obtener las partes
                        JSONArray parts = content.getJSONArray("parts");

                        // Verificar si hay partes
                        if (parts.length() > 0) {

                            // Obtener la primera parte
                            JSONObject firstPart = parts.getJSONObject(0);

                            // Verificar si hay texto
                            if (firstPart.has("text")) {

                                // Obtener el texto
                                return firstPart.getString("text");
                            }
                        }
                    }
                }
            }
        }

        // Si no se encontró contenido
        return "No content found";

    }

}