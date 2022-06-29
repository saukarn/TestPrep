package com.saurabh.covid19tracker.controllers;

import com.saurabh.covid19tracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    com.saurabh.covid19tracker.service.Covid19trackerDataService Covid19trackerDataService;
    @GetMapping({"/", "home"})
    public String home(Model model) {
        List<LocationStats> allstats = Covid19trackerDataService.getAllstats();
        int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getConfirmedCases()).sum();
        int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allstats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
