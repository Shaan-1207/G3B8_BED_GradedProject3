package org.greatlearning.tt.services;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Optional;

import org.greatlearning.tt.entity.Ticket;
import org.greatlearning.tt.repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImpl implements Service {

	// autowiring the ticketrepo class
	@Autowired
	private TicketRepo ticketRepo;

	// creating ticket function
	public void addTicket(Ticket t) {

		ticketRepo.save(t);
	}

	// reading tickets
	public List<Ticket> getAllTickets() {
		return ticketRepo.findAll();
	}

	// finding ticket by id to edit and update ticket
	public Ticket getTicketById(int id) {

		Optional<Ticket> t = ticketRepo.findById(id);
		if (t.isPresent()) {
			return t.get();
		}
		return null;

	}

	// delete function
	public void delTicket(int id) {

		ticketRepo.deleteById(id);
	}

	// search function
	public List<Ticket> getByKeyword(String keyword) {
		return ticketRepo.findByKeyword(keyword);
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

}
