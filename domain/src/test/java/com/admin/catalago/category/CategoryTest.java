package com.admin.catalago.category;

import com.admin.catalago.exception.DomainException;
import com.admin.catalago.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAnvalidParams_whenCallNewCategory_thenInstantiateCategory(){
        final var  expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), expectedName);
        Assertions.assertEquals(actualCategory.isActive(), expectedIsActive);
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenReceiveError(){
        final String  expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMsg = "name should not be null";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMsg,actualException.getErrors().get(0).msg());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenReceiveError(){
        final String  expectedName = "  ";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMsg = "name should not be empty";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMsg,actualException.getErrors().get(0).msg());
    }

    @Test
    public void givenAnInvalidNameLenghtLessThan3_whenCallNewCategoryAndValidate_thenReceiveError(){
        final String  expectedName = "ra ";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMsg = "'name' must between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMsg,actualException.getErrors().get(0).msg());
    }

    @Test
    public void givenAnInvalidNameLenghtLessThan255_whenCallNewCategoryAndValidate_thenReceiveError(){
        final String  expectedName = "A n??vel organizacional, o consenso sobre a necessidade de qualifica????o maximiza as possibilidades por conta do fluxo de informa????es." +
                "Podemos j?? vislumbrar o modo pelo qual a consulta aos diversos militantes assume importantes posi????es no estabelecimento do retorno esperado a longo prazo." +
                "O cuidado em identificar pontos cr??ticos na consolida????o das estruturas auxilia a prepara????o e a composi????o do levantamento das vari??veis envolvidas.";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMsg = "'name' must between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount,actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMsg,actualException.getErrors().get(0).msg());
    }

    @Test
    public void givenAnvalidEmptyDescription_whenCallNewCategory_thenReceiveCategory(){
        final var  expectedName = "Filmes";
        final var expectedDescription = " ";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        Assertions.assertDoesNotThrow(() ->actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), expectedName);
        Assertions.assertEquals(actualCategory.isActive(), expectedIsActive);
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());

    }

    @Test
    public void givenAnvalidFalseIsActive_whenCallNewCategory_thenReceiveCategory(){
        final var  expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida ";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,expectedIsActive);

        Assertions.assertDoesNotThrow(() ->actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), expectedName);
        Assertions.assertEquals(actualCategory.isActive(), expectedIsActive);
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());

    }
    @Test
    public void givenAnValidCategory_whenDeactivate_thenReturnCategoryInactivated(){
        final var  expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida ";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,true);

        final var  updatedAt = actualCategory.getUpdatedAt();

        Assertions.assertTrue(actualCategory.isActive());
        Assertions.assertNull(actualCategory.getDeletedAt());

        final var aCategory = actualCategory.deactivate();

        Assertions.assertDoesNotThrow(() ->actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), expectedName);
        Assertions.assertEquals(aCategory.isActive(), expectedIsActive);
        Assertions.assertNotNull(aCategory.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(aCategory.getDeletedAt());
    }

    @Test
    public void givenAnValidInactiveCategory_whenDeactivate_thenReturnCategoryActivated(){
        final var  expectedName = "Filmes";
        final var expectedDescription = "A Categoria mais assistida ";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName,
                expectedDescription,false);

        final var  updatedAt = actualCategory.getUpdatedAt();

        Assertions.assertFalse(actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getDeletedAt());

        final var aCategory = actualCategory.activated();

        Assertions.assertDoesNotThrow(() ->actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(actualCategory.getId(), aCategory.getId());
        Assertions.assertEquals(actualCategory.getName(), expectedName);
        Assertions.assertEquals(aCategory.isActive(), expectedIsActive);
        Assertions.assertNotNull(aCategory.getCreatedAt());
        Assertions.assertTrue(aCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(aCategory.getDeletedAt());
    }
}
