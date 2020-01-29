package org.xenotek.redisviewer.ui;

import org.xenotek.redisviewer.ui.event.BrowserListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ServerBrowser extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JList keyList;
    private BrowserListener browserListener;
    private DefaultListModel<String> listModel;

    public ServerBrowser() {
        SearchHandler searchHandler = new SearchHandler();
        SelectionHandler selectionHandler = new SelectionHandler();

        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(searchHandler);

        keyList = new JList();
        keyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        keyList.setModel(listModel);
        keyList.getSelectionModel().addListSelectionListener(selectionHandler);

        JPanel searchArea = new JPanel();
        searchArea.setLayout(new BoxLayout(searchArea, BoxLayout.LINE_AXIS));
        searchArea.add(searchField);
        searchArea.add(searchButton);

        JScrollPane selectionList = new JScrollPane(keyList);

        add(searchArea, BorderLayout.PAGE_START);
        add(selectionList, BorderLayout.CENTER);
    }

    public void refreshList(Set<String> keys) {
        listModel.clear();
        for (String k : keys) {
            listModel.addElement(k);
        }
    }

    public void setBrowserListener(BrowserListener browserListener) {
        this.browserListener = browserListener;
    }

    private class SearchHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (browserListener != null)
                browserListener.searchPerformed(searchField.getText());
        }
    }

    private class SelectionHandler implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (listSelectionEvent.getValueIsAdjusting())
                return;
            ListSelectionModel lsm = (ListSelectionModel)listSelectionEvent.getSource();
            int index = lsm.getMinSelectionIndex();
            if (index > -1 && index < listModel.getSize()) {
                if (browserListener != null)
                    browserListener.selectionChanged(listModel.elementAt(index));
            }
        }
    }
}
