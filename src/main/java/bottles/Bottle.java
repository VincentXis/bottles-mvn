package bottles;

public class Bottle {
    private int maxCapacity;
    private int currentVolume;

    public Bottle(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentVolume = 0;
    }

    // ---- Extracted Logic for readability---- //
    private boolean isFillable() {
        return this.maxCapacity > currentVolume;
    }

    private boolean isValidAmount(int amount) {
        return amount > 0;
    }

    // ------------ Getters ------------ //
    public int getRemainingCapacity() {
        return this.maxCapacity - currentVolume;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public int getCurrentVolume() {
        return this.currentVolume;
    }

    // ------------ Setter ------------ //
    private void setCurrentVolume(int newVolume) {
        this.currentVolume = newVolume;
    }

    // ------- Bottle Actions ------- //
    public void fill(int amount) {
        if (isFillable() && isValidAmount(amount)) {
            if (amount > getMaxCapacity()) {
                setCurrentVolume(getMaxCapacity());
            } else {
                if (amount + getCurrentVolume() > getMaxCapacity()) {
                    setCurrentVolume(getMaxCapacity());
                } else {
                    setCurrentVolume(getCurrentVolume() + amount);
                }
            }
        }
    }

    public int pour(int targetRemainingCapacity) {
        int newVolume = getCurrentVolume();
        if (targetRemainingCapacity >= getCurrentVolume()) {
            setCurrentVolume(0);
            return newVolume;
        } else {
            newVolume = getCurrentVolume() - targetRemainingCapacity;
            setCurrentVolume(newVolume);
            return targetRemainingCapacity;
        }
    }

    public void empty() {
        setCurrentVolume(0);
    }

    // ----------- status ------------ //
    public String getStatus() {
        return String.format("-\nmax litre: %d,  current litre: %d", getMaxCapacity(), getCurrentVolume());
    }
}
