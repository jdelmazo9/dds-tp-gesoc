package grupo6.spark.utils;

import com.google.gson.Gson;
import spark.Request;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;

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
}
