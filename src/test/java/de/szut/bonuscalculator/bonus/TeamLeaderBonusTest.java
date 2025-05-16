package de.szut.bonuscalculator.bonus;

import de.szut.bonuscalculator.model.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class TeamLeaderBonusTest {
    @Test
    void shouldAddBonusForTeamLeader() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new TeamLeaderBonus(wrappedComponent);

        // Fall 1: Teamleiter
        Employee teamLeader = Employee.builder()
                .isTeamLeader(true)
                .build();

        // Fall 2: Kein Teamleiter
        Employee nonTeamLeader = Employee.builder()
                .isTeamLeader(false)
                .build();

        // When
        double bonusTeamLeader = decorator.calculateBonus(teamLeader);
        double bonusNonTeamLeader = decorator.calculateBonus(nonTeamLeader);

        // Then
        assertThat(bonusTeamLeader).isEqualTo(520.0); // 500 + 20
        assertThat(bonusNonTeamLeader).isEqualTo(500.0); // unverändert

        verify(wrappedComponent, times(2)).calculateBonus(any(Employee.class));
    }

    @Test
    void givenNullTeamleader_wennCalculateBonus_thenIllegalArgumentException() {
        // Given
        Bonus wrappedComponent = mock(Bonus.class);
        when(wrappedComponent.calculateBonus(any(Employee.class))).thenReturn(500.0);

        Bonus decorator = new TeamLeaderBonus(wrappedComponent);

        // Fall 1: Teamleiter
        Employee teamLeader = Employee.builder()
                .build();

        // When
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    double bonusNonTeamLeader = decorator.calculateBonus(teamLeader);
                }).withMessageContaining("isTeamLeader boolean is null");

        verify(wrappedComponent, times(1)).calculateBonus(any(Employee.class));
    }
}
