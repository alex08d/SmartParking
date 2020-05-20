import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTMLDocument;
import java.io.*;;
import java.security.KeyStore;
import java.time.Instant;
import java.util.*;

//Capacitatea maxima a parcarii este de 4 locuri

public class Main {
    public static void main(String[] args) throws Exception {
        float value = 0;
        HashMap<Integer, Float> MasiniInParcare = new HashMap<>();
        HashMap<Integer, Integer> LoculMasinii = new HashMap<>();
        List<Boolean> locuriLibere = new LinkedList<>();
        for (int i = 0; i < Parcare.MAXCAPACITY; i++) {
            locuriLibere.add(true);                         //locurile libere din parcare sunt marcate cu variabila true, iar cele ocupate cu variabila false.
        }
        Queue<Masina> coadaAsteptare = new LinkedList<>();
        File file = new File("C:\\Users\\alexd\\IdeaProjects\\Actionari\\src\\input.txt");
        Scanner sc = new Scanner(file);
        Parcare parcare = new Parcare(3, locuriLibere); //creez parcarea
        int numarTel = 0;
        float timp = 0;

        while (sc.hasNextInt() || sc.hasNextFloat()) {
            if (sc.hasNextInt()) {
                numarTel = sc.nextInt();
            }
            if (sc.hasNextFloat()) {                            //adaug in coada de asteptare masinile care vor sa intre in parcare
                timp = sc.nextFloat();
            }
            coadaAsteptare.add(new Masina(numarTel, timp));
        }
        BarieraIntrare barieraIntrare = new BarieraIntrare(true, false, 0); //creez doua instante de Bariera de intrare
        BarieraIesire barieraIesire = new BarieraIesire(true, false, 0);    //respectiv Bariera de iesire
        float timpMinim = 10;
        List<Long> timpiIntrare = new LinkedList<>();
        while (MasiniInParcare.size() < Parcare.MAXCAPACITY) {
            int numarTelTimpMin = 0;
            int numarTelTimpMinP5 = 0;
            float timpMinimPeste5 = 10;            // cat timp parcarea nu este plina parcurg urmatorii pasi
            if (!coadaAsteptare.isEmpty()) {

                barieraIntrare.intrerupereStator1();    // se verifica coada de asteptare
                barieraIntrare.alimentareStator2();     // daca aceasta este goala nu mai are rost sa se simuleze proiectul
                barieraIntrare.unghiTeta90();  // se deschide bariera de intrare
                barieraIntrare.ridicareBariera();
                System.out.println("Intra masina cu numarul de tel " + coadaAsteptare.peek().numarTelefon);
                MasiniInParcare.put(coadaAsteptare.peek().numarTelefon, coadaAsteptare.peek().timp);     //daca coada nu este goala introducem masinile din coada in parcare
                for (int i = 0; i < locuriLibere.size(); i++) {
                    if (locuriLibere.get(i) == true) {
                        LoculMasinii.put(coadaAsteptare.peek().numarTelefon, i);
                        timpiIntrare.add(i,Instant.now().toEpochMilli());   //se adauga intr-o lista a timpilor de intrare timpii de intrare pentru fiecare masina
                        locuriLibere.set(i, false); // se marcheaza cu false locul ocupat de catre o masina intrata in parcare
                        break;
                    }
                }
                coadaAsteptare.poll();  //se scoata din coada de asteptare masina care a intrat
                barieraIntrare.intrerupereStator2();
                barieraIntrare.alimentareStator1();
                barieraIntrare.unghiTeta0();       // sunt demarate procedurile inchiderii barierei
                barieraIntrare.coborareBariera();
            }
            if (MasiniInParcare.size() == Parcare.MAXCAPACITY) {    //  parcarea isi atinge capacitatea maxima

                timpMinim = Collections.min(MasiniInParcare.values()); // se afla timpul minim mai mic decat 5 care trebuie sa paraseasca parcarea

                for (Map.Entry mapElement : MasiniInParcare.entrySet()) {
                     value = ((float) mapElement.getValue());
                    if (value >= 5 && value < timpMinimPeste5) {
                        timpMinimPeste5 = value;
                        numarTelTimpMinP5 = (Integer) mapElement.getKey();  // se afla numarul de telefon al celui care trebuie sa paraseasca parcarea
                    }                                                        // caci a depasit pragul de 5s
                }
                System.out.print("Urmatoarele locuri vor fi disponibile dupa timpul de ");
                for (Map.Entry mapElement : MasiniInParcare.entrySet()) {
                     value = ((float) mapElement.getValue());
                    if (value < 5) {
                        System.out.println(value + " ");    //afisam locurile care vor fi disponibile, timpul acestora de ocupare fiind mai mic decat 5s
                    }
                    if (value == timpMinim) {
                        numarTelTimpMin = (Integer) mapElement.getKey();
                    }

                }
                while(MasiniInParcare.size() == parcare.MAXCAPACITY) {   // cat timp capacitatea maxima a parcarii este atinsa
                    if (Math.abs(timpiIntrare.get(LoculMasinii.get(numarTelTimpMin)) - Instant.now().toEpochMilli()) >= timpMinim * 1000 && Math.abs(timpiIntrare.get(LoculMasinii.get(numarTelTimpMin)) - Instant.now().toEpochMilli()) < 5000) { //se calculeaza daca diferenta dintre timpul curent si timpul de intrare al celui
                        barieraIesire.intrerupereStator1();                                                          // cu cel mai mic timp mai mic ecat 5s este egal cu timpul estimat de ocupare al locului
                        barieraIesire.alimentareStator2();
                        barieraIesire.unghiTeta90();      //se ridica bariera de iesire, respectand toti pasii
                        barieraIesire.ridicareBariera();
                        System.out.println("Iese masina cu numarul de telefon " + numarTelTimpMin);  //iese masina care a stat cel mai putin
                        if (LoculMasinii.containsKey(numarTelTimpMin)) {
                            locuriLibere.set(LoculMasinii.get(numarTelTimpMin), true); // se elibereaza un loc, cel care inainte a fost ocupat de masina cu cel mai mic timp
                            MasiniInParcare.remove(numarTelTimpMin, timpMinim);  // iese din parcare masina
                            timpiIntrare.remove(timpiIntrare.get(LoculMasinii.get(numarTelTimpMin)));  // este scos si timpul de intrare odata cu parasirea parcarii de catre masina
                            LoculMasinii.remove(numarTelTimpMin);

                        }
                        barieraIesire.intrerupereStator2();
                        barieraIesire.alimentareStator1();
                        barieraIesire.unghiTeta0();     //se inchide bariera de iesire
                        barieraIesire.coborareBariera();

                    }
                    if(MasiniInParcare.size() == parcare.MAXCAPACITY) {
                        if (Math.abs(timpiIntrare.get(LoculMasinii.get(numarTelTimpMinP5)) - Instant.now().toEpochMilli()) >= parcare.TIMPPARCARE) {
                            if (MasiniInParcare.containsValue(timpMinimPeste5)) {
                                System.out.println("Se anunta prin mesaj posesorul cu timpul cel mai mic, mai mare decat 5s, si anume " + numarTelTimpMinP5);
                            }   // se trimite mesajul catre cel care care trebuie sa paraseasca parcarea caci a depasit timpul de 5s
                            barieraIesire.intrerupereStator1();
                            barieraIesire.alimentareStator2();
                            barieraIesire.unghiTeta90();    // se ridica bariera
                            barieraIesire.ridicareBariera();
                            System.out.println("Iese masina al carui posesor a fost anuntat ");
                            if (LoculMasinii.containsKey(numarTelTimpMinP5)) {
                                locuriLibere.set(LoculMasinii.get(numarTelTimpMinP5), true);    // se elibereaza un loc
                                MasiniInParcare.remove(numarTelTimpMinP5, timpMinimPeste5);   // iese din parcare masina
                                timpiIntrare.remove((timpiIntrare.get(LoculMasinii.get(numarTelTimpMinP5))));  // este scos si timpul de intrare odata cu parasirea parcarii de catre masina
                                LoculMasinii.remove(numarTelTimpMinP5);

                            }
                            barieraIesire.intrerupereStator2();
                            barieraIesire.alimentareStator1();  // se inchide bariera
                            barieraIesire.unghiTeta0();
                            barieraIesire.coborareBariera();

                        }
                    }

                }
            }
                if (coadaAsteptare.isEmpty()) { // se verifica daca coada de asteptare este goala
                    System.out.println("Nu se mai afla nicio masina in coada de asteptare ");
                    System.out.println("In parcare se afla masinile "+ MasiniInParcare);
                    Iterator iterator1 = MasiniInParcare.entrySet().iterator();
                    while(iterator1.hasNext()) {                                                    // ies masinile din parcare care depasesc timpyl de 5s
                        Map.Entry<Integer,Float> item = (Map.Entry<Integer, Float>)iterator1.next();
                        if((Float)item.getValue() > 5.0) {
                            System.out.println("Iese masina cu numarul de tel: " + item.getKey());
                            iterator1.remove();
                        }
                    }

                    Iterator iterator2 = MasiniInParcare.entrySet().iterator();
                    while(iterator2.hasNext()) {
                        Map.Entry<Integer,Float> item = (Map.Entry<Integer, Float>)iterator2.next();
                        if((Float)item.getValue() < 5.0) {                                                     // ies masinile care au timpul de ocupare mai mic decat 5s.
                            System.out.println("Iese masina cu numarul de tel: " + item.getKey());
                            iterator2.remove();
                        }
                    }
                    System.out.println("In parcare nu se mai afla nicio masina.");

                        break;
                }
            }
        }
    }



