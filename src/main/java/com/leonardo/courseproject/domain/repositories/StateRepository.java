package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
