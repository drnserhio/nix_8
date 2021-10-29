package ua.com.alevel.hw_3_oop.controller;

import annotation.Autowired;
import annotation.Service;
import ua.com.alevel.StringerUtil;
import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.service.UserService;
import ua.com.alevel.hw_3_oop.service.impl.UserServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UserController implements Controller {

    @Autowired
    private UserService service;

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

                "\n\t\t| If you entry : |\n" +
                        " 1 - Add new user \n" +
                        " 2 - Drop user from\n" +
                        " 3 - Update user from db \n" +
                        " 4 - Find user by id \n" +
                        " 5 - View all user from db\n" +
                        " 6 - Exit"
        );
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
                StringerUtil.exception("Empty wrong...");
        }
        menu();

    }


    public void create(BufferedReader reader) throws IOException {
        try {
            System.out.println("Entry name :");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                throw new NullPointerException();
            }

            System.out.println("Entry email");
            String email = reader.readLine();
            if (StringerUtil.strIsEmpty(email)) {
                throw new NullPointerException();
            }

            System.out.println("Entry age :");
            int age = Integer.parseInt(reader.readLine());

            User user = new User();
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setAge(age);

            service.create(user);

            result(UserStateBD.USER_CREATE.name() + " -> " + service.findById(user.getId()));
        } catch (NullPointerException e) {
            StringerUtil.exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            StringerUtil.exception(e.getClass().getName());
        }

    }

    public void drop(BufferedReader reader) throws IOException {
        try {
            System.out.println("Entry id user :");
            long id = Long.parseLong(reader.readLine());
            if (service.findById(id) == null) {
                throw new NullPointerException();
            }
            service.delete(id);
            result(UserStateBD.USER_DROP.name());
        } catch (NullPointerException e) {
            StringerUtil.exception(e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name());
        } catch (NumberFormatException e) {
            StringerUtil.exception(e.getClass().getName());
        }
    }

    public void update(BufferedReader reader) throws IOException {
        try {

            System.out.println("Entry id :");
            long id = Long.parseLong(reader.readLine());


            System.out.println("Entry new Name user : ");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                throw new NullPointerException();
            }

            System.out.println("Entry new Email user : ");
            String email = reader.readLine();
            if (StringerUtil.strIsEmpty(email)) {
                throw new NullPointerException();
            }

            System.out.println("Entry new Age user :");
            int age = Integer.parseInt(reader.readLine());

            User updateUser = new User();
            updateUser.setId(id);
            updateUser.setName(name.trim());
            updateUser.setEmail(email.trim());
            updateUser.setAge(age);
            service.update(updateUser);
            result(UserStateBD.USER_UPDATE.name());

        } catch (NullPointerException e) {
            StringerUtil.exception((e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name()));
        } catch (NumberFormatException e) {
            StringerUtil.exception((e.getClass().getName()));
        }

    }

    public void findById(BufferedReader reader) throws IOException {
        try {
            System.out.println("Entry id user");
            long id = Long.parseLong(reader.readLine());

            User user = service.findById(id);
            if (user == null) {
                throw new NullPointerException();
            } else {
                result(String.valueOf(user).trim());
            }
        } catch (NullPointerException e) {
            StringerUtil.exception((e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name()));
        } catch (NumberFormatException e) {
            StringerUtil.exception((e.getClass().getName()));
        }
    }

    public void findAll() {
        result("\n" + String.valueOf(service.findAll()).trim());
    }


    private void result(String str) {
        System.out.println("___________________________________\n"
                + "You result: " + str + "\n"
                + "___________________________________");
    }
}
