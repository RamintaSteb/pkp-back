package main.items.WorkTime.json;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkTimeView {

        @NotNull
        private long person_id;

        @NotNull
        private LocalDate work_date;

        @NotNull
        private Time works_from;

        @NotNull
        private Time works_to;

        @NotNull
        private boolean is_working_from_home;
}
