

package com.DeliveryOnTimeBackend.Backend.controller;

import com.DeliveryOnTimeBackend.Backend.extras.AccountType;
import com.DeliveryOnTimeBackend.Backend.model.Customer;
import com.DeliveryOnTimeBackend.Backend.model.Rider;
import com.DeliveryOnTimeBackend.Backend.repository.CustomerRepository;
import com.DeliveryOnTimeBackend.Backend.repository.RiderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.DeliveryOnTimeBackend.Backend.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.DeliveryOnTimeBackend.Backend.model.User;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RiderRepository riderRepository;
    @Autowired
    CustomerRepository customerRepository;

@GetMapping
public List<User> getAllUsers(){
    return userRepository.findAll();
}

@PostMapping("/addUser")
public User addUser(@RequestBody User user){
    return userRepository.save(user);
}

   @PostMapping("/signUp")
   ResponseEntity<?> signUp(@RequestBody User user){

    if(userRepository.findByEmail(user.getEmail()).isPresent()){
        return ResponseEntity.badRequest().body(("Email is already in Use"));
    }

    if(user.getAccountType() == AccountType.Rider){
        Rider rider = new Rider();
        rider.setName(user.getName());
        rider.setEmail(user.getEmail());
        rider.setPhone(user.getPhone());
        rider.setPassword(user.getPassword());
        rider.setAccountType(user.getAccountType());

        riderRepository.save(rider);
    }
       if(user.getAccountType() == AccountType.Customer){
           Customer customer = new Customer();
           customer.setName(user.getName());
           customer.setEmail(user.getEmail());
           customer.setPhone(user.getPhone());
           customer.setPassword(user.getPassword());
           customer.setAccountType(user.getAccountType());

           customerRepository.save(customer);
       }



     /*/  if(user.getAccountType() == AccountType.Rider){
       //    Rider rider = new Rider(user);
           riderRepository.save(user);
       //}
       else if(user.getAccountType() == AccountType.Customer){
           Customer customer = new Customer(user);
           customerRepository.save(customer);
       }*/
    return ResponseEntity.ok("User Has been regestered");




}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("User not found");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Incorrect password");
        }



        return ResponseEntity.ok(true);
    }



}
