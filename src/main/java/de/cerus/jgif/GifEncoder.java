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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

public class GifEncoder extends AnimatedGifEncoder {

    /**
     * Clones the given GifEncoder object
     *
     * @param encoder
     * @return a new, cloned gif encoder
     */
    public static GifEncoder cloneFrom(GifEncoder encoder) {
        GifEncoder gifEncoder = new GifEncoder();
        gifEncoder.setDelay(encoder.getDelay());
        gifEncoder.setRepeat(encoder.getRepeat());
        gifEncoder.setBackground(encoder.getBackground());
        gifEncoder.setDispose(encoder.getDispose());
        gifEncoder.setQuality(encoder.getSample());
        if (encoder.getWidth() >= 1 && encoder.getHeight() >= 1)
            gifEncoder.setSize(encoder.getWidth(), encoder.getHeight());
        gifEncoder.setTransparent(encoder.getTransparent(), encoder.isTransparentExactMatch());
        return gifEncoder;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return transparent
     */
    public Color getTransparent() {
        return transparent;
    }

    /**
     * @return transparentExactMatch
     */
    public boolean isTransparentExactMatch() {
        return transparentExactMatch;
    }

    /**
     * @return background
     */
    public Color getBackgroundColor() {
        return background;
    }

    /**
     * @return transIndex
     */
    public int getTransIndex() {
        return transIndex;
    }

    /**
     * @return repeat
     */
    public int getRepeat() {
        return repeat;
    }

    /**
     * @return delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @return out
     */
    public OutputStream getOut() {
        return out;
    }

    /**
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @return pixels
     */
    public byte[] getPixels() {
        return pixels;
    }

    /**
     * @return indexedPixels
     */
    public byte[] getIndexedPixels() {
        return indexedPixels;
    }

    /**
     * @return colorDepth
     */
    public int getColorDepth() {
        return colorDepth;
    }

    /**
     * @return colorTab
     */
    public byte[] getColorTab() {
        return colorTab;
    }

    /**
     * @return usedEntry
     */
    public boolean[] getUsedEntry() {
        return usedEntry;
    }

    /**
     * @return palSize
     */
    public int getPalSize() {
        return palSize;
    }

    /**
     * @return dispose
     */
    public int getDispose() {
        return dispose;
    }

    /**
     * @return closeStream
     */
    public boolean isCloseStream() {
        return closeStream;
    }

    /**
     * @return firstFrame
     */
    public boolean isFirstFrame() {
        return firstFrame;
    }

    /**
     * @return sizeSet
     */
    private boolean isSizeSet() {
        return sizeSet;
    }

    /**
     * @return sample
     */
    private int getSample() {
        return sample;
    }
}
