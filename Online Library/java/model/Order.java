package model;

public class Order {

    private Long id;
    private Long employeeId;
    private Long customerId;
    private Float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d | Employee ID: %d | Customer ID: %d | Total: %.2f", getId(), getEmployeeId(), getCustomerId(), getTotal());
    }
}
