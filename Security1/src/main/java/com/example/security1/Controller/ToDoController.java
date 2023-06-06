package com.example.security1.Controller;

import com.example.security1.Model.MyUser;
import com.example.security1.Model.ToDo;
import com.example.security1.Service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class ToDoController {


    private final ToDoService toDoService;

    //CRUD for todos only,Like Twitter

    @GetMapping("/get")
    public ResponseEntity getToDos(@AuthenticationPrincipal MyUser myUser){
        //@AuthenticationPrincipal to get the id of the user while logging in to get the list of the specific user
        List<ToDo>toDos=toDoService.getToDos(myUser.getId());//myUser up right
        return ResponseEntity.status(200).body(toDos);
    }


    @PostMapping("/add")
    public ResponseEntity addToDo(@AuthenticationPrincipal MyUser myUser, @RequestBody ToDo toDo){
        // we have to add to do inside this special user, we take todo and myuser to add in
        toDoService.addToDO(myUser.getId(),toDo);
        return ResponseEntity.status(200).body("ToDo Added!");
    }


    @PutMapping("/update/{todoid}")
    public ResponseEntity updateToDo(@AuthenticationPrincipal MyUser myUser, @RequestBody ToDo toDo,@PathVariable Integer todoid){

        toDoService.updateToDo(myUser.getId(), toDo,todoid);
        return ResponseEntity.status(200).body("ToDo Updated!");
    }

    @DeleteMapping("/delete/{todoid}")
    public ResponseEntity deleteToDo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer todoid){
        toDoService.deleteToDo(myUser.getId(),todoid); //.getId();
        return ResponseEntity.status(200).body("ToDo Deleted!");
    }

}
