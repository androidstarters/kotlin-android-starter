package in.mvpstarter.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import in.mvpstarter.sample.common.TestDataFactory;
import in.mvpstarter.sample.data.DataManager;
import in.mvpstarter.sample.data.model.Pokemon;
import in.mvpstarter.sample.ui.detail.DetailMvpView;
import in.mvpstarter.sample.ui.detail.DetailPresenter;
import in.mvpstarter.sample.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Mock
    DetailMvpView mMockDetailMvpView;
    @Mock
    DataManager mMockDataManager;
    private DetailPresenter mDetailPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mDetailPresenter = new DetailPresenter(mMockDataManager);
        mDetailPresenter.attachView(mMockDetailMvpView);
    }

    @After
    public void tearDown() {
        mDetailPresenter.detachView();
    }

    @Test
    public void getPokemonDetailReturnsPokemon() throws Exception {
        Pokemon pokemon = TestDataFactory.makePokemon("id");
        when(mMockDataManager.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon));

        mDetailPresenter.getPokemon(anyString());

        verify(mMockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mMockDetailMvpView).showPokemon(pokemon);
        verify(mMockDetailMvpView, never()).showError(new RuntimeException());
    }

    @Test
    public void getPokemonDetailReturnsError() throws Exception {
        when(mMockDataManager.getPokemon("id"))
                .thenReturn(Single.error(new RuntimeException()));

        mDetailPresenter.getPokemon("id");

        verify(mMockDetailMvpView, times(2)).showProgress(anyBoolean());
        verify(mMockDetailMvpView).showError(any(Throwable.class));
        verify(mMockDetailMvpView, never()).showPokemon(any(Pokemon.class));
    }

}