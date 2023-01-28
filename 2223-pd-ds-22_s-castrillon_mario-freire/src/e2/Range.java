package e2;

public record Range(double MIN, double MAX) {

    public Range {
        if (MIN >= MAX) throw new IllegalArgumentException("MIN cannot be greater than MAX");
    }

    public boolean isInRange(double value) {
        return value > MIN && value < MAX;
    }

    public boolean belowRange(double value) {
        return value < MIN;
    }

    public boolean aboveRange(double value) {
        return value > MAX;
    }
}
