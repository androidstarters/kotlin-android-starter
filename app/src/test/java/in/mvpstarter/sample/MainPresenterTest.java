package in.mvpstarter.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import in.mvpstarter.sample.common.TestDataFactory;
import in.mvpstarter.sample.data.DataManager;
import in.mvpstarter.sample.ui.main.MainMvpView;
import in.mvpstarter.sample.ui.main.MainPresenter;
import in.mvpstarter.sample.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainMvpView mMockMainMvpView;
    @Mock
    DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mMockDataManager);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void getPokemonReturnsPokemonNames() throws Exception {
        List<String> pokemonList = TestDataFactory.makePokemonNamesList(10);
        when(mMockDataManager.getPokemonList(10))
                .thenReturn(Single.just(pokemonList));

        mMainPresenter.getPokemon(10);

        verify(mMockMainMvpView, times(2)).showProgress(anyBoolean());
        verify(mMockMainMvpView).showPokemon(pokemonList);
        verify(mMockMainMvpView, never()).showError(new RuntimeException());

    }

    @Test
    public void getPokemonReturnsError() throws Exception {
        when(mMockDataManager.getPokemonList(10))
                .thenReturn(Single.error(new RuntimeException()));

        mMainPresenter.getPokemon(10);

        verify(mMockMainMvpView, times(2)).showProgress(anyBoolean());
        verify(mMockMainMvpView).showError(any(Throwable.class));
        verify(mMockMainMvpView, never()).showPokemon(ArgumentMatchers.anyList());
    }

}