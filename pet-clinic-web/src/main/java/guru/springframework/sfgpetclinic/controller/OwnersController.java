package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnersController {

  private final OwnerService service;

  public OwnersController(OwnerService service) {
    this.service = service;
  }

  @RequestMapping({"", "/", "/index",  "/index.html"})
  public String listOwners(Model model) {
    model.addAttribute("owners", service.findAll());
    return "owners/index";
  }

}
