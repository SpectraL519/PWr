#include<iostream>
using namespace std;

class Samochod
{
	public:
	int pojemnosc;
	virtual void ObliczSpalanie(){
		    cout << "Samochod" << endl; 
	}
};

class SamochodCiezarowy: public Samochod{

	void ObliczSpalanie(){
		    cout << "Samochod ciezarowy" << endl; 
	}
};

class Pojazd
{
	public:
	int pojemnosc;
};

class Amfibia: public Pojazd,Samochod
{
	public:
	int pojemnosc;
};

int main(int argc, char* argv[]) {
	
	Samochod *s = new Samochod();
	s->ObliczSpalanie();

	Samochod *s1 = new SamochodCiezarowy();
	s1->ObliczSpalanie();

	Samochod s2;
	s2.ObliczSpalanie();

	Amfibia *a = new Amfibia(); 
	a->pojemnosc=1500;


    cout << "Pojemnosc" << endl; 
	cout << a->pojemnosc << endl; 

	delete s;
	delete a;
	delete s1;
    return 0;

}