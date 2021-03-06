package control;

import model.Application;
import view.PlayerGui;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Erwin
 */
public class Controller implements ActionListener{
    private Application app;
    private PlayerGui view;

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        try {
            if (source.equals(view.getBtnAdd())){
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter 
                        = new FileNameExtensionFilter("MP3 FILES", "mp3","mp3");
                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(view);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    String path = fc.getSelectedFile().getAbsolutePath();
                    app.addMusic(path);
                    view.setListMusic(app.getMusicList());
                } else if (source.equals(view.getBtnPlay())){
                    int selected = view.getSelectedMusic();
                    app.stop();
                    app.play(selected);
                    view.setTxFieldPlaying(app.getNowPlayed());
                } else if (source.equals(view.getBtnStop())){
                    app.stop();
                    view.setTxFieldPlaying("");
                } else if (source.equals(view.getBtnNext())){
                    app.next();
                    view.setTxFieldPlaying(app.getNowPlayed());
                } else if (source.equals(view.getBtnPrev())){
                    app.prev();
                    view.setTxFieldPlaying(app.getNowPlayed());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }
    
    public Controller(){
        app = new Application();
        view = new PlayerGui();
        view.setVisible(true);
        view.addListener(this);
        view.setListMusic(app.getMusicList());
    }
    
}
