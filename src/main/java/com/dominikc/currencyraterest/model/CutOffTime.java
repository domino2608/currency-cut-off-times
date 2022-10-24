package com.dominikc.currencyraterest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

import static com.dominikc.currencyraterest.model.CutOffTimeStaticValue.*;

@Getter
@Setter
@Entity
public class CutOffTime implements Comparable<CutOffTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CutOffTimeStaticValue staticValue; // TODO optional?

    private LocalTime time;

    protected CutOffTime() {
    }

    private CutOffTime(LocalTime localTime) {
        this.time = localTime;
    }

    private CutOffTime(CutOffTimeStaticValue staticValue) {
        this.staticValue = staticValue;
    }

    public static CutOffTime fromTime(int hour, int minutes) {
        return new CutOffTime(LocalTime.of(hour, minutes));
    }

    public static CutOffTime fromHour(int hour) {
        return fromTime(hour, 0);
    }

    public static CutOffTime neverPossible() {
        return new CutOffTime(NEVER_POSSIBLE);
    }

    public static CutOffTime alwaysPossible() {
        return new CutOffTime(ALWAYS_POSSIBLE);
    }

    /**
     * Returns earlier cut off time comparing it with other cut off time
     * @param other the other cut off time to compare with
     * @return earlier cut off time
     */
    public CutOffTime getEarlierCutOffTime(CutOffTime other) {
        if (this.compareTo(other) < 0) {
            return this;
        }

        if (this.compareTo(other) > 0) {
            return other;
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CutOffTime that = (CutOffTime) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        if (staticValue != null) {
            return staticValue.toString();
        }

        return time.toString();
    }

    @Override
    public int compareTo(CutOffTime other) { // TODO
        if (this.equals(other)) return 0;

        if (this.staticValue == NEVER_POSSIBLE) return -1;

        if (this.staticValue == ALWAYS_POSSIBLE) return 1;

        if (other.staticValue == NEVER_POSSIBLE) return 1;

        if (other.staticValue == ALWAYS_POSSIBLE) return -1;

        return this.time.compareTo(other.time);
    }
}
