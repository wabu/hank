package de.javauni.jarcade.presenter.impl;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.javauni.jarcade.model.MainModel;
import de.javauni.jarcade.model.MainState;
import static de.javauni.jarcade.model.StateModel.ChangeListener;

import de.javauni.jarcade.model.scene.SceneModel;

import de.javauni.jarcade.presenter.Presentor;


import de.javauni.jarcade.presenter.interactions.TransitionPerformer;

import de.javauni.jarcade.utils.guice.ManagedScope;

import de.javauni.jarcade.view.GraphicsContext;
import de.javauni.jarcade.view.GraphicsOutput;
import de.javauni.jarcade.view.View;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;

@ManagedScope
public class MainOutputPresenter<G extends GraphicsContext> 
    implements Presentor<MainModel>, TransitionPerformer<MainState>, ChangeListener<MainState> {

    private static final Logger logger =
        LoggerFactory.getLogger(MainOutputPresenter.class);

    private final GraphicsOutput<G> gfx;
    private final MainModel model;

    private final Provider<Presentor<SceneModel>> scenePP;
    private final Provider<KeyboardGameInteractor<?>> keyInteractorP;

    @Nullable
    private View<?, G> current = null;

    @Inject
    public MainOutputPresenter(GraphicsOutput<G> gfx, MainModel model, 
            Provider<Presentor<SceneModel>> scenePP, Provider<KeyboardGameInteractor<?>> keyInteractorP) {
        this.gfx = gfx;
        this.model = model;

        this.scenePP = scenePP;
        this.keyInteractorP = keyInteractorP;

        model.getStateChangeChannel().addListener(this);
    }

    @Override
    public void onStateChange(MainState src, MainState tgt) {
        // TODO setup subcontrols/subviews
        if (current!=null) {
            current.unbindGraphics();
            current = null;
        }

        G gc = gfx.getCleanGraphicsContext();
        switch(tgt) {
            case level:
                scenePP.get();
                if (gc instanceof JavaGraphicsContext) {
                    // TODO typesafty
                    keyInteractorP.get().register((JavaGraphicsContext)gc);
                }
                break;
            default:
                logger.warn("output for {} not implemented yet", tgt);
                break;
        }

        // TODO how to give scenePP the gfx context
        if (current!=null) {
            current.bindGraphics(gc);
        }
    }

    @Override
    public void doTransition(MainState target) {
        // XXX handel transition exceptions here?
        model.setState(target);
    }

    @Override
    public void transitToNext() {
        // XXX handel transition exceptions here?
        model.setNextState();
    }
}
