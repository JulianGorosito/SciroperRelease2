package tup.backend.controllers;

// ----- Imports ----- //
import tup.backend.models.User_Datos;
import tup.backend.models.Users;
import tup.backend.repositories.UsersRepository;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// ----- *** ----- //
@RestController

// ----- *** ----- //
@RequestMapping("")
public class UsersController {

    // ----- UserDatos: Necesario para retorno ----- //
    User_Datos UserDatos = new User_Datos();

    // ----- *** ----- //
    @Autowired
    private UsersRepository UsersRepository;

    // ----- LOGIN ----- //
    
    // --- PutLogged : loggea al usuario. --- //
    @GetMapping("/login/{user_input}/{password_input}")
    public @ResponseBody String PutLogged(@PathVariable String user_input, @PathVariable String password_input) {
        Iterable <Users> iterable = UsersRepository.findAll();

        // ----- Variables ----- //
        String Respuesta = "";
        String UserBuscar;
        String PasswordBuscar;

            // ----- Obtiene el usuario solicitado  ----- //
            for (Users users : iterable) {
                UserBuscar = users.getUser();

                // --- Si es encontrado carga el nombre de usuario ---- //
                if(UserBuscar.equals(user_input)){
                    UserDatos.setPlayer_name(UserBuscar);

                    // ----- Obtiene la contraseña solicitada  ----- //
                    for (Users password : iterable) {
                        PasswordBuscar = password.getPassword();

                        // --- Si es encontrada completa los datos del mismo ---- //
                        if(PasswordBuscar.equals(password_input)){
                            UserDatos.setPlayer_password(PasswordBuscar);
                            UserDatos.setPlayer_points(users.getPoints());
                            UserDatos.setPlayer_logged_on(true);
                            Respuesta = "Conectado";
                        }

                        // --- En caso de que no exita el usuario ---- //
                        else{
                            Respuesta = "Usuario no encontrado";
                        }
                    }
                }
            }
        return Respuesta;
    }

    // --- GetLoggedOn : Confirma si el usuario se encuentra Logeado. --- //
    @GetMapping("/logged")
    public @ResponseBody User_Datos GetLogged() {
        getPoints();
        return UserDatos;
    }

    /* --- User_Salir: Determina los datos en "vacío" hasta ---
       ---------- que un nuevo usuario inicie seción ---------- */
    @PutMapping("/salir")
    public @ResponseBody void User_Salir(){
        UserDatos.setPlayer_name("");
        UserDatos.setPlayer_password("");
        UserDatos.setPlayer_points(0);
        UserDatos.setPlayer_logged_on(false);
    }

    // --- RegisterUser: Agregar Usuarios (sólo en url) --- //
    @GetMapping("/register/{user}/{password}")
    public @ResponseBody String RegisterUser(@PathVariable String user,@PathVariable String password) {
        Iterable<Users> iterable = UsersRepository.findAll();
           Users UserNew = new Users();
           UserNew.setUser(user);
           UserNew.setPassword(password);
           UserNew.setPoints(0);
           UsersRepository.save(UserNew);


        return "registrado";
    }

    // ----- Puntos ----- //

    // --- AddPoints: Agregar los puntos ganados al usuario --- //
    @PutMapping("/puntos/{user}/{points}")
    public @ResponseBody void AddPoints(@PathVariable String user, @PathVariable int points){
        Iterable<Users> iterable = UsersRepository.findAll();
        String User_Seatch = "";

        // --- Obtiene al usuario logeado actualmente --- //
        for (Users users : iterable) {
            User_Seatch = users.getUser();

                // --- Si obtiene el usuario agrega los puntos ganados --- //
                if (User_Seatch.equals(user)){
                    int PuntosADD = users.getPoints()+points;
                    users.setPoints(PuntosADD);
                    UsersRepository.saveAll(iterable);
                    //limitar el Juego a 1000pts
                    // if (PuntosADD < 1000) {
                    //     System.out.println("Puntos por debajo del limite");
                    //     users.setPoints(PuntosADD);
                    //     UsersRepository.saveAll(iterable);
                    // }
                    // if (PuntosADD >= 1000){
                    //     System.out.println("Puntos en el limite");
                    //     users.setPoints(1000);
                    //     UsersRepository.saveAll(iterable);
                    // }
                }

        }
    }

    // --- SetPoints: Modifica los puntos del usuario --- //
    @PutMapping("/setp/{user}/{points}")
    public @ResponseBody String SetPoints(@PathVariable String user, @PathVariable int points){
        Iterable<Users> iterable = UsersRepository.findAll();
        String User_Seatch = "";

        // --- Obtiene al usuario logeado actualmente --- //
        for (Users users : iterable) {
            User_Seatch = users.getUser();
                // --- Si obtiene el usuario modifica los puntos del usuario --- //
                if (User_Seatch.equals(user)){
                    System.out.println("Usuario Encontrado");
                    if (users.getPoints() < 1000) {
                        System.out.println("Puntos por debajo del limite");
                        users.setPoints(points);
                        UsersRepository.saveAll(iterable);
                    }
                    if (users.getPoints() >= 1000){
                        System.out.println("Puntos en el limite");
                        users.setPoints(points);
                        UsersRepository.saveAll(iterable);
                    }
                }
        }

        return "Puntos Seteados";
    }

    // --- GetPoints: Metodo para mantener actualizado los puntos del usuario
    public void getPoints(){
        Iterable <Users> iterable = UsersRepository.findAll();
        String UserBuscar;
        String PasswordBuscar;
  
        for (Users users : iterable) {
          UserBuscar = users.getUser();
  
          if(UserBuscar.equals(UserDatos.getPlayer_name())){
              for (Users password : iterable) {
                  PasswordBuscar = password.getPassword();
  
                  if(PasswordBuscar.equals(UserDatos.getPlayer_password())){
                      UserDatos.setPlayer_points(users.getPoints());
                  }
                  else{
                  }
              }
          }
        }
      }    

    // ----- URL ----- //

    // --- GetAllUsers: Devuelve una lista de todos los usuarios (sólo en url) --- //
    @GetMapping("/all")
    public @ResponseBody String GetAllUsers() {
    
        // --- iterable: recorre y obtiene datos de UsersRepository --- //
        Iterable<Users> iterable = UsersRepository.findAll();
    
            // --- *** --- //
        String resp = "";
            resp =  """
                <style>
                #users {
                  font-family: Arial, Helvetica, sans-serif;
                  border-collapse: collapse;
                  width: 100%;
                }
    
                #users td, #users th {
                  border: 1px solid #ddd;
                  padding: 8px;
                }
    
                #users tr:nth-child(even){background-color: #f2f2f2;}
    
                #users tr:hover {background-color: #ddd;}
    
                #users th {
                  padding-top: 12px;
                  padding-bottom: 12px;
                  text-align: left;
                  background-color: #04AA6D;
                  color: white;
                }
                </style>
                """;
        resp += "<table id ='users'>"
                + "<tr>"
                + "<th>Id</th>"
                + "<th>Name</th>"
                + "<th>password</th>"
                + "<th>points</th>"
                + "</tr>";
        for (Users users: iterable) {
                    resp += "<tr>"
                    + "<th>" + users.getId() + "</th>"
                    + "<th>" + users.getUser() + "</th>"
                    + "<th>" + users.getPassword() + "</th>"
                    + "<th>" + users.getPoints() + "</th>"
                    + "</tr>";
        }
                    
            
        return resp + "</table>";
    }
    
    // --- GetUser: Obtiene todos los datos de un usuario segun su ID (sólo en url) --- //
    @GetMapping("/{id}")
    public @ResponseBody String GetUser(@PathVariable int id) {

        // --- iterable: Recorre y obtiene datos de UsersRepository --- //
        Iterable <Users> iterable = UsersRepository.findAll();

        // ----- Variables ----- //
        String ReturnString = "";
        int Id_Seatch = 0;

        // ----- ReturnString: Almacena los datos que se mostrarán de la tabla ----- //
        ReturnString = """
                    <style>
                    #users {
                    font-family: Arial, Helvetica, sans-serif;
                    border-collapse: collapse;
                    width: 100%;
                    }

                    #users td, #users th {
                    border: 1px solid #ddd;
                    padding: 8px;
                    }

                    #users tr:nth-child(even){background-color: #f2f2f2;}

                    #users tr:hover {background-color: #ddd;}

                    #users th {
                    padding-top: 12px;
                    padding-bottom: 12px;
                    text-align: left;
                    background-color: #04AA6D;
                    color: white;
                    }
                    </style>
                    """;
            ReturnString += "<table id ='users'>"
                            + "<tr>"
                            + "<th>Id</th>"
                            + "<th>Name</th>"
                            + "<th>Password</th>"
                            + "<th>Points</th>"
                            + "</tr>";

        // ----- Busca entre todos los usuarios de id idéntica a la solicitada ----- //
        for (Users users : iterable) {
            Id_Seatch = users.getId();
                if (Id_Seatch == id){
                    ReturnString += "<tr>"
                                + "<th>" + users.getId() + "</th>"
                                + "<th>" + users.getUser() + "</th>"
                                + "<th>" + users.getPassword() + "</th>"
                                + "<th>" + users.getPoints() + "</th>"
                                + "</tr>";
                }
        }
        return ReturnString;
    }

    // --- DeleteUserByID: Eliminar un usuario segun su ID (sólo en url) --- //
    @GetMapping("/delete/{id}") 
    public @ResponseBody String DeteleUserByID(@PathVariable int id) {
        UsersRepository.deleteById(id);
        System.out.println("Usuario de id ("+id+") fue eliminado");
        return "Deleted";
    }

    // --- GetRanking: da los datos de todos los usuarios para luego ordenarlos en el frontend ---//
    @GetMapping("/PlayerList")
    public @ResponseBody Iterable GetRanking() {
        Iterable<Users> iterable = UsersRepository.findAll();
        
    return iterable;
    }

}

    

