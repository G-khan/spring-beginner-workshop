package dev.gokhana.demo.service;

import dev.gokhana.demo.entity.Mentee;
import dev.gokhana.demo.repository.MenteeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenteeServiceImpl implements MenteeService {

    private final MenteeRepository menteeRepository;

    public MenteeServiceImpl(MenteeRepository menteeRepository) {
        this.menteeRepository = menteeRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(MenteeServiceImpl.class);


    @Override
    public Mentee getMenteeById(Long id) {
        return menteeRepository.findById(id).orElseThrow(() -> new RuntimeException("Mentee not found"));
    }

    @Override
    public Mentee createMentee(Mentee menteeDTO) {
        var mentee = menteeRepository.save(menteeDTO);
        logger.info("Mentee created mentee: {}", mentee);
        return mentee;
    }

    @Override
    public Mentee updateMenteeById(Long id, Mentee mentee) {
        var menteeFromDb = menteeRepository.findById(id).orElseThrow(() -> new RuntimeException("Mentee not found"));
        menteeFromDb.setName(mentee.getName());
        menteeFromDb.setSurname(mentee.getSurname());
        menteeFromDb.setEmail(mentee.getEmail());
        menteeFromDb.setId(id);
        logger.info("Mentee updatding as: {}", menteeFromDb);
        return menteeRepository.save(menteeFromDb);
    }

    @Override
    public List<Mentee> getMenteeByName(String name) {
        logger.info("Mentee getting by name: {}", name);
        return menteeRepository.findByName(name);
    }

    @Override
    public void deleteMenteeById(Long id) {
        logger.info("Mentee deleting by id: {}", id);
        menteeRepository.deleteById(id);
    }

}
