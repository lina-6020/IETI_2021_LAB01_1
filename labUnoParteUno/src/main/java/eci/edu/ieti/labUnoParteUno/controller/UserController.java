package eci.edu.ieti.labUnoParteUno.controller;

import eci.edu.ieti.labUnoParteUno.datos.User;
import eci.edu.ieti.labUnoParteUno.dto.UserDto;
import eci.edu.ieti.labUnoParteUno.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping( "/v1/user" )
public class UserController
{
    private final UserService userService;

    public UserController(@Autowired UserService userService )
    {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> all()
    {
        try{
            List<User> user=userService.all();
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se han encontrado usuarios",HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById(@PathVariable String id )
    {
        try{
            User user=userService.findById(id);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se encontro usuario",HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping()
    public ResponseEntity create(@RequestBody UserDto userDto){
        try{
            userService.create(new User(Integer.toString(userService.all().size()+1),userDto.getName(),userDto.getLastName(),userDto.getEmail(), LocalDateTime.now()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity<>("No se pudo crear el usuario",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id )
    {
       try{
           userService.update(new User(id,userDto.getName(),userDto.getEmail(),userDto.getLastName(),LocalDateTime.now()),id);
           return new ResponseEntity<>(HttpStatus.CREATED);
       }catch (Exception ex){
           Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,null,ex);
           return new ResponseEntity("No se pudo actulizar el usuario",HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception ex){
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE,null,ex);
            return new ResponseEntity("No se pudo borrar el usuario",HttpStatus.NOT_FOUND);
        }
    }
}
