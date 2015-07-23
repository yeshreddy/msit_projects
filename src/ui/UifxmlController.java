/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import searchengine.search.SearchDriver;
import searchengine.search.Socketclient;


/**
 * FXML Controller class
 *
 * @author yeswanth
 */
public class UifxmlController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    
    @FXML TextField searchfield;
    @FXML Button searchbutton;
    @FXML TextArea searchresult;
    @FXML Label mysearchengine;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        
    } 
    public void search()
    {   
        searchresult.clear();
        SearchDriver sd=new SearchDriver();
        String some=sd.Play(searchfield.getText());
        if(some.equals("")) some="sorry!No matches found!!!";
        //for(String i:h)
        searchresult.setText(some);
        //Socketclient sc=new Socketclient();
        //sc.clientplay(searchfield.getText());
    }
    
    
   
    
    
}
