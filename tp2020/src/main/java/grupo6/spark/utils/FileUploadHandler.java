package grupo6.spark.utils;

import com.google.gson.Gson;
import grupo6.dominio.entidades.Presupuesto;
import spark.Request;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileUploadHandler {

    public static <T> T readJsonTo(Request request, String id, Class<T> targetClass) {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        String json = null;
        try {
            InputStream input = request.raw().getPart(id).getInputStream();
            json = IOUtils.toString(input);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(json, targetClass);
    }

    public static <T> ArrayList<T> readJsonToArry(Request request, String id, Class<T> targetClass) {
        request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
        String json = null;
        try {
            InputStream input = request.raw().getPart(id).getInputStream();
            json = IOUtils.toString(input);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        new Gson().f
        return new Gson().fromJson(json);
    }
}
