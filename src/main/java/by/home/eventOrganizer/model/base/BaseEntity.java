package by.home.eventOrganizer.model.base;


import javax.persistence.*;


/**
 * The type Base entity.
 */
@MappedSuperclass
@SequenceGenerator(name = "sequence", sequenceName = "sequence_base", initialValue = 110)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
