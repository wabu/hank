package de.javauni.jarcade.presenter;

import com.google.inject.Inject;

import de.javauni.jarcade.model.MainModel;
import static de.javauni.jarcade.model.StateModel.ChangeListener;

import de.javauni.jarcade.view.gui.OutputFrame;

public class MainOutputPresenter implements ChangeListener<MainModel.State> {
    private final OutputFrame frame;
    private final MainModel model;

    @Inject
    public MainOutputPresenter(OutputFrame frame, MainModel model) {
        this.frame = frame;
        this.model = model;

        model.getStateChangeChannel().addListener(this);
    }

    @Override
    public void onStateChange(MainModel.State state) {
        // TODO setup subcontrols/subviews
    }
}
