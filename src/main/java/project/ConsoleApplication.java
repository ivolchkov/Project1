package project;

import project.domain.gift.Gift;
import project.domain.sweet.LollyPop;
import project.domain.sweet.Pie;
import project.domain.sweet.choice.PieTaste;
import project.domain.sweet.choice.Shape;
import project.domain.user.User;

public class ConsoleApplication {
    public static void main(String[] args) {
        User user = new User.UserBuilder().withName("Ihor").
                withSurname("Volchkov").
                withEmail("igorik@ua.fm").
                withPassword("Vfkmdbyf1997").
                build();

        System.out.println(user);

        Pie pie = new Pie(PieTaste.BERRY);
        LollyPop lollyPop = new LollyPop(Shape.ROOSTER);
        Gift gift = new Gift(user);

        System.out.println(pie);
        System.out.println(lollyPop);
        System.out.println(gift);

        gift.addSweet(pie);
        gift.addSweet(lollyPop);

        System.out.println(gift);

    }
}
