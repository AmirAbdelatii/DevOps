package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.repositories.OperatorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OperatorServiceImplTest {

    @InjectMocks
    OperatorServiceImpl operatorService;

    @Mock
    OperatorRepository operatorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllOperators() {
        // Mock the repository to return a list of operators
        List<Operator> operators = new ArrayList<>();
        when(operatorRepository.findAll()).thenReturn(operators);

        List<Operator> result = operatorService.retrieveAllOperators();
        assertEquals(operators, result);
    }

    @Test
    public void testAddOperator() {
        Operator operatorToAdd = new Operator();
        when(operatorRepository.save(any(Operator.class))).thenReturn(operatorToAdd);

        Operator result = operatorService.addOperator(operatorToAdd);
        assertEquals(operatorToAdd, result);
    }

    @Test
    public void testDeleteOperator() {
        Long operatorId = 1L;

        // Mock the repository to do nothing when delete is called
        operatorService.deleteOperator(operatorId);

        Mockito.verify(operatorRepository).deleteById(operatorId);
    }

    @Test
    public void testUpdateOperator() {
        Operator operatorToUpdate = new Operator();
        when(operatorRepository.save(any(Operator.class))).thenReturn(operatorToUpdate);

        Operator result = operatorService.updateOperator(operatorToUpdate);
        assertEquals(operatorToUpdate, result);
    }

    @Test
    public void testRetrieveOperator() {
        Long operatorId = 1L;
        Operator operator = new Operator();
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.of(operator));

        Operator result = operatorService.retrieveOperator(operatorId);
        assertEquals(operator, result);
    }

    @Test
    public void testRetrieveOperatorNotFound() {
        Long operatorId = 1L;
        when(operatorRepository.findById(operatorId)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> operatorService.retrieveOperator(operatorId));
    }
}
