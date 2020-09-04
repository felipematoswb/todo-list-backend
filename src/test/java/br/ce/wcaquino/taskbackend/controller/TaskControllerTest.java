package br.ce.wcaquino.taskbackend.controller;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void naoDeveSalvaTarefaSemDescricao() {

        final Task todo = new Task();
        // todo.setTask("Descricao");
        todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto!");
        } catch (final ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }

    }

    public void naoDeveSalvaTarefaSemData() {

        final Task todo = new Task();
        todo.setTask("Descricao");
        // todo.setDueDate(LocalDate.now());
        try {
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto!");
        } catch (final ValidationException e) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    public void naoDeveSalvaTarefaComDataPassada() {

        final Task todo = new Task();
        todo.setTask("Descricao");
        todo.setDueDate(LocalDate.of(2010, 01, 01));
        try {
            controller.save(todo);
            Assert.fail("Não deveria chegar nesse ponto!");
        } catch (final ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    public void DeveSalvaTarefaComSucesso() throws ValidationException {

        final Task todo = new Task();
        todo.setTask("Descricao");
        todo.setDueDate(LocalDate.now());
        controller.save(todo);

        Mockito.verify(taskRepo).save(todo);
        
    }
}