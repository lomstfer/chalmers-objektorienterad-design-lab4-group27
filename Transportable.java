public interface Transportable {
    void transport(double x, double y);
    boolean getBeingTransported();
    void setBeingTransported(boolean b);
    double getPositionX();
    double getPositionY();
}
