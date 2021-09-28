import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.jna.Native;
import com.sun.jna.Library;
import com.sun.jna.win32.W32APIOptions;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.*;

public class Main implements ActionListener {
    File img0 = new File("horse1.jpg");
    File img2 = new File("horse2.jpg");
    File img1 = new File("horse3.jpg");
    File img3 = new File("horse4.jpg");
    File music = new File("horsesound.wav");
    Clip clip = null;
    AudioInputStream audio = null;
    public static void main(String[] args)
    {
       Main main = new Main();
    }
    JFrame frame = new JFrame("Horse wallpaper");
    JButton button = new JButton();
    Main()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(button);
        ImageIcon img = new ImageIcon("horseicon.jpg");
        frame.setIconImage(img.getImage());

        ImageIcon image = new ImageIcon("horsebutton.jpg");
        Image change = image.getImage();
        Image finalimage = change.getScaledInstance(100,100, Image.SCALE_DEFAULT);
        image = new ImageIcon(finalimage);

        button.setBounds(90, 50, 100, 100);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setIcon(image);
        try
                {
                    audio = AudioSystem.getAudioInputStream(music);
                    clip = AudioSystem.getClip();
                    clip.open(audio);
                }
        catch(Throwable a)
                {
                }
    }

    public static interface User32 extends Library
    {
        User32 INSTANCE = (User32)  Native.loadLibrary("user32",User32.class,W32APIOptions.DEFAULT_OPTIONS);
        boolean SystemParametersInfo (int one, int two, String s, int three);
    }
int flag = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Random random = new Random();
            int x = random.nextInt(4);
            switch (x)
            {
                case 0: User32.INSTANCE.SystemParametersInfo(0x0014, 0, img0.getAbsolutePath(), 1);
                break;
                case 1: User32.INSTANCE.SystemParametersInfo(0x0014, 0, img1.getAbsolutePath(), 1);
                break;
                case 2: User32.INSTANCE.SystemParametersInfo(0x0014, 0, img2.getAbsolutePath(), 1);
                break;
                case 3: User32.INSTANCE.SystemParametersInfo(0x0014, 0, img3.getAbsolutePath(), 1);
                break;
            }
            if(flag==0)
            {
                clip.start();
                flag++;
            }
            else
            {
                clip.setMicrosecondPosition(0);
            }

        }catch(Throwable a)
        {
            System.out.println(a.getMessage());
            JOptionPane.showMessageDialog(frame, "Something wenrong");
        }

    }
}

