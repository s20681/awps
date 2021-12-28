package com.pjatk.awps.controller;

import com.pjatk.awps.model.Group;
import com.pjatk.awps.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/group/")
public class GroupController {
    GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @PostMapping("/")
    public Group get(HttpSession httpSession, @RequestParam Long groupId){
        return groupService.get(httpSession, groupId);
    }

    @PostMapping("create")
    public Group create(HttpSession httpSession, @RequestBody Group group){
        return groupService.create(httpSession, group);
    }

    @PostMapping("/join")
    public Group join(HttpSession httpSession, @RequestParam Long groupId){
        return groupService.join(httpSession, groupId);
    }

    @PostMapping("set")
    public Group set(@RequestBody Group group){
        return groupService.save(group);
    }

    @GetMapping("get")
    public Group getSample(){
        return groupService.getSample();
    }

    @GetMapping("getlist")
    public List<Group> getList(){
        return groupService.getList();
    }


}
