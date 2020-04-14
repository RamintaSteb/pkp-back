package main.items.WorkTime.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkTimeDataView {

    private Long id;
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;
    private boolean workFromHome;
}
