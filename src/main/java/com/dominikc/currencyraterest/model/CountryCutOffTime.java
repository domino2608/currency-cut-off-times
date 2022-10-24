package com.dominikc.currencyraterest.model;

import com.dominikc.currencyraterest.exception.ApiException;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class CountryCutOffTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String isoCode;

    private String country;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "today_id")
    private CutOffTime today;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tomorrow_id")
    private CutOffTime tomorrow;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "after_tomorrow_id")
    private CutOffTime afterTomorrow;

    public CountryCutOffTime() {
    }

    public CountryCutOffTime(String isoCode, String country) {
        this.isoCode = isoCode;
        this.country = country;
    }

    /**
     * Gets cut off time for number of days. 0 = today, 1 = tomorrow, anything > 1 is after tomorrow
     *
     * @param numberOfDays days number, 0 indicates today, 1 indicates tomorrow and anything bigger than 1 indicates after tommorow
     * @return cut off time for the provided day
     */
    public CutOffTime getCutOffTimeForDays(int numberOfDays) {
        if (numberOfDays < 0) {
            throw new ApiException("Something went wrong when getting cut off date.");
        }

        return switch (numberOfDays) {
            case 0 -> this.today;
            case 1 -> this.tomorrow;
            default -> this.afterTomorrow;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountryCutOffTime that = (CountryCutOffTime) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
