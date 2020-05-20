Simulator Smart Parking

 In cadrul proiecutului exista 5 clase, si anume: BarieraIntrare, BarieraIesire, Parcare, Masina si Main.

In cadrul claselor avem constructori, setteri si getteri pentru fiecare parametru si metode.

 Metoda ridicareBariera() transmite mesajul ca bariera a fost ridicata. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).
 Metoda coborareBariera() transmite mesajul ca bariera a fost ridicata. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire). 
 Metoda intrerupereStator1() transmite mesajul ca alimentararea infasurarii statorului 1 este intrerupta. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).
 Metoda intrerupereStator2() transmite mesajul ca alimentararea infasurarii statorului 2 este intrerupta. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).
 Metoda alimentareStator1() transmite mesajul ca statorul 1 este alimentat. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).
 Metoda alimentareStator2() transmite mesajul ca statorul 2 este alimentat. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).
 Metoda unghiTeta90() transmite mesajul ca unghiul teta devine 90. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).
 Metoda unghiTeta0() transmite mesajul ca unghiul teta devine 0. (Aceasta metoda este comuna claselor BarieraIntrare si Bariera Iesire).

 In cadrul clasei Main avem:

 Un hashmap MasiniInParcare care reprezinta masinile care sunt in parcare alaturi de numarul de telefon al posesorului masinii.
 Un hashmap LoculMasinii care contine numarul de telefon al posesorului masinii alaturi de locul de parcare in care se fala masina acestuia.
 O coada coadaAsteptare care contine masinile care asteapta sa intre in parcare.
 O lista locuriLibere care contine locurile libere din parcare in timp real.
 O lista timpiIntrare care contine timpii de intrare pentru fiecare masina care intra in parcare.

 Ideea programului principal este urmatoarea:

 Se citesc din fisier datele de intrare : numarul de telefon al posesorului masinii si timpul cat acesta sta in parcare.
 Se adauga in coada de asteptare masinile.
 In bucla de while principala se valideaza urmatoarea conditie: numarul de masini din parcare trebuie sa fie mai mic decat capacitatea maxima a parcarii.
 - se verifica daca coada de asteptare nu este goala si se proceseaza masinile care se afla in ea.
 - masinile intra in parcare pe rand in functie de disponibilitatea locurilor de parcare.
 - se afla masinile care stau mai putin de 5s (timpul maxim de stat in parcare).
 - se afla masinile care stau mai mult de 5s, iar posesorul masinii este anunat sa paraseasca parcarea.
 - masinile ies din parcare in functie de lucrurile mentionate mai sus.

 Acest proces se repeta pana cand coada de asteptare se goleste.
 In final, in timp ce se verifica daca Coada de astepare este goala, ies din parcare masinile care depasesc timpul presetat de 5 secunde si masinile care au timpul de ocupare al parcarii mai mic decat 5 secunde.

