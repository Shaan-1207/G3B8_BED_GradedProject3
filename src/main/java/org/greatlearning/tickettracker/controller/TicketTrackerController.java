package org.greatlearning.tt.controller;

import java.util.List;

import org.greatlearning.tt.entity.Ticket;
import org.greatlearning.tt.services.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class TicketController {

	// injecting ticket serviceimpl
	@Autowired
	private TicketServiceImpl ticketServiceImpl;

	// index page
	@GetMapping("/tickets")
	public String mainPage(Model theModel) {

		List<Ticket> ticket = ticketServiceImpl.getAllTickets();
		theModel.addAttribute("ticket", ticket);
		return "index";
	}

	// new ticket page
	@GetMapping("/newTicket")
	public String newTicketPage() {
		return "newTicket";
	}

	// adding new data
	@PostMapping("/create")
	public String createTicket(@ModelAttribute Ticket t) {
		System.out.println(t);

		ticketServiceImpl.addTicket(t);
		return "redirect:/tickets";

	}

	// viewing the edit page
	@GetMapping("/edit/{id}")
	public String editTicket(@PathVariable int id, Model themodel) {

		Ticket t = ticketServiceImpl.getTicketById(id);
		themodel.addAttribute("ticket", t);
		return "editTicket";

	}

	// updating the changes
	@PostMapping("/update")
	public String updateTicket(@ModelAttribute Ticket t) {

		ticketServiceImpl.addTicket(t);
		return "redirect:/tickets";

	}

	// delete ticket
	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable int id, Model themodel) {
		ticketServiceImpl.delTicket(id);
		return "redirect:/tickets";
	}

	// viewing ticket
	@GetMapping("/view/{id}")
	public String viewTicket(@PathVariable int id, Model theModel) {

		Ticket ticket = ticketServiceImpl.getTicketById(id);
		theModel.addAttribute("ticket", ticket);
		return "viewTicket";

	}

	// going back to index page
	@PostMapping("/submit")
	public String submitTicket(@ModelAttribute Ticket t) {

		return "redirect:/tickets";

	}

	// search functionality
	@GetMapping(path = { "/search" })
	public String home(Ticket ticket, Model model, String keyword) {
		if (keyword != null) {
			List<Ticket> ticket1 = ticketServiceImpl.getByKeyword(keyword);
			model.addAttribute("ticket", ticket1);
		} else {
			List<Ticket> list = ticketServiceImpl.getAllTickets();
			model.addAttribute("list", list);
		}
		return "index";
	}
}
