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

package de.javauni.jarcade.control.buttonclick;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author kmochi
 */
public class ButtonClickImpl implements ButtonClick{
    private final org.slf4j.Logger log = LoggerFactory.getLogger(ButtonClickImpl.class);
    @Override
    public void actionPerformed(ActionEvent ae) {
        log.debug(ae.getActionCommand());
    }

}
