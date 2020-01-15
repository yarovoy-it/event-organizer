package by.home.eventOrganizer.model.human;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Customer.
 */
@Entity
@Table(name = "customers")
public class Customer extends Person{

    private Integer discount;

    private Double summa;

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * Gets summa.
     *
     * @return the summa
     */
    public Double getSumma() {
        return summa;
    }

    /**
     * Sets summa.
     *
     * @param summa the summa
     */
    public void setSumma(Double summa) {
        this.summa = summa;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "discount=" + discount +
                ", summa=" + summa +
                "} " + super.toString();
    }
}
