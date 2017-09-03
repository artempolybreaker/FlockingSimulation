/*Erstellt von:
* Anja Handrianz (s72812)
* Artem Dyadichkin (s72788)*/

package frame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class BasisFenster {
    private int width, height;
    private String title;
    public static String fragShaderSource = "varying vec4 color; uniform vec4 variable; void main() { gl_FragColor = color; }";
    public static String vertexShaderSource = "varying vec4 color;float ambient = 0.85; void main() { gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; color = vec4(1,gl_Position.y*ambient,0,1);}";

    public BasisFenster() {
        this("BasisFenster", 800, 600);
    }

    public BasisFenster(String title, int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
        this.title  = title;
    }

    public void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(this.getWidth(), this.getHeight()));
            Display.setTitle(this.title);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    public abstract void renderLoop();

    public void start() {
        initDisplay();
        extractVertexAndFragmentShader();
        renderLoop();
        Display.destroy();
        System.exit(0);
    }

    public void extractVertexAndFragmentShader() {
        int myProgram = GL20.glCreateProgram();
        int fragShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragShader, fragShaderSource);
        GL20.glCompileShader(fragShader);
        GL20.glAttachShader(myProgram, fragShader);
        int vertShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertShader, vertexShaderSource);
        GL20.glCompileShader(vertShader);
        System.out.println(GL20.glGetShaderInfoLog(vertShader, 1024));
        GL20.glAttachShader(myProgram, vertShader);
        GL20.glLinkProgram(myProgram);
        GL20.glUseProgram(myProgram);
        GL11.glEnable(2929);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

