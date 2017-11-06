package Practica3;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Expresion 
{
  
    public boolean validarExp(String tkn, String exp){
        
        Pattern p = Pattern.compile( exp + "[a-z]*");
        Matcher m = p.matcher(tkn);
        
        return m.matches();
    }   
}