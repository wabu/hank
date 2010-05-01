package de.javauni.jarcade.impl.scene;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;

import com.google.inject.name.Named;

import de.javauni.jarcade.impl.scene.SceneUpdateLoop;
import de.javauni.jarcade.model.scene.Layer;
import de.javauni.jarcade.model.scene.SceneChangeListener;
import de.javauni.jarcade.model.scene.SceneEdit;
import de.javauni.jarcade.model.scene.entity.Entity;

import de.javauni.jarcade.model.scene.operate.LayerOperatorFactory;
import de.javauni.jarcade.model.scene.operate.Operator;
import de.javauni.utils.UpdateLoop;

import java.util.concurrent.ThreadFactory;

/**
 * @author wabu
 */
public class SceneUpdateLoop extends UpdateLoop implements SceneChangeListener {
    private final SceneEdit scene;
    private final Logger log = LoggerFactory.getLogger(SceneUpdateLoop.class);

    private final List<OpBinding<?>> ops;
    private final List<LayerOperatorFactory<?>> layOpFac;

    /**
     * @param intervall aspired time intervall in ms between the calls
     * @param tf        factory used to create a thread
     * @param scene
     */
    @Inject
    public SceneUpdateLoop(
            @Named("level-update-intervall") int intervall, ThreadFactory tf, SceneEdit scene) {
        super(intervall, tf);
        this.scene = scene;
        this.scene.getSceneChannel().addListener(this);
        this.ops = new ArrayList<OpBinding<?>>();
        this.layOpFac = new ArrayList<LayerOperatorFactory<?>>();
    }

    @Inject
    void initialize(Injector inj) {
        for(Key<?> k : inj.getBindings().keySet()) {
            if(k.getTypeLiteral().getRawType().isAssignableFrom(Operator.class)) {
                Key<? extends Operator<?>> key = (Key<? extends Operator<?>>)k;
                log.debug("registering operator "+key);
                //registerOperator(inj.getInstance(ko));
            }
            if(k.getTypeLiteral().getRawType().isAssignableFrom(LayerOperatorFactory.class)) {
                Key<? extends LayerOperatorFactory> key = (Key<LayerOperatorFactory>)k;
                log.debug("registering "+key+" for layer operation");
                layOpFac.add(inj.getInstance(key));
            }
        }
    }

    @Override
    public void onLayerAdded(Layer layer) {
        // TODO access key for layer type and annotation
        for(LayerOperatorFactory<?> fac : layOpFac) {
            log.debug("binding layer operator");
            ops.add(new OpBinding(layer, fac.create(layer)));
        }
    }

    @Override
    protected void update(long delta) {
        for(OpBinding<?> op : ops) {
            if(log.isDebugEnabled()) {
                log.debug("calling "+op);
            }
            op.apply(delta);
        }
    }

    @Override
    public void onEntitySpawned(Entity e, Layer layer) {
    }

    @Override
    public void onEntityRemoved(Entity e, Layer layer) {
    }

    @Override
    public void onEntityLayerChange(Entity e, Layer before, Layer layer) {
    }
}

