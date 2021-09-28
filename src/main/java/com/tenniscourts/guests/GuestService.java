package com.tenniscourts.guests;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private GuestMapper guestMapper;
	
	public GuestDTO registerNewGuest(GuestDTO newGuestDTO) {
		Guest guest = new Guest();
		guest.setName(newGuestDTO.getName());
		
		return guestMapper.map(guestRepository.save(guest));
	}
	
	public GuestDTO updateGuest(GuestDTO updateGuestDTO) {
		return guestRepository.findById(updateGuestDTO.getId()).map(guest -> {
			guest.setName(updateGuestDTO.getName());
			return guestMapper.map(guestRepository.save(guest));
		}).orElseThrow(() -> new EntityNotFoundException("Guest with this ID not found."));
	}
	
	public String removeGuest(Long id) {
		Guest guest = guestRepository.findById(id).get();
		guestRepository.delete(guest);
		
		return "The Guest was deleted succcessfully";
	}
	
	public List<Guest> findAllGuests() {
		return guestRepository.findAll();
	}
	
	public Optional<Guest> findGuestById(Long id) {
		return guestRepository.findById(id);
	}
	
	public List<Guest> findGuestByName(String name) {
		return guestRepository.findByName(name);
	}
}
