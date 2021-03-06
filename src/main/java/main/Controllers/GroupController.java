package main.Controllers;

import main.items.Group.Service.GroupService;
import main.items.Group.json.GroupDataView;
import main.items.Group.json.GroupView;
import main.items.Group.json.GroupViewForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/createGroup")
    public Long addNewGroup(@RequestBody GroupView groupView) {
        return groupService.createNewGroup(groupView);
    }

    @PostMapping("/updateGroup")
    public void updateGroup(@RequestBody GroupViewForUpdate groupView) {
        groupService.updateGroup(groupView);
    }

    @PostMapping("/delete")
    public void deleteGroup(@RequestBody Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping("/group")
    public GroupDataView getGroupData(@RequestParam Long id) {
        return groupService.getGroupData(id);
    }

    @GetMapping("/getAllGroups")
    public List<GroupDataView> getAllGroups() {
        return groupService.findAllGroups();
    }
}
