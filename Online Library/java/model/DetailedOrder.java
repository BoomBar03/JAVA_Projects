package model;

public class DetailedOrder {

    private Long id;
    private Long bookId;
    private Long quantity;
    private Float price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d | Book ID: %d | Quantity: %d | Price: %.2f",
                getId(), getBookId(), getQuantity(), getPrice());
    }
}
