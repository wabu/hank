/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.yarrish.control;

import com.google.inject.Inject;
import de.javauni.jarcade.control.Control;
import de.javauni.jarcade.model.Managed;
import de.javauni.yarrish.model.MainStates;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wabu
 */
public class MainControl implements Control<MainStates>, Managed {
    private final Logger log = LoggerFactory.getLogger(MainControl.class);

    private final MainStates model;
    private Frame frame;

    @Inject
    public MainControl(MainStates model) {
        this.model = model;
    }

    public void start() {
        frame = new Frame("yarrr!");
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                log.debug("keyevent: {}", e);
                switch(e.getKeyChar()) {
                    case KeyEvent.VK_ENTER:
                        model.send(MainStates.Action.Start);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        model.send(MainStates.Action.Escape);
                        break;
                    default:
                        // TODO
                }
            }
        });
        frame.pack();
        frame.setVisible(true);

        // finish spalsh
        model.send(MainStates.Action.Finish);
    }

    public void suspend() {
        // TODO
    }

    public void resume() {
        // TODO
    }

    public void dellocate() {
        frame.dispose();
    }
}
