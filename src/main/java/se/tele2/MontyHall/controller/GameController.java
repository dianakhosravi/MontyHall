package se.tele2.MontyHall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.tele2.MontyHall.common.CommonConstant;
import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.service.GameService;

import java.util.Map;

@Slf4j
@Controller
public class GameController {

    @Autowired
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String showWelcomePage() {

        return "welcomePage";
    }

    @GetMapping("/result")
    public String showTheResult(@RequestParam(value = "times") String times, Model model)
            throws InvalidContentException {
        int timesValue;
        try {
            timesValue = Integer.parseInt(times);
        } catch (NumberFormatException e) {
            log.error("input value {} cant be a number ", times, e.getCause());
            model.addAttribute("errorMessage", "Times should be an integer");
            return "errorPage";
        }
        if (timesValue < 1) {
            log.error("times is less then 1, {} ", timesValue);
            model.addAttribute("errorMessage", "Times should be more than zero");
            return "errorPage";
        }

        Map<String, Double> simulate = gameService.simulate(timesValue);

        model.addAttribute("times", timesValue);
        Double c_s = simulate.get(CommonConstant.CHANGE_CHOICE_SUCCESS);
        Double c_f = simulate.get(CommonConstant.CHANGE_CHOICE_FAIL);
        Double k_s = simulate.get(CommonConstant.KEEP_CHOICE_SUCCESS);
        Double k_f = simulate.get(CommonConstant.KEEP_CHOICE_FAIL);

        model.addAttribute("isChangeSuccess", c_s > k_s);
        model.addAttribute("isKeepSuccess", k_s > c_s);

        model.addAttribute("change_success", c_s);
        model.addAttribute("change_fail", c_f);
        model.addAttribute("keep_success", k_s);
        model.addAttribute("keep_fail", k_f);

        return "resultPage";
    }
}
