package main.items.WorkTime.json;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class WorkTimeListView {

    private List<WorkTimeView> workTimeLists;
}
