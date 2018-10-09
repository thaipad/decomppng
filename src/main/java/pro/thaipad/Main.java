package pro.thaipad;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            System.out.println("Parameter expected");
            return;
        }



        try {
            Path directory = Paths.get(args[0]);

/***************
 *          It's implementation using executor with fixed numbers of threads
 ***************
            int numProc = Runtime.getRuntime().availableProcessors();
            final ExecutorService executor = Executors.newFixedThreadPool(numProc > 1 ? numProc - 1 : 1);
            Files.walk(directory)
                    .filter(path -> !path.toFile().isDirectory() && path.toString().endsWith(".png"))
                    .forEach(path -> executor.submit(() -> {
                            String str = path.getFileName() + " - ";
                            for (Card card : CardDeal.getDeal(path.toFile())) {
                                str += card;
                            }
                            System.out.println(str);
                        }));

            executor.shutdown();
 */

//            It's implementation using internal stream method 'parallel'
//            I think on small tasks it's more simple and useful

            Files.walk(directory)
                    .filter(path -> !path.toFile().isDirectory() && path.toString().endsWith(".png"))
                    .parallel()
                    .forEach(path -> {
                        String str = path.getFileName() + " - ";
                        for (Card card : CardDeal.getDeal(path.toFile())) {
                            str += card;
                        }
                        System.out.println(str);
                    });

        } catch (NoSuchFileException e) {
            System.out.println("No such directory found");
        }
    }

}
