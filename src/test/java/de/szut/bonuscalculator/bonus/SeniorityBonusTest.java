package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SeniorityBonusTest {
    @Test
    void shouldAddSeniorityBonusBasedOnYearsOfService() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new SeniorityBonus(wrappedComponent);

        // Fall 1: 5 Jahre Betriebszugehörigkeit (5 * 10 = 50)
        Employee employee1 = Employee.builder().yearsOfService(5).build();

        // Fall 2: 12 Jahre (10 Jahre á 10€ + 2 Jahre á 20€ = 100 + 40 = 140)
        Employee employee2 = Employee.builder().yearsOfService(12).build();

        // When
        double bonus1 = decorator.calculateBonus(employee1);
        double bonus2 = decorator.calculateBonus(employee2);

        // Then
        assertThat(bonus1).isEqualTo(550.0); // 500 + 50
        assertThat(bonus2).isEqualTo(640.0); // 500 + 140

        verify(wrappedComponent, times(2)).calculateBonus(any(Employee.class));
    }
}
