
package teil3;
import com.google.inject.Inject;
import com.google.inject.name.Named;
public class HalloSager implements IAnzeige{
        private int a;
        @Inject
        public HalloSager(@Named("intwert") int a) {
                this.a=a;
        }
        public void zeig(String string) {
                System.out.println(string + a);
        }
}