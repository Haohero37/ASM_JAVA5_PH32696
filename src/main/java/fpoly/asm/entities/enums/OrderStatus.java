package fpoly.asm.entities.enums;

public enum OrderStatus {
    PENDING(0),
    PROCESSING(1),
    SHIPPED(2),
    DELIVERED(3),
    PARTIALLY_DELIVERED(4),
    CANCELLED(5),
    RETURNED(6),
    COMPLETED(7);
    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus fromValue(int value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.value == value) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }

}
