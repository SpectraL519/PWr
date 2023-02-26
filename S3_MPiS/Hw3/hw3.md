<script type="text/javascript"
  src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.0/MathJax.js?config=TeX-AMS_CHTML">
</script>
<script type="text/x-mathjax-config">
  MathJax.Hub.Config({
    tex2jax: {
      inlineMath: [['$','$'], ['\\(','\\)']],
      processEscapes: true},
      jax: ["input/TeX","input/MathML","input/AsciiMath","output/CommonHTML"],
      extensions: ["tex2jax.js","mml2jax.js","asciimath2jax.js","MathMenu.js","MathZoom.js","AssistiveMML.js", "[Contrib]/a11y/accessibility-menu.js"],
      TeX: {
      extensions: ["AMSmath.js","AMSsymbols.js","noErrors.js","noUndefined.js"],
      equationNumbers: {
      autoNumber: "AMS"
      }
    }
  });
</script>

# MPiS - Homework 3:

<br />

### Zadanie 1: Testowanie losowych ciągów bitów

* Generator `rand()` języka C++ z biblioteki `<random>`

    > Test został przeprowadzony dla ciągi bitów o długości $2 * 10^6$
    >
    > Kod w pliku `hw3_src.cpp`

    Generator nie przeszedł wielu testów, w tym np.:

    * **Test for the longest run of ones in a block** - sprawdzenie najdłuższego ciąg bitów `1`

    * **Binary matrix rank test** - sprawdzenie zależności liniowej między podciągami o stałej długości oryginalnej sekwencji bitów

    To pokazuje, że taki generator ma słabą jakość

![](images/ex1/cpp.png)

<br />
<br />

* Generator `numpy.random` języka Python (`Marsynne Twister MT19937`)

    > Test został przeprowadzony dla ciągi bitów o długości $2 * 10^6$
    >
    > Kod w pliku `hw3_src.ipynb`

    Generator przeszedł wszystkie testy, zatem można stwierdzić, że ma on dobre własności statystyczne

![](images/ex1/python.png)

<br />
<br />

* Hash nazwiska (`SHA1()`)

    > Test został przeprowadzony dla ciągu bitów otrzymanego przez konwersję hasha mojego nazwiska za pomocą `SHA1()`: `44d6c93858d282226d5603e4dfbde042ad261f91`

    Dla wielu testów wynikiem jest `Error`, co sugeruje, że taki ciąg bitów jest zbyt krótki, by móc przeprowadzić taki test.

    Możemy zauważyć, że funkcja `SHA1()` generuje bity z lepszymi właściwościami niż generator liniowy języka C++ i gorszymi niż generator `Marsynne Twister` języka Python

![](images/ex1/last_name_sha1.png)

<br/>
<br/>
<br/>

<a id="zad2"></a>
### Zadanie 2: Bładzenie losowe na liczbach całkowitych

> Kod źródłowy w pliku `hw3_src.ipynb`

> Dystrybuantę zmiennej losowej $S_N$ wyznaczono generując $k = 10000$ sum $N$ losowo generowanych wartości ze zbioru $\{-1, 1\}$ dla $N \epsilon \{5, 10, 15, 20, 25, 30, 100\}$. >
> 
> Wyniki zostały porównane z dystrybuantą ciągłej zmiennej losowej o rozkładzie normalnym

<br />

**Uzyskane wyniki:**

Można zauważyć, że wraz z wzostem wartości $N$ dystrybuanta zmiennej losowej $S_N$ jest coraz bliższa rozkładowi normalnemu.

Zatem ciągła zmienna losowa o rozkładzie normalnym jest aproksymacją dyskretnej zmiennej losowej $S_N$

![](images/ex2/s5_cdf.png)

![](images/ex2/s10_cdf.png)

![](images/ex2/s15_cdf.png)

![](images/ex2/s20_cdf.png)

![](images/ex2/s25_cdf.png)

![](images/ex2/s30_cdf.png)

![](images/ex2/s100_cdf.png)

<br />
<br />
<br />

### Zadanie 3: Bładzenie losowe na liczbach całkowitych - rozkład "czasu nad osią $OX$"

> Kod źródłowy w pliku `hw3_src.ipynb`

> Funkcję masy prawdopodobieństwa zmiennej losowej $P_N$ wyznaczono generując $k = 5000$ "frakcji czasu nad osią $OX$" w bładzeniu losowym (zmienna losowa $S_N$: [zadanie2](#zad2)) dla $N \epsilon \{100, 1000, 10000\}$. 
>
> Wyniki zostały prównane z funkcją gęstośi ciągłej zmiennej losowej o rozkładzie arcusa sinusa.

<br />

**Uzyskane wyniki:**

Można zauważyć, że wraz z wzostem wartości $N$ funkcja masy prawdopodobieństwa zmiennej losowej $P_N$ jest coraz bliższa funkcji gęstości zmiennej losowej o rozkładzie arkusa sinusa.

Zatem ciągła zmienna losowa o rozkładzie arcusa sinusa jest aproksymacją dyskretnej zmiennej losowej $P_N$

![](images/ex3/p100_pdf.png)

![](images/ex3/p1000_pdf.png)

![](images/ex3/p10000_pdf.png)

