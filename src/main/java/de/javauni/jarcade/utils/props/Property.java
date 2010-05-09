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

package de.javauni.jarcade.utils.props;

/**
 * The property annotation is used in entitys for constructor parameters
 * that can be set inside the level editor.
 * @author Daniel Waeber <wabu@inf.fu-berlin.de>
 */
public @interface Property {
    /** @return name of the propertie */
    String name();
    /** @return descripton of the property */
    String description() default "";
    /** @return default value */
    String value();
}
