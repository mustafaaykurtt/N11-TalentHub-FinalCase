package com.n11.userservice.config;

import com.n11.userservice.model.User;
import com.n11.userservice.repository.UserRepository;
import com.n11.userservice.util.enums.Gender;
import com.n11.userservice.util.enums.UserStatus;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created By Mustafa Aykurt
 * Date:16.03.2024
 * Time:03:04
 */

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUsersFromCsv();
    }

    private void loadUsersFromCsv() {
        try (InputStream inputStream = getClass().getResourceAsStream("/users.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                User user = new User();
                user.setName(data[0]);
                user.setSurname(data[1]);
                user.setBirthDate(LocalDate.parse(data[2],formatter));
                user.setEmail(data[3]);
                user.setPassword(data[4]);
                user.setLatitude(Double.valueOf(data[5]));
                user.setLongitude(Double.valueOf(data[6]));
                user.setGender(Gender.MALE);
                user.setStatus(UserStatus.ACTIVE);
                user.getBaseAdditionalFields().setUpdateDate(LocalDateTime.now());
                user.getBaseAdditionalFields().setCreateDate(LocalDateTime.now());
                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
