package com.dominikc.currencyraterest.repository;

import com.dominikc.currencyraterest.model.CutOffTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CutOffTimeRepository extends JpaRepository<CutOffTime, Long> {
}
