package de.javauni.jarcade;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

import de.javauni.jarcade.model.MainModel;
import de.javauni.jarcade.model.MainState;

import de.javauni.jarcade.model.scene.SceneModel;

import de.javauni.jarcade.presenter.Presentor;
import de.javauni.jarcade.presenter.impl.GameActionPerformerImpl;
import de.javauni.jarcade.presenter.impl.MainOutputPresenter;
import de.javauni.jarcade.presenter.impl.SceneRenderingPresentor;

import de.javauni.jarcade.presenter.interactions.GameActionPerformer;
import de.javauni.jarcade.presenter.interactions.TransitionPerformer;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;

public class PresentorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<Presentor<MainModel>>(){})
            .to(new TypeLiteral<MainOutputPresenter<JavaGraphicsContext>>(){});
        bind(new TypeLiteral<TransitionPerformer<MainState>>(){})
            .to(new TypeLiteral<MainOutputPresenter<JavaGraphicsContext>>(){});

        bind(new TypeLiteral<Presentor<SceneModel>>(){})
            .to(new TypeLiteral<SceneRenderingPresentor<JavaGraphicsContext>>(){});
        bind(new TypeLiteral<GameActionPerformer<?>>(){})
            .to(new TypeLiteral<GameActionPerformerImpl>(){});
    }

}
