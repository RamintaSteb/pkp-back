package main.items.Group.json;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupView {

    @NotNull
    private String title;

    @NotNull
    private String description;

    private Long administratorPersonId;

    private List<Long> personListIds;
}
