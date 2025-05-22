package payloads;

import com.github.javafaker.Faker;
import pojo.UserModel;

public class Payload {

    public static final Faker faker = new Faker();


    //User payload
   public static UserModel usernamePayload() {
        String email = faker.internet().emailAddress();
        String first_name = faker.name().firstName();
        String last_name = faker.name().lastName();
        String avatar = faker.internet().image();

        return new UserModel(email, first_name, last_name, avatar);
    }

}
