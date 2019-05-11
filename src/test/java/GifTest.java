/*
 *  Copyright (c) 2018 Cerus
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Cerus
 *
 */

import de.cerus.jgif.GifImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

public class GifTest {

    public static void main(String[] args) {
        GifImage image = new GifImage();
        image.setOutputFile(new File("./output/output.gif"));
        try {
            image.loadFrom(new File("./idiot_sandwich.gif"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for(int i = 0; i < image.getFrames().size(); i++){
            BufferedImage frame = image.getFrame(i);
            Graphics graphics = frame.getGraphics();
            graphics.setColor(Color.CYAN);
            graphics.drawString("FRAME "+i, 50, 50);
            graphics.dispose();
            image.setFrame(i, frame);
        }

        image.reverseFrames();

        image.save();
    }

}
