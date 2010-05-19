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
package de.javauni.jarcade.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.javauni.jarcade.utils.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kmochi
 */
public class ControlManagementTest {

    KeyboardControlMap keymap;
    ControlManagement manager;

    public ControlManagementTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Injector inj = Guice.createInjector(new ControlModule());
        keymap = inj.getInstance(KeyboardControlMapImpl.class);
        manager = inj.getInstance(ControlManagementImpl.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void loadAndSave() {
        keymap.put(1, new Pair<Integer, ControlEvent>(1, ControlEvent.Jump));
        keymap.put(2, new Pair<Integer, ControlEvent>(1, ControlEvent.MoveLeft));
        keymap.put(3, new Pair<Integer, ControlEvent>(1, ControlEvent.MoveRight));

        try {
            manager.save();

            manager.load();
        } catch (CouldNotLoadExeption ex) {
            Logger.getLogger(ControlManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ControlDataIsCorruptExeption ex) {
            Logger.getLogger(ControlManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CouldNotSaveExeption ex) {
            Logger.getLogger(ControlManagementImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Set<Integer> set = keymap.keySet();
        for (Integer i : set) {
            Pair<Integer, ControlEvent> a = keymap.get(i);
            System.out.println(i + " " + keymap.get(i).fst + " " + keymap.get(i).snd);
            if (i == 1) {
                assertTrue((i + " " + keymap.get(i).fst + " " + keymap.get(i).snd).equals("1 1 Jump"));
            } else if (i == 2) {
                assertTrue((i + " " + keymap.get(i).fst + " " + keymap.get(i).snd).equals("2 1 MoveLeft"));
            } else if (i == 3) {
                assertTrue((i + " " + keymap.get(i).fst + " " + keymap.get(i).snd).equals("3 1 MoveRight"));
            }
        }
    }
    @Test
    public void save() throws IOException{
        keymap.put(1, new Pair<Integer, ControlEvent>(1, ControlEvent.Jump));
        keymap.put(2, new Pair<Integer, ControlEvent>(1, ControlEvent.MoveLeft));
        keymap.put(3, new Pair<Integer, ControlEvent>(1, ControlEvent.MoveRight));
        File file = new File("Keyboard.ini");
        System.out.println(file.exists());
        try {
            manager.save(file.getAbsolutePath());
        } catch (CouldNotSaveExeption ex) {
            
            Logger.getLogger(ControlManagementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            assertTrue(br.readLine().equals("1\t1\t3"));
            assertTrue(br.readLine().equals("2\t1\t1"));
            assertTrue(br.readLine().equals("3\t1\t2"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControlManagementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(keymap.hashCode());
    }

}
// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

