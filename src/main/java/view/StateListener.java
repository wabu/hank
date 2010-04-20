package view;


/**
 * listener interface for a state change
 * @param <S> state type
 * @author wabu
 */
public interface StateListener<S extends Enum<S>> extends Listener {
    void onStateChange(S state);
}
