package in.mvpstarter.sample.data.remote;


import in.mvpstarter.sample.data.model.Pokemon;
import in.mvpstarter.sample.data.model.PokemonListResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public interface MvpStarterService {

    @GET("pokemon")
    Single<PokemonListResponse> getPokemonList(@Query("limit") int limit);

    @GET("pokemon/{name}")
    Single<Pokemon> getPokemon(@Path("name") String name);

}
