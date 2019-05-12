# JGif
JGif is a little gif editing library based on [animated-gif-lib-for-java](https://github.com/rtyley/animated-gif-lib-for-java) by [rtyley](https://github.com/rtyley).

## Usage
1. Implement this library in your project
2. Create a GifImage object
3. (Optional) Load a gif with GifImage#loadFrom(File)
4. Edit your gif, add frames etc
5. Save your gif to a file with GifImage#setOutputFile(File) and GifImage#save()

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
	
<dependencies>
    <dependency>
        <groupId>com.github.RealCerus</groupId>
        <artifactId>JGif</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Example
```java
public class GifTest {
    public static void main(String[] args) {
        GifImage image = new GifImage();
        image.setOutputFile(new File("./output/output.gif"));
        try {
            image.loadFrom(new File("./my_gif.gif"));
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
```

Output:\
![Output](https://i.imgur.com/cFPzGNK.gif)
