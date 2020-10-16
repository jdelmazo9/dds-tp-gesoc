package grupo6.dominio.servicios;

import grupo6.dominio.entidades.MedioDePago;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ServicioMercadoPagoInterfaz {

    @GET("payment_methods")
    Call<List<MedioDePagoMP>> mediosDePago();
}
