package dk.kea.adventureproject.controllers;

import dk.kea.adventureproject.models.Activity;
import dk.kea.adventureproject.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Daniel Westphal & Hans
 */
@Controller
public class AdventureController {
    @Autowired
    ActivityService activityService;

    @GetMapping("/")
    public String index(Model model) {
        List<Activity> activityList = activityService.readAllActivities();
        model.addAttribute("activities", activityList);
        return "index.html";
    }

    @GetMapping("/activityDetails/{activityID}")
    public String readActivityDetails(@PathVariable int activityID, Model model){
        model.addAttribute("activity", activityService.readActivityByID(activityID));
        return "/activityDetails";
    }

    @GetMapping("/createActivity")
    public String createActivity() {
        return "/createActivity";
    }

    @PostMapping("/createActivity")
    public String createActivity(@ModelAttribute Activity activity) {
        activityService.createNewActivity(activity);
        return "redirect:/";
    }

    @GetMapping("/updateActivity/{activityID}")
    public String updateActivity(@PathVariable("activityID") int activityID, Model model) {
        model.addAttribute("activity", activityService.readActivityByID(activityID));
        return "/updateActivity";
    }

    @PostMapping("/updateActivity")
    public String updateActivity(@ModelAttribute Activity activity) {
        activityService.updateActivity(activity.getActivityID(), activity);
        return "redirect:/";
    }

    @GetMapping("/deleteActivity/{activityID}")
    public String deleteActivity(@PathVariable("activityID") int activityID) {
        activityService.deleteActivity(activityID);
        return "redirect:/";
    }

    @GetMapping("/Gocart")
    public String renderGocart() {
        return "gocart.html";
    }

    @GetMapping("/Sumowrestling")
    public String renderSumo() {
        return "sumo.html";
    }

    @GetMapping("/Paintball")
    public String renderPaintball() {
        return "paintball.html";
    }

    @GetMapping("/Minigolf")
    public String renderMinigolf() {
        return "minigolf.html";
    }

    @GetMapping("/test")
    public String renderTest(Model model) {
        List<Activity> list = activityService.readAllActivities();
        model.addAttribute("list", list);
        return "test.html";
    }
}
