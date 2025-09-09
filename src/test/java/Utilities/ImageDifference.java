package Utilities;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDifference {

    public static void imageDiff(BufferedImage expected,BufferedImage actual) throws IOException {

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(expected, actual);

        if (diff.hasDiff()) {
            System.out.println("Visual Regression Detected!");
            ImageIO.write(diff.getMarkedImage(), "PNG", new File("target/dif.png"));
        } else {
            System.out.println("No visual differences found.");
        }

    }


}


