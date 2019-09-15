package resources.files;

import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import net.bytebuddy.asm.Advice.This;
public class JFilePicker extends JPanel {
	private static final long serialVersionUID = 1L;
private String textFieldLabel;
private String buttonLabel;
private JTextField textField;
private JButton button;

private JFileChooser fileChooser;

private int mode;
public static final int MODE_OPEN = 1;
public static final int MODE_SAVE = 2;

public JFilePicker(String textFieldLabel, String buttonLabel) {
this.textFieldLabel = textFieldLabel;
this.buttonLabel = buttonLabel;

fileChooser = new JFileChooser();

textField = new JTextField(30);
textField.setBounds(10, 1, 78, 19);
button = new JButton(buttonLabel);
button.setBounds(98, 0, 69, 21);

button.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent evt) {
buttonActionPerformed(evt);
}
});
setLayout(null);
add(textField);
add(button);

}

private void buttonActionPerformed(ActionEvent evt) {
if (mode == MODE_OPEN) {
if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}
} else if (mode == MODE_SAVE) {
if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
}
}
}

public void addFileTypeFilter(String extension, String description) {
FileTypeFilter filter = new FileTypeFilter(extension, description);
fileChooser.addChoosableFileFilter(filter);
}

public void setMode(int mode) {
this.mode = mode;
}

public String getSelectedFilePath() {
return textField.getText();
}
public void saveFile(File source, File dest) throws IOException {
    FileUtils.copyFileToDirectory(source, dest);

}
public JFileChooser getFileChooser() {
return this.fileChooser;
}
}