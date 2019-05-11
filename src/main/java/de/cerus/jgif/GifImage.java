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

package de.cerus.jgif;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cerus
 */

public class GifImage {

    private GifDecoder decoder;
    private GifEncoder encoder;
    private File outputFile;
    private List<BufferedImage> frames;

    public GifImage() {
        decoder = new GifDecoder();
        encoder = new GifEncoder();
        frames = new ArrayList<>();
    }

    /**
     * Creates this object and loads a gif from a file.
     *
     * @param file
     * @throws FileNotFoundException
     */
    public GifImage(File file) throws FileNotFoundException {
        this();
        loadFrom(file);
    }

    /**
     * Loads a gif from a file.
     *
     * @param file
     * @return status
     * @throws FileNotFoundException
     */
    public int loadFrom(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException("File does not exist");
        return loadFrom(new FileInputStream(file));
    }

    /**
     * Loads a gif from an input stream.
     *
     * @param stream
     * @return status
     */
    public int loadFrom(InputStream stream) {
        frames.clear();
        encoder.setRepeat(0);
        int status = decoder.read(stream);
        for (int n = 0; n < decoder.getFrameCount(); n++) frames.add(decoder.getFrame(n));
        return status;
    }

    /**
     * Reverses the frames.
     */
    public void reverseFrames() {
        Collections.reverse(frames);
    }

    /**
     * Saves all frames to a file in a directory.
     *
     * @param outputDirectory
     */
    public void saveAllFrames(File outputDirectory) {
        saveAllFrames(outputDirectory, "png");
    }

    /**
     * Saves all frames to a file with the specified format in a directory.
     *
     * @param outputDirectory
     * @param format
     */
    public void saveAllFrames(File outputDirectory, String format) {
        for (BufferedImage frame : getFrames()) {
            try {
                ImageIO.write(frame, format, new File(outputDirectory, "frame" + getFrames().indexOf(frame) + "." + format));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return whether it was successful or not
     * @see GifImage#finish()
     */
    public boolean save() {
        return finish();
    }

    /**
     * Saves the gif to a file.
     *
     * @return whether it was successful or not
     */
    public boolean finish() {
        if (outputFile == null) throw new NullPointerException("Output file is null");
        encoder = GifEncoder.cloneFrom(encoder);
        encoder.start(outputFile.getAbsolutePath());
        for (BufferedImage frame : frames) {
            encoder.addFrame(frame);
        }
        return encoder.finish();
    }

    /**
     * Sets the frame of the specified index.
     *
     * @param index
     * @param frame
     */
    public void setFrame(int index, BufferedImage frame) {
        frames.set(index, frame);
    }

    /**
     * Sets whether the gif should repeat infinitely or not.
     *
     * @param repeat
     */
    public void repeatInfinitely(boolean repeat) {
        if (encoder.isStarted()) throw new UnsupportedOperationException("Encoder has already started");
        encoder.setRepeat(repeat ? 0 : 1);
    }

    /**
     * Adds a frame to the gif.
     *
     * @param frame
     */
    public void addFrame(BufferedImage frame) {
        frames.add(frame);
    }

    /**
     * Removes a frame from the gif.
     *
     * @param frame
     */
    public void removeFrame(BufferedImage frame) {
        frames.remove(frame);
    }

    /**
     * Removes a frame from the gif.
     *
     * @param frameIndex
     */
    public void removeFrame(int frameIndex) {
        frames.remove(frameIndex);
    }

    /**
     * @return all frames
     */
    public List<BufferedImage> getFrames() {
        return frames;
    }

    /**
     * Returns the first frame
     *
     * @return the first frame
     */
    public BufferedImage getFirstFrame() {
        return getFrame(0);
    }

    /**
     * Returns the frame of index n
     *
     * @param index
     * @return frame of index n
     */
    public BufferedImage getFrame(int index) {
        return getFrames().get(index);
    }

    /**
     * @return decoder
     */
    public GifDecoder getDecoder() {
        return decoder;
    }

    /**
     * @return encoder
     */
    public AnimatedGifEncoder getEncoder() {
        return encoder;
    }

    /**
     * @return output file
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * Sets the file to which the gif will be saved.
     *
     * @param outputFile
     */
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * @param iteration
     */
    public void setRepeat(int iteration) {
        encoder.setRepeat(iteration);
    }

    /**
     * @param color
     */
    public void setTransparent(Color color) {
        encoder.setTransparent(color);
    }

    /**
     * @param color
     * @param exactMatch
     */
    public void setTransparent(Color color, boolean exactMatch) {
        setTransparent(color, exactMatch);
    }

    /**
     * @param quality
     */
    public void setQuality(int quality) {
        encoder.setQuality(quality);
    }

    /**
     * @param ms
     */
    public void setDelay(int ms) {
        encoder.setDelay(ms);
    }

    /**
     * @param background
     */
    public void setBackground(Color background) {
        encoder.setBackground(background);
    }

    /**
     * @param fps
     */
    public void setFramerate(int fps) {
        encoder.setFrameRate(fps);
    }

    /**
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        encoder.setSize(width, height);
    }

    /**
     * @param dispose
     */
    public void setDispose(int dispose) {
        encoder.setDispose(dispose);
    }
}
