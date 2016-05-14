package com.tpokora.projects.user.todo;

import com.tpokora.projects.user.model.todo.TODOList;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pokor on 14.05.2016.
 */
public class TODOListTest {

    private static final Logger logger = Logger.getLogger(TODOListTest.class);

    private TODOList todoList;

    @Before
    public void setup() {
        todoList = new TODOList();
    }

    public void createTodoElements(int amount) {
        for (int i = 0; i < amount; i++) {
            todoList.createElement("TEST" + i);
            todoList.getElementByIndex(i).setId(i);
        }
    }

    /**
     * Tests if createElement creates new element in todolist
     */
    @Test
    public void test_createElement_and_getByIndex_0_elementFound() {
        todoList.createElement("TEST");
        Assert.assertTrue("Element not found.", todoList.getElementByIndex(0) != null);
    }



    /**
     * Test if removeElementById remove element
     */
    @Test
    public void test_removeElementById_1_elementNotFound() {
        createTodoElements(5);

        todoList.removeElementById(1);
        Assert.assertNull("Element should not exist", todoList.getElementById(1));
    }

    /**
     * Set element to checked by id
     */
    @Test
    public void test_setElementToCheckedById_2_elementChecked() {
        createTodoElements(5);

        todoList.setElementToChecked(2);
        Assert.assertTrue("Element is not checked", todoList.getElementById(2).isChecked());

    }
}
