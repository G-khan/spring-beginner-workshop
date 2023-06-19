package dev.gokhana.demo.service;

import dev.gokhana.demo.entity.Mentee;

import java.util.List;

public interface MenteeService {
    Mentee getMenteeById(Long id);

    Mentee createMentee(Mentee menteeDTO);

    Mentee updateMenteeById(Long id, Mentee mentee);

    List<Mentee> getMenteeByName(String name);

    void deleteMenteeById(Long id);
}
