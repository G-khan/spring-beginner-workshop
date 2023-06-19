package dev.gokhana.demo.repository;

import dev.gokhana.demo.entity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenteeRepository  extends JpaRepository<Mentee, Long> {
    List<Mentee> findByName(String name);
}
