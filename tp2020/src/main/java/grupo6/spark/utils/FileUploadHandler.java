package grupo6.spark.utils;

import com.google.gson.*;
import grupo6.dominio.entidades.Presupuesto;
import spark.Request;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileUploadHandler {
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
        }
    }).create();

    public static <T> T readJsonTo(Request request, String id, Class<T> targetClass) {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        String json = null;
        try {
            InputStream input = request.raw().getPart(id).getInputStream();
            json = IOUtils.toString(input);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        return gson.fromJson(json, targetClass);
    }

//    public static <T> ArrayList<T> readJsonToArry(Request request, String id, Class<T> targetClass) {
//        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
//        String json = null;
//        try {
//            InputStream input = request.raw().getPart(id).getInputStream();
//            json = IOUtils.toString(input);
//        } catch (IOException | ServletException e) {
//            e.printStackTrace();
//        }
//        new Gson().f
//        return new Gson().fromJson(json);
//    }
}
