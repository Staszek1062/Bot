package org.example;

import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;


public class StackTest {
    private Stack stack1,stack2;
    final String string = "quot";
    final String check = "check";
    @Before
    public void setUp() throws Exception {
        stack1 = new Stack(10);
        stack2 = new Stack(10);

        for(int i=0;i<5;i++)
        stack1.push(string);

    }

    @Test
    @DisplayName("Check if empty")
    public void isEmpty() {

        assertThat(stack1.isEmpty()).isEqualTo(false);
        assertThat(stack2.isEmpty()).isEqualTo(true);

    }

    @Test
    @DisplayName("Adding to stack")
    public void push() {
        int x= stack1.top+1;

        stack1.push(check);
        assertThat(stack1.getVector().get(stack1.top)).isEqualTo(check);
        assertThat(stack1.top).isEqualTo(x);
    }

    @Test
    @DisplayName("Setting one element")
    public void setElementAt() {

    stack1.setElementAt(check+"s",0);

    assertThat(stack1.getVector().get(0)).isEqualTo("checks");

    stack1.setElementAt(check+"s",9);
    assertThat(stack1.getVector().get(9)).isEqualTo("checks");
    }

    @Test
    @DisplayName("Taking one element")
    public void pop() {

        assertThat(stack1.getVector().get(stack1.top)).isEqualTo(stack1.pop());
    }
}