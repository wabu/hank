/*
 *  Copyright (C) 2010 kmochi
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.javauni.jarcade.view.gui.Main;

import de.javauni.jarcade.control.buttonclick.ButtonClickImpl;
import de.javauni.jarcade.view.gui.OutputPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author kmochi
 */
public class MainPanel extends OutputPanel {
    public MainPanel(Integer x, Integer y, Integer width, Integer height) {
        super(x,y,width,height);
        JButton startBtn = new JButton("Start");
        startBtn.setBounds(x/4, y/2, x/2, 80);
        startBtn.setVisible(true);
        startBtn.setActionCommand("Level");
        startBtn.addActionListener(new StartButtonActionListener(new ButtonClickImpl()));
    }
}
