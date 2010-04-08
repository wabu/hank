package teil3;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Test {
	        public static void main(String[] args) {
	                Injector injector = Guice.createInjector(new HalloSagerModul());
	                IAnzeige an= injector.getInstance(IAnzeige.class);
	                an.zeig("hallo welt!");
	        }
}
