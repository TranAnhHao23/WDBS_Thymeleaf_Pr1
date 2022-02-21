package controllers;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ICustomerService;

import java.util.ArrayList;

@Controller
@RequestMapping("/home")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView showList(){
        ModelAndView modelAndView = new ModelAndView("view");
        ArrayList<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView showDetail(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("view-detail");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("view");
        customerService.remove(id);
        ArrayList<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("create");
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("view");
        customer.setId(customerService.findAll().size()+1);
        customerService.save(customer);
        ArrayList<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("view");
        customerService.update(customer.getId()-1,customer);
        ArrayList<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}
