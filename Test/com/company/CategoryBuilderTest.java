package com.company;

import com.company.model.Category;
import com.company.model.Node;
import com.company.helpers.CategoryBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class CategoryBuilderTest {

    CategoryBuilder testeObject = new CategoryBuilder();

    @Test
    public void shouldReturnTrueIfRootIsCreated(){
        assertThat(testeObject.getBuilder().isRoot()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfListIsNotEmpty(){
        assertThat(testeObject.getBuilder().getChildren().isEmpty()).isFalse();
    }

    @Test
    public void shouldBeEqualIfElectronicCategoryExist(){
        List<Node<Category>> testedList = testeObject.getBuilder().getChildren();

        assertThat(testedList.get(0).getItem().getName()).isEqualTo("Electronics");
    }

    @Test
    public void shouldBeEqualIfMotorsCategoryExist(){
        List<Node<Category>> testedList = testeObject.getBuilder().getChildren();

        assertThat(testedList.get(1).getItem().getName()).isEqualTo("Motors");
    }
}
