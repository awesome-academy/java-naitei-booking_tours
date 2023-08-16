package com.naite.bookingTour.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.naite.bookingTour.model.Booking;
import com.naite.bookingTour.model.Tour;
import com.naite.bookingTour.repository.BookingRepository;
import com.naite.bookingTour.repository.ClientRepository;
import com.naite.bookingTour.repository.TourRepository;

@Controller
@RequestMapping("/booking")
public class BookingController {

//	@Autowired
//	private TourRepository tourRepo;
//
//	@Autowired
//	private ClientRepository clientRepo;
//
//	@Autowired
//	private BookingRepository bookingRepo;
//
//	@ModelAttribute("currentTour")
//	public Tour tour() {
//		return new Tour();
//	}
//
//	@GetMapping("/{id}") 
//	public String bookingForm(Model model, @PathVariable("id") Long id, @ModelAttribute("currentTour") Tour tour) {
//		Booking booking = new Booking();
//		Tour tour2 = tourRepo.findById(id).orElse(null);
//		if (tour2 != null) {
//			tour.setId(tour2.getId());
//			tour.setName(tour2.getName());
//			tour.setDescription(tour2.getDescription());
//			tour.setType(tour2.getType());
//			tour.setDescription(tour2.getDescription());
//			tour.setImage(tour2.getImage());
//		}
//		model.addAttribute("tour", tour2);
//		model.addAttribute("booking", booking);
//		return "bookingInfo";
//	}
//
//
//	public void setTourRepo(TourRepository tourRepo) {
//		this.tourRepo = tourRepo;
//	}
//
//	public BookingRepository getBookingRepo() {
//		return bookingRepo;
//	}
//
//	public void setBookingRepo(BookingRepository bookingRepo) {
//		this.bookingRepo = bookingRepo;
//	}
//
//	public ClientRepository getClientRepo() {
//		return clientRepo;
//	}
//
//	public void setClientRepo(ClientRepository clientRepo) {
//		this.clientRepo = clientRepo;
//	}

}