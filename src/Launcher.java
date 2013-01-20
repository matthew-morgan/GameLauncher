import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.net.URL;
import java.net.URLClassLoader;
import java.lang.Class;
import java.lang.reflect.*;

public class Launcher extends JFrame {

    protected JMenuBar menuBar;
    protected JMenu gameMenu;
    protected JMenuItem addGameButton;
    protected JMenuItem addNewUserButton;
    final protected JFileChooser fc;
    protected LauncherState state = null;
    protected File file;
    protected JOptionPane inputDialogBox;
    protected String className = null;
    private FileOpener fo;
    protected String stateFileName = "launcher.State";


    protected void SerializeState(){
        try
        {
            FileOutputStream fileOut = new FileOutputStream(stateFileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(state);
            out.close();
            fileOut.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    protected LauncherState DeserializeState(){
        try
        {
            FileInputStream fileIn = new FileInputStream(stateFileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            state = (LauncherState) in.readObject();
            in.close();
            fileIn.close();
            for(URLAndClassName game : state.getStoredGames()){
                addGameToMenu(game.getURL(), game.getClassName());
            }
            return state;
        }
        catch(java.io.FileNotFoundException e){
            //This will always be thrown on the first run, when no state file has been created.
            //Ce sera toujours lancée au premier lancement, en l'absence de fichier d'état.
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            //System.out.println("" + e);  //Todo: get rid of these print calls
        }
        return new LauncherState();
    }
    protected void addGameToMenu(URL url, String className){
        JMenuItem jmi = fo.open(file, className);
        if(jmi != null){
            gameMenu.add(jmi);
            jmi.addActionListener(new addedGameMenuListener(file, className));
            if(state != null) {
                state.addGame(url, className);
            }
        }
    }

    public Launcher(){
        super ("Game Launcher");
        fo = new FileOpener();
        menuBar = new JMenuBar();
        gameMenu = new JMenu("Start");
        addGameButton = new JMenuItem("Add game");
        addNewUserButton = new JMenuItem("Add new user");
        gameMenu.add(addGameButton);
        gameMenu.add(addNewUserButton); //Todo: make separate menus here
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
        inputDialogBox = new JOptionPane();

        state = DeserializeState();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Java archive", "jar");
        fc = new JFileChooser();
        fc.setFileFilter(filter);

        addGameButton.addActionListener(new newGameMenuListener());
        addNewUserButton.addActionListener(new newUserMenuListener());

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                SerializeState();
                ((JFrame)(e.getComponent())).dispose();
            }
        });


        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        setLocation (250, 250);
        pack ();
        setVisible (true);
    }

    public class FileOpener{

        public JMenuItem open(File file, String className){
            try {
                URL u = file.toURI().toURL();
                URLClassLoader loader = new URLClassLoader(new URL[]{u});
                Class clmj = Class.forName(className, true, loader);
                Constructor constructor = clmj.getConstructor();
                constructor.newInstance();
                JMenuItem newGameItem = new JMenuItem(clmj.getName());
                return newGameItem;
            }
            catch(java.lang.InstantiationException exception){
                System.out.println(exception.getMessage());
            }
            catch(java.lang.IllegalAccessException exception){
                System.out.println(exception.getMessage());
            }
            catch(java.lang.reflect.InvocationTargetException exception){
                System.out.println(exception.getMessage());
            }
            catch(java.lang.NoSuchMethodException exception){
                System.out.println("No such method: " + exception.getMessage());
            }
            catch (java.lang.ClassNotFoundException exception){
                System.out.println("No such class");
            }
            catch (java.net.MalformedURLException exception){
                System.out.println("Malformed URL");
            }
            catch (java.io.IOException exception){
                System.out.println("I/O Error");
            }

            return null;
        }
    }

    private class newGameMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e){

            if (fc.showOpenDialog(Launcher.this) == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                className = JOptionPane.showInputDialog(null, "Enter the name of the class you require:");
                try {addGameToMenu(file.toURI().toURL(), className); }
                catch (java.net.MalformedURLException exception){
                    System.out.println("Malformed URL");
                }
            }
        }
    }

    private class addedGameMenuListener implements ActionListener {
        protected File file;
        protected String className;
        addedGameMenuListener(File file, String className){
            this.file = file;
            this.className = className;
        }
        public void actionPerformed(ActionEvent e){
        fo.open(file, className);
        }
    }

    private class newUserMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String username = JOptionPane.showInputDialog(null, "Enter your desired username:");
            String password = JOptionPane.showInputDialog(null, "Enter your desired password:");
            System.out.println("User: " + username + " password: " + password);
        }
    }
    public static void main(String[] args){
        new Launcher();
    }
}
