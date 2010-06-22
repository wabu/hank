package de.javauni.jarcade.presenter.impl;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import de.javauni.jarcade.model.MainModel;
import static de.javauni.jarcade.model.StateModel.ChangeListener;


import de.javauni.jarcade.presenter.interactions.TransitionPerformer;

import de.javauni.jarcade.view.GraphicsContext;
import de.javauni.jarcade.view.GraphicsOutput;
import de.javauni.jarcade.view.View;

import de.javauni.jarcade.view.impl.JavaGraphicsContext;
import de.javauni.jarcade.view.impl.SceneView;

public class MainOutputPresenter<G extends GraphicsContext> 
    implements TransitionPerformer<MainModel.State>, ChangeListener<MainModel.State> {

    private static final Logger logger =
        LoggerFactory.getLogger(MainOutputPresenter.class);

    private final GraphicsOutput<G> gfx;
    private final MainModel model;

    private final Provider<SceneView> sceneViewP;
    private final Provider<KeyboardGameInteractor> keyInteractorP;

    @Nullable
    private View<G> current = null;

    @Inject
    public MainOutputPresenter(GraphicsOutput<G> gfx, MainModel model, 
            Provider<SceneView> sceneViewP, Provider<KeyboardGameInteractor> keyInteractorP) {
        this.gfx = gfx;
        this.model = model;

        this.sceneViewP = sceneViewP;
        this.keyInteractorP = keyInteractorP;

        model.getStateChangeChannel().addListener(this);
    }

    @Override
    public void onStateChange(MainModel.State src, MainModel.State tgt) {
        // TODO setup subcontrols/subviews
        if (current!=null) {
            current.unbindGraphics();
            current = null;
        }

        G gc = gfx.getCleanGraphicsContext();
        switch(tgt) {
            case Level:
                // TOOD typesafty
                current = (View<G>)sceneViewP.get();
                keyInteractorP.get().register((JavaGraphicsContext)gc);
                break;
            default:
                logger.warn("output for {} not implemented yet", tgt);
                break;
        }

        if (current!=null) {
            // FIXME bad cast foo bar
            current.bindGraphics(gc);
        }
    }

    @Override
    public void doTransition(MainModel.State target) {
        // XXX handel transition exceptions here?
        model.setState(target);
    }

    @Override
    public void transitToNext() {
        // XXX handel transition exceptions here?
        model.setNextState();
    }
}
