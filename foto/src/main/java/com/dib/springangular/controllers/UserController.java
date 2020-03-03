package com.dib.springangular.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dib.springangular.models.Photo;
import com.dib.springangular.models.PhotoAux;
import com.dib.springangular.models.User;
import com.dib.springangular.repositories.PhotoRepository;
import com.dib.springangular.repositories.UserRepository;
import com.dib.springangular.services.PhotoService;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class UserController {
	  @Autowired
	    private PhotoRepository photoRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/users")
    public Iterable<User> user() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(path = "/users")
    public User addUser(@RequestBody User user) {
        User pt = userRepository.save(user);
        System.out.println(pt);
        return pt;
    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User user, @PathVariable String id) {

        Optional<User> userData= userRepository.findById(id);
        if (userData.isPresent()) {
        	
            User _user = userData.get();
            _user.setFullname(user.getFullname());
            _user.setEmail(user.getEmail());
            _user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            
            
            

            return new ResponseEntity<User> (userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable String id) {
    	userRepository.deleteById(id);
    }
    @DeleteMapping("/photos/{id}")
    void deletePhoto(@PathVariable String id) {
    	photoRepository.deleteById(id);
    }
    @PostMapping(value="/photos/add")
    public String addPhoto(@RequestParam(name = "image") MultipartFile image) throws IOException {
       
//    	String id = photoService.addPhoto(image);
        return "redirect:/photos/";// + id;
    }
    
    @PostMapping(value="/photos/addaux")
    public String addPhoto(@RequestBody PhotoAux image) throws IOException {
       System.out.println("llega");
       String id = photoService.addPhoto(image);
        return "redirect:/photos/";// + id;
    }
  
 
    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
       model.addAttribute("image", photo.getImage());
        return photoRepository.findById(id).get();
    }
    
    @GetMapping("/photos")
    public Iterable<Photo> photo() {
        return photoRepository.findAll();
    }

    @GetMapping("/photos/upload")
    public String uploadPhoto(Model model) {
        model.addAttribute("message", "hello");
        return "uploadPhoto";
    }
}