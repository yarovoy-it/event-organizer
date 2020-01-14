package by.home.eventOrganizer.model.human;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends Person{

    private Integer discount;

    private Double summa;

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getSumma() {
        return summa;
    }

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
