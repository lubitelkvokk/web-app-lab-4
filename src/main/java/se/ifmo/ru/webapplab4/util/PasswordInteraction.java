package se.ifmo.ru.webapplab4.util;


import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class PasswordInteraction {


    public String hashPassword(String password) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

        Hash hash = Password.hash(password)
                .with(bcrypt);

        return hash.getResult();
    }

    public boolean verifyPassword(String password, String token) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

        return Password.check(password, token)
                .with(bcrypt);
    }

}
