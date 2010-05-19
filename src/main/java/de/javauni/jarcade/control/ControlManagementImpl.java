package de.javauni.jarcade.control;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.javauni.jarcade.utils.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlManagementImpl implements ControlManagement {

    private File file = new File("Keyboard.ini");
    private KeyboardControlMap keymap;

    public ControlManagementImpl() {
        
            Injector inj = Guice.createInjector(new ControlModule());
            keymap = inj.getInstance(KeyboardControlMapImpl.class);
         

    }

    public void load(String dateiname) throws CouldNotLoadExeption, ControlDataIsCorruptExeption {
        file=new File(dateiname);
        load();
    }
    @Override
    public void load() throws CouldNotLoadExeption, ControlDataIsCorruptExeption {
        Logger.getLogger("(ControleManagement)load Controls");
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
        Logger.getLogger("(ControleManagement)Controls loaded");
    }

    public void save(String dateiname) throws CouldNotSaveExeption {
        file=new File(dateiname);
        save();
    }
    @Override
    public void save() throws CouldNotSaveExeption {
        Logger.getLogger("(ControleManagement)save Controls");
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
        Logger.getLogger("(ControleManagement)Controls saved");
    }
    public static void main(String[] args){
        Injector inj = Guice.createInjector(new ControlModule());
        KeyboardControlMap map1 = inj.getInstance(KeyboardControlMap.class);
        
        KeyboardControlMap map2 = inj.getInstance(KeyboardControlMap.class);
        map1.put(1, new Pair(1,ControlEvent.Jump));
        System.out.println(map2.get(1).fst+""+map2.get(1).snd);
    }
    
}
