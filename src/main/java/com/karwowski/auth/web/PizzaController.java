package com.karwowski.auth.web;

import com.karwowski.auth.model.Cake;
import com.karwowski.auth.model.Pizza;
import com.karwowski.auth.model.Size;
import com.karwowski.auth.model.User;
import com.karwowski.auth.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/addPizza", method = RequestMethod.GET)
    public String addPizza(Model model,String error) {
        if (error != null) {
            model.addAttribute("error", "We have pizza with the same name.");
        }
        model.addAttribute("pizzaForm", new Pizza());
        return "addPizza";
    }
    @RequestMapping(value = "/addPizza", method = RequestMethod.POST)
    public String addPizza(@ModelAttribute("pizzaForm") Pizza pizzaForm, Model model) {
        Pizza pizzaFromRepo = pizzaService.findByName(pizzaForm.getName());
        if (pizzaFromRepo!=null){
            model.addAttribute("error", "We have pizza with the same name.");
            return "redirect:/addPizza";
        }else {
            pizzaForm.setCake(Cake.THICK_TRUST);
            pizzaForm.setSize(Size.LARGE);
            pizzaService.save(pizzaForm);
            return "redirect:/pizzas";
        }
    }
    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.GET)
    public String updatePizza(@PathVariable Long id, Model model) {
        Pizza pizza = pizzaService.findById(id);
        model.addAttribute("pizzaForm", pizza);
        return "editPizza";
    }
    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.POST)
    public String updatePizza(@ModelAttribute("pizzaForm") Pizza pizzaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editPizza";
        }
        pizzaService.save(pizzaForm);
        return "redirect:/pizzas";
    }
    @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public String allPizzas(Model model) {
        List<Pizza> allPizzas = pizzaService.allPizzas();
        model.addAttribute("pizzas",allPizzas);
        return "pizzas";
    }
}
