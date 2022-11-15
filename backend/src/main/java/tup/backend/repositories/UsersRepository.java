package tup.backend.repositories;

// ----- Imports ----- //
import org.springframework.data.repository.CrudRepository;
import tup.backend.models.Users;

public interface UsersRepository extends CrudRepository <Users, Integer> {
    
}

/*
    --------------------------------------------------------------------------------------------
    ------------------- Esta clase extiende de CrudRepository permitiendo ----------------------
    ---------------------------- la utilización de los métodos CRUD ----------------------------
    --------------------------------------------------------------------------------------------
*/