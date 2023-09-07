package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.dao.UserDAO;
import com.example.bookingmeetingroom.model.entity.User;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean hasCSVFormat(MultipartFile file) {
        String type = "text/csv";
        if (!type.equals(file.getContentType())) return false;
        return true;
    }

    @Override
    public void processAndSaveData(MultipartFile file) throws IOException {
        List<User> users = csvToUsers(file.getInputStream());
        userDAO.saveAll(users);
    }

    private List<User> csvToUsers(InputStream inputStream) {
        try (Reader reader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(reader);) {
            String[] nextLine;
            csvReader.readNext();
            List<User> users = new ArrayList<>();
            while ((nextLine = csvReader.readNext()) != null) {
                String username = nextLine[0];
                String password = passwordEncoder.encode(nextLine[1]);
                String name = nextLine[2];
                String email = nextLine[3];
                String role = "ROLE_USER";
                Optional<User> user1 = userDAO.findByUsername(username);
                Optional<User> user2 = userDAO.findByUsername(email);
                if (user1.isPresent() || user2.isPresent()) continue;
                User user = new User(username, password, name, email, role);
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
