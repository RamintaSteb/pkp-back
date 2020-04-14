package main.items.WorkTime.json;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkTimeView {

        @NotNull
        private Long personId;

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime from;

        @NotNull
        private LocalTime to;

        @NotNull
        private boolean workFromHome;
}
