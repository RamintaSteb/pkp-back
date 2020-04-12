package main.items.Group.Service;

import main.items.Group.json.GroupView;
import main.items.Group.json.GroupViewForUpdate;

public interface GroupService {

    Long createNewGroup(GroupView groupView);

    void updateGroup(GroupViewForUpdate groupViewForUpdate);

    void deleteGroup(Long id);
}
