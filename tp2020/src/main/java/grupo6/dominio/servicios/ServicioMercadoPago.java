package grupo6.dominio.servicios;
import grupo6.dominio.entidades.MedioDePago;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioMercadoPago {
    private static ServicioMercadoPago instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private Retrofit retrofit;

    private ServicioMercadoPago() {

        OkHttpClient cliente = new OkHttpClient.Builder().addInterceptor(
            new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "TEST-4993571172493654-101523-92c837c3389735c44871b99196e42444-279799519")
                        .build();
                    return chain.proceed(newRequest);
                }
            }
        ).build();

        this.retrofit = new Retrofit.Builder()
            .client(cliente)
            .baseUrl("https://api.mercadopago.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static ServicioMercadoPago instancia(){
        if(instancia== null){
            instancia = new ServicioMercadoPago();
        }
        return instancia;
    }

    public List<MedioDePagoMP> listadoMediosDePago() throws IOException {
        ServicioMercadoPagoInterfaz servicioMP = this.retrofit.create(ServicioMercadoPagoInterfaz.class);
        Call<List<MedioDePagoMP>> reqMediosDePago = servicioMP.mediosDePago();
        Response<List<MedioDePagoMP>> respMediosDePago = reqMediosDePago.execute();
        List<MedioDePagoMP> mediosDePago = respMediosDePago.body();
        return mediosDePago;
    }

//    public ListadoDeProvincias listadoDeProvincias() throws IOException {
//        GeorefService georefService = this.retrofit.create(GeorefService.class);
//        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias();
//        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
//        ListadoDeProvincias provinciasArgentinas = responseProvinciasArgentinas.body();
//        return provinciasArgentinas;
//    }
//
//    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(Provincia provincia) throws IOException {
//        GeorefService georefService = this.retrofit.create(GeorefService.class);
//        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id, nombre", maximaCantidadRegistrosDefault);
//        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
//        ListadoDeMunicipios listadoDeMunicipios = responseListadoDeMunicipios.body();
//        return listadoDeMunicipios;
//    }
}
