import java.util.Comparator;

public class MpgComparator implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle v1, Vehicle v2) {
        // Compare vehicles based on their MPG
        return Double.compare(v1.getMpg(), v2.getMpg());
    }
}