package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;

import static play.libs.Json.toJson;

/**
 * Created by Thiloshon on 03-Mar-17.
 */
public class BookController extends Controller {
    static ArrayList<User> db = new ArrayList<>();
    private final FormFactory formFactory;

    /**
     * The constructor of Controller Class
     *
     * @param formFactory The FormFactory instance is passed to facilitate the form inputs of HTML
     */
    @Inject
    public BookController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }


    /**
     * The method to render initial login/sign up page
     *
     * @return the render of page
     */
    public Result index() {
        return ok(views.html.index.render());
    }


    /**
     * The Sign Up method. Converts form value to User object and adds to file storage
     *
     * @return A message to indicate user has been added successfully.
     */
    @Transactional
    public Result addUser() {
        DynamicForm requestData = formFactory.form().bindFromRequest();
        db.add(new User(requestData.get("name"), requestData.get("id"), requestData.get("password")));
        return ok("User Added Successfully");
        //return redirect(routes.PersonController.index());
    }

    /**
     * The Login method. Gets data from form, checks if the password, username combination exists in database.
     *
     * @return A message saying either the User is logged in or the combination is wrong.
     */
    public Result getUser() {

        DynamicForm requestData = formFactory.form().bindFromRequest();
        String reqid = requestData.get("reqID");
        String pass = requestData.get("reqPassword");

        User user = null;
        boolean flag = false;

        for (User u : db) {
            if (u.getMailID().equals(reqid) && u.getPassword().equals(pass)) {
                user = u;
                flag = true;
            }
        }

        if (flag) {
            return ok(user.getName() + " is logged in with ID " + user.getMailID());
        }
        return ok("No such user password combination found");
    }


    /**
     * The Method to view all user name combinations.
     *
     * @return JSON of the database
     */
    @Transactional(readOnly = true)
    public Result getUsers() {
        return ok(toJson(db).toString());
    }
}
