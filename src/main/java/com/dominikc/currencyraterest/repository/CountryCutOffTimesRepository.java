package com.dominikc.currencyraterest.repository;

import com.dominikc.currencyraterest.model.CountryCutOffTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryCutOffTimesRepository extends JpaRepository<CountryCutOffTime, Long> {

    Optional<CountryCutOffTime> findOneByIsoCode(String isoCode);
}
