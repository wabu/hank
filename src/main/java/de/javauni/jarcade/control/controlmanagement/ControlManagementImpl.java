package de.javauni.jarcade.control.controlmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.javauni.jarcade.control.ControlModule;
import de.javauni.jarcade.control.playercontrol.PlayerControlMap;
import de.javauni.jarcade.utils.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

@Singleton
public class ControlManagementImpl implements ControlManagement {
    private final Logger log = LoggerFactory.getLogger(ControlManagementImpl.class);
    private File file = new File("Keyboard.ini");
    private PlayerControlMap keymap;

    @Inject
    public ControlManagementImpl(PlayerControlMap keymap) {
        this.keymap = keymap;
    }

    public void load(String dateiname) throws CouldNotLoadExeption, ControlDataIsCorruptExeption {
        file=new File(dateiname);
        load();
    }

    @Override
    public void load() throws CouldNotLoadExeption, ControlDataIsCorruptExeption {
        log.debug("(ControleManagement)load Controls");
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(file)));
            while (br.ready()) {
                String tmp = br.readLine();
                String[] parts = tmp.split("\t");
                keymap.put(Integer.valueOf(parts[0]),
                       new Pair<Integer,ControlEvent>(
                            Integer.valueOf(parts[1]),
                            ControlEvent.valueOf(parts[2])
                      )
                );
                
                //Keyvalue    Spielernr     Enum(int)
            }
            br.close();
        } catch (IOException e) {
            throw new CouldNotLoadExeption("Keyboard.ini nicht gefunden");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ControlDataIsCorruptExeption("es gibt mind. 1 Eintrag mit weniger als 3 Eintraegen in der Keyboard.ini");
        }
        log.debug("(ControleManagement)Controls loaded");
    }

    public void save(String dateiname) throws CouldNotSaveExeption {
        file=new File(dateiname);
        save();
    }
    @Override
    public void save() throws CouldNotSaveExeption {
        log.debug("(ControleManagement)save Controls");
        try {
            FileWriter fw = new FileWriter(file, false);
            Set<Integer> set = keymap.keySet();
            for (Integer i : set) {
                Pair<Integer, ControlEvent> a = keymap.get(i);
                fw.write("" + i + "\t" + a.fst + "\t" +a.snd+"\n");
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            throw new CouldNotSaveExeption("Keyboard.ini nicht gefunden");
        }
        log.debug("(ControleManagement)Controls saved");
    }
    public static void main(String[] args) throws CouldNotSaveExeption {
        Injector inj = Guice.createInjector(new ControlModule());
        ControlManagement cm = inj.getInstance(ControlManagement.class);

        PlayerControlMap map1 = inj.getInstance(PlayerControlMap.class);
        
        PlayerControlMap map2 = inj.getInstance(PlayerControlMap.class);
        map1.put(1, new Pair(1,ControlEvent.Jump));

        cm.save();

        System.out.println(map2.get(1).fst+""+map2.get(1).snd);
    }
    
}
