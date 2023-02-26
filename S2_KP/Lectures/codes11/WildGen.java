import java.util.List;
import java.util.ArrayList;

class Z {}
class A extends Z { }
class B extends A { }
class C extends A { }
class E extends B { }
class D { }

class WildGen
{

  static void processElements(List<?> elements){
    for(Object o : elements){
      System.out.println(o);
        }
    }
    
    static void processElementsA(List<? extends A> elements){
    for(Object o : elements){
      System.out.println(o);
        }
    }
    
    // Co jesli zmienie A na B?
    public static void insertElements(List<? super A> elements){     
    elements.add(new A());
    elements.add(new B());
    elements.add(new E());
    }

    public static void main(String args[])
    {
        List<A> listA = new ArrayList<A>();
        List<B> listB = new ArrayList<B>();    
        List<D> listD = new ArrayList<D>();  
        List<C> listC = new ArrayList<C>();  
        List<E> listE = new ArrayList<E>(); 
        List<Z> listZ = new ArrayList<Z>();
        List<Object> listO = new ArrayList<Object>();  
        
        A a1 = new A();
        A a2 = new A();
        
        listA.add(a1);
        listA.add(a2);

        // Jakakolwiek lista moze byc argumentem
        processElements(listA);
        processElements(listB);
        processElements(listC);
        processElements(listD);
        processElements(listE);
        processElements(listZ);
        processElements(listO);
        
        // Tylko lista ktorej elementy dziedzicza po A
        processElementsA(listA);
        processElementsA(listB);
        processElementsA(listC);
        processElementsA(listE);
       // processElementsA(listZ);
        
       // Tylko lista z elementami po ktorych dziedziczy A
        insertElements(listZ);
        insertElements(listA);
        insertElements(listO);
        // Tak nie mozna
        //insertElements(listB);      
    
    }
    
}
