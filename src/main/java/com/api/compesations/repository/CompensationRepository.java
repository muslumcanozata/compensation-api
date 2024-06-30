package com.api.compesations.repository;

import com.api.compesations.domain.entity.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, Long>, JpaSpecificationExecutor<Compensation> {}