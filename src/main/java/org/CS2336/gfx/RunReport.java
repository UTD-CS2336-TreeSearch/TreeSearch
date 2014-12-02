package org.CS2336.gfx;

import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.gui.layout.LayoutParameter;
import org.CS2336.TreeSearch;
import org.CS2336.btree.Util;

import java.io.IOException;
import java.util.ArrayList;


class RunReport implements Action {
    private final GUIScreen textGUI;

    public RunReport(GUIScreen textGUI) {
        this.textGUI = textGUI;
    }


    @Override
    public void doAction() {
        try {
            String preOrder = Util.reportPreOrder(TreeSearch.myTree);
            String postOrder = Util.reportPostOrder(TreeSearch.myTree);
            String inOrder = Util.reportInOrder(TreeSearch.myTree);
            textGUI.showWindow(new ReportWindow(inOrder, postOrder, preOrder));
        } catch(IOException e) {
            MessageBox.showMessageBox(textGUI, "ERROR", "A serious error has occurred" + e.toString());
        }
    }
}

class ReportWindow extends Window {
    public ReportWindow(String in, String post, String pre) {
        super("Report Results");
        Panel preOrderPanel = new Panel("Pre-Order", new Border.Bevel(true), Panel.Orientation.HORISONTAL);
        Panel postOrderPanel = new Panel("Post-Order", new Border.Bevel(true), Panel.Orientation.HORISONTAL);
        Panel inOrderPanel = new Panel("In-Order", new Border.Bevel(true), Panel.Orientation.HORISONTAL);

        preOrderPanel.addComponent(new Label(pre));
        postOrderPanel.addComponent(new Label(post));
        inOrderPanel.addComponent(new Label(in));

        addComponent(inOrderPanel);
        addComponent(preOrderPanel);
        addComponent(postOrderPanel);
        addComponent(new Button("Dismiss", new CloseWindow(this)));
    }
}