package main.items.Group.Service;

import main.items.Group.json.GroupDataView;
import main.items.Group.json.GroupView;
import main.items.Group.json.GroupViewForUpdate;

import java.util.List;

public interface GroupService {

    Long createNewGroup(GroupView groupView);

    void updateGroup(GroupViewForUpdate groupViewForUpdate);

    void deleteGroup(Long id);

    GroupDataView getGroupData(Long id);

    List<GroupDataView> findAllGroups();
}
