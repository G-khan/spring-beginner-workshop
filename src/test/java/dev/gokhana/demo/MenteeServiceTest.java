package dev.gokhana.demo;

import dev.gokhana.demo.entity.Mentee;
import dev.gokhana.demo.repository.MenteeRepository;
import dev.gokhana.demo.service.MenteeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenteeServiceTest {

    private MenteeServiceImpl menteeService;

    @Mock
    private MenteeRepository menteeRepositoryMock;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menteeService = new MenteeServiceImpl(menteeRepositoryMock);
    }

    @Test
    void testGetMenteeById() {
        Long menteeId = 1L;
        Mentee mentee = new Mentee();
        mentee.setId(menteeId);
        when(menteeRepositoryMock.findById(menteeId)).thenReturn(Optional.of(mentee));

        Mentee result = menteeService.getMenteeById(menteeId);

        assertEquals(mentee, result);
        verify(menteeRepositoryMock, times(1)).findById(menteeId);
    }

    @Test
    void testCreateMentee() {
        Mentee mentee = new Mentee(null, "John", "Doe", "john.doe@example.com");
        Mentee savedMentee = new Mentee(1L, "John", "Doe", "john.doe@example.com");
        when(menteeRepositoryMock.save(mentee)).thenReturn(savedMentee);

        Mentee result = menteeService.createMentee(mentee);

        assertEquals(savedMentee, result);
    }

    @Test
    void testUpdateMenteeById() {
        Long menteeId = 1L;
        Mentee mentee = new Mentee(menteeId, "John", "Doe", "john.doe@example.com");
        when(menteeRepositoryMock.findById(menteeId)).thenReturn(Optional.of(mentee));
        when(menteeRepositoryMock.save(mentee)).thenReturn(mentee);

        Mentee updatedMentee = menteeService.updateMenteeById(menteeId, mentee);

        assertEquals(mentee, updatedMentee);
    }

    @Test
    void testGetMenteeByName() {
        String name = "John";
        Mentee mentee = new Mentee(1L, "John", "Doe", "john.doe@example.com");
        List<Mentee> expectedMentees = Collections.singletonList(mentee);
        when(menteeRepositoryMock.findByName(name)).thenReturn(expectedMentees);

        List<Mentee> result = menteeService.getMenteeByName(name);

        assertEquals(expectedMentees, result);
    }

    @Test
    void testDeleteMenteeById() {
        Long menteeId = 1L;

        assertDoesNotThrow(() -> menteeService.deleteMenteeById(menteeId));
        verify(menteeRepositoryMock, times(1)).deleteById(menteeId);
    }

}
