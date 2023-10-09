package com.org.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.domain.UserInfo;
import com.org.dto.AuthRequest;
import com.org.dto.OrderDTO;
import com.org.dto.UserDtlsRequest;
import com.org.service.IOrderService;
import com.org.service.JwtService;
import com.org.service.UserInfoUserDetailsService;

@RestController
@RequestMapping("/order")
public class OderRestController {

	@Autowired
	IOrderService iorderService;
	
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    UserInfoUserDetailsService userInfoUserDetailsService;
    
    @PostMapping("/userSave")
    public ResponseEntity<?> saveUser(@RequestBody UserDtlsRequest user ){
    String saveUser = userInfoUserDetailsService.saveUser(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }
    
    
    
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
    
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getName());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }


	@PostMapping("/save")
	public ResponseEntity<?> saveOrderDtls(@RequestBody OrderDTO dto) {
		String saveOrder = iorderService.saveOrder(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveOrder);
	}

	@GetMapping("getOrder/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")

	public ResponseEntity<?> getOrderDtls(@PathVariable Integer id) {
		OrderDTO orderDTO = iorderService.getOrderById(id);
		return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
	}

	@GetMapping("getAllOrder")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")

	public ResponseEntity<?> saveOrderDtls() {
		List<OrderDTO> allOrders = iorderService.getAllOrders();
		return ResponseEntity.status(HttpStatus.OK).body(allOrders);
	}

}
