public class BarieraIesire {
    protected boolean stator1;
    protected boolean stator2;
    protected float teta;

    public BarieraIesire(boolean stator1, boolean stator2, float teta) {
        this.stator1 = stator1;
        this.stator2 = stator2;
        this.teta = teta;
    }

    public boolean isStator1() {
        return stator1;
    }

    public void setStator1(boolean stator1) {
        this.stator1 = stator1;
    }

    public boolean isStator2() {
        return stator2;
    }

    public void setStator2(boolean stator2) {
        this.stator2 = stator2;
    }

    public float getTeta() {
        return teta;
    }

    public void setTeta(float teta) {
        this.teta = teta;
    }
    protected void ridicareBariera() {
        System.out.println("Bariera de iesire a fost ridicata  ");
    }
    protected void coborareBariera() {
        System.out.println("Bariera de iesire  a fost coborata ");
    }
    protected void intrerupereStator1() {
        System.out.println("Se intrerupe alimentarea infasurarii statorului 1");
        this.setStator1(false);
    }
    protected void alimentareStator2() {
        System.out.println("Se porneste alimentarea statorului 2");
        this.setStator2(true);
    }
    protected void intrerupereStator2() {
        System.out.println("Se intrerupe alimentarea infasurarii statorului 2 ");
        this.setStator2(false);
    }
    protected void alimentareStator1() {
        System.out.println("Se porneste alimentarea infasurarii statorului 1");
        this.setStator1(true);
    }
    protected void unghiTeta90() {
        this.setTeta(90);
        System.out.println("Unghiul teta devine " + this.getTeta());
    }
    protected void unghiTeta0() {
        this.setTeta(0);
        System.out.println("Unghiul teta devine " + this.getTeta());
    }
}
