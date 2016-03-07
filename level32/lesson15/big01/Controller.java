package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by IGOR on 15.01.2016.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view)
    {
        this.view = view;
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    public void init() {
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document = doc;
        doc.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader reader = new StringReader(text);
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.read(reader, document, 0);
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter writer = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try
        {
            htmlEditorKit.write(writer, document, 0, document.getLength());
        }
        catch (IOException | BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public void createNewDocument()
    {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new HTMLFileFilter());
        int n = jfc.showOpenDialog(view);
        if (n == JFileChooser.APPROVE_OPTION) {
            currentFile = jfc.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader fr = new FileReader(currentFile)) {
                new HTMLEditorKit().read(fr, document, 0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }
    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null)
            saveDocumentAs();
        else
            try (FileWriter fw = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fw, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
    }

    public void saveDocumentAs()
    {
        view.selectHtmlTab();
        JFileChooser jfileChooser = new JFileChooser();
        jfileChooser.setFileFilter(new HTMLFileFilter());
        int n = jfileChooser.showSaveDialog(view);
        if (n == JFileChooser.APPROVE_OPTION) {
            currentFile = jfileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter writer = new FileWriter(currentFile))
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            }
            catch (IOException | BadLocationException e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
