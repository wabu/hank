/*
 *  Copyright (C) 2010 Daniel Waeber <wabu@inf.fu-berlin.de>
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
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package de.javauni.jarcade.impl.model;

import de.javauni.jarcade.event.Channel;
import de.javauni.jarcade.exceptions.IllegalAction;
import de.javauni.jarcade.model.menu.MenuItem;
import de.javauni.jarcade.model.menu.MenuModelAccess;
import de.javauni.jarcade.model.menu.MenuModelExport;
import de.javauni.jarcade.model.state.StateListener;
import java.util.List;
import java.util.Map;

/**
 * @param <S>
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public class AbstractMenuModel<S extends Enum<S>>
        extends AbstractStateModel<S>
        implements MenuModelAccess, MenuModelExport<S>{
    private final Map<S, ? extends List<? extends MenuItem>> pages;
    private List<? extends MenuItem> page;
    private int sel = 0;

    public AbstractMenuModel(Channel<StateListener<S>> channel,
            Map<S, ? extends List<? extends MenuItem>> pages,
            S startPage) {
        super(channel);
        this.pages = pages;
        this.page = pages.get(startPage);
    }

    @Override
    protected void doStateTransition(S src, S tgt) throws IllegalAction {
        if(!pages.containsKey(tgt)) {
            throw new IllegalAction(new UnsupportedOperationException
                    ("menu "+tgt+" has not been implemented"));
        }
        page = pages.get(tgt);
        sel = 0;
    }

    public List<? extends MenuItem> getItems() {
        return page;
    }

    public MenuItem getSelectedItem() {
        return page.get(sel);
    }

    public void select(int i) {
        sel = i;
    }

    public void selectNext() {
        select((sel+1) % page.size());
    }

    public void selectPrevious() {
        select((sel-1+page.size()) % page.size());
    }

    public void submit() {
        submit(sel);
    }

    public void submit(int i) {
        page.get(i).submit();
    }
}
