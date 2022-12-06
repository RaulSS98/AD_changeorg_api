package org.jesuitasrioja.ad_changeorg_api.controller;


import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.jesuitasrioja.ad_changeorg_api.payload.Response;
import org.jesuitasrioja.ad_changeorg_api.service.PeticionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PeticionesController{


    @Autowired
    PeticionesService peticionesService;

    @GetMapping("/peticiones")
    public ResponseEntity<List<Peticion>> listarPeticiones(){
        return new ResponseEntity<>(peticionesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/peticiones/{id}")
    public ResponseEntity<Peticion> findById(@PathVariable Long id) {
        Optional<Peticion> peticion = peticionesService.findById(id);
        return new ResponseEntity<>(peticion.get(), HttpStatus.OK);
    }

    @DeleteMapping("/peticiones/delete/{id}")
    public ResponseEntity<Response> deletePeticion(@PathVariable Long id){
        peticionesService.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }

    @PutMapping("/peticiones/edit")
    public ResponseEntity<Peticion> updateById(@RequestBody Peticion newPetition){
        Peticion peticion = peticionesService.updatePeticionById(newPetition);
        return new ResponseEntity<>(peticion,HttpStatus.OK);
    }
    @PutMapping("/peticiones/{id}")
    public ResponseEntity<Peticion> cambiarEstadoPeticion(@PathVariable Long id){
        return new ResponseEntity<>(peticionesService.cambiarEstadoPeticion(id),HttpStatus.OK);
    }
    @PutMapping("/peticiones/{peticion_id}/{user_id}")
    public ResponseEntity<Peticion> addFirmante(@PathVariable Long peticion_id, @PathVariable Long user_id){
        return new ResponseEntity<>(peticionesService.addFirmante(peticion_id,user_id),HttpStatus.OK);
    }

    @PostMapping("/peticiones/create/{user_id}/{categoria_id}")
        public ResponseEntity<Peticion> addPeticion(@RequestBody Peticion newPeticion, @PathVariable Long user_id, @PathVariable Long categoria_id){
        Peticion peticion = peticionesService.addPeticion(newPeticion,user_id,categoria_id);
        return new ResponseEntity<>(peticion,HttpStatus.OK);
    }


}
