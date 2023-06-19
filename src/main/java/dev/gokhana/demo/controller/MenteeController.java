package dev.gokhana.demo.controller;

import dev.gokhana.demo.entity.Mentee;
import dev.gokhana.demo.service.MenteeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentees")
public class MenteeController {

    private final MenteeService menteeService;
    @Autowired
    public MenteeController(MenteeService menteeService) {
        this.menteeService = menteeService;
    }


    @GetMapping("/greetings")
    @ResponseBody
    public String getGreetings(){
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<Mentee> addMentee(@RequestBody Mentee mentee){
        var savedMentee = menteeService.createMentee(mentee);
        return ResponseEntity.ok(savedMentee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentee> getMentee(@PathVariable Long id){
        var mentee = menteeService.getMenteeById(id);
        return ResponseEntity.ok(mentee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentee(@PathVariable Long id){
        menteeService.deleteMenteeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentee> updateMentee(@PathVariable Long id, @RequestBody Mentee mentee){
        var updatedMentee = menteeService.updateMenteeById(id, mentee);
        return ResponseEntity.ok(updatedMentee);
    }

    @GetMapping()
    public ResponseEntity<List<Mentee>> getMenteeByName(@RequestParam String name){
        var mentees = menteeService.getMenteeByName(name);
        return ResponseEntity.ok(mentees);
    }
}
