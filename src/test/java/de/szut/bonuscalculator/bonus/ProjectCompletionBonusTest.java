package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProjectCompletionBonusDecoratorTest {

    @Test
    void shouldAddBonusBasedOnCompletedProjects() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new ProjectCompletionBonus(wrappedComponent);

        Employee employee = Employee.builder()
                .completedProjects(5)
                .build();

        // When
        double bonus = decorator.calculateBonus(employee);

        // Then
        assertThat(bonus).isEqualTo(550.0); // 500 + (5 * 10)
        verify(wrappedComponent).calculateBonus(employee);
    }
}
