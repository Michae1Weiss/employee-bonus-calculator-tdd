package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PerformanceBonusTest {
    @Test
    void shouldMultiplyBonusBasedOnPerformance() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(1000.0);

        Bonus decorator = new PerformanceBonus(wrappedComponent);

        // Fall 1: Niedrige Performance (0-3)
        Employee employee1 = Employee.builder().performanceRating(2).build();

        // Fall 2: Mittlere Performance (4-7)
        Employee employee2 = Employee.builder().performanceRating(6).build();

        // Fall 3: Hohe Performance (8-10)
        Employee employee3 = Employee.builder().performanceRating(9).build();

        // When
        double bonus1 = decorator.calculateBonus(employee1);
        double bonus2 = decorator.calculateBonus(employee2);
        double bonus3 = decorator.calculateBonus(employee3);

        // Then
        assertThat(bonus1).isEqualTo(500.0);  // 1000 * 0.5
        assertThat(bonus2).isEqualTo(1000.0); // 1000 * 1.0
        assertThat(bonus3).isEqualTo(1100.0); // 1000 * 1.1

        verify(wrappedComponent, times(3)).calculateBonus(any(Employee.class));
    }

    @Test
    void shouldThrowExceptionForNullEmployee() {
        // Given
        Bonus performanceBonus = new PerformanceBonus(new BasicBonus());
        // Then
        assertThrows(IllegalArgumentException.class, () -> performanceBonus.calculateBonus(null));
    }

    @Test
    void shouldThrowExceptionForPerformanceRatingBelowZero() {
        // Given
        Bonus performanceBonus = new PerformanceBonus(new BasicBonus());
        // Then
        assertThrows(IllegalArgumentException.class, () -> performanceBonus.calculateBonus(Employee.builder().performanceRating(-1).build()));
    }
}
