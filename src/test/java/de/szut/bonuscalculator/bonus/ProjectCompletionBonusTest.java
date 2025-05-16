package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProjectCompletionBonusTest {

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

    @Test
    void shouldThrowExceptionWhenEmployeeIsNull() {
        Bonus wrappedComponent = mock(Bonus.class);
        Bonus decorator = new ProjectCompletionBonus(wrappedComponent);

        assertThatThrownBy(() -> decorator.calculateBonus(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldAdd1000BonusFor100CompletedProjects() {
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new ProjectCompletionBonus(wrappedComponent);

        Employee employee = Employee.builder()
                .completedProjects(100)
                .build();

        double bonus = decorator.calculateBonus(employee);

        assertThat(bonus).isEqualTo(1500.0); // 500 + (100 * 10)
    }

    @Test
    void shouldNotAddBonusWhenNoProjectsCompleted() {
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new ProjectCompletionBonus(wrappedComponent);

        Employee employee = Employee.builder()
                .completedProjects(0)
                .build();

        double bonus = decorator.calculateBonus(employee);

        assertThat(bonus).isEqualTo(500.0); // no bonus
    }

    @Test
    void shouldIgnoreNegativeCompletedProjects() {
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new ProjectCompletionBonus(wrappedComponent);

        Employee employee = Employee.builder()
                .completedProjects(-10)
                .build();

        double bonus = decorator.calculateBonus(employee);

        assertThat(bonus).isEqualTo(400.0);
    }
}
