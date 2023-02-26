class Samochod{
    public String Nazwa;
    public String NrRejestracyjny;
       
    public void ObliczSpalanie(){
        System.out.println("Samochod");
        Nazwa="bla";
    };

}
class SamochodOsobowy extends Samochod
{
    public int Ilosc;
    
    public void ObliczSpalanie(){
        System.out.println("Samochod Osobowy");
    };

}

public class Pr2 {
    public static void main(String[] Arg){


    int a =5;

    Samochod s2 = new Samochod();
    s2.ObliczSpalanie();
    s2.Nazwa="bla";
    s2.NrRejestracyjny="gg";

           
    SamochodOsobowy s = new SamochodOsobowy();
    s.ObliczSpalanie();
    s.Ilosc=5;
    s.NrRejestracyjny="hh";
    
                              
    Samochod s1 = new SamochodOsobowy();
    s1.ObliczSpalanie();
    
        
    }
}