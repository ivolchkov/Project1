package project.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.domain.gift.Gift;
import project.domain.sweet.*;
import project.domain.sweet.choice.*;
import project.domain.user.User;
import project.service.*;
import project.service.exception.InvalidRegistrationException;

import java.util.*;

@Component
public class Menu {
    private static final ResourceManager MANAGER = ResourceManager.INSTANCE;
    private static final Scanner IN = new Scanner(System.in);
    private static final String LANGUAGE = "Select an appropriate language:\n" + "1)Russian\n" + "2)Default(English)";

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
        MANAGER.changeResource(locale);
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
        } catch (Exception e) {
            System.out.println(MANAGER.getString("registration.invalidRegistration") + '\n' + e.getMessage());
            System.exit(0);
        }
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
                    addOrder(user);
                    break;
                }
                case 2: {
                    orderService(user);
                    break;
                }
                case 3: {
                    isFinish = true;
                    System.out.println(MANAGER.getString("registration.goodBye"));
                    break;
                }
                default: {
                    System.out.println(MANAGER.getString("invalidChoice"));
                }
            }
        }
    }

    private void addOrder(User user) {
        try {
            giftService.showGiftByOwner(user);
            System.out.println(MANAGER.getString("orderService.alreadyHasGift"));
        } catch (IllegalArgumentException e) {
            boolean isFinish = false;
            Map<Long, Integer> order = new HashMap<>();

            System.out.println(MANAGER.getString("orderService.addSweets"));

            for (AbstractSweet sweet: sweetService.showAllSweets()) {
                System.out.println(sweet);
            }

            while (!isFinish) {
                String choice = IN.next();
                Long id = Long.parseLong(choice);
                choice = IN.next();
                Integer count = Integer.parseInt(choice);

                if ( id < 1 || id > 19) {
                    isFinish = true;
                } else {
                    order.put(id, count);
                }
            }

            try {
                List<AbstractSweet> sweets = sweetService.getSweetsByOrder(order);
                Gift gift = new Gift(user, sweets);
                giftService.addGift(gift);
            } catch (IllegalArgumentException exception) {
                System.out.println(MANAGER.getString("invalidChoice"));
            }
        }
    }

    private void orderService(User user) {
        boolean isFinish = false;

        while (!isFinish) {
            System.out.println(MANAGER.getString("orderService.selection"));
            int choice = Integer.parseInt(IN.next());

            switch (choice) {
                case 1: {
                    showCurrentGift(user);
                    break;
                }
                case 2: {
                    showSweetsBySugarContent(user);
                    break;
                }
                case 3: {
                    showSweetsByPrice(user);
                    break;
                }
                case 4: {
                    showSweetsByWeight(user);
                    break;
                }
                case 5: {
                    showSweetsBySugarContentRange(user);
                    break;
                }
                case 6: {
                    addSweetToOrder(user);
                    break;
                }
                case 7: {
                    removeSweetFromOrder(user);
                    break;
                }
                case 8: {
                    deleteCurrentGift(user);
                    isFinish = true;
                    break;
                }
                case 9: {
                    isFinish = true;
                    break;
                }
                default: {
                    isFinish = true;
                    System.out.println(MANAGER.getString("invalidChoice"));
                }
            }
        }
    }

    private void showCurrentGift(User user) {
        try {
            Gift gift = giftService.showGiftByOwner(user);
            System.out.println(gift);
        } catch (Exception e) {
            System.out.println(MANAGER.getString("invalidChoice"));
        }
    }

    private void showSweetsBySugarContent(User user) {
        List<AbstractSweet> sweets = giftService.sortSweetsBySugarContent(user);

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
    }

    private void showSweetsByPrice(User user) {
        List<AbstractSweet> sweets = giftService.sortSweetsByPrice(user);

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
    }

    private void showSweetsByWeight(User user) {
        List<AbstractSweet> sweets = giftService.sortSweetsByWeight(user);

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
    }

    private void showSweetsBySugarContentRange(User user) {
        System.out.println(MANAGER.getString("orderService.sugarContentRange"));
        String choice = IN.next();
        Byte start = Byte.parseByte(choice);
        choice = IN.next();
        Byte end = Byte.parseByte(choice);

        List<AbstractSweet> sweets = giftService.showSweetsBySugarContentRange(user, start, end);

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
    }

    private void addSweetToOrder(User user) {
        for (AbstractSweet sweet: sweetService.showAllSweets()) {
            System.out.println(sweet);
        }
        System.out.println(MANAGER.getString("orderService.sweetToAdd"));
        Long id = Long.parseLong(IN.next());
        giftService.addSweet(user, sweetService.showSweetById(id));
    }

    private void removeSweetFromOrder(User user) {
        List<AbstractSweet> sweets = giftService.showGiftByOwner(user).getSweets();

        for (AbstractSweet sweet: sweets) {
            System.out.println(sweet);
        }
        System.out.println(MANAGER.getString("orderService.sweetToRemove"));
        Long id = Long.parseLong(IN.next());
        giftService.removeSweet(user, sweetService.showSweetById(id));
    }

    private void deleteCurrentGift(User user) {
        giftService.deleteByOwner(user);
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
