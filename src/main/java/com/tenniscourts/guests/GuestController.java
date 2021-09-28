package com.tenniscourts.guests;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenniscourts.config.BaseRestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/guests")
public class GuestController extends BaseRestController {
	
	@Autowired
	private GuestService guestService;
	
	@ApiOperation("Guest creation endpoint")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Guest was created successfully!")})
	@PostMapping(value = "/register")
	public ResponseEntity<?> registerNewGuest(@RequestBody GuestDTO newGuestDTO) {
		return ResponseEntity.created(locationByEntity(guestService.registerNewGuest(newGuestDTO).getId())).build();
	}
	
	@ApiOperation("Guest update endpoint")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Guest was updated successfully!")})
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateGuest(@RequestBody GuestDTO updateGuestDTO) {
		return ResponseEntity.created(locationByEntity(guestService.updateGuest(updateGuestDTO).getId())).build();
	}
	
	@ApiOperation("Find all Guests endpoint")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "List of all Guests")})
	@GetMapping(value = "/find-all")
	public List<Guest> findAllGuests() {
		return guestService.findAllGuests();
	}
	
	@ApiOperation("Find by id endpoint")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Guests was found by id")})
	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Optional<Guest>> findGuestById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(guestService.findGuestById(id));
	}
	
	@ApiOperation("Find by name endpoint")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Guests was found by name")})
	@GetMapping(value = "/findByName/{name}")
	public ResponseEntity<List<Guest>> findGuestByName(@PathVariable("name") String name) {
		return ResponseEntity.ok().body(guestService.findGuestByName(name));
	}
	
	@ApiOperation("Delete Guest endpoint")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Guests was deleted successfully")})
	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<?> removeGuest(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(guestService.removeGuest(id));
	}
}
