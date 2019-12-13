package se.terhol.telenorapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import se.terhol.telenorapi.model.Account;
import se.terhol.telenorapi.model.BusinessType;
import se.terhol.telenorapi.model.Greeting;


@RestController
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String generateGreeting(@RequestParam Account account, @RequestParam(defaultValue = "-1") String id, @RequestParam(defaultValue = "small")
            BusinessType type) {
        if (account == null) {
            throw new IllegalArgumentException("Please fill in correct data.");
        }
        String result = "";
        try {
            switch (account) {
                case PERSONAL:
                    result = makePrivateGreeting(id);
                    break;

                case BUSINESS:
                    result = makeBusinessGreeting(type);
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        return result;
    }

    private String makePrivateGreeting(String id) throws IllegalArgumentException {
        String message = "";
        int idNumber = Integer.valueOf(id);
        if (idNumber > 0) {
            Greeting greeting = new Greeting(idNumber);
            message = greeting.getMessage();
        } else {
            throw new IllegalArgumentException("User ID must be filled in and larger than 0!");
        }
        return message;
    }

    private String makeBusinessGreeting(BusinessType type) throws IllegalArgumentException {
        String message = "";
        switch (type) {
            case BIG:
                message = "Welcome business user!";
                break;
            case SMALL:
                throw new IllegalArgumentException("This case is not implemented yet!");
            default:
                throw new IllegalArgumentException("Please fill in correct business type!");
        }

        return message;
    }
}
