package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;


public class GridTest {
    final List<Integer> cord= new ArrayList<>(3);
    Grid grid;
    Stack stack;
    final String s= "check";
    StringJoiner temp;
    int tests =5;
    char charTab[];
    @Before
    public void setUp() throws Exception {
        cord.add(10);
        cord.add(3);
        cord.add(5);

        grid= new Grid(cord);
        String temp =s+s+s+s+s+s;
        charTab =temp.toCharArray();







    }
    @Test
    public void createGrid() {
        grid.createGrid(charTab);

        int[] tab = new int[]{0, 0};
        assertThat(grid.getNode(tab).getIndex()).isEqualTo('c');
        tab[0]=1;
        tab[1]=1;
        assertThat(grid.getNode(tab).getIndex()).isEqualTo('h');
        assertThat(grid.getNode(tab).getStack().getVector().get(4)).isEqualTo("");
        tab[0]=2;
        tab[1]=2;
        assertThat(grid.getNode(tab).getIndex()).isEqualTo('e');

    }

    @Test
    public void fillGrid() {
        grid.createGrid(charTab);
        String[][] input ={{"139","4","1","4"},
                        {"13","9","1","4"}};

        grid.fillGrid(input);
        assertThat(grid.getNode(new int[]{4, 1}).getStack().getVector().get(4)).isEqualTo("139");
        assertThat(grid.getNode(new int[]{9, 1}).getStack().getVector().get(4)).isEqualTo("13");

    }
}