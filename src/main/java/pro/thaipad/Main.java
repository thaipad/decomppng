package pro.thaipad;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            System.out.println("Parameter expected");
            return;
        }
        try {
            Path directory = Paths.get(args[0]);
            Files.walk(directory)
                    .filter(path -> !path.toFile().isDirectory() && path.toString().endsWith(".png"))
                    .map(Path::toFile)
                    .forEach(file -> {
                        CardDeal cardDeal = new CardDeal(file);
                        System.out.print(file.getName() + " - ");
                        if (cardDeal.getDeal() != null) {
                            cardDeal.getDeal().forEach(System.out::print);
                        }
                        System.out.println();
                    });
        } catch (NoSuchFileException e) {
            System.out.println("No such directory found");
        }
    }

}
