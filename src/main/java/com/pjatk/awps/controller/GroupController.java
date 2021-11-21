package com.pjatk.awps.controller;

import com.pjatk.awps.model.Group;
import com.pjatk.awps.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group/")
public class GroupController {
    GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("set")
    public Group set(@RequestBody Group group){
        return groupService.save(group);
    }

    @GetMapping("get")
    public Group get(){
        return groupService.getSample();
    }
}
