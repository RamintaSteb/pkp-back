package main.items.Group.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupDataView {

    private Long id;
    private String title;
    private String description;
    private String administratorFullName;
    private int numberOfMembers;
    private Long membersTasksNumber;
    private float membersTasksTime;
}
