package org.greatlearning.tickettracker.repo;

import java.util.List;

import org.greatlearning.tickettracker.entity.TicketTrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Repository extends JpaRepository<TicketTrackerEntity, Integer> {

	// custom query for search operation
	@Query(value = "select * from Tickets t where t.title like %:keyword% or t.description like %:keyword%", nativeQuery = true)
	List<TicketTrackerEntity> findByKeyword(@Param("keyword") String keyword);

}
