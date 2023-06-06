package com.example.security1.Service;

import com.example.security1.APIException.APIException;
import com.example.security1.Model.MyUser;
import com.example.security1.Model.ToDo;
import com.example.security1.Respository.AuthRepository;
import com.example.security1.Respository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final AuthRepository authRepository;
    public List<ToDo> getToDos(Integer userId) {
        return toDoRepository.findToDoByUserId(userId);
    }

    public void addToDO(Integer userId, ToDo toDo){
        toDo.setUserId(userId); //take the id coming from user and make it== userId; {not relations, just to make each operation done by someone exactly}
        toDoRepository.save(toDo);
    }

    public void updateToDo(Integer userId, ToDo toDo, Integer todoid){

        ToDo oldTodo=toDoRepository.findToDoById(todoid);
        if(oldTodo==null) {
            throw new APIException("sorry no such ToDo to update");
        }

        if(oldTodo.getMyUser().getId()!=userId) {
            throw new APIException("unAuthorized, this todo doesn't belong to u");
        }

        oldTodo.setMessage(toDo.getMessage());
        toDoRepository.save(oldTodo);
    }

    public void deleteToDo(Integer userId, Integer todoid){
        //check if todo  exist

        ToDo oldTodo=toDoRepository.findToDoById(todoid);
        if(oldTodo==null )
        {
            throw new APIException("sorry no such ToDo to delete or user not found, try another ID");
        }

        if(oldTodo.getMyUser().getId()!=userId) {
            throw new APIException("unAuthorized, this todo doesn't belong to u");
        }


        //else, if found
        toDoRepository.delete(oldTodo);
    }

    //----------------assign ToDO to users-----------------------
    public void assignToDoToUser(Integer user_id,Integer todo_id){
        //check if they both exist
        MyUser myUser=authRepository.findMyUserById(user_id);
        ToDo toDo=toDoRepository.findToDoById(todo_id);
        if(myUser==null||toDo==null){
            throw new APIException("can't assign ToDo, wrong id");
        }
        toDo.setMyUser(myUser);
        toDoRepository.save(toDo);
    }
}
