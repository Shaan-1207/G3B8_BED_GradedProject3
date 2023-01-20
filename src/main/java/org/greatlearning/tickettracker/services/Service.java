package org.greatlearning.tt.services;

import java.util.List;

import org.greatlearning.tt.entity.Ticket;

public interface Service {

	public void addTicket(Ticket t);

	public List<Ticket> getAllTickets();

	public Ticket getTicketById(int id);

	public void delTicket(int id);

	public List<Ticket> getByKeyword(String keyword);

}
