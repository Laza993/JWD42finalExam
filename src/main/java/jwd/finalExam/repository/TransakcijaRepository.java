package jwd.finalExam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.finalExam.model.Transakcija;

@Repository
public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {

}
