import java.util.LinkedList;
import java.util.List;

public class Parcare {
    final static int MAXCAPACITY = 4;
    final static float TIMPPARCARE = 5000;
    protected float senozrDistanta;
    protected List locuriParcare;

    public Parcare(float senozrDistanta, List<Boolean> locuriParcare) {
        this.senozrDistanta = senozrDistanta;
        this.locuriParcare = locuriParcare;
    }

    public float getSenozrDistanta() {
        return senozrDistanta;
    }

    public void setSenozrDistanta(float senozrDistanta) {
        this.senozrDistanta = senozrDistanta;
    }

    public List<Boolean> getLocuriParcare() {
        return locuriParcare;
    }

    public void setLocuriParcare(List<Integer> locuriParcare) {
        this.locuriParcare = locuriParcare;
    }
}