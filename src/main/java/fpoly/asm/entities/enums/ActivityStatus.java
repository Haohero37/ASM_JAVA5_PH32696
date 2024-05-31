package fpoly.asm.entities.enums;

public enum ActivityStatus {
    ACTIVE(1),
    INACTIVE(0);
    private final int value;

    ActivityStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ActivityStatus fromValue(int value) {
        for (ActivityStatus activityStatus : ActivityStatus.values()) {
            if (activityStatus.value == value) {
                return activityStatus;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }

    public static void main(String[] args) {
        ActivityStatus activityStatus = ActivityStatus.INACTIVE;
        System.out.println(activityStatus.value);
    }
}
