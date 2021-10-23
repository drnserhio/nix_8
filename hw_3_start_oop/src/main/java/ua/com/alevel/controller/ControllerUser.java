package ua.com.alevel.controller;

import ua.com.alevel.config.DaoFactory;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerUser {
    public static final String INDENT = "--------------------";

    private final UserService service = DaoFactory.getInstance().getDataBaseObject(UserService.class);


    public void run() {
        menu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        try {
            while ((in = reader.readLine()) != null) {
                choose(in, reader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void menu() {
        System.out.println(

                "\t\t| If you entry : |\n" +
                        " 1 - Add new user \n" +
                        " 2 - Drop user from\n" +
                        " 3 - Update user from db \n" +
                        " 4 - Find user by id \n" +
                        " 5 - View all user from db\n" +
                        " 6 - Exit"
        );
    }

    private void problem(String msg) {
        System.out.println(INDENT);
        System.out.println("Exception : | " + msg + " |");
        System.out.println(INDENT);
    }

    private void choose(String in, BufferedReader reader) throws IOException {

        switch (in) {

            case "1":
                create(reader);
                break;
            case "2":
                drop(reader);
                break;
            case "3":
                update(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                problem("Empty wrong...");
        }
        menu();

    }


    public void create(BufferedReader reader) throws IOException {
        try {
            System.out.println("Entry name :");
            String name = reader.readLine();
            if (strIsEmpty(name)) {
                throw new NullPointerException();
            }

            System.out.println("Entry email :");
            String email = reader.readLine();
            if (strIsEmpty(email)) {
                throw new NullPointerException();
            }

            System.out.println("Entry age :");
            int age = Integer.parseInt(reader.readLine());

            User user = new User();
            user.setName(name.trim());
            user.setEmail(email);
            user.setAge(age);

            service.create(user);

            result(UserStateBD.USER_CREATE.name() + " -> " + service.findById(user.getId()));
        } catch (NullPointerException e) {
            problem(e.getClass().getName());
        } catch (NumberFormatException e) {
            problem(e.getClass().getName());
        }

    }

    public void drop(BufferedReader reader) throws IOException {
        try {
            System.out.println("Entry id user :");
            String id = reader.readLine();
            if (strIsEmpty(id)) {
                throw new NullPointerException();
            }
            service.drop(id.trim());
            result(UserStateBD.USER_DROP.name());
        } catch (NullPointerException e) {
            problem(e.getClass().getName() + " -> " + UserStateBD.USER_NOT_DROP.name());
        }
    }

    public void update(BufferedReader reader) throws IOException {
        try {

            System.out.println("Entry id :");
            String id = reader.readLine();
            if (strIsEmpty(id)) {
                throw new NullPointerException();
            }

            System.out.println("Entry new Name user : ");
            String name = reader.readLine();
            if (strIsEmpty(name)) {
                throw new NullPointerException();
            }

            System.out.println("Entry new Age user :");
            int age = Integer.parseInt(reader.readLine());

            User updateUser = new User();
            updateUser.setId(id.trim());
            updateUser.setName(name.trim());
            updateUser.setAge(age);
            service.update(updateUser);
            result(UserStateBD.USER_UPDATE.name());

        } catch (NullPointerException e) {
            problem(e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name());
        } catch (NumberFormatException e) {
            problem(e.getClass().getName());
        }

    }

    public void findById(BufferedReader reader) throws IOException {
        try {
            System.out.println("Entry id user");
            String id = reader.readLine();
            if (strIsEmpty(id)) {
                throw new NullPointerException();
            }
            result(String.valueOf(service.findById(id.trim())).trim());
        } catch (NullPointerException e) {
            problem(e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name());
        }
    }

    public void findAll() {
        result("\n" + String.valueOf(service.findAll()).trim());
    }

    public boolean strIsEmpty(String str) {
        if (str.isEmpty()
                || str.length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    public void result(String str) {
        System.out.println("___________________________________\n"
                            + "You result: " + str + "\n"
                                + "___________________________________");
    }
}
