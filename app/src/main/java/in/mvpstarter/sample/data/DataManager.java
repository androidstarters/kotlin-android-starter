package in.mvpstarter.sample.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.mvpstarter.sample.data.model.NamedResource;
import in.mvpstarter.sample.data.model.Pokemon;
import in.mvpstarter.sample.data.model.PokemonListResponse;
import in.mvpstarter.sample.data.remote.MvpStarterService;
import io.reactivex.Single;

@Singleton
public class DataManager {

    private final MvpStarterService mMvpStarterService;

    @Inject
    public DataManager(MvpStarterService mvpStarterService) {
        mMvpStarterService = mvpStarterService;
    }

    public Single<List<String>> getPokemonList(int limit) {
        return mMvpStarterService.getPokemonList(limit)
                .toObservable()
                .flatMapIterable(PokemonListResponse::getResults)
                .map(NamedResource::getName)
                .toList();
    }

    public Single<Pokemon> getPokemon(String name) {
        return mMvpStarterService.getPokemon(name);
    }

}