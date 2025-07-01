package in.mollosradix.example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("Products")
    Call<ProductResponse> getProducts();
}
