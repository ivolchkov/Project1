package project.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.domain.gift.Gift;
import project.domain.sweet.*;
import project.domain.sweet.choice.*;
import project.domain.user.User;
import project.service.GiftService;
import project.service.SweetService;
import project.service.UserService;
import project.service.exception.InvalidRegistrationException;

import java.time.LocalDate;
import java.util.*;

@Component
public class Menu {
    private static final ResourceManager MANAGER = ResourceManager.INSTANCE;
    private static final Scanner IN = new Scanner(System.in);
    public static final String LANGUAGE = "Select an appropriate language:\n" + "1)Russian\n" + "2)Default(English)";

    private final UserService userService;
    private final GiftService giftService;
    private final SweetService sweetService;

    @Autowired
    public Menu(UserService userService, GiftService giftService, SweetService sweetService) {
        this.userService = userService;
        this.giftService = giftService;
        this.sweetService = sweetService;
    }

    public void run() {
        autoFilling();
        int language;
        int choice;

        System.out.println(LANGUAGE);

        language = Integer.parseInt(IN.nextLine());
        selectLanguage(language);

        System.out.println(MANAGER.getString("introduction"));
        choice = Integer.parseInt(IN.next());

        switch (choice) {
            case 1:
                signUp();
                break;
            case 2:
                signIn();
                break;
            default:
                System.out.println(MANAGER.getString("invalidChoice"));
        }
    }

    private void selectLanguage(int language) {
        String country;
        String languageLocale;

        if (language == 1) {
            country = "ru";
            languageLocale = "RU";
        } else {
            country = "en";
            languageLocale = "US";
        }
        Locale locale = new Locale(country, languageLocale);
        MANAGER.changeResourse(locale);
    }

    private void signUp() {
        String name;
        String surname;
        String email;
        String password;

        System.out.println(MANAGER.getString("registration.start"));
        name = IN.next();
        System.out.println(MANAGER.getString("registration.surname"));
        surname = IN.next();
        System.out.println(MANAGER.getString("registration.email"));
        email = IN.next();
        System.out.println(MANAGER.getString("registration.password"));
        password = IN.next();

        User user = new User.UserBuilder().
                withName(name).
                withSurname(surname).
                withEmail(email).
                withPassword(password).
                build();

        try {
            userService.register(user);

            System.out.println(MANAGER.getString("registration.success"));
            System.out.println(MANAGER.getString("registration.yourId") + user.getId());
            System.out.println(MANAGER.getString("registration.nextStep"));

            int signIn = Integer.parseInt(IN.next());

            if (signIn == 1) {
                signIn();
            } else {
                System.out.println(MANAGER.getString("registration.goodBye"));
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(MANAGER.getString("registration.invalidRegistration") + e.getMessage());
        }
    }

    private void signIn() {
        String email;
        String password;

        System.out.println(MANAGER.getString("login.email"));
        email = IN.next();
        System.out.println(MANAGER.getString("login.password"));
        password = IN.next();

        try {
            User user = userService.login(email, password);
            operationService(user);
        } catch (InvalidRegistrationException e) {
            System.out.println(MANAGER.getString("login.invalidOperation"));
        }
    }

    private void operationService(User user) {
        boolean isFinish = false;

        while (!isFinish) {
            System.out.println(MANAGER.getString("operation.selection"));
            int choice = Integer.parseInt(IN.next());

            switch (choice) {
                case 1: {
                    //Add order
                    addOrder(user);
                    break;
                }
                case 2: {
                    //Show orders
                    orderService(user);
                    break;
                }
                case 3: {
                    isFinish = true;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid operation");
                }
            }
        }
    }

    private void orderService(User user) {
        boolean isFinish = false;

        while (!isFinish) {
            System.out.println(MANAGER.getString("orderService.selection"));
            int choice = Integer.parseInt(IN.next());
//            List<AbstractSweet> sortSweetsBySugarContent(Long id);
//            List<AbstractSweet> sortSweetsByPrice(Long id);
//            List<AbstractSweet> sortSweetsByWeight(Long id);
//            List<AbstractSweet> showSweetsBySugarContentRange(Long id, Byte start, Byte end);
//
//            void removeSweet(Long id, AbstractSweet sweet);
//            void addSweet(Long id, AbstractSweet sweet);
//
//            Gift deleteByOwner(User owner);
            switch (choice) {
                case 1: {
                    showSweetsBySugarContent(user);
                    break;
                }
                case 2: {
                    showSweetsByPrice(user);
                    break;
                }
                case 3: {
                    showSweetsByWeight(user);
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    break;
                }
                case 7: {
                    break;
                }
                case 8: {
                    isFinish = true;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid operation");
                }
            }
        }
    }

    private void showSweetsByPrice(User user) {
        List<AbstractSweet> sweets = giftService.sortSweetsByPrice(user);

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
    }

    private void showSweetsBySugarContent(User user) {
        List<AbstractSweet> sweets = giftService.sortSweetsBySugarContent(user);

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
    }

    private void addOrder(User user) {
        boolean isFinish = false;
        Map<Long, Integer> order = new HashMap<>();

        System.out.println(MANAGER.getString("order.addSweets"));
        for (AbstractSweet sweet: sweetService.showAllSweets()) {
            System.out.println(sweet);
        }
        while (!isFinish) {
            String choice = IN.next();
            Long id = Long.parseLong(choice);
            Integer count = Integer.parseInt(choice);

            if ( id < 1 || id > 19) {
                isFinish = true;
            } else {
                order.put(id, count);
            }
        }
        List<AbstractSweet> sweets = sweetService.getSweetsByOrder(order);
        Gift gift = new Gift(user, sweets);
        giftService.addGift(gift);
    }


    private void autoFilling() {
        User igor = new User.UserBuilder().withName("Igor").
                withSurname("Volchkov").
                withEmail("igorik@gmail.com").
                withPassword("Vfkmdbyf1997").build();
        User dima = new User.UserBuilder().withName("Dima").
                withSurname("Ilchenko").
                withEmail("ilchenko@gmail.com").
                withPassword("Dima1997").build();

        userService.register(igor);
        userService.register(dima);

        sweetService.addSweet(new CaramelFruit(Fruit.APPLE));
        sweetService.addSweet(new CaramelFruit(Fruit.PEAR));
        sweetService.addSweet(new CaramelFruit(Fruit.BANANA));
        sweetService.addSweet(new CaramelFruit(Fruit.MELON));

        sweetService.addSweet(new ChocolateBar(ChocolateBarType.MILK));
        sweetService.addSweet(new ChocolateBar(ChocolateBarType.BLACK));
        sweetService.addSweet(new ChocolateBar(ChocolateBarType.WHITE));

        sweetService.addSweet(new ChocolateSweet(SweetFilling.BUTTER_CREAM));
        sweetService.addSweet(new ChocolateSweet(SweetFilling.CANDIED_FRUIT));
        sweetService.addSweet(new ChocolateSweet(SweetFilling.NUTS));

        sweetService.addSweet(new LollyPop(Shape.ROOSTER));
        sweetService.addSweet(new LollyPop(Shape.CIRCLE));
        sweetService.addSweet(new LollyPop(Shape.HEART));

        sweetService.addSweet(new Pie(PieTaste.BERRY));
        sweetService.addSweet(new Pie(PieTaste.APPLE));
        sweetService.addSweet(new Pie(PieTaste.PEACH));

        sweetService.addSweet(new Tartalet(TartaletFilling.CHERRY));
        sweetService.addSweet(new Tartalet(TartaletFilling.STRAWBERRY));
        sweetService.addSweet(new Tartalet(TartaletFilling.RASPBERRY));



    }
}
