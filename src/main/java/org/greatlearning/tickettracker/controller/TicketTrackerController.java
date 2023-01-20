package org.greatlearning.tickettracker.controller;

import java.util.List;

import org.greatlearning.tickettracker.entity.TicketTrackerEntity;
import org.greatlearning.tickettracker.services.TicketService;
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
public class TicketTrackerController {

	// injecting ticket serviceimpl
	@Autowired
	private TicketService ticketServiceImpl;

	// index page
	@GetMapping("/tickets")
	public String mainPage(Model theModel) {

		List<TicketTrackerEntity> ticket = ticketServiceImpl.getAllTickets();
		theModel.addAttribute("ticket", ticket);
		return "index";
	}

	// new ticket page
	@GetMapping("/newPage")
	public String newTicketPage() {
		return "newPage";
	}

	// adding new data
	@PostMapping("/create")
	public String createTicket(@ModelAttribute TicketTrackerEntity t) {
		System.out.println(t);

		ticketServiceImpl.addTicket(t);
		return "redirect:/tickets";

	}

	// viewing the edit page
	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable int id, Model themodel) {

		TicketTrackerEntity t = ticketServiceImpl.getTicketById(id);
		themodel.addAttribute("ticket", t);
		return "editPage";

	}

	// updating the changes
	@PostMapping("/update")
	public String updateTicket(@ModelAttribute TicketTrackerEntity t) {

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
	public String viewPage(@PathVariable int id, Model theModel) {

		TicketTrackerEntity ticket = ticketServiceImpl.getTicketById(id);
		theModel.addAttribute("ticket", ticket);
		return "viewPage";

	}

	// going back to index page
	@PostMapping("/submit")
	public String submitTicket(@ModelAttribute TicketTrackerEntity t) {

		return "redirect:/tickets";

	}

	// search functionality
	@GetMapping(path = { "/search" })
	public String home(TicketTrackerEntity ticket, Model model, String keyword) {
		if (keyword != null) {
			List<TicketTrackerEntity> ticket1 = ticketServiceImpl.getByKeyword(keyword);
			model.addAttribute("ticket", ticket1);
		} else {
			List<TicketTrackerEntity> list = ticketServiceImpl.getAllTickets();
			model.addAttribute("list", list);
		}
		return "index";
	}
}
