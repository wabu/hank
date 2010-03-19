/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.javauni.utils.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.grapher.GrapherModule;
import com.google.inject.grapher.InjectorGrapher;
import com.google.inject.grapher.graphviz.GraphvizModule;
import com.google.inject.grapher.graphviz.GraphvizRenderer;
import de.javauni.yarrish.YarrishModule;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Grapher {
  private void graph(String filename, Injector demoInjector) throws IOException {
    PrintWriter out = new PrintWriter(new File(filename), "UTF-8");

    Injector injector = Guice.createInjector(new GrapherModule(), new GraphvizModule());
    GraphvizRenderer renderer = injector.getInstance(GraphvizRenderer.class);
    renderer.setOut(out).setRankdir("TB");

    injector.getInstance(InjectorGrapher.class)
        .of(demoInjector)
        .graph();
  }

  public static void main(String args[]) throws IOException {
      String filename = args.length > 0 ? args[0] : "guice-graph.dot";
      
      Injector inj = Guice.createInjector(new YarrishModule());
      new Grapher().graph(filename, inj);
  }
}
